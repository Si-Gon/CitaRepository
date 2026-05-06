package com.silvio.citas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.silvio.citas.client")
public class CitasServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasServicesApplication.class, args);
	}

}
