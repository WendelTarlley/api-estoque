package br.com.devtarlley.apiestoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiEstoqueApplication.class, args);
	}

}
