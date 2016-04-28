/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entity.Customer;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import session.CustomerFacade;
import session.Gp14messageFacade;
import session.Gp14userFacade;

/**
 *
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
@Named(value = "userProfileBean")
@RequestScoped
public class UserProfileBean implements Serializable {
    
    @EJB
    private Gp14userFacade uf;
    
    @EJB
    private CustomerFacade cf;
    
    @EJB
    private Gp14messageFacade mf;
    
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Creates a new instance of ViewProfileBean
     */
    public UserProfileBean() {
        System.err.println("Calling");
    }
    
    public String showUserProfile()
    {
        Integer profileId = null;
        String userName  = FacesContext.getCurrentInstance()
                    .getExternalContext().getRemoteUser();
        if(userName != null)
        {
            profileId = uf.getCustomerIdByUsername(userName);
        }
        
        if(profileId != null)
        {
            this.customer = cf.getCustomerById(profileId);
            return "profile";
        }
        else
            return "404";
    }
    
    public String getMessage()
    {
        Integer profileId = null;
        if(customer != null) // view request without parameters -> Viewing own profile
        {
            profileId = customer.getCustomerId();
            if(profileId != null)
            {
                return mf.getMessageById(profileId);
            }
        }
        return null;
    }
    
    public boolean isOwnProfile()
    {
       return (this.customer != null && this.customer.getCustomerId().equals(this.getLoggedInUserId()));
    }
    
    public Integer getCustId() {
        if(customer != null)
            return this.customer.getCustomerId();
        return null;
    }
    
    public Integer getLoggedInUserId()
    {
        Integer userId = null;
        String userName  = FacesContext.getCurrentInstance()
                    .getExternalContext().getRemoteUser();
        if(userName != null)
        {
           userId = uf.getCustomerIdByUsername(userName);
        }
        return userId;
    }
    
    public String getProfileById(Integer profileId)
    {
        if(profileId != null)
        {
            this.customer = cf.getCustomerById(profileId);
            return "profile";
        }
        
        return "404";
    }
    
    public String getCustName()
    {
        if(customer != null)
            return this.customer.getName();
        return null;
    }
    
    public String getName()
    {
        if(customer != null)
            return this.customer.getName();
        return null;
    }
    
    public String getEmail()
    {
        if(customer != null)
            return this.customer.getEmail();
        return null;
    }
    
    public String getPhone()
    {
        if(customer != null)
            return this.customer.getPhone();
        return null;
    }
    
    public String getAddress1()
    {
        if(customer != null)
            return this.customer.getAddressline1();
        return null;
    }
    
    public String getAddress2()
    {
        if(customer != null)
            return this.customer.getAddressline1();
        return null;
    }
    
     public String getState()
    {
        if(customer != null)
            return this.customer.getState();
        return null;
    }
     
    public String getCity()
    {
        if(customer != null)
            return this.customer.getCity();
        return null;
    }
    
    public boolean isCustomerExist()
    {
        return this.customer != null;
    }
}
