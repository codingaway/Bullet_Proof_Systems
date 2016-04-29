/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PO;

import ShoppingCartEjb.ShoppingCartLocal;
import entity.Customer;
import entity.PurchaseOrder;
import java.math.BigDecimal;
import java.util.Date;
import java.util.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import log.LoggingLocal;


/**
 *
 * @author Enda
 */
@Stateless
public class CreatePO implements CreatePOLocal {

    
    /**
     * Injected Beans
     */
    @EJB
    private LoggingLocal logging;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")    
    @EJB
    private ShoppingCartLocal shoppingCart;
    
    

    /**
     * Persistence Manager
     */
    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    private String message;
    private Date currentTime;
    private List<PurchaseOrder> poList;
    private PurchaseOrder po;
    private Customer customer;
    private BigDecimal shipCost;
    private int size,orderNum;
    private Query query;
    
    
    /**
     * create individual PO`s
     * @param custId
     * @return 
     */
    @Override
    public List<PurchaseOrder> createPO(Integer custId)
    {
        poList = new ArrayList();
        size = shoppingCart.getProducts().size();
        query = em.createNamedQuery("PurchaseOrder.findMax");
        orderNum= (int)query.getSingleResult();

        Long curTwoWeeks = (new Date().getTime()) + (14 * 24 * 3600 * 1000) ;
        
        for(int i=0;i<size;i++)
        {
            currentTime = new Date();
            po = new PurchaseOrder();
            orderNum++;
        
            po.setOrderNum(orderNum);
                  
            query = em.createNamedQuery("Customer.findByCustomerId")
                                .setParameter("customerId", custId);
            
            customer = (Customer)query.getSingleResult();
            
            po.setCustomerId(customer);
            po.setFreightCompany("DPD Couriers");
            po.setProductId(shoppingCart.getProducts().get(i).getProduct());
            po.setQuantity((short)shoppingCart.getProducts().get(i).getProduct_quantity());
            po.setSalesDate(currentTime);
            po.setShippingDate(currentTime = new Date(curTwoWeeks));
            po.setShippingCost(shipCost = new BigDecimal(20));
            
            poList.add(po);
            
            logging.sendMessageToQueue("PO:--OrderNo:"+po.getOrderNum()+", CustID:"+po.getCustomerId()
            +", Carrier:"+po.getFreightCompany()+", ProdID:"+po.getProductId()+", Qty:"+po.getQuantity()
            +", DateSold:"+po.getSalesDate()+", ShipDate:"+po.getShippingDate()+", ShipCost:"+po.getShippingCost());
             
            em.persist(po);
        }
        return poList;
    }

    /**
     * get current time
     * @return 
     */
    public Date getCurrentTime() {
        return currentTime;
    }

    /**
     * set current time
     * @param currentTime 
     */
    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * get customer 
     * @return 
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * set customer
     * @param customer 
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * get shipping cost
     * @return 
     */
    public BigDecimal getShipCost() {
        return shipCost;
    }

    /**
     * set shipping cost
     * @param shipCost 
     */
    public void setShipCost(BigDecimal shipCost) {
        this.shipCost = shipCost;
    }

    /**
     * get size
     * @return 
     */
    public int getSize() {
        return size;
    }

    /**
     * set size
     * @param size 
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * get order number
     * @return 
     */
    public int getOrderNum() {
        return orderNum;
    }

    /**
     * set order number
     * @param orderNum 
     */
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    
    
    
}
