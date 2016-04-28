/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ShoppingCartEjb.ShoppingCartLocal;
import admin.adminBeanLocal;
import checkout.checkoutProcessLocal;
import entity.ProductCode;
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

    @EJB
    private adminBeanLocal adminBean;

    @EJB
    private ShoppingCartLocal shoppingCart;
    
    private BigDecimal totalCost;
    private String message = "Please select your required quantity for the items below.";
    

    @EJB
    private checkoutProcessLocal process;
    
    
    
    public CheckoutBean() {  
        totalCost = new BigDecimal(0);
    }
    
    
    public String logCancel()
    {
        process.setCancelLog();
        return "catalog";
    }
    
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
     
     public void calculateCost(){
         this.totalCost = process.getTotalCost();
     }
     

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void clearList()
    {
        shoppingCart.getProducts().clear();
    }
    
   
    public String check()
    {
        if(shoppingCart.getProducts().size()>0)
            return "checkout";
        
        else
            return null;
    }

   
     
     

    
    
    
    
    
    
    
    
}
