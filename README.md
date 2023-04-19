# RetailStore

1. **Data Storage:** MySQL DataBase

2. **API Layer:** REST API

3. **Search and Edit Functionality:** Spring Framework can be used. JPA Repository provides PagingAndSortingRepository which can be used for Search.

4. **Authentication and Authorization:** To ensure that only authorized users can access and modify pricing records, authentication and authorization mechanisms should be implemented. This can be achieved by integrating with an existing authentication provider such as LDAP, Active Directory, or OAuth, or by implementing Spring Security.

5. **Scalability:** The system should be designed to be scalable and able to handle increasing workloads as the number of retail stores and pricing feeds grows. This can be achieved by using a distributed architecture with load balancers, caching mechanisms, and horizontal scaling techniques.

### Context Diagram:

![context diagram](https://github.com/sindhujaSJ/RetailStore/blob/main/Context%20Diagram.png)

In this context diagram, the Pricing Feed CSV Uploader component is responsible for uploading and persisting pricing feeds from multiple retail stores. The pricing feeds contain information such as Store ID, SKU, Product Name, Price, and Date.

The Pricing Record Search/Edit component allows users to search for pricing records using various criteria and make changes to any record. This component interacts with the Pricing Database, which stores all pricing records.

The Retail Stores are the source of the pricing feeds. There can be multiple retail stores, each with its own pricing feed. The Pricing Feed CSV Uploader component processes the pricing feeds from all these retail stores and stores the information in the Pricing Database for further use.


### Architecture Diagram

![Architecture Diagram](https://github.com/sindhujaSJ/RetailStore/blob/main/ArchitectureDiagram.png)

The above diagram shows the following components:

1. **Angular UI:** This is the user interface component that is responsible for displaying the web application to the user. It communicates with the API server using HTTP.

2. **Web Application:** This component acts as an intermediary between the Angular UI and the API server. It receives requests from the Angular UI and forwards them to the API server.

3. **API Server:** This component is responsible for handling incoming requests from the Web Application and processing them. It communicates with the Pricing Service using ORM (Hibernate) and provides a REST API for the Web Application to interact with.

4. **Pricing Service:** This is the core component of the system. It is responsible for storing pricing data in the MySQL database and processing requests from the API server. It communicates with the MySQL database using JDBC.

5. **MySQL Database:** This component is responsible for storing pricing data in a structured manner. The Pricing Service communicates with the database using JDBC.



### MySQL Table Creation:

The "pricing_feeds" table will store all the pricing feeds uploaded from retail stores using CSV files. Each row in the table represents a single pricing feed and has a unique identifier "feed_id" (auto-incremented) along with other details such as "store_id", "sku", "product_name", "price", and "date".
To search for pricing records, you can use various criteria such as store_id, sku, date, etc. You can also edit and save changes to any record by updating the corresponding row in the table using the feed_id.

    CREATE TABLE pricing_feeds (
      feed_id INT PRIMARY KEY AUTO_INCREMENT,
      store_id INT,
      sku VARCHAR(50),
      product_name VARCHAR(255),
      price DECIMAL(10,2),
      date DATE
    );


### REST API:

*	/pricing-feeds (POST): to upload a CSV file containing pricing feeds from retail stores.
*	/pricing-records (GET): to search for pricing records using various criteria, such as Store ID, SKU, Product Name, and Date.
*	/pricing-records/{id} (PUT): to update a pricing record identified by its unique identifier (ID).
