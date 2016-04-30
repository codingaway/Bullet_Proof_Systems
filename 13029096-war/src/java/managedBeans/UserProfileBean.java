/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entity.Customer;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import session.CustomerFacade;
import session.Gp14messageFacade;
import session.Gp14userFacade;

/**
 * Managed bean to display user profile data.
 * 
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
@Named(value = "userProfileBean")
@SessionScoped
public class UserProfileBean implements Serializable {
    
    @EJB
    private Gp14userFacade uf;
    
    @EJB
    private CustomerFacade cf;
    
    @EJB
    private Gp14messageFacade mf;
    
    private Customer customer;

    /**
     * Creates a new instance of ViewProfileBean
     */
    public UserProfileBean() {
        
    }
    
    /**
     *
     * @return page outcome as "profile" that displays logged in user profile
     */
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
    
    /**
     *
     * @return Customer's message 
     */
    public String getMessage()
    {
        Integer profileId = null;
        if(customer != null)
        {
            profileId = customer.getCustomerId();
            if(profileId != null)
            {
                return mf.getMessageById(profileId);
            }
        }
        return null;
    }
    
    /**
     *
     * @return true if requested customerId is same as logged-in userId false otherwise
     * 
     */
    public boolean isOwnProfile()
    {
       return (this.customer != null && this.customer.getCustomerId().equals(this.getLoggedInUserId()));
    }
    
    /**
     *
     * @return customer ID
     */
    public Integer getCustId() {
        if(customer != null)
            return this.customer.getCustomerId();
        return null;
    }
    
    /**
     *
     * @return customer id for logged in user
     */
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
    
    /**
     *
     * @param profileId - customer ID
     * @return view outcome "profile" if customer ID is valid
     */
    public String getProfileById(Integer profileId)
    {
        if(profileId != null)
        {
            this.customer = cf.getCustomerById(profileId);
            return "profile";
        }
        
        return "404";
    }
    
    
    /**
     *
     * @return Customer name
     */
    public String getName()
    {
        if(customer != null)
            return this.customer.getName();
        return null;
    }
    
    /**
     *
     * @return Customer email address
     */
    public String getEmail()
    {
        if(customer != null)
            return this.customer.getEmail();
        return null;
    }
    
    /**
     *
     * @return Customer phone number
     */
    public String getPhone()
    {
        if(customer != null)
            return this.customer.getPhone();
        return null;
    }
    
    /**
     *
     * @return Customer address line 1
     */
    public String getAddress1()
    {
        if(customer != null)
            return this.customer.getAddressline1();
        return null;
    }
    
    /**
     *
     * @return Customer Address line 2
     */
    public String getAddress2()
    {
        if(customer != null)
            return this.customer.getAddressline2();
        return null;
    }
    
    /**
     *
     * @return Return customer address State
     */
    public String getState()
    {
        if(customer != null)
            return this.customer.getState();
        return null;
    }
     
    /**
     *
     * @return Customer city
     */
    public String getCity()
    {
        if(customer != null)
            return this.customer.getCity();
        return null;
    }
    
    /**
     *
     * @return Customer object
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer Customer entity object
     * 
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    /**
     *
     * @return "true" if customer is not null
     */
    public boolean isCustomerExist()
    {
        return this.customer != null;
    }
    
    /**
     *
     * @return
     */
    public String editProfile()
    {
        return "editprofile";
    }
    
}
