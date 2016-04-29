/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.SessionScoped;
import session.MessageHandlerLocal;

/**
 *
 * @author Anon
 */
@Named(value = "errorMessageBackingBean")
@Dependent
@SessionScoped
@ManagedBean
public class ErrorMessageBackingBean {
    /**
     *  This Managed Bean is designed to handle and manage all  Message types
     * required during the live time of the application
     */
    @EJB
    public MessageHandlerLocal messageHandler;

    /**
     * Creates a new instance of ErrorMessageBackingBean
     * 
     * This class is the Error handles Backing Bean 
     * We inquire the current Error message whenever we need and display
     * it on the UI
     */

    
    public ErrorMessageBackingBean() {
        
    }
    /**
     * 
     * @return a errorMessage
     * @brief get latest errorMessage from messageHandler instance
     * And display on error page.
     */
    public String  errorMessage(){
     
      return this.messageHandler.getErroMessage();
    }
    /**
     * 
     * @return a information message
     * @brief this method returns a informational
     * message to the user
     */
    public String userMessage(){
      return this.messageHandler.getMessage();
    }
}
