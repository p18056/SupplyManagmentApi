package com.Projects.ProducrManagmentAPI.controllers;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Projects.ProducrManagmentAPI.models.Product;
import com.Projects.ProducrManagmentAPI.models.User;

@RestController 
public class UserController {

    @GetMapping("/users")
    public List<User> getAllUsers()
    {   
        Configuration config = new Configuration();
		config.addAnnotatedClass(User.class);
		config.configure();
        SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
        SelectionQuery<User> query = session.createSelectionQuery("FROM User", User.class);
        return query.getResultList();
    }

    @GetMapping("/users/{email}")
    public User getUser(@PathVariable String email)
    {   
        Configuration config = new Configuration();
		config.addAnnotatedClass(User.class);
		config.configure();
        SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
        return session.find(User.class,email);
    }

    public void createUser(User user)
    {   
        Configuration config = new Configuration();
		config.addAnnotatedClass(User.class);
		config.configure();
        SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
        try
        {   
            if(session.find(User.class,user.getEmail()) != null)
            {
                session.persist(user);
		        transaction.commit();
            }
        }
        catch(Exception e)
        {

        }
    }

    public void updateUser(User user)
    {   
        Configuration config = new Configuration();
		config.addAnnotatedClass(User.class);
		config.configure();
        SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
        session.persist(user);
		transaction.commit();
    }

    public void deleteUser(String email)
    {
        Configuration config = new Configuration();
		config.addAnnotatedClass(User.class);
		config.configure();
        SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
        try{
            User user = session.find(User.class, email);
            session.remove(user);
        }
        catch(Exception e)
        {

        }
		transaction.commit();
    }
    
}
