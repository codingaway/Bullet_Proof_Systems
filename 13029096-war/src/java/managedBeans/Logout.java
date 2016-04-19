/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
public class Logout {

    /**
     * Creates a new instance of Logout
     */
    public Logout() {
    }
    
    public String doLogout()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        if (session != null)
            session.invalidate();
        return "login";
    }
}
