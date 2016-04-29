/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ProductCode;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
public class ProductCodeBean {
    
    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;
    
    /**
     *
     * @return
     */
    public List<ProductCode> getProductCode()
    {
        Query query = em.createNamedQuery("ProductCode.findAll");
        // return query result
        return query.getResultList();
    }
}
