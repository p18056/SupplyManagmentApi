package com.Projects.ProducrManagmentAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.security.config.web.server.ServerSecurityMarker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Projects.ProducrManagmentAPI.models.InventoryItem;
import com.Projects.ProducrManagmentAPI.models.Product;
import com.Projects.ProducrManagmentAPI.models.Recipie;
import com.Projects.ProducrManagmentAPI.models.RecipieIngredients;
import com.Projects.ProducrManagmentAPI.models.User;

@Service 
public class TestService {

    private Configuration config;
    private SessionFactory factory;

    public TestService ()
    {
        config = new Configuration();
        config.addAnnotatedClass(InventoryItem.class);
        config.addAnnotatedClass(RecipieIngredients.class);
        config.addAnnotatedClass(Recipie.class);
        config.addAnnotatedClass(Product.class);
        config.addAnnotatedClass(User.class);
        config.configure();
        factory = config.buildSessionFactory();

    }

    public void addUsers()
    {   
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        User user1 =  new User();
        user1.setEmail("stathis@gmail.com");
        user1.setUsername("stathis");
        user1.setHashedPassword(encoder.encode("password1"));
        user1.setRole("Admin");
        user1.setAge(26);

        User user2 =  new User();
        user2.setEmail("user2@gmail.com");
        user2.setUsername("user2");
        user2.setHashedPassword(encoder.encode("password2"));
        user2.setRole("User");
        user2.setAge(20);

        User user3 =  new User();
        user3.setEmail("random@gmail.com");
        user3.setUsername("random");
        user3.setHashedPassword(encoder.encode("password3"));
        user3.setRole("User");
        user3.setAge(25);


        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user1);
        session.persist(user2);
        session.persist(user3);
        transaction.commit();
        session.close();

    }


    public void addProducts()
    {
        Product product =  new Product();
        product.setCategory("Meat");
        product.setName("Chicken");
        product.setDescription("very good cource of protein");
        product.setPrice(3.0);

        Product product2 =  new Product();
        product2.setCategory("Vegtable");
        product2.setName("Potato");
        product2.setDescription("can be used for many recipies");
        product2.setPrice(0.7);

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(product);
        session.persist(product2);
        transaction.commit();
        session.close();
    }
}


