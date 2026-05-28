package com.pruebaTecnica.crudPrueba.Controller;

import com.pruebaTecnica.crudPrueba.DTOS.ArchivoResponseDTO;
import com.pruebaTecnica.crudPrueba.DTOS.ProductoRequestDTO;
import com.pruebaTecnica.crudPrueba.DTOS.ProductoResponseDTO;
import com.pruebaTecnica.crudPrueba.Service.JwtService;
import com.pruebaTecnica.crudPrueba.Service.ProductosServices;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductosServices productoService;
    private final JwtService jwtService;

    public ProductoController(ProductosServices productoService, JwtService jwtService) {
        this.productoService = productoService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> guardar(@Valid
            @RequestBody ProductoRequestDTO dto, @RequestHeader("Authorization")
        String authHeader) {
            String token =
            authHeader.substring(7);

    Integer idUsuarioAU =
            jwtService.extraerIdUsuario(
                    token
            );
        
        ProductoResponseDTO productoGuardado = productoService.guardar(dto, idUsuarioAU);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoGuardado);
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listar() {

        List<ProductoResponseDTO> productos = productoService.listar();

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(
            @PathVariable Integer idProducto) {

        ProductoResponseDTO producto = productoService.obtenerPorId(idProducto);

        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<ProductoResponseDTO> actualizar(@Valid
            @PathVariable Integer idProducto,
            @RequestBody ProductoRequestDTO dto) {

        ProductoResponseDTO productoActualizado =
                productoService.actualizar(idProducto, dto);

        return ResponseEntity.ok(productoActualizado);
    }

   @DeleteMapping("/{idProducto}")
public ResponseEntity<Void> eliminar(@PathVariable Integer idProducto) {

    productoService.eliminar(idProducto);

    return ResponseEntity.noContent().build();
}

    @GetMapping("/filtrar")
    public ResponseEntity<List<ProductoResponseDTO>> filtrar(
            @RequestParam(required = false) String nombreProducto,
            @RequestParam(required = false) String claveProducto,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax) {

        List<ProductoResponseDTO> productos = productoService.filtrar(
                nombreProducto,
                claveProducto,
                precioMin,
                precioMax
        );

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/reporte-excel")
    public ResponseEntity<ArchivoResponseDTO> generarReporteExcel(
            @RequestParam(required = false) String nombreProducto,
            @RequestParam(required = false) String claveProducto,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax) {

        ArchivoResponseDTO archivo = productoService.generarExcelBase64(
                nombreProducto,
                claveProducto,
                precioMin,
                precioMax
        );

        return ResponseEntity.ok(archivo);
    }

}
