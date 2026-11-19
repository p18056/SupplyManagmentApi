package com.Projects.ProducrManagmentAPI.reposetory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.Projects.ProducrManagmentAPI.models.RecipieIngredients;
import com.Projects.ProducrManagmentAPI.models.InventoryItem;
import com.Projects.ProducrManagmentAPI.models.Product;
import com.Projects.ProducrManagmentAPI.models.Recipie;

@Repository 
public class RecipieRepo {

    private Configuration config;
    private SessionFactory factory;


    public RecipieRepo()
    {
        config = new Configuration();
        config.addAnnotatedClass(InventoryItem.class);
        config.addAnnotatedClass(RecipieIngredients.class);
        config.addAnnotatedClass(Product.class);
        config.addAnnotatedClass(Recipie.class);
		config.configure();
        factory = config.buildSessionFactory();

    }

    public Recipie findRecipie(int recipieId)
    {

		Session session = factory.openSession();
        var recipie = session.find(Recipie.class, recipieId);
        session.close();
        if(recipie != null) return recipie;

        else return new Recipie();
    }

    public List<Recipie> getRecipies()
    {
        Session session = factory.openSession();
        var recipies = session.createQuery("FROM recipies",Recipie.class).getResultList();
        session.close();
        return recipies;
    }

    public void addRecipie(Recipie recipie)
    {   
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(recipie);
        transaction.commit();
        session.close();

    }

    public void updateRecipie(Recipie recipie)
        {   
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(recipie);
        transaction.commit();
        session.close();

    }

    public void DeleteRecipie(Recipie recipie)
    {   
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(recipie);
        transaction.commit();
        session.close();
    }

    public void UpdateRecipieIngredients(List<RecipieIngredients> recipieIngredients)
    {   
        Session session = factory.openSession();
        for(RecipieIngredients ingredient: recipieIngredients)
        {
        Transaction transaction = session.beginTransaction();
        session.persist(ingredient);
        transaction.commit();
        }
        session.close();

    }
    
    
}