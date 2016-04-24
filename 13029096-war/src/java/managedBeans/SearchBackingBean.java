/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entity.Customer;
import entity.Product;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import session.CustomerFacade;
import session.MessageHandlerLocal;
import session.ProductFacade;
import userSearches.userSearchesLocal;

/**
 *
 * @author Anon
 */



@Named(value = "searchBackingBean")
@ManagedBean
@Dependent
@SessionScoped
public class SearchBackingBean {

    @EJB
    private MessageHandlerLocal messageHandler;
/**
 * 
 * This class is the Backing Bean for our User defined Searches
 */
    
    /**
     *  Enterprise Bean responsible to keep track and store user searches
     */
    @EJB
    private userSearchesLocal userSearches;

     
    /**
     * Helper class to access Product Entity
     */
    @EJB
    private ProductFacade productFacade;
     
     /**
      * Helper class to access customer Entity
      */
    @EJB
    private CustomerFacade customerFacade;
    
    
     /**
      * check is there a error in the user input
      */
    private boolean noErrorsInSelection;

    public boolean isNoErrorsInSelection() {
        return noErrorsInSelection;
    }
    /**
     * 
     * @param noErrorsInSelection set error value to true if there is 
     * an error in the selection
     */
    public void setNoErrorsInSelection(boolean noErrorsInSelection) {
        this.noErrorsInSelection = noErrorsInSelection;
    }
    /**
     * 
     * @return the current status of a search i.e
     * has search come up with a result or is it empty 
     */
     public boolean getResultStatus(){
        return this.userSearches.isNoResult();
     } 
     /**
      * 
      * @return the current error status of a search
      * ie has something gone wrong like a NUmberFormatexception
      */
    public boolean getErrorStatus(){
       return this.userSearches.isError();
    }
    /**
     * 
     * @return simple helper method return
     * the user input is easy to parse or cast into any type
     */
    public String getTmp() {
        return this.userSearches.getuserInput();
    }
    /**
     * 
     * @param tmp here is the user Input from the UI i.e a ID or Name 
     */
    public void setTmp(String tmp) {
        
        if(tmp!="" || tmp != null){
          this.userSearches.setUserInput(tmp);
        }
    }
    /**
     * This method makes sure we clear the the results list every time we leave
     * enter the search page
     */
    
    public void clearLists(){
      this.userSearches.getSearchedCustomer().clear();
      this.userSearches.getSearchedProducts().clear();
    
    }
    /**
     * Stores the user selected option from UI
     */
    public int getUser_selection1() {
        return this.userSearches.getUser_selection1();
        
    }
    
    /**
     * You don't want to know....
     */
    public void test(){
       System.out.println("Test user search "+this.userSearches.getUser_selection1());
       System.out.println("Test user search "+this.userSearches.getUser_selection2());
    }
    /**
     * 
     * @param user_selection
     * @brief we are setting the user selection in the Enterprise bean
     */
    public void setUser_selection1(int user_selection) {
         this.userSearches.setUser_selection1(user_selection);
    }
    /**
     * 
     * @return the user selection fro Enterprise bean 
     */
    public int getUser_selection2() {
        return this.userSearches.getUser_selection2();
    }

    public void setUser_selection2(int user_selection) {
        this.userSearches.setUser_selection2(user_selection);
    }
    /**
     * 
     * @param product_id
     * @brief we search for a product by its id
     *        if we find a product we set searchStatus to true
     *        if not we set it to false 
     */
    public void searchProductByID(int product_id){
        if(this.productFacade.find(product_id)!=null){
          this.userSearches.addSingleSearchResultForProduct((Product)this.productFacade.find(product_id));
          this.userSearches.setSingleProductFound(true);
          this.userSearches.setSuccessfulSearch(true);
        }
        else{
        this.userSearches.setSuccessfulSearch(false);
        }
    }
    public void searchCustomerbyID(int customer_id){
        if(this.customerFacade.find(customer_id)!=null){
          this.userSearches.addSingleSerachResultForCustomer((Customer)this.customerFacade.find(customer_id));
          this.userSearches.setSingleCustomerFound(true);
          this.userSearches.setSuccessfulSearch(true);
        }
        else{
            this.userSearches.setSuccessfulSearch(false);
        }
    }
    public void searchProductByNAme(String name){
       List<Product>allProducts = this.userSearches.getAllProducts();
       int len = allProducts.size();
       for(int i = 0;i<len;i++){
           System.out.println("Test in loop "+i+allProducts.get(i).getDescription().toLowerCase()+" vs "+name.toLowerCase());
          if(allProducts.get(i).getDescription().toLowerCase().contains(name.toLowerCase())==true){
              System.out.println("Found "+i+allProducts.get(i).getDescription().toLowerCase()+" vs "+name.toLowerCase());
           Product p = (Product)allProducts.get(i);
           this.userSearches.addAProductSearchresult(p);
            this.userSearches.setSuccessfulSearch(true);
          }
       }
    }
    public String initialiseSearch(){
        String page = "";
      switch(this.userSearches.getUserSearchchoice()){
          
          case 1: page = this.performAction1();break;
          case 2:page = this.performAction2();break;
      } 
      return page;
    }
    public void searchCustomerByName(String name){
          System.out.println("In serach");
       List<Customer>allCustomers = this.userSearches.getAllCustomers();
       int len = allCustomers.size();
       for(int i = 0;i<len;i++){
            System.out.println("Test in loop "+i+allCustomers.get(i).getName().toLowerCase()+" vs "+name.toLowerCase());
          if(allCustomers.get(i).getName().toLowerCase().contains(name.toLowerCase())==true){
              Customer c = (Customer)allCustomers.get(i);
             this.userSearches.addACustomerSearchResult(c);
             this.userSearches.setSuccessfulSearch(true);
          }
       }
    }
    /**
     * 
     * @return string for the next page
     * This method is fired when the user clicks on commandButton
     * All required fields have been set in the UI
     */
    public String performAction1(){
        
       this.clearLists();
       this.userSearches.setIsNoResult(false);
       this.userSearches.setIserror(false);
       String  result_page ="searchresults";
       switch(this.userSearches.getUser_selection1()){
           case 1: try{
                      searchProductByID(Integer.parseInt(this.userSearches.getuserInput()));
                   }catch(NumberFormatException e){
                       this.userSearches.setIserror(true);
                   };
                   break;
           case 2: searchProductByNAme(this.userSearches.getuserInput()); System.out.println("test Am I in here?");break;   
       }
       if(this.userSearches.isError()==true){
          this.messageHandler.setErroMessage("Invalid format for product ID\n\nProduct ID's are numeric i.e 89053");
       }
       else if(this.userSearches.getSearchStatus()==false){
           this.userSearches.setIsNoResult(true);
           this.messageHandler.setMessage("No Results found!");
       }
       else{
          this.userSearches.setIsNoResult(false);
       }
        this.userSearches.setUserInput("");
       return  result_page;
    }
    public String performAction2(){
        System.out.println("I am i here!!");
        this.clearLists();
        this.userSearches.setIsNoResult(false);
        this.userSearches.setIserror(false);
        String result_page="searchresults";
        switch(this.userSearches.getUser_selection1()){
           case 1: try{ 
                     System.out.println("In ID");
                     searchCustomerbyID(Integer.parseInt(this.userSearches.getuserInput()));
                   }catch(NumberFormatException e){
                       this.userSearches.setIserror(true);
                     
                   };
                   break;
           case 2: searchCustomerByName(this.userSearches.getuserInput());  System.out.println("In Name");break;
        }
       if(this.userSearches.isError()==true){
          this.messageHandler.setErroMessage("Invalid format for product ID\n\nCustomer ID's are numeric i.e 89053");
       }
       else if (this.userSearches.getSearchStatus()==false){
           this.userSearches.setIsNoResult(true);
           this.messageHandler.setMessage("No Results found!");
       }
       else{
           this.userSearches.setIsNoResult(false);
       }
       this.userSearches.setUserInput("");
       return result_page;
    }
    /**
     * 
     * @return a single product from Enterprise Bean
     */
    public Product getProduct() {
        return this.userSearches.getSingleProduct();
    }
    /**
     * 
     * @return a single customer from EnterPrice Bean 
     */
    public Customer getCustomer() {
        return this.userSearches.getSingleCustomer();
    }
    /**
     *    Following methods return lists of Customers or Products
     * 
     * @return 
     */
    public List<Product> getSearchResultsForProducts(){
        
        return this.userSearches.getSearchedProducts();
    }
    public List<Customer>getSearchResultForCustomers(){
       // System.out.println(this.userSearches.getAllCustomers().get(0).getName());
       
      return this.userSearches.getSearchedCustomer();
    }
    public boolean productsNotEmpty(){
      if(this.userSearches.getSearchedProducts().size()>0){
        return true;
      } 
      else{
        return false;
      }
    }
    public void setUserSearchChoice(int choice){
        this.userSearches.setUserSearchChoice(choice);
    }
    public int getUserSearchChoice(){
     return this.userSearches.getUserSearchchoice();
    }
    public boolean isProductFound(){
        return this.userSearches.getProductFound();
    }
    public boolean isCustomerFound(){
     return this.userSearches.getCustomerFound();
    }
    public boolean customersNotEmpty(){
      if(this.userSearches.getSearchedCustomer().size()>0){
        return true;
      }
      else{
       return false;
      }
    }
    public SearchBackingBean() {
        
    }

    
    
}
