/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.ejb.EJB;
import usersngroups.CreateUser;
import usersngroups.GroupEjb;

/**
 *
 * @author casper
 */
public class CreateUsersBean {
    
    @EJB
    private CreateUser creatUser;
    @EJB
    private GroupEjb gejb;
    /**
     * Creates a new instance of CreateUsersBean
     */
    public CreateUsersBean() {
    }
    
    public void createUsersAndGroups()
    {
        // Create user "joe" and add to groups
        creatUser.createUser(1, "joe", "1D10T?");
        gejb.addUserToGroup(1, "joe", "user");
        gejb.addUserToGroup(1, "joe", "customer");
        
        // Create user "toor" and add to groups 
        creatUser.createUser(2, "toor", "4uIdo0!");
        gejb.addUserToGroup(2, "toor", "user");
        gejb.addUserToGroup(2, "toor", "admin");
        
    }
}
