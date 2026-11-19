package com.Projects.ProducrManagmentAPI.reposetory;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.Projects.ProducrManagmentAPI.models.InventoryItem;
import com.Projects.ProducrManagmentAPI.models.InventoryItemId;
import com.Projects.ProducrManagmentAPI.models.Product;
import com.Projects.ProducrManagmentAPI.models.Recipie;
import com.Projects.ProducrManagmentAPI.models.RecipieIngredients;

@Repository 
public class InventoryRepo {

    public Configuration config;
    public SessionFactory factory;


    public InventoryRepo()
    {
        config = new Configuration();
        config.addAnnotatedClass(Recipie.class);
        config.addAnnotatedClass(RecipieIngredients.class);
        config.addAnnotatedClass(Product.class);
        config.addAnnotatedClass(InventoryItem.class);
		config.configure();
        factory = config.buildSessionFactory();
    }

    public InventoryItem inventoryItem(InventoryItemId inventoryItemId)
    {   
        Session session = factory.openSession();
        var inventoryItem = session.find(InventoryItem.class,inventoryItemId);
        session.close();
        if(inventoryItem != null) return inventoryItem;
        else return new InventoryItem();
    }

    public List<InventoryItem> getInventoryItems()
    {   
        Session session = factory.openSession();
        long millis = System.currentTimeMillis();
        Date today = new java.sql.Date(millis);
        List<InventoryItem> inventory = session.createQuery("SELECT * FROM inventory as inv WHERE inv.expire_date > :today ORDER BY expire_date DESC",InventoryItem.class)
        .setParameter("today",today)
        .getResultList();        
        session.close();
        return inventory;
    }

    public List<InventoryItem> getInventoryItems(int productId)
    {   
        Session session = factory.openSession();
        long millis = System.currentTimeMillis();
        Date today = new java.sql.Date(millis);
        List<InventoryItem> inventory = session.createQuery("SELECT * FROM inventory as inv WHERE inv.product_id = :product_id and inv.expire_date > :today ORDER BY expire_date DESC",InventoryItem.class)
        .setParameter("product_id", productId)
        .setParameter("today",today)
        .getResultList();
        session.close();
        return inventory;
    }

    public double getProductQuantity(int productId)
    {   
        Session session = factory.openSession();
        Product product = new ProductRepo().getProduct(productId);
        try{
            if(product != null)
                {   
                    long millis = System.currentTimeMillis();
                    Date today = new java.sql.Date(millis);
                    var selectedInventoryQuantity = session.createQuery("SELECT quantity FROM inventory as inv WHERE inv.product_id = :product_id and inv.expire_date > :today",double.class)
                    .setParameter("product_id", productId)
                    .setParameter("today",today)
                    .getResultList();
                    double sum = 0;
                    for(double quantity: selectedInventoryQuantity) sum += quantity;
                    return sum;

            }
        }
        catch(Exception e){
            
            return 0.0;
        }
        return  0;
    }

    public void addProductToInventory(int productId, double quantity)
    {
        long millis = System.currentTimeMillis();
        Date today = new java.sql.Date(millis);
        InventoryItemId inventoryItemId = new InventoryItemId(productId,today);
        InventoryItem item = new InventoryItem();
        item.setInventoryItemId(inventoryItemId);
        item.setQuantity(quantity);
        addInventoryItem(item);
    }

    public void addInventoryItem(InventoryItem item)
    {
        InventoryItemId inventoryItemId = new InventoryItemId(item.getInventoryId().getProductId(),item.getInventoryId().getExpireDate());
        Session session =  factory.openSession();
        Transaction transaction = session.beginTransaction();
        InventoryItem selectedItem;
        try{

            selectedItem = session.find(InventoryItem.class, inventoryItemId);
            if( selectedItem == null)
            {
                selectedItem = new InventoryItem();
                selectedItem.setInventoryItemId(inventoryItemId);
                selectedItem.setQuantity(0);
            }
            selectedItem.setQuantity(selectedItem.getQuantity() + item.getQuantity());
            if(selectedItem.getQuantity() > 0)
            {
                session.persist(selectedItem);
                transaction.commit();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally 
        {
            session.close();
        }
    }

    public void updateInventory(List<InventoryItem> inventoryItems)
    {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(inventoryItems);
        transaction.commit();
        session.close();
        removeExpiredAndZeros();
    }

    public void removeExpiredAndZeros()
    {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        long millis = System.currentTimeMillis();
        Date today = new java.sql.Date(millis);
        var itemsToDelete = session.createQuery("SELECT * FROM inventory as inv WHERE inv.quantity <= 0 or inv.expire_date <= :today",InventoryItem.class)
        .setParameter("today",today)
        .getResultList();
        session.remove(itemsToDelete);
        transaction.commit();
        session.close();
    }

    
    
}