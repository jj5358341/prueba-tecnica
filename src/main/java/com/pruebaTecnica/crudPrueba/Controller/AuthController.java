
package com.pruebaTecnica.crudPrueba.Controller;


import com.pruebaTecnica.crudPrueba.JPA.Usuario;
import com.pruebaTecnica.crudPrueba.Service.JwtService;
import com.pruebaTecnica.crudPrueba.Service.ProductosServices;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ProductosServices productosServices;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Usuario usuario
    ) {
        Usuario usuarioLog = productosServices.validar(usuario);
        


        if (usuarioLog.getCorreo().isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED).body(this);
              
        }

        Usuario usuarioValidado =
                (Usuario) usuarioLog;

        String token =
                jwtService.generarToken(
                        usuarioValidado
                );

        Map<String, Object> response =
                new HashMap<>();

        response.put("token", token);
        response.put("usuario", usuarioValidado);

        return ResponseEntity.ok(response);
    }
}
