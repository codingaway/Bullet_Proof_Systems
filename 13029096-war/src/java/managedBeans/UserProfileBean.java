/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entity.Customer;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import session.CustomerFacade;
import session.Gp14userFacade;

/**
 *
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
public class UserProfileBean {
    
    @EJB
    private Gp14userFacade uf;
    
    @EJB
    private CustomerFacade cf;
    
    /**
     * Creates a new instance of ViewProfileBean
     */
    public UserProfileBean() {
    }
    
    public Customer getCutomerDetails()
    {
        String userName  = FacesContext.getCurrentInstance()
                .getExternalContext().getRemoteUser();
        if(userName != null)
        {
            Integer custId = uf.getCustomerIdByUsername(userName);
            Customer customer = cf.getCustomerById(custId);
            return customer;
        }
        return null;
    }
    
}
