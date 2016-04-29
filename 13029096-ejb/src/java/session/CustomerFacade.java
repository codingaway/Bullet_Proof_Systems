/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import entity.Gp14message;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public CustomerFacade() {
        super(Customer.class);
    }
    
    /**
     *
     * @param custId
     * @return
     */
    public Customer getCustomerById(Integer custId)
    {
        List<Customer> cList = em.createNamedQuery("Customer.findByCustomerId")
                .setParameter("customerId", custId).getResultList();
        
        if(cList.size() > 0){
            return cList.get(0);
        }
        else
            return null;     
    }
    
    /**
     *
     * @param id
     * @param name
     * @param addr1
     * @param addr2
     * @param city
     * @param state
     * @param email
     * @param phone
     * @param message
     * @return
     */
    public boolean updateCustomer(Integer id, String name, String addr1, String addr2, 
            String city, String state, String email, String phone, String message) 
    {
        try
        {
            Customer customer = getCustomerById(id);
            if (customer != null) 
            {
                customer.setName(name);
                customer.setCity(city);
                customer.setState(state);
                customer.setAddressline1(addr1);
                customer.setAddressline2(addr2);
                customer.setEmail(email);
                customer.setPhone(phone);

                Gp14message custMessage = em.find(Gp14message.class, id);
                if(custMessage == null)
                {
                    System.err.println("Gp14Message is NULL");
                    custMessage = new Gp14message(id);
                    custMessage.setMessage(message);
                    em.persist(custMessage);
                }
                else
                {
                    System.err.println("Gp14Message is NOT NULL");
                    custMessage.setMessage(message);
                }
                em.flush();
            }
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    
}
