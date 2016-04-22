/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usersngroups;

import entity.Gp14user;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abdul Halim
 * 
 */
@Stateless
@LocalBean
public class CreateUser {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;

    public void createUser(Integer customerId, String username, String password) 
    {
        Gp14user user = new Gp14user(customerId, username, password);
        em.persist(user);
        //em.close();
    }
    
    
}
