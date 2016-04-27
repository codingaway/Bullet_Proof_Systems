/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entity.Manufacturer;
import entity.ProductCode;
import entity.Product;
import admin.adminBeanLocal;
import java.math.BigDecimal;
import java.util.*;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import session.ManufacturerFacade;
import session.ProductCodeFacade;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author Enda
 */
@Named(value = "administratorBean")
@RequestScoped
//@FacesConverter("converter")
public class AdministrationBean {//implements Converter{

    @EJB
    private ProductCodeFacade pcf;

    @EJB
    private ManufacturerFacade mf;

    @EJB
    private adminBeanLocal adminBean;
    
    private String message;
    
    private Integer manufacturer;
    private String code;
    private String description;
    private int quantity;
    private String available; 
    private BigDecimal purchaseCost;
    private BigDecimal markup;
    private Product po;
    
    public AdministrationBean() {
        message = "Please fill in details for new Item.";
    }
    
    public String addItem()
    {
        po = new Product();
        po.setDescription(description);
        po.setQuantityOnHand(quantity);
        po.setAvailable(available);
        po.setPurchaseCost(purchaseCost);
        po.setMarkup(markup);
        
        adminBean.addItem(po, code, manufacturer);
        
        return "manageshop.xhtml";
    }
    
    public void removeItem(int id)
    {
      adminBean.removeItem(id); 
    }
    
    public List<Manufacturer> getManufacturers()
    {
        return mf.findAll();   
    }
    
     public List<ProductCode> getProductCode()
    {
        return pcf.findAll();   
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    } 

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(BigDecimal purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public BigDecimal getMarkup() {
        return markup;
    }

    public void setMarkup(BigDecimal markup) {
        this.markup = markup;
    }

   /* 
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ProductCode getCode() {
        return code;
    }

    public void setCode(ProductCode code) {
        this.code = code;
    }*/
    
    /*@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)throws ConverterException {
       return adminBean.getCode(value);
    }*/


    public Integer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Integer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
     
    
}
