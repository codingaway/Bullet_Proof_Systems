/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkout;

import ShoppingCartEjb.ShoppingCartLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import log.LoggingLocal;

/**
 *
 * @author Enda
 */
@Stateless
public class checkoutProcess implements checkoutProcessLocal, Serializable{

    

    /**
     * Injected Beans
     */
    @EJB
    private LoggingLocal logging;
    
    @EJB
    private ShoppingCartLocal shoppingCart;

    /**
     * Entity manager for persistence
     */
    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    private String message;
    private Date currentTime;
    private int qty, size;
    private Query query;
    
    /**
     * Check if required quantity is available for all products
     * If one fails, return product description string
     * Else reduce qty from database
     * Return confirmation page string
     * @return 
     */
    @Override
    public String checkQty()
    {
        size = shoppingCart.getProducts().size();
        int i=0;
        while(i<size)
        {
            query = em.createNamedQuery("Product.findTotalQuantity")
                    .setParameter("productID",shoppingCart.getProducts().get(i).getProduct().getProductId());
            
            qty = (int)query.getSingleResult();
            
            if(qty < shoppingCart.getProducts().get(i).getProduct_quantity())
                return shoppingCart.getProducts().get(i).getProduct().getDescription();
            
            i++;
        }
            
         i=0;
         while(i<size)
            {
                query = em.createNamedQuery("Product.decreaseQty")
                        .setParameter("qty",shoppingCart.getProducts().get(i).getProduct_quantity())
                        .setParameter("productID",shoppingCart.getProducts().get(i).getProduct().getProductId());
                
                query.executeUpdate();
                i++;
            }
            
            
            
        
        return "confirmation";
    }
    
    /**
     * Add cart details to log if cancelled transaction
     */
    @Override
    public void setCancelLog()
    {
        size = shoppingCart.getProducts().size();
        message="";  
        
        for(int i=0;i<size;i++)
        {
            currentTime = new Date();
            message+= "Date:"+currentTime.toGMTString()+", ID: 13062344, Product ID:"+ 
                    shoppingCart.getProducts().get(i).getProduct().getProductId()+", Qty:"+
                    shoppingCart.getProducts().get(i).getProduct_quantity()+", Order Cancelled";  
            logging.sendMessageToQueue(message);
            message="";
        }
        shoppingCart.getProducts().clear();
    }
    
    /**
     * Return total cost of cart
     * @return 
     */
    @Override
    public BigDecimal getTotalCost()
    {
        BigDecimal cost = new BigDecimal(0);
        size = shoppingCart.getProducts().size();
        
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<shoppingCart.getProducts().get(i).getProduct_quantity();j++)
            {
                cost = cost.add(shoppingCart.getProducts().get(i).getProduct().getPurchaseCost());
            }
        }
        return cost;
    }
   
/**
 * Persistence operation
 * @param object 
 */
    public void persist1(Object object) {
        em.persist(object);
    }
    
    
    
 

    
}
