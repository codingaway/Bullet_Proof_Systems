/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import ShoppingCartEjb.ShoppingCartLocal;
import entity.Manufacturer;
import entity.Product;
import entity.ProductCode;
import java.util.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import log.LoggingLocal;

/**
 *
 * @author Enda
 */
@Stateless
public class adminBean implements adminBeanLocal {

    @EJB
    private LoggingLocal logging;

    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    
    private String path;
    private Query query;
    private Manufacturer manufacturer;
    private ProductCode code;
    private boolean created = false;

    @Override
    public void addItem(Product po, String prodcode, int id)
    {
        
      query = em.createNamedQuery("Product.findMaxID");     
      po.setProductId((int)query.getSingleResult()+1);
      
      query = em.createNamedQuery("Manufacturer.findByManufacturerId")
              .setParameter("manufacturerId",id);
      
      manufacturer = (Manufacturer)query.getSingleResult();
      po.setManufacturerId(manufacturer);
      
      query = em.createNamedQuery("ProductCode.findByProdCode")
              .setParameter("prodCode",prodcode);
      
      code = (ProductCode)query.getSingleResult();
      po.setProductCode(code);
      System.out.println(po.toString());
      
      em.persist(po);
      logging.sendMessageToQueue("Added Item:Product ID ="+po.getProductId()+":Description = "+po.getDescription()+":Qty = "+po.getQuantityOnHand());
    }
    
    @Override
    public void removeItem(final int id)
    {
       query = em.createNamedQuery("Product.findByProductId")
               .setParameter("productId", id);
       em.remove(query.getSingleResult());
       logging.sendMessageToQueue("Removed Item: "+query.getSingleResult().toString());
    }
    
    @Override 
    public void setQty(final int id, final int qty)
    {
        
        query = em.createNamedQuery("Product.setQty")
                        .setParameter("qty",qty)
                        .setParameter("productID",id);
                
                query.executeUpdate();
    }
    
    @Override
    public void setFilePath(String path){
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }
    
    @Override
    public void createLogFile()
    {
        if(!created)
        {
        logging.sendMessageToQueue("");
        created=true;
        }
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }   
}
