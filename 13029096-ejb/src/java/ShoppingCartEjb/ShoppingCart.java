/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingCartEjb;

import classes.PurchaseItem;
import java.math.BigDecimal;
import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Benjamin
 */

@Stateless
public class ShoppingCart implements ShoppingCartLocal {
    /**
     * A list of Products for the Shopping cart activities
     */
    private List<PurchaseItem>cartProducts = new ArrayList();
    
    
    /**
     * Entity Manager to allow access to Entities
     */
    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;

    /**
     * 
     * @return a List of Purchase Items
     * This method return a List of Purchase Items chosen by the user
     * and added to his Shopping Cart
     */
    @Override
    public List<PurchaseItem> getProducts() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return cartProducts;
    }
    /**
     * 
     * @param item 
     * This method adds a Purchase Item
     */
    @Override
    public void addProduct(PurchaseItem item) {
      cartProducts.add(item);
    }
    /**
     * 
     * @param product_id 
     * This method removes a product from the the list of PurchaseItems
     */
    @Override
    public void removeProduct(int product_id) {
        for(int i=0;i<cartProducts.size();i++){ 
          if(cartProducts.get(i).getProduct().getProductId()==product_id){
            cartProducts.remove(i);
            break;
          }
       }  
    }
    /**
     * 
     * @param object 
     */
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public BigDecimal getTotalCost() {
        BigDecimal cost = new BigDecimal(0);
        int  size = cartProducts.size();
        
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<cartProducts.get(i).getProduct_quantity();j++)
            {
                cost = cost.add(cartProducts.get(i).getProduct().getPurchaseCost());
            }
        }
        return cost;
    }
    
    @Override 
    public boolean isItemInCart(Integer pId)
    {
        for(PurchaseItem pItem: this.cartProducts)
        {
            if(pItem.getProduct().getProductId().equals(pId))
            {
                return true;
            }
        }
        
        return false;
    }
}
