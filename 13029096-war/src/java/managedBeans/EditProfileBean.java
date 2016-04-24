/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entity.Customer;
import entity.Gp14message;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import session.CustomerFacade;
import session.Gp14messageFacade;
import session.Gp14userFacade;

/**
 *
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
@Named(value = "editProfileBean")
@SessionScoped
public class EditProfileBean implements Serializable {
    
    private String name; 
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String email;   
    private String message;   
    private String phone;  
    private Customer customer;
    
    @ManagedProperty(value = "userProfileBean")
    private UserProfileBean upb;
    
    @EJB
    private CustomerFacade cf;
    
    @EJB
    private Gp14userFacade userf;
    
    @EJB
    private Gp14messageFacade msgf;

    /**
     * Get the value of phone
     *
     * @return the value of phone
     */
    public String getPhone() {
        return customer.getPhone();
    }

    /**
     * Set the value of phone
     *
     * @param phone new value of phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return customer.getEmail();
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of addressLine2
     *
     * @return the value of addressLine2
     */
    public String getAddressLine2() {
        return customer.getAddressline1();
    }

    /**
     * Set the value of addressLine2
     *
     * @param addressLine2 new value of addressLine2
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }


    /**
     * Get the value of addressLine1
     *
     * @return the value of addressLine1
     */
    public String getAddressLine1() {
        return customer.getAddressline2();
    }

    /**
     * Set the value of addressLine1
     *
     * @param addressLine1 new value of addressLine1
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    
   
    
    
    /**
     * Creates a new instance of EditProfileBean
     */
    public EditProfileBean() {
        
        System.err.println("EditProfileConstructor invoked");
        Integer userId = null;
        String userName  = FacesContext.getCurrentInstance()
                    .getExternalContext().getRemoteUser();
        System.err.println("User Name" + userName + " UserId: " + userId);
        if(userName != null)
        {
           userId = userf.getCustomerIdByUsername(userName);          
        }
        
        Customer cust = cf.find(userId);
        if(cust == null)
        {
            System.out.println("Customer is null");
        }
        this.customer = cust;
        //Gp14message custMessae = msgf.find(userId);
        //this.message = custMessae.getMessage();
        
    }
    
    /**
     * Get the value of message
     *
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the value of message
     *
     * @param message new value of message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * Get the value of state
     *
     * @return the value of state
     */
    public String getState() {
        return state;
    }

    /**
     * Set the value of state
     *
     * @param state new value of state
     */
    public void setState(String state) {
        this.state = state;
    }

    

    /**
     * Get the value of city
     *
     * @return the value of city
     */
    public String getCity() {
        return customer.getCity();
    }

    /**
     * Set the value of city
     *
     * @param city new value of city
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return customer.getName();
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    
    public void updateProfile()
    {   
        Integer userId = null;
        String userName  = FacesContext.getCurrentInstance()
                    .getExternalContext().getRemoteUser();
        if(userName != null)
        {
           userId = userf.getCustomerIdByUsername(userName);
           
        }
        System.err.println(userId + " " + name  + " " +  addressLine1  + " " + addressLine2 
             + " " + city  + " " +   state  + " " +  email  + " " + phone);
//        cf.updateCustomer(userId,  name,  addressLine1,  addressLine2, 
//             city,  state,  email,  phone);
    }    
}
