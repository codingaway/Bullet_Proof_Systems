# Bullet Proof Systems

## Intorduction
A B2B shopping cart systems based on Netbeans sample database. Being developed using Java EE7 based project using EJB 3.1, Netbeans, GlassFish Server. 

# TO-DO


##Core Contributors
University of Limerick Students: Abdul Halim, Enda O'Shea, Benjamin Kiel and Xiaolong Liang

##Requirements
~~The following are general requirements for this project to be implemented~~

1. User Login
2. Customers can perform the following:
  * Browse through all your items.
  * Search products by ID number and browse through the search results.
  * Search products by name and browse through the search results.
  * Add displayed items to their shopping cart.
  * Remove items from their shopping cart.
  * Edit their profile - must contain at least name, Customer ID and a message to other
users. Name and ID are taken from Customer table, message can be any text – allow at
least for 500 characters.
  * View profiles from other users – provide search by name and search by ID. o Check out or cancel current order.

3. Administrators can perform:
  * Add new products to the database.
  * Remove products from the database.
  * Increase/decrease the available amount (quantity_on_hand) of any product.
4. When customers check out, the quantity for your items in the database is adjusted correspondingly. Make sure the quantity of a product in the database cannot drop below 0 – if an order would cause this, display an error message to the user’s screen. On successful order, you need to add a purchase order (PO) entry. If using the provided PO table, create a new PO for each item. Alternatively, feel free to create your own PO table (see comments below)
5. When customers cancel their order, the database should remain unchanged.
6. Logging facility:
  * Every time a customer confirms an order or cancels an order a corresponding entry is added to the log (either a log-file or database table – see comments below).
  * Every time an administrator adds/removes a product an entry is added to the log.

#Contribution

##Pull Request
We will be using git pull request work flow (google this term if you're not familiar with it)

1. Fork main project
You need to have a github account. After login go to project repository here
https://github.com/codingaway/Bullet_Proof_Systems




Once you fork it you  will have your copy of the project inside your github repository.

2. Clone your fork
Copy your repo URL from github page
You need to have git tools installed on your PC, there are commandline tools as well as GUI.
Using your preferred tool clone your repository into your PC.

3. Add my repository as upstream
From command line:

  ```  
    git remote add upstream https://github.com/codingaway/Bullet_Proof_Systems.git
  ```  


4. Create new branch
Always ALWAYS base your work with most upto date upstream master branch. This will help merging your work to the main repo at ease.

So before you start working on a new feature, follow this

    git rebase upstream/master
    git checkout -b my_awesome_feature

* Hack away -  commit - repeat
* Push your new changes(i.e new branch ) to your github repo (Do rebase again before push incase some else's changes your branch is missing)

    git rebase upstream/master 
    git push origin --all

* Login to your Github
* Open your repository - select Head to your awesome_feature branch
* Click New Pull Request  (ref: https://help.github.com/articles/using-pull-requests/ ) 
* I should be able to get your changes and merge them to the main repo

REPEAT the above steps for every new feature / changes / bug-fix  you're working on. i.e create a separate branch and push to your github then make pull request.

