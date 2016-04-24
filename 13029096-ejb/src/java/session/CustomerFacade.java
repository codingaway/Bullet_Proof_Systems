/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }
    
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
    
    public void updateCustomer(int id, String name, String addr1, String addr2, 
            String city, String state, String email, String phone) 
    {
        Customer customer = getCustomerById(id);
        if (customer != null) {
            customer.setName(name);
            customer.setCity(city);
            customer.setState(state);
            customer.setAddressline1(addr1);
            customer.setAddressline2(addr2);
            customer.setEmail(email);
            customer.setPhone(phone);
        }
    }

    
}
