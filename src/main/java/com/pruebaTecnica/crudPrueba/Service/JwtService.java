
package com.pruebaTecnica.crudPrueba.Service;


import com.pruebaTecnica.crudPrueba.JPA.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import org.springframework.stereotype.Service;


@Service
public class JwtService {

    private final String SECRET_KEY =
            "MiClaveSuperSeguraMiClaveSuperSegura_66544455";

    public String generarToken(Usuario usuario) {

        return Jwts.builder()

                .setSubject(usuario.getCorreo())


                .claim("idUsuario", usuario.getIdUsuario())

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )

                .signWith(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes()
                        ),
                        SignatureAlgorithm.HS256
                )

                .compact();
    }

    public String extraerCorreo(String token) {

        return Jwts.parserBuilder()

                .setSigningKey(
                        SECRET_KEY.getBytes()
                )

                .build()

                .parseClaimsJws(token)

                .getBody()

                .getSubject();
    }
    public Integer extraerIdUsuario(
        String token
) {

    Claims claims = Jwts.parserBuilder()

            .setSigningKey(
                SECRET_KEY.getBytes()
            )

            .build()

            .parseClaimsJws(token)

            .getBody();

    return claims.get(
            "idUsuario",
            Integer.class
    );
}

}