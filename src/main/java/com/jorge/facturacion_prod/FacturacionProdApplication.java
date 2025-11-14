package com.jorge.facturacion_prod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FacturacionProdApplication {

	public static void main(String[] args) {

		Producto p = new Producto(2, "Computador");
		p.setNombre("Televisor");

		p.getNombre();

		SpringApplication.run(FacturacionProdApplication.class, args);
	}

}
