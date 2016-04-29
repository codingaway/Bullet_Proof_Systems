/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Benjamin
 */
public class ErrorHandler  {
    /**
     * This class is intended to act as error message helper
     * we can use it across all instances of EJB and dynamically decide what error messages to
     * display at any given time
     */
    public String erroMessage;
    /**
     * 
     * @return returns a error Message 
     */
    public String getErroMessage() {
        return erroMessage;
    }
    /**
     * 
     * @param erroMessage
     * @brief set errorMesage
     */
    public void setErroMessage(String erroMessage) {
        this.erroMessage = erroMessage;
    }
    
    
}
