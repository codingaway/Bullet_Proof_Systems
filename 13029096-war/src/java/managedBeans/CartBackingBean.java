/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;

import session.ProductFacade;
import ShoppingCartEjb.ShoppingCartLocal;
import classes.PurchaseItem;
import entity.Product;
import java.util.List;
import javax.enterprise.context.Dependent;


/**
 *
 * @author Vilma
 */
@ManagedBean
@Dependent
@Named(value = "cardBackingBean")
public class CartBackingBean {

    @EJB
    private ProductFacade pf;
    
    @EJB
    private ShoppingCartLocal shoppingCart;
    
    private String client_message;
    /**
     * Creates a new instance of CardBackingBean
     */
    
    public CartBackingBean() {
        
    }
    public boolean checkList(List<PurchaseItem> n,int id){
       boolean allreadyOrdered = false;
        
       for(int i=0;i<n.size();i++){ 
          if(n.get(i).getProduct().getProductId()==id){
            allreadyOrdered =true;
            n.get(i).addQuantity(1);
            break;
          }
       }
       return allreadyOrdered;
    }
    public String getClient_message() {
        return client_message;
    }
    public List<PurchaseItem>returnProducts(){
     return this.shoppingCart.getProducts();
    }
    public void setClient_message(String client_message) {
        this.client_message = client_message;
    }
    public void add(int productID){
        System.out.println("Test id"+ productID);
        Product p =(Product)pf.find(productID);
        PurchaseItem item =new PurchaseItem(p);
        //   System.out.println("Test: {"+p.getAvailable()+"}");
        if(p.getAvailable().equals("TRUE")){
           item = new PurchaseItem((Product)pf.find(productID));
           if(checkList(shoppingCart.getProducts(),productID)==true){
           }
           else{
               shoppingCart.addProduct(item);
           }
            client_message="Product was succesfully added";
            System.out.println("Help adding here");
        }
        else{
           this.client_message="Product is not available";
           System.out.println("Added nothging because is empty");
        }
    }
    public void removeProductFromList(int product_id){
       shoppingCart.removeProduct(product_id);
    
    }
}
