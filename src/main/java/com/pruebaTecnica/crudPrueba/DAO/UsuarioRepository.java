
package com.pruebaTecnica.crudPrueba.DAO;


import com.pruebaTecnica.crudPrueba.JPA.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

@Query("""
       FROM Usuario 
       WHERE correo = :pcorreo 
       AND contrasena = :pcontrasena
       """)
Usuario validar(@Param("pcorreo") String correo,
                 @Param("pcontrasena") String contrasena);
Optional<Usuario> findByCorreo(String correo);
}
