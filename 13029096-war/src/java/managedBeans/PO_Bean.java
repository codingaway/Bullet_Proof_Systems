/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import PO.CreatePOLocal;
import entity.PurchaseOrder;
import java.math.BigDecimal;
import java.util.*;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import session.Gp14userFacade;

/**
 *
 * @author Enda
 */
@Named(value = "pO_Bean")
@RequestScoped
public class PO_Bean {

    /**
     * Injected Beans
     */
    @EJB
    private CreatePOLocal createPO;
    
    @EJB
    private Gp14userFacade uf;

    int po_ID;
    int order_Num; 
    int customer_ID;
    short quantity;
    BigDecimal shipping_Cost;
    Date sales_Date;
    Date shipping_Date; 
    String freight_Company; 
    List <PurchaseOrder> pos;
    
    /**
     * Constructor
     * Initialise list
     */
    public PO_Bean() {
       pos = new ArrayList();  
    }
    
    /**
     * Create PO
     */
    public void createPO()
    {  
        Integer custId = getLoggedInUserId();
        if(custId != null)
        {
            pos = createPO.createPO(custId);
        }
    }

    /**
     * get purchase order list
     * @return 
     */
    public List<PurchaseOrder> getPos() {
        return pos;
    }

    /**
     * set purchase order list
     * @param pos 
     */
    public void setPos(List<PurchaseOrder> pos) {
        this.pos = pos;
    }

    /**
     * get id
     * @return 
     */
    public int getPo_ID() {
        return po_ID;
    }

    /**
     * set id
     * @param po_ID 
     */
    public void setPo_ID(int po_ID) {
        this.po_ID = po_ID;
    }

    /**
     * get order number
     * @return 
     */
    public int getOrder_Num() {
        return order_Num;
    }

    /**
     * set order number
     * @param order_Num 
     */
    public void setOrder_Num(int order_Num) {
        this.order_Num = order_Num;
    }

    /**
     * get customer
     * @return 
     */
    public int getCustomer_ID() {
        return customer_ID;
    }

    /**
     * set customer id
     * @param customer_ID 
     */
    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    /**
     * get quantity
     * @return 
     */
    public short getQuantity() {
        return quantity;
    }

    /**
     * set quantity
     * @param quantity 
     */
    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    /**
     * get shipping cost
     * @return 
     */
    public BigDecimal getShipping_Cost() {
        return shipping_Cost;
    }

    /**
     * set shipping cost
     * @param shipping_Cost 
     */
    public void setShipping_Cost(BigDecimal shipping_Cost) {
        this.shipping_Cost = shipping_Cost;
    }

    /**
     * get sales date
     * @return 
     */
    public Date getSales_Date() {
        return sales_Date;
    }

    /**
     * set sales date
     * @param sales_Date 
     */
    public void setSales_Date(Date sales_Date) {
        this.sales_Date = sales_Date;
    }

    /**
     * get shipping date
     * @return 
     */
    public Date getShipping_Date() {
        return shipping_Date;
    }

    /**
     * set shipping date
     * @param shipping_Date 
     */
    public void setShipping_Date(Date shipping_Date) {
        this.shipping_Date = shipping_Date;
    }

    /**
     * get freight company
     * @return 
     */
    public String getFreight_Company() {
        return freight_Company;
    }

    /**
     * set freight company
     * @param freight_Company 
     */
    public void setFreight_Company(String freight_Company) {
        this.freight_Company = freight_Company;
    }
    
    /**
     * get user id
     * @return 
     */
    private Integer getLoggedInUserId()
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
