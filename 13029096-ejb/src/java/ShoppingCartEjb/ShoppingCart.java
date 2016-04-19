/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingCartEjb;

import classes.PurchaseItem;
import entity.Product;
import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vilma
 */

@Stateless
public class ShoppingCart implements ShoppingCartLocal {

    private List<PurchaseItem>cardProducts = new ArrayList();
    
    
    
    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;

    @Override
    public List<PurchaseItem> getProducts() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return cardProducts;
    }

    @Override
    public void addProduct(PurchaseItem item) {
      cardProducts.add(item);
      System.out.println(item.p.getDescription());
    }
    @Override
    public void removeProduct(int product_id) {
        for(int i=0;i<cardProducts.size();i++){ 
          if(cardProducts.get(i).getProduct().getProductId()==product_id){
            cardProducts.remove(i);
            break;
          }
       }  
    }
    public void persist(Object object) {
        em.persist(object);
    }
}
