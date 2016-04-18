/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopManagedBeans;

import entity.Customer;
import entity.Product;
import entity.ProductCode;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import session.ProductCodeFacade;
import session.ProductFacade;

/**
 *
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
@Named(value = "catelog")
@RequestScoped
public class Catelog {
    
    @EJB
    private ProductFacade pf;
    
    @EJB
    private ProductCodeFacade pcf;
    /*
     * Creates a new instance of Catelog
     */
    public Catelog() {
    }
    
    public List<Integer> getNumbers(){
        
        List<Integer> aList = new ArrayList<>();
        
        aList.add(1);
        aList.add(200);
        aList.add(400);
        aList.add(51);
        
        return aList;
    }
    
    public String getHello()
    {
        return "Hello there Catelog";
    }
    
    public List<Product> getProductList()
    {
        return pf.findAll();
    }
    
    public List<ProductCode> getProductCodeList()
    {
        return pcf.findAll();
    }

}
