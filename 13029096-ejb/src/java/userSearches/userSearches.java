/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userSearches;

import classes.ErrorHandler;
import entity.Customer;
import entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Anon
 */
@Stateless
public class userSearches implements userSearchesLocal {
   
    private List<Product> productSearchResults =new ArrayList();;
    
    private List<Customer> customerSearchResult =new ArrayList();;
    private Product searchResultProduct;
    private Customer searchResultCustomer;
    private int user_selection1;
    private int user_selection2;
    private String userInput;
    private boolean isError;
    private boolean isNoResult;
    private boolean productFound;
    private boolean customerFound;
    private int userSearchChoice;
    
    private boolean successfulSearch;
    
    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;
    
    
    @Override
    public List<Product> getSearchedProducts() {
         return this.productSearchResults;
    }

    @Override
    public List<Customer> getSearchedCustomer() {
       return this.customerSearchResult;
    }
    @Override
    public int getUser_selection1() {
        return user_selection1;
    }
    @Override
    public void setUser_selection1(int user_selection1) {
        this.user_selection1 = user_selection1;
    }
    @Override
    public int getUser_selection2() {
        return user_selection2;
    }
    @Override
    public void setUser_selection2(int user_selection2) {
        this.user_selection2 = user_selection2;
    }

    @Override
    public Product getSingleProduct() {
        return this.searchResultProduct;
    }

    @Override
    public void setUserSearchChoice(int userSearchChoice) {
        this.userSearchChoice = userSearchChoice;
    }

    @Override
    public Customer getSingleCustomer() {
        return this.searchResultCustomer;
    }
    
    @Override
    public void addACustomerSearchResult(Customer customer) {
        this.customerSearchResult.add(customer);
    }

    @Override
    public void addAProductSearchresult(Product product) {
       this.productSearchResults.add(product);
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public void addSingleSerachResultForCustomer(Customer customer) {
        this.searchResultCustomer = customer;
    }

    @Override
    public void addSingleSearchResultForProduct(Product product) {
        this.searchResultProduct=product;
    }

    @Override
    public List<Product> getAllProducts() {
        return em.createNamedQuery("Product.findAll").getResultList();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return em.createNamedQuery("Customer.findAll").getResultList();
    }

    @Override
    public boolean getSearchStatus() {
        return this.successfulSearch;
    }
    @Override
    public void setSuccessfulSearch(boolean successfulSearch) {
        this.successfulSearch = successfulSearch;
    }

    @Override
    public String getuserInput() {
        return this.userInput;
    }

    @Override
    public void setUserInput(String input) {
        System.out.println("Test "+ input);
        this.userInput = input;
    }

    @Override
    public boolean isNoResult() {
        return this.isNoResult;
    }

    @Override
    public boolean isError() {
        return this.isError;
    }

    @Override
    public void setIsNoResult(boolean isNoResult) {
        this.isNoResult = isNoResult;
    }

    @Override
    public void setIserror(boolean isError) {
       this.isError = isError;
    }

    @Override
    public void setSingleProductFound(boolean found) {
       this.productFound=found;
    }

    @Override
    public void setSingleCustomerFound(boolean found) {
        this.customerFound=found;
    }

    @Override
    public boolean getProductFound() {
       return this.productFound;
    }

    @Override
    public boolean getCustomerFound() {
       return this.customerFound;
    }

    @Override
    public int getUserSearchchoice() {
       return this.userSearchChoice;
    }

}
