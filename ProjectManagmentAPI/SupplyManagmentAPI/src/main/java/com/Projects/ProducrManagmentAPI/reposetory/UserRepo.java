package com.Projects.ProducrManagmentAPI.reposetory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.Projects.ProducrManagmentAPI.models.Product;
import com.Projects.ProducrManagmentAPI.models.Recipie;
import com.Projects.ProducrManagmentAPI.models.RecipieIngredients;
import com.Projects.ProducrManagmentAPI.models.User;

@Repository 
public class UserRepo {

    private Configuration config;
    private SessionFactory factory;

    public UserRepo()
    {
        config = new Configuration();
        config.addAnnotatedClass(User.class);
		config.configure();
        factory = config.buildSessionFactory();

    }

    public List<User> getUsers()
    {
        Session session = factory.openSession();
        var users = session.createQuery("FROM users",User.class).getResultList();
        session.close();
        return users;
    }

    public void addUser(User user)
    {   
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();

    }

    public void updateUser(User user)
        {   
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
        session.close();

    }

    public void DeleteUser(User user)
    {   
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(user);
        transaction.commit();
        session.close();
    }
    
}
