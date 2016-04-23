/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Gp14user;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
@Stateless
public class Gp14userFacade extends AbstractFacade<Gp14user> {

    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Gp14userFacade() {
        super(Gp14user.class);
    }
    
    public Integer getCustomerIdByUsername(String userName)
    {
        List<Gp14user> userList = em.createNamedQuery("Gp14user.findByUsername")
                .setParameter("username", userName)
                .getResultList();
        
        if(userList.size() > 0)
            return userList.get(0).getCustomerId();
        else
            return null;
    }
    
}
