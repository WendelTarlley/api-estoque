package br.com.devtarlley.apiestoque.feign;

import br.com.devtarlley.apiestoque.model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "usuario",url = "http://localhost:8082/api-usuarios")
public interface UsuarioFeign {

    @GetMapping(value = "/usuarios")
    List<Usuario> getUsuarios();
}
