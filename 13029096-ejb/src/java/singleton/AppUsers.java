/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import usersngroups.CreateUser;
import usersngroups.GroupEjb;

/**
 *
 * This is a singleton bean that adds required users and groups in DB if not
 * exist at start up
 * 
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
@Singleton
@LocalBean
@Startup
public class AppUsers {

    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private CreateUser creatUser;
    
    @EJB
    private GroupEjb gejb;
    
    
    @PostConstruct
    public void init() {
        // here you can perform queries or transactions
        
        // Create user "joe" and add to groups
        if(!creatUser.isUserExist("joe"))
        {
            creatUser.createUser(1, "joe", "1D10T?");
        }
        if(!gejb.isUserInGroup(1, "user"))
        {
            gejb.addUserToGroup(1, "joe", "user");
        }
        if(!gejb.isUserInGroup(1, "customer"))
        {
            gejb.addUserToGroup(1, "joe", "customer");
        }
        
        
        // Create user "toor" and add to groups 
        if(!creatUser.isUserExist("toor"))
        {
            creatUser.createUser(2, "toor", "4uIdo0!");
        }
        if(!gejb.isUserInGroup(2, "user"))
        {
            gejb.addUserToGroup(2, "toor", "user");
        }
        if(!gejb.isUserInGroup(2, "admin"))
        {
            gejb.addUserToGroup(2, "toor", "admin");
        }
    }
}
