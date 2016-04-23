/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import entity.Gp14message;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
@Stateless
public class Gp14messageFacade extends AbstractFacade<Gp14message> {

    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Gp14messageFacade() {
        super(Gp14message.class);
    }
    
    public String getMessageById(Integer custId)
    {
        List<Gp14message> mList = em.createNamedQuery("Gp14message.findByCustomerId")
                .setParameter("customerId", custId).getResultList();
        
        if(mList.size() > 0){
            return mList.get(0).getMessage();
        }
        else
            return null;     
    }
    
}
