/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import entity.Manufacturer;
import entity.Product;
import entity.ProductCode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    /**
     * Injected Beans
     */
    @EJB
    private LoggingLocal logging;

    /**
     * Entity Manager for Persistence
     */
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
    private Date date;
    

    /**
     * Add item to database 
     *Send details to log.
     * @param id
     */
    @Override
    public void addItem(Product po, String prodcode, int id,final int adminID)
    {
      date = new Date();  
        
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
      logging.sendMessageToQueue("Date "+date+", AdminID:"+adminID+
              ", Added Item:Product ID ="+po.getProductId()+
              ":Description = "+po.getDescription()+":Qty = "+po.getQuantityOnHand());
    }
    
    /**
     * Remove item from database
     * Send details to log.
     * @param id 
     */
    @Override
    public void removeItem(final int id, final int adminID)
    {
       date = new Date();
       query = em.createNamedQuery("Product.findByProductId")
               .setParameter("productId", id);
       em.remove(query.getSingleResult());
       logging.sendMessageToQueue("Date "+date+", AdminID:"+adminID
               +", Removed Item: "+query.getSingleResult().toString());
    }
    
    /**
     * Set quantity in database for product.
     * @param id
     * @param qty 
     */
    @Override 
    public void setQty(final int id, final int qty)
    {
        
        query = em.createNamedQuery("Product.setQty")
                        .setParameter("qty",qty)
                        .setParameter("productID",id);
                
                query.executeUpdate();
    }
    
    /**
     * Set log file path.
     * @param path 
     */
    @Override
    public void setFilePath(String path){
        this.path = path;
        System.out.print("Path1:"+path);
    }

    /**
     * get logfile path
     * @return 
     */
    @Override
    public String getPath() {
        return path;
    }
    
    /**
     * Create log file
     */
    @Override
    public void createLogFile()
    {
        if(!created)
        {
        logging.sendMessageToQueue("");
        created=true;
        }
    }

    /**
     * Get file created value
     * @return 
     */
    public boolean isCreated() {
        return created;
    }

    /**
     * Set file created value.
     * @param created 
     */
    public void setCreated(boolean created) {
        this.created = created;
    }
    
  
    
    
    
    
    
    

    
    
}
