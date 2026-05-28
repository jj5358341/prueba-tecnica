
package com.pruebaTecnica.crudPrueba.DTOS;

import com.pruebaTecnica.crudPrueba.JPA.Usuario;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;


public class ProductoRequestDTO {
    
       @NotBlank(message = "La clave del producto es obligatoria")
    @Size(max = 10, message = "La clave no debe exceder 10 caracteres")
    private String claveProducto;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 200, message = "El nombre no debe exceder 200 caracteres")
    private String nombreProducto;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener máximo 10 enteros y 2 decimales")
    private BigDecimal precio;

    private Integer activo;

    private Usuario  usuario;

    public ProductoRequestDTO() {
    }

    public ProductoRequestDTO(String claveProducto, String nombreProducto, BigDecimal precio, Integer activo, Usuario usuario) {
        this.claveProducto = claveProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.activo = activo;
        this.usuario = usuario;
    }

    public String getClaveProducto() {
        return claveProducto;
    }

    public void setClaveProducto(String claveProducto) {
        this.claveProducto = claveProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
