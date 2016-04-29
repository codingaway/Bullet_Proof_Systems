/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ShoppingCartEjb.ShoppingCartLocal;
import admin.adminBeanLocal;
import checkout.checkoutProcessLocal;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
/**
 *
 * @author Enda
 */
@Named(value = "checkoutBean")
@RequestScoped
public class CheckoutBean {

    /**
     * Injected Beans
     */
    @EJB
    private adminBeanLocal adminBean;

    @EJB
    private ShoppingCartLocal shoppingCart;
    
    @EJB
    private checkoutProcessLocal process;
    
    private BigDecimal totalCost;
    private String message = "Please select your required quantity for the items below.";
    
    /**
     * Constructor
     * instantiate totalCost
     */
    public CheckoutBean() {  
        totalCost = new BigDecimal(0);
    }
    
    /**
     * process cancel message
     * @return 
     */
    public String logCancel()
    {
        process.setCancelLog();
        return "catalog";
    }
    
    /**
     * Check cart size
     * log details for cart
     * @return 
     */
     public String logProcess()
    {
        if(shoppingCart.getProducts().size()>0)
        {
            String result = process.checkQty();
        
            if(!result.equals("confirmation"))
            {
            setMessage("That quantity is no longer available for "+result+"."
                        +"Please select another quantity.");
             result = "cart";
            }
        
        return result;
        }
        
        return "catalog";
    }
     
     /**
      * set total cost of cart
      */
     public void calculateCost(){
         this.totalCost = process.getTotalCost();
     }
     
     /**
      * get total cost
      * @return 
      */
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    /**
     * get message
     * @return 
     */
    public String getMessage() {
        return message;
    }

    /**
     * set message
     * @param message 
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * clear cart list
     */
    public void clearList()
    {
        shoppingCart.getProducts().clear();
    }
    
   /**
    * check if cart is empty
    * @return 
    */
    public String check()
    {
        if(shoppingCart.getProducts().size()>0)
            return "checkout";
        
        else
            return null;
    }

   
     
     

    
    
    
    
    
    
    
    
}
