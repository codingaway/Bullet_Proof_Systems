/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import entity.Manufacturer;
import entity.Product;
import entity.ProductCode;
import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Enda
 */
@Stateless
public class adminBean implements adminBeanLocal {

    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    private Query query;
    private Manufacturer manufacturer;
    private ProductCode code;

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
    }
    
    @Override
    public void removeItem(final int id)
    {
       query = em.createNamedQuery("Product.findByProductId")
               .setParameter("productId", id);
       em.remove(query.getSingleResult());
    }
    
    
}
