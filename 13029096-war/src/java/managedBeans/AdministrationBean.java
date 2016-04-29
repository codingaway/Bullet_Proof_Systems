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
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import session.Gp14userFacade;
import session.ManufacturerFacade;
import session.ProductCodeFacade;
import session.ProductFacade;


/**
 *
 * @author Enda
 */
@Named(value = "administratorBean")
@RequestScoped
//@FacesConverter("converter")
public class AdministrationBean {

    /**
     * Injected Beans
     */
    @EJB
    private ProductFacade pf;

    @EJB
    private ProductCodeFacade pcf;
    
    @EJB
    private ManufacturerFacade mf;

    @EJB
    private adminBeanLocal adminBean;
    
    @EJB
    private Gp14userFacade uf;
    
    private List<String> logFile;
    private String line = "";
    private Scanner in;
    private File file;
    private String path;
    private String message;
    private Integer manufacturer;
    private String code;
    private String description;
    private int quantity;
    private String available; 
    private BigDecimal purchaseCost;
    private BigDecimal markup;
    private Product po;
    private String adminMessage;
    private int adminID;
    private int id; 
    
    /**
     * Constructor
     * Set message
     */
    public AdministrationBean() {
        message = "Please fill in details for new Item.";
        logFile = new ArrayList();
    }
    
    /**
     * Create product 
     * Add product to database
     * @return 
     */
    public String addItem()
    {
        po = new Product();
        po.setDescription(description);
        po.setQuantityOnHand(quantity);
        po.setAvailable(available);
        po.setPurchaseCost(purchaseCost);
        po.setMarkup(markup);
        adminID = getLoggedInUserId();
        
        
        adminBean.addItem(po, code, manufacturer,adminID);
        
        adminMessage = "Item Added";
        return "manageshop.xhtml";
    }
    
    /**
     * remove item from database
     * @param id 
     */
    public void removeItem(int id)
    {
        adminID = getLoggedInUserId();
      adminBean.removeItem(id,adminID ); 
      adminMessage = "Item Removed";
    }
    
    /**
     * get list of manufacturers
     * @return 
     */
    public List<Manufacturer> getManufacturers()
    {
        return mf.findAll();   
    }
    
    /**
     * get list of product codes
     * @return 
     */
     public List<ProductCode> getProductCode()
    {
        return pcf.findAll();   
    }

     /**
      * get message
      * @return 
      */
    public String getMessage() {
        return message;
    }

    /**
     * set message
     * @param message 
     */
    public void setMessage(String message) {
        this.message = message;
    } 

    /**
     * get description
     * @return 
     */
    public String getDescription() {
        return description;
    }

    /**
     * set description
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get quantity
     * @return 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * set quantity
     * @param quantity 
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * get available
     * @return 
     */
    public String getAvailable() {
        return available;
    }

    /**
     * set available
     * @param available 
     */
    public void setAvailable(String available) {
        this.available = available;
    }

    /**
     * get purchase cost
     * @return 
     */
    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    /**
     * set purchase cost
     * @param purchaseCost 
     */
    public void setPurchaseCost(BigDecimal purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    /**
     * get markup
     * @return 
     */
    public BigDecimal getMarkup() {
        return markup;
    }

    /**
     * set markup
     * @param markup 
     */
    public void setMarkup(BigDecimal markup) {
        this.markup = markup;
    }

    /**
     * get manufacturer
     * @return 
     */
    public Integer getManufacturer() {
        return manufacturer;
    }

    /**
     * set manufacturer
     * @param manufacturer 
     */
    public void setManufacturer(Integer manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * get code
     * @return 
     */
    public String getCode() {
        return code;
    }

    /**
     * set code
     * @param code 
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * create logfile
     */
     public void createLog()
     {
         adminBean.createLogFile();
         this.path = adminBean.getPath();
         System.out.print("Path3:"+path);
     }

     /**
      * get logfile path
      * @return 
      */
    public String getPath() {     
        return path;
    }

    /**
     * set logfile path
     */
    public void setPath()  {
        this.path = adminBean.getPath(); 
        System.out.print("Path2:"+path);
    }
    
    /**
     * Read logfile details 
     * @throws java.io.FileNotFoundException 
     */
    public void openLog() throws FileNotFoundException
    {
        logFile.clear();
        file = new File(adminBean.getPath());
        in = new Scanner(file);
        
        System.out.println("Log");
        
        while(in.hasNext())
        {
            line = "";
            line += in.nextLine();
            System.out.println(line);
            logFile.add(line);
        }
    }

    /**
     * get id
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * set id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * set quantity in database
     */
     public void setQty(){
         adminBean.setQty(id, quantity);
         
         if(id > 0)
         adminMessage = "Quantity has been updated";
     }
     
     /**
      * get product quantity
      * @return 
      */
     public int getProductQty()
     {
         return pf.find(id).getQuantityOnHand();
     }

     /**
      * return logfile list
      * @return 
      */
    public List<String> getLogFile() {
        return logFile;
    }

    /**
     * set logfile list
     * @param logFile 
     */
    public void setLogFile(List<String> logFile) {
        this.logFile = logFile;
    }

    /**
     * get admin message
     * @return 
     */
    public String getAdminMessage() {
        return adminMessage;
    }

    /**
     * set admin message
     * @param adminMessage 
     */
    public void setAdminMessage(String adminMessage) {
        this.adminMessage = adminMessage;
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
