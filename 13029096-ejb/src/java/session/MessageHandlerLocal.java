/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Local;

/**
 *
 * @author Anon
 * This Interface is designed to provide appropriate methods 
 * for MessageHandle EJB
 */
@Local
public interface MessageHandlerLocal {
    public String getErroMessage();
    public void setErroMessage(String message); 
    public String getMessage();
    public void setMessage(String message); 
}
