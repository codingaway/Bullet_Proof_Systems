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
import java.math.BigDecimal;
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
    /**
     * Enterprise Bean to manage Product Entity
     */
    @EJB
    private ProductFacade pf;
    /**
     * Enterprise Bean 
     */
    @EJB
    private ShoppingCartLocal shoppingCart;
    
    /**
     * String to display message to client
     */
    private String client_message;
    /**
     * Creates a new instance of CardBackingBean
     */
    
    double totalCost;
    public CartBackingBean() {
        
    }
    /**
     * 
     * @param n
     * @param id
     * @return boolean to evaluate is this product already in the basket
     *         We don't need to add extra product to list we just increase the quantity in the
     *         PurchaseItem helper class 
     */
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
    /**
     * 
     * @return a client message 
     */
    public String getClient_message() {
        return client_message;
    }
    /**
     * 
     * @return the shoppingCart list 
     */
    public List<PurchaseItem>returnProducts(){
     return this.shoppingCart.getProducts();
    }
    /**
     * 
     * @param client_message
     * @brief this method sets a client message
     */
    public void setClient_message(String client_message) {
        this.client_message = client_message;
    }
    /**
     * 
     * @param productID
     * Add new PurchaseItem to list for ShoppingCart
     * We find the Product and check is this Item already in Basket
     * If not we add it other wise we increase quantity in PurchaseItem class
     * 
     */
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
    /**
     * This method is fired when a user decides to cancel all items in the Shopping Basket
     */
    public String cancelTransaction(){
        this.shoppingCart.getProducts().clear();
        return "catalog";
    }
    /**
     * 
     * @param product_id 
     * This method removes a PurchaseItem and its Product from the list
     */
    
    public void removeProductFromList(int product_id){
       shoppingCart.removeProduct(product_id);
    
    }
     public BigDecimal getTotalCost(){
       return this.shoppingCart.getTotalCost();
     }
}
