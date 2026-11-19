package com.Projects.ProducrManagmentAPI;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.Projects.ProducrManagmentAPI.services.TestService;


@SpringBootApplication
public class ProductManagmentAPIApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(ProductManagmentAPIApplication.class, args);
		TestService service =  new TestService();
		service.addUsers();
		service.addProducts();

	}

}
