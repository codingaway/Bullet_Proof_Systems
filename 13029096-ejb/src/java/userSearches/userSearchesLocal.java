/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userSearches;

import classes.ErrorHandler;
import entity.Customer;
import entity.Product;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Anon
 */
@Local
public interface userSearchesLocal {
    
    /**
     * 
     * This Interface is provides methods 
     * for the the UserSearches EnterPrice Bean class 
     */
    public int getUserSearchchoice();
    public void setUserSearchChoice(int choice);
    public String getuserInput();
    public void setSingleProductFound(boolean found);
    public void setSingleCustomerFound(boolean found);
    public boolean getProductFound();
    public boolean getCustomerFound();
    public void setIsNoResult(boolean isNoResult);
    public boolean isNoResult();
    public void setIserror(boolean isError);
    public boolean isError();
    public void setUserInput(String input);
    public int getUser_selection1();
    public int getUser_selection2();
    public void setUser_selection2(int user_selection);
    public void setUser_selection1(int user_selection);
    public List<Product> getAllProducts();
    public List<Customer>getAllCustomers();
    public List<Product> getSearchedProducts();
    public List<Customer>getSearchedCustomer();
    public void addACustomerSearchResult(Customer customer);
    public void addAProductSearchresult(Product product);
    public Product getSingleProduct();
    public Customer getSingleCustomer();
    public void addSingleSerachResultForCustomer(Customer customer);
    public void addSingleSearchResultForProduct(Product product);
    public boolean getSearchStatus();
    public void setSuccessfulSearch(boolean successfulSearch);
}