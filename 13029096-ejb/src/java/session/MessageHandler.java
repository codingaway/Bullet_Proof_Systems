/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;

/**
 *
 * @author Anon
 */
@Stateless
public class MessageHandler implements MessageHandlerLocal {

/**
 *
 * @author Benjamin
    /**
     * This class is intended to act as error message coordinator
     * we can use it across the app  and dynamically decide what error messages to
     * display at any given time
     */
    private String errorMessage;
    private String message;
/**
 * 
 * @return a errorMessage 
 */

    /**
     *
     * @return
     */
    @Override
    public String getErroMessage() {
        return this.errorMessage;
    }
/**
 * 
 * @param message sets a errorMessage 
 */

    /**
     *
     * @param message
     */

    @Override
    public void setErroMessage(String message) {
        this.errorMessage = message;
    }
/**
 * 
 * @return a user Message
 */

    /**
     *
     * @return
     */
    @Override
    public String getMessage() {
       return this.message;
    }
/**
 * 
 * @param message set a userMessage 
 */

    /**
     *
     * @param message
     */
    
    @Override
    public void setMessage(String message) {
       this.message = message;
    }
    
}
