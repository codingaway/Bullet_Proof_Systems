/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entity.Customer;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import session.CustomerFacade;
import session.Gp14userFacade;

/**
 *
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
@Named(value = "userProfileBean")
@RequestScoped
public class UserProfileBean {
    
    @EJB
    private Gp14userFacade uf;
    
    @EJB
    private CustomerFacade cf;
    
    @ManagedProperty(value = "#{param.custId}")
    private Integer custId;

    /**
     * Creates a new instance of ViewProfileBean
     */
    public UserProfileBean() {
    }
    
    public Customer getCutomerDetails()
    {
        Integer profileId = null;
        System.err.println("Customer ID param: " + custId);
        if(custId == null) // view request without parameters -> Viewing own profile
        {
            System.err.println("Customer ID is NULL: ");
            String userName  = FacesContext.getCurrentInstance()
                    .getExternalContext().getRemoteUser();
            if(userName != null)
            {
                profileId = uf.getCustomerIdByUsername(userName);
                System.err.println("Getting logged in users Id ");
            }
        }
        else
            profileId = custId;
        
        if(profileId != null)
        {
            Customer customer = cf.getCustomerById(profileId);
            return customer;
        }
        else
            return null;
    }
    
    public boolean isOwnProfile()
    {
       return (custId == null);
    }
    
        public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }
    
}
