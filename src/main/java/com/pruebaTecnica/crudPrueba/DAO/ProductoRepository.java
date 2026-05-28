
package com.pruebaTecnica.crudPrueba.DAO;

import com.pruebaTecnica.crudPrueba.JPA.Productos;
import com.pruebaTecnica.crudPrueba.JPA.Usuario;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Productos, Integer>{
    
        @Query("""
        SELECT p FROM Productos p
        WHERE (:nombreProducto IS NULL OR LOWER(p.nombreProducto) LIKE LOWER(CONCAT('%', :nombreProducto, '%')))
        AND (:claveProducto IS NULL OR LOWER(p.claveProducto) LIKE LOWER(CONCAT('%', :claveProducto, '%')))
        AND (:precioMin IS NULL OR p.precio >= :precioMin)
        AND (:precioMax IS NULL OR p.precio <= :precioMax)
    """)
    List<Productos> filtrarProductos(
            @Param("nombreProducto") String nombreProducto,
            @Param("claveProducto") String claveProducto,
            @Param("precioMin") BigDecimal precioMin,
            @Param("precioMax") BigDecimal precioMax
    );
    

}
