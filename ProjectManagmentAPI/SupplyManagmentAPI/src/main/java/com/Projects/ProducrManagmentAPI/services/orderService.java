package com.Projects.ProducrManagmentAPI.services;

import java.sql.Date;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Projects.ProducrManagmentAPI.models.InventoryItem;
import com.Projects.ProducrManagmentAPI.models.Order;
import com.Projects.ProducrManagmentAPI.models.Product;
import com.Projects.ProducrManagmentAPI.models.Recipie;
import com.Projects.ProducrManagmentAPI.models.RecipieIngredients;
import com.Projects.ProducrManagmentAPI.reposetory.InventoryRepo;
import com.Projects.ProducrManagmentAPI.reposetory.OrderRepo;

@Service
public class orderService { 

    private Configuration config;
    private SessionFactory factory;
    private Queue<Order> orderQueue;
    private BlockingQueue<Order> currentOrder;

    public orderService()
    {
        config = new Configuration();
        config.addAnnotatedClass(InventoryItem.class);
        config.addAnnotatedClass(RecipieIngredients.class);
        config.addAnnotatedClass(Product.class);
		config.addAnnotatedClass(Recipie.class);
        config.addAnnotatedClass(Order.class);
		config.configure();
        factory = config.buildSessionFactory();
        orderQueue = new ArrayDeque<Order>();
        currentOrder = new LinkedBlockingQueue<Order>(1);
    }

    public void addOrder(Order order)
    {
        orderQueue.add(order);
        {
            executeOrder();
        }while(currentOrder.size() > 0);
    }
    
    public boolean executeOrder()
    {   
		Session session = factory.openSession();
        Order order = currentOrder.peek();
        try{

            Recipie recipie = order.getRecipie();
            for (RecipieIngredients ingredients: recipie.getRecipieIngredients()) 
            {   
                boolean avalableProducts = true;
                InventoryRepo inventoryRepo =  new InventoryRepo();
                if (inventoryRepo.getProductQuantity(ingredients.getProduct().getId()) < ingredients.getQuantity())
                {
                    avalableProducts = false;
                }
                if(avalableProducts) 
                    {
                        executeOrder(order);
                        currentOrder.poll();
                        Order newOrder = orderQueue.peek();
                        currentOrder.put(newOrder);
                        orderQueue.poll();
                        session.close();
                        return true;
                    }

            }
        }
        catch(Exception e)
        {

        }
        finally
        {   
            try{
                session.close();
            }
            catch(Exception e)
            {

            }
            return false;
        }
    }

    private void executeOrder(Order order)
    {   
        Session session = factory.openSession();
        Recipie recipie = order.getRecipie();
        List<RecipieIngredients> ingredients = recipie.getRecipieIngredients();
        for(RecipieIngredients ingredient: ingredients)
        {
            orderIngredient(ingredient.getProduct().getId(),ingredient.getQuantity());
        }
        OrderRepo orderRepo = new OrderRepo(); 
        orderRepo.addOrder(order);
    }
    
    private void orderIngredient(int productId, double quantity)
    {
        InventoryRepo inventoryrepo = new InventoryRepo();
        List<InventoryItem> inventory = inventoryrepo.getInventoryItems(productId);
        var remeiningQuantity = quantity;
        for(InventoryItem item: inventory)
        {   
            if(remeiningQuantity <= 0) break;
            else if(item.getQuantity() >= remeiningQuantity)
            {   
                var temp = remeiningQuantity;
                remeiningQuantity = 0;
                item.setQuantity(item.getQuantity()-temp);
            }
            else
            {
                remeiningQuantity -= item.getQuantity();
                item.setQuantity(0);
            }
        }
        inventoryrepo.updateInventory(inventory);

    }

}
