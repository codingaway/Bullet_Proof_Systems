/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usersngroups;

import entity.Gp14user;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityExistsException;
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
        try{
            em.persist(user);
            em.flush();
        }
        catch(Exception ex)
        {
            System.err.println("Exception on DUPLICATE USER");
        }
    }
    
    public boolean isUserExist(String userName)
    {
        List <Gp14user> uList = em.createNamedQuery("Gp14user.findByUsername")
                .setParameter("username", userName).getResultList();
        
        if(uList.size() > 0)
            return true;
        else
            return false;
    }
    
    
}
