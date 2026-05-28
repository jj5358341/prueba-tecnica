
package com.pruebaTecnica.crudPrueba.Service;

import com.pruebaTecnica.crudPrueba.DAO.ProductoRepository;
import com.pruebaTecnica.crudPrueba.DAO.UsuarioRepository;
import com.pruebaTecnica.crudPrueba.DTOS.ArchivoResponseDTO;
import com.pruebaTecnica.crudPrueba.DTOS.ProductoRequestDTO;
import com.pruebaTecnica.crudPrueba.DTOS.ProductoResponseDTO;
import com.pruebaTecnica.crudPrueba.JPA.Usuario;
import com.pruebaTecnica.crudPrueba.Util.IdentificadorUtil;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.pruebaTecnica.crudPrueba.JPA.Productos;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

@Service
public class ProductosServices {
    
   private final ProductoRepository productoRepository;
   private final UsuarioRepository usuarioRepository;

    public ProductosServices(
            ProductoRepository productoRepository, UsuarioRepository usuarioRepository) {

        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        
    }
    @Transactional
    public ProductoResponseDTO guardar(ProductoRequestDTO dto, int idUsuario) {
        Productos guardado = new Productos();
        try{
         Usuario usuario = new Usuario();
        
        Productos producto = new Productos();

        producto.setIdentificadorNegocio(
                IdentificadorUtil.generarIdentificador("PROD"));

        producto.setClaveProducto(dto.getClaveProducto());
        producto.setNombreProducto(dto.getNombreProducto());
        producto.setPrecio(dto.getPrecio());
        producto.setUsuario (usuario);

         guardado = productoRepository.save(producto);   
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        return convertirDTO(guardado);
    }

    public List<ProductoResponseDTO> listar() {

        return productoRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .collect(Collectors.toList());
    }
    public ProductoResponseDTO obtenerPorId(Integer idProducto) {

    Productos producto = productoRepository.findById(idProducto)
            .orElseThrow(() ->
                    new RuntimeException("Producto no encontrado"));

    return convertirDTO(producto);
}
    @Transactional
public ProductoResponseDTO actualizar(Integer idProducto, ProductoRequestDTO dto) {

     Productos producto = productoRepository.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    producto.setClaveProducto(dto.getClaveProducto());
    producto.setNombreProducto(dto.getNombreProducto());
    producto.setPrecio(dto.getPrecio());
    producto.setActivo(dto.getActivo());

    Productos productoActualizado = productoRepository.save(producto);

    return convertirDTO(productoActualizado);
}

    private ProductoResponseDTO convertirDTO(Productos producto) {

        ProductoResponseDTO dto = new ProductoResponseDTO();

        dto.setIdProducto(producto.getIdProducto());
        dto.setIdentificadorNegocio(producto.getIdentificadorNegocio());
        dto.setClaveProducto(producto.getClaveProducto());
        dto.setNombreProducto(producto.getNombreProducto());
        dto.setPrecio(producto.getPrecio());
        dto.setActivo(producto.getActivo());
        dto.setFechaRegistro(producto.getFechaRegistro());

        return dto;
    }
    
@Transactional 
public void eliminar(Integer idProducto) {

    Productos producto = productoRepository.findById(idProducto)
            .orElseThrow(() ->
                    new RuntimeException("Producto no encontrado"));

    productoRepository.delete(producto);
}
    public Usuario validar(Usuario usuario) {

        Usuario usuarioLog = usuarioRepository.validar(usuario.getCorreo(), usuario.getContrasena());

        if (usuario == null) {
            throw new RuntimeException("Correo o contraseña incorrectos");
        }

        return usuarioLog;
    }
    
public List<ProductoResponseDTO> filtrar(
        String nombreProducto,
        String claveProducto,
        BigDecimal precioMin,
        BigDecimal precioMax) {

    List<Productos> productos = productoRepository.filtrarProductos(
            nombreProducto,
            claveProducto,
            precioMin,
            precioMax
    );

    return productos.stream()
            .map(this::convertirDTO)
            .collect(Collectors.toList());
}
    public ArchivoResponseDTO generarExcelBase64(
        String nombreProducto,
        String claveProducto,
        BigDecimal precioMin,
        BigDecimal precioMax) {

    try {
        List<Productos> productos = productoRepository.filtrarProductos(
                nombreProducto,
                claveProducto,
                precioMin,
                precioMax
        );

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Productos");

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("ID Producto");
        header.createCell(1).setCellValue("Identificador Negocio");
        header.createCell(2).setCellValue("Clave Producto");
        header.createCell(3).setCellValue("Nombre Producto");
        header.createCell(4).setCellValue("Precio");
        header.createCell(5).setCellValue("Activo");
        header.createCell(6).setCellValue("Fecha Registro");

        int rowIndex = 1;

        for (Productos producto : productos) {
            Row row = sheet.createRow(rowIndex++);

            row.createCell(0).setCellValue(producto.getIdProducto());
            row.createCell(1).setCellValue(producto.getIdentificadorNegocio());
            row.createCell(2).setCellValue(producto.getClaveProducto());
            row.createCell(3).setCellValue(producto.getNombreProducto());
            row.createCell(4).setCellValue(producto.getPrecio().doubleValue());
            row.createCell(5).setCellValue(producto.getActivo());

            row.createCell(6).setCellValue(
                    producto.getFechaRegistro() != null
                            ? producto.getFechaRegistro().toString()
                            : ""
            );
        }

        for (int i = 0; i <= 6; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        workbook.write(outputStream);
        workbook.close();

        byte[] bytesExcel = outputStream.toByteArray();

        String base64 = Base64.getEncoder().encodeToString(bytesExcel);

        return new ArchivoResponseDTO(
                200,
                "Reporte generado correctamente",
                "productos.xlsx",
                base64
        );

    } catch (Exception ex) {
        return new ArchivoResponseDTO(
                500,
                "Error al generar el reporte: " + ex.getMessage(),
                null,
                null
        );
    }
}
    
    
}
