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
import javax.faces.bean.ManagedProperty;
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
        if(custId == null) // view request without parameters -> Viewing own profile
        {
            String userName  = FacesContext.getCurrentInstance()
                    .getExternalContext().getRemoteUser();
            if(userName != null)
            {
                profileId = uf.getCustomerIdByUsername(userName);
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
    
    public String getCutomerMessage()
    {
        Integer profileId = null;
        if(custId == null) // view request without parameters -> Viewing own profile
        {
             profileId = getLoggedInUserId();
        }
        else
            profileId = custId;
        
        if(profileId != null)
        {
            return mf.getMessageById(profileId);
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
    
}
