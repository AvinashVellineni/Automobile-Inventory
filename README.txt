-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		                          	     				  *Automobile Inventory System*
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSTRUCTIONS TO DEPLOY AND RUN THE PROJECT:

 	1. Copy "Automobile_Inventory" folder to tomcat webapps folder.

	2. Start the MangoDB server with mongod.exe and mongo.exe with respect to location. 
		a) database name used is CustomerReviews
		b) Collection name is myReviews
                c) MongodB Version used - 3.4

	3. Start the MySQL database server
		a) database used is smartdatabase
		b) tables used to store data are registration,productdetails,product_accessories and customerorders.

        4. Start tomcat server after setting up the environment variables.

 	5. Open mozilla browser/ Google chrome and type url as ==> localhost/Automobile_Inventory/Home.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Number of lines of Code Written:

JavaScript - 306
Java - 9885
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Instruction to run python script:

 1. Open jupyter notebook using anaconda prompt.
 2. Open DealMatches.ipnyb and specify the src path where the output has to be written.
 3. This will access Twitter API from "AutomobileDeal1 " and access all automobile deals. The program will compare these details with automobile details in our database and gives the result
 4. Execute the python program to generate DealMatches.txt
 5. Open Deals.ipnyb and specify the src path where the output has to be written.
 6. This will access Twitter API from "AutomobileDeal1" and access top selling automobiles like cars etc. The program will compare these details with our productdetails in our database and gives the result.
 7. Execute the python program to generate DealMatches.txt
 8. Open ProductRecommender1.ipnyb and specify the src path where the csv file has to be written.
 9. This connects to the mongodb and generates the output.
 10. Run the website to see the results.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

ENVIRONMENT SETUP FILE:
 1. set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_65
 2. set PATH="C:\Program Files\Java\jdk1.8.0_65\bin";%PATH%
 3. set CLASSPATH=.;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\servlet-api.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\jsp-api.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\el-api.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\mysql-connector-java-5.1.47-bin.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\mongo-java-driver-3.2.2.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\gson-2.3.1.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\mail.jar;C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\activation.jar
 4. set ANT_HOME=C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34
 5. set TOMCAT_HOME=C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34
 6. set CATALINA_HOME=C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Below are the DDL commands used in this application.
 1. create table Registration(username varchar(40),password varchar(40),repassword varchar(40),usertype varchar(40));

 2. create table Productdetails(ProductType varchar(40),Id varchar(40),productName varchar(40),productPrice double,productImage varchar(40),productManufacturer varchar(40),productCondition varchar(40),
    productDiscount double,quantity double,manufacturerRebate varchar(40),primary key(Id,productName));

 3. create table Product_accessories(productName varchar(40),accessoriesName varchar(40));

 4.  create table customerOrders(OrderId Integer,UserName varchar(40),OrderName varchar(40),OrderPrice double,userAddress varchar(40),creditCardNo varchar(40),deliverydate varchar(40),emailid varchar(40),primary key(OrderId,UserName,OrderName));
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

FEATURES IMPLEMENTED:
1.Assignment #1 (ALL FEATURES IMPLEMENTED)

  	a) Registration - Login,Logout (Two user types - customer,Store Manager)
  	b) Header/Side Navigation bar access to product details and automobile inventory features.
    CUSTOMER FEATURES:
   	a) Adding an Item to cart.
        b)Customer shall be able to shop online to buy one or multiple items in the same session.
   	c) Add/Remove Item from the cart before checkout process.
   	d) Carousal feature(has product  accessories that could be bought separately) in the cart before checkout process.
   	e) Payment using credit card and Order confirmation number (Order Id and delivery date).
   	f) Cancelling an order from their account based on order id.
   	g) View orders using order id.

    STORE MANAGER FEATURES:
        a)Create new customer accounts.
        b)Add/delete/update customer orders.
        c)Add/delete/update product details in the inventory.

2.Assignment #2 (ALL FEATURES IMPLEMENTED)

	 a) All accounts login information is stored in MySQL database.
	 b)All Customers transactions/orders is stored in SQL database (MySQL).
	 c)All order updates to insert/delete/update orders is reflected in the MySQL database; not only the HashMap objects.
	 d)Customer must be able to write and view product reviews.
	 e)Product reviews(usingproduct review form) is stored in NoSQL database (MongoDB).
	 f)Data Analytics and Data visualization feature. 
	 g)All implementation for MySQL is placed in a class called MySQLDataStoreUtilities.java.
	 h)All implementation  added for MongoDB shall be placed in a class called MongoDBDataStoreUtilities.java.
         i) Added Trending feature for Top five most liked products, Top five zip-codes where maximum number of products sold, Top five most sold products regardless of the rating.
   
3. ASSIGNMENT #3 (ALL FEATURES IMPLEMENTED)
        
        Implemented inventory and Sales report that is accessible only to Store Manager.
        INVENTORY REPORT:
	  a) Generated a table of all products and how many items of every product currently available in the store using product name, price, number of item items that product available.
	  b) Generated a Bar Chart that shows the product names and the total number of items available for every product.
	  c) Generated a table of all products currently on sale.
	  d) Generated a table of all products currently that have manufacturer rebates.

        SALES REPORT:
          a)Generated a table of all products sold and number of items of every product sold by listing the product name, product price, number of items sold, and total sales of every product sold.
	  b) Generated a Bar Chart by showing the product names and the total sales for every product.
	  c) Generated a table of total daily sales transactions by listing the dates and total sales for every day-date.

4. ASSIGNMENT #4 (ALL FEATURES IMPLEMENTED)

	AUTO-COMPLETION SEARCH FEATURE:
	  a) When the app-server starts up, the Products are first read into a hashmap from ProductCatalog.xml file and then stored in MySQL database.
	  b) Store manager can insert/update/delete products are reflected in the hashmap and then in MySQL database
	  c) All new code added for the auto-complete-complete feature are added in a class called AjaxUtility.java

5.ASSIGNMENT #5(ALL FEATURES IMPLEMENTED)

	DEAL MATCHES FEATURE:
         a)The deal match component uses DealMatches.java servlet and the provided python script to extract deals from twitter, cross check them with products in the MySQL server and write
           them into a text file - DealMatches.txt.
         b)The DealMatches.java servlet will be used to check and display deal offers and relevant product deals on the home page of AUtomobile Inventory System Web Application.

        PRODUCT RECOMMENDATION SYSTEM:
         a)Based on the history of the user purchases and after the user makes a purchase, your enterprise web application, shall make three product recommendations for the logged in user.

EXTRA FEATURES IMPLEMENTED:
      a) Auto-zoom in option for all products before adding to cart
      b) e-mail notification to the customer for the order confirmation.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	