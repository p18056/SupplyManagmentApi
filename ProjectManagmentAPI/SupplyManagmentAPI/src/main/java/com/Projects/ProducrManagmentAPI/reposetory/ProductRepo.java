package com.Projects.ProducrManagmentAPI.reposetory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.Projects.ProducrManagmentAPI.models.InventoryItem;
import com.Projects.ProducrManagmentAPI.models.Product;
import com.Projects.ProducrManagmentAPI.models.Recipie;
import com.Projects.ProducrManagmentAPI.models.RecipieIngredients;

@Repository 
public class ProductRepo {

    private Configuration config;
    private SessionFactory factory;

    public ProductRepo()
    {
        config = new Configuration();
        config.addAnnotatedClass(InventoryItem.class);
        config.addAnnotatedClass(RecipieIngredients.class);
        config.addAnnotatedClass(Recipie.class);
        config.addAnnotatedClass(Product.class);
        config.configure();
        factory = config.buildSessionFactory();

    }

    public Product getProduct(int productId)
    {   
        Session session = factory.openSession();
        Product product = session.find(Product.class, productId);
        if(product != null)
            {
                session.close();
                return product;
            }
        else 
            {   
                session.close();
                return new Product();
            }
    }

    public List<Product> getProducts()
        {
            Session session = factory.openSession();
            return session.createQuery("FROM products",Product.class).getResultList();

        }
    
    public void addProduct(Product product)
    {   
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(product);
        transaction.commit();
        session.close();

    }
        public void updateProduct(Product product)
    {   
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(product);
        transaction.commit();
        session.close();

    }

    public void DeleteProduct(Product product)
    {   
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(product);
        transaction.commit();
        session.close();
    }
    
}
