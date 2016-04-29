/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PurchaseOrder;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
@Local
public interface PurchaseOrderFacadeLocal {

    /**
     *
     * @param purchaseOrder
     */
    void create(PurchaseOrder purchaseOrder);

    /**
     *
     * @param purchaseOrder
     */
    void edit(PurchaseOrder purchaseOrder);

    /**
     *
     * @param purchaseOrder
     */
    void remove(PurchaseOrder purchaseOrder);

    /**
     *
     * @param id
     * @return
     */
    PurchaseOrder find(Object id);

    /**
     *
     * @return
     */
    List<PurchaseOrder> findAll();

    /**
     *
     * @param range
     * @return
     */
    List<PurchaseOrder> findRange(int[] range);

    /**
     *
     * @return
     */
    int count();
    
    
}
