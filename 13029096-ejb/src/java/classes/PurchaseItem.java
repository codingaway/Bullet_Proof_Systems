/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.Product;

/**
 *
 * @author Anon
 */
public class PurchaseItem {
    /**
     * This class is a helper class to manage 
     * and handle quantities of user selected Products
     * Its attributes are a Product and the corresponding quantity
     */
    public Product p =null;
    private int product_quantity=0;
    private int new_quantity =0;
    /**
     * 
     * @param p 
     * Constructor to instantiate the Purchase Item
     */
    public PurchaseItem(Product p){
       this.p = p;
       this.product_quantity=1;
    }
    /**
     * 
     * @return quantity 
     */
    public int getNew_quantity() {
        return new_quantity;
    }
    /**
     * 
     * @param new_quantity
     * set the Quantity useful when user decides to
     * remove a Product from the Shopping cart or add one to it
     */
    public void setNew_quantity(int new_quantity) {
        this.new_quantity = new_quantity;
    }
    /**
     * 
     * @return Product quantity 
     */
    public int getProduct_quantity() {
        return product_quantity;
    }
    /**
     * This method add onto the overall quantity of the product in the user shopping Basket
     */
    public void singleAddition(){
        System.out.println("Test in addidtion");
        this.product_quantity=product_quantity;
    }
    /**
     * 
     * @param i is the quantity for the corresponding product 
     */
    public void addQuantity(int i){
    this.product_quantity =this.product_quantity + i;
    }
    /**
     * 
     * @param i  
     */
    public void setProduct_quantity(int i) {
        this.product_quantity =  i;
    }
    /**
     * 
     * @return a product  
     */
    public Product getProduct(){
      return p;
    }
    
}
