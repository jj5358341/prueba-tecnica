
package com.pruebaTecnica.crudPrueba.DTOS;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductoResponseDTO {

    private Integer idProducto;

    private String identificadorNegocio;

    private String claveProducto;

    private String nombreProducto;

    private BigDecimal precio;

    private Integer activo;

    private LocalDateTime fechaRegistro;

    public ProductoResponseDTO() {
    }

    public ProductoResponseDTO(Integer idProducto, String identificadorNegocio, String claveProducto, String nombreProducto, BigDecimal precio, Integer activo, LocalDateTime fechaRegistro) {
        this.idProducto = idProducto;
        this.identificadorNegocio = identificadorNegocio;
        this.claveProducto = claveProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdentificadorNegocio() {
        return identificadorNegocio;
    }

    public void setIdentificadorNegocio(String identificadorNegocio) {
        this.identificadorNegocio = identificadorNegocio;
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

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

  
}
