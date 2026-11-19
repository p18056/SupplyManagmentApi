package com.Projects.ProducrManagmentAPI.controllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Projects.ProducrManagmentAPI.models.Product;


@RestController 
public class ProductController {

    @GetMapping("/products")
    public List<Product> Products()
    {
        Product product = new Product();
        product.setName("Laptop");
        product.setCategory("Computer");
        product.setDescription("Very good laptop !!!");
        product.setPrice(500.0);
        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }

    @GetMapping("products/{id}")
    public Product getProduct(int id)
    {   
        return new Product();
    }
    
    public void createProduct(Product product)
    {   
        Configuration config = new Configuration();
		config.addAnnotatedClass(Product.class);
		config.configure();
        SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
        try
        {   
            if(session.find(Product.class,product.getId()) != null)
            {
                session.persist(product);
		        transaction.commit();
            }
        }
        catch(Exception e)
        {

        }
    }

    public void updateProduct(Product product)
    {   
        Configuration config = new Configuration();
		config.addAnnotatedClass(Product.class);
		config.configure();
        SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
        session.persist(product);
		transaction.commit();
    }

    public void deleteProduct(int id)
    {
        Configuration config = new Configuration();
		config.addAnnotatedClass(Product.class);
		config.configure();
        SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
        try{
            Product product = session.find(Product.class, id);
            session.remove(product);
        }
        catch(Exception e)
        {

        }
		transaction.commit();
    }
}
