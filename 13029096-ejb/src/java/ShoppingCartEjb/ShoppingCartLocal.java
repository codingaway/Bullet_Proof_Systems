/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingCartEjb;

import classes.PurchaseItem;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Benjamin
 */
@Local
public interface ShoppingCartLocal {
    /**
     * 
     * This Interface is provides methods 
     * for the the ShoppingCart EnterPrice Bean class 
     */
    public BigDecimal getTotalCost();
    public List<PurchaseItem>getProducts();
    public void addProduct(PurchaseItem item);
    public void removeProduct(int product_id);
    public boolean isItemInCart(Integer pId);
}