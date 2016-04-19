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
    
    public Product p =null;
    private int product_quantity=0;
    private int new_quantity =0;
    
    public PurchaseItem(Product p){
       this.p = p;
       this.product_quantity=1;
    }

    public int getNew_quantity() {
        return new_quantity;
    }

    public void setNew_quantity(int new_quantity) {
        this.new_quantity = new_quantity;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }
    public void singleAddition(){
        System.out.println("Test in addidtion");
        this.product_quantity=product_quantity;
    }
    public void addQuantity(int i){
    this.product_quantity =this.product_quantity + i;
    }
    public void setProduct_quantity(int i) {
        this.product_quantity =  i;
    }
    public Product getProduct(){
      return p;
    }
    
}
