/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
@Named(value = "itemCatalog")
@Dependent
public class ItemCatalog {

    /**
     * Creates a new instance of ItemCatalog
     */
    public ItemCatalog() {
    }
    
}
