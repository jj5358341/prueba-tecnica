
package com.pruebaTecnica.crudPrueba.Config;



import com.pruebaTecnica.crudPrueba.Service.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
@Autowired
private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http
    ) throws Exception {

       http

    .cors(Customizer.withDefaults())

    .csrf(csrf -> csrf.disable())

    .addFilterBefore(
        jwtFilter,
        UsernamePasswordAuthenticationFilter.class
    )

    .authorizeHttpRequests(auth -> auth

        .requestMatchers("/auth/**")
        .permitAll()
            
        .requestMatchers("/productos/**").permitAll()


        .anyRequest()
        .authenticated()
    );
        return http.build();
    }
}