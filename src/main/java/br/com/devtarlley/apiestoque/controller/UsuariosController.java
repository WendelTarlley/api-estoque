package br.com.devtarlley.apiestoque.controller;

import br.com.devtarlley.apiestoque.feign.UsuarioFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuarioFeign usuarioFeign;

    public UsuariosController(UsuarioFeign usuarioFeign) {
        this.usuarioFeign = usuarioFeign;
    }

    @GetMapping
    @CircuitBreaker(name = "usuarios",fallbackMethod = "")
    public String buscarUsuarios(){

        return usuarioFeign.getUsuarios().toString();
    }
}
