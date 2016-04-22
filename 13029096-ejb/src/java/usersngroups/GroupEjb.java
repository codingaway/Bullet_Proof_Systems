/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usersngroups;

import entity.Gp14group;
import entity.Gp14groupPK;
import entity.Gp14user;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abdul Halim
 */
@Stateless
@LocalBean
public class GroupEjb {
    
    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public void addUserToGroup(Integer customerId, String username, String groupName) 
    {
        Gp14groupPK gpk = new Gp14groupPK(customerId, groupName);
        Gp14group group = new Gp14group(gpk, username);
        em.persist(group);
    }
}
