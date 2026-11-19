package com.Projects.ProducrManagmentAPI.reposetory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.Projects.ProducrManagmentAPI.models.InventoryItem;
import com.Projects.ProducrManagmentAPI.models.Order;
import com.Projects.ProducrManagmentAPI.models.Product;
import com.Projects.ProducrManagmentAPI.models.Recipie;
import com.Projects.ProducrManagmentAPI.models.RecipieIngredients;

@Repository 
@Component 
public class OrderRepo {

    public Configuration config;
    public SessionFactory factory;


    public OrderRepo()
    {   
        config = new Configuration();
        config.addAnnotatedClass(InventoryItem.class);
        config.addAnnotatedClass(Recipie.class);
        config.addAnnotatedClass(RecipieIngredients.class);
        config.addAnnotatedClass(Product.class);
		config.configure();
        factory = config.buildSessionFactory();
    }
    
    public void addOrder(Order order)
    {  
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(order);
        transaction.commit();
        session.close();
    }

    public Order getOrder(int orderId)
    {  
        Session session = factory.openSession();
        Order order = session.find(Order.class,orderId);
        session.close();
        return order;
    }

    public List<Order> getOrders()
    {  
        Session session = factory.openSession();
        var orders = session.createQuery("SELECT * FROM orders",Order.class).getResultList();
        session.close();
        return orders;
    }
}
