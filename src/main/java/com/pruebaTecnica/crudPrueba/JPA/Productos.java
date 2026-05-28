
package com.pruebaTecnica.crudPrueba.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name ="productos")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto")
    private Integer idProducto;
    
    @Column(name = "identificadornegocio")
    private String identificadorNegocio;
    
    @Column(name = "claveproducto")
    private String claveProducto;
    
    @Column(name = "nombreproducto")
    private String nombreProducto;
    
    private BigDecimal precio;
    
        @Column(nullable = false)
    private Integer activo = 1;
    
    @CreationTimestamp
    @Column(name = "fecharegistro")
    private LocalDateTime fechaRegistro;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    public Productos() {
    }

    public Productos(Integer idProducto, String identificadorNegocio, String claveProducto, String nombreProducto, BigDecimal precio, Integer activo, LocalDateTime fechaRegistro, Usuario usuario) {
        this.idProducto = idProducto;
        this.identificadorNegocio = identificadorNegocio;
        this.claveProducto = claveProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
