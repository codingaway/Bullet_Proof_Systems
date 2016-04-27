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
import log.LoggingLocal;
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
    
    
    public PO_Bean() {
       pos = new ArrayList();  
    }
    
    public void createPO()
    {  
        Integer custId = getLoggedInUserId();
        if(custId != null)
        {
            pos = createPO.createPO(custId);
        }
    }

    public List<PurchaseOrder> getPos() {
        return pos;
    }

    public void setPos(List<PurchaseOrder> pos) {
        this.pos = pos;
    }

    public int getPo_ID() {
        return po_ID;
    }

    public void setPo_ID(int po_ID) {
        this.po_ID = po_ID;
    }

    public int getOrder_Num() {
        return order_Num;
    }

    public void setOrder_Num(int order_Num) {
        this.order_Num = order_Num;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getShipping_Cost() {
        return shipping_Cost;
    }

    public void setShipping_Cost(BigDecimal shipping_Cost) {
        this.shipping_Cost = shipping_Cost;
    }

    public Date getSales_Date() {
        return sales_Date;
    }

    public void setSales_Date(Date sales_Date) {
        this.sales_Date = sales_Date;
    }

    public Date getShipping_Date() {
        return shipping_Date;
    }

    public void setShipping_Date(Date shipping_Date) {
        this.shipping_Date = shipping_Date;
    }

    public String getFreight_Company() {
        return freight_Company;
    }

    public void setFreight_Company(String freight_Company) {
        this.freight_Company = freight_Company;
    }
    
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
