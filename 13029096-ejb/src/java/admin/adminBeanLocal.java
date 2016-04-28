/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;


import entity.Manufacturer;
import entity.Product;
import entity.ProductCode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Enda
 */
@Local
public interface adminBeanLocal {
    
    public void addItem(Product po, String code, int id);
    public void removeItem(final int po);
    public void setQty(final int id, final int qty);
    public void setFilePath(String path);
    public String getPath();
    public void createLogFile();
}
