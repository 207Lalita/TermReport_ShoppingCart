/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ShoppingcartTable {
    // Simulate a database table for the shopping cart
    private static Map<Integer, Shoppingcart> cart = new HashMap<>();
    private static int cartIdCounter = 1;

    public static int addToCart(int productId, int quantity) {
        if (cart.containsKey(productId)) {
            // Product already in the cart, update quantity
            Shoppingcart item = cart.get(productId);
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            // Product not in the cart, add a new entry
            Shoppingcart newItem = new Shoppingcart(cartIdCounter, productId, quantity);
            cart.put(productId, newItem);
            cartIdCounter++;
        }
        return cartIdCounter - 1;
    }

    public static void removeFromCart(int productId) {
        cart.remove(productId);
    }

    public static List<Shoppingcart> getCartContents() {
        return new ArrayList<>(cart.values());
    }

    public static int insertShoppingcart(Shoppingcart cart) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShoppingCartPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(cart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();
            return 0; 
        } finally {
            em.close();
            emf.close();
        }
        return 1; 
    }

    public static List<Shoppingcart> findAllShopppingcart() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingWebAppPU");
        EntityManager em = emf.createEntityManager();
        List<Shoppingcart> movCartList = null;
        try {
            movCartList = (List<Shoppingcart>) em.createNamedQuery("Shoppingcart.findAll").getResultList();         
        } catch (Exception e) {
            throw new RuntimeException(e);
            
        }
        finally {
            em.close();
            emf.close();
        }
        return movCartList;
    }

    public static List<Shoppingcart> findListShoppingcartById(ShoppingcartPK id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShoppingCartPU");
        EntityManager em = emf.createEntityManager();
        List<Shoppingcart> cartList = null;
        try {
            TypedQuery<Shoppingcart> query = em.createNamedQuery("Shoppingcart.findByCartId", Shoppingcart.class);
            query.setParameter("cartId", id.getCartId()); 
            cartList = query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
            emf.close();
        }
        return cartList;
    }
}

