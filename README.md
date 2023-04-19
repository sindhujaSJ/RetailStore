# RetailStore

1. **Data Storage:** MySQL DataBase

2. **API Layer:** REST API

3. **Search and Edit Functionality:** Spring Framework can be used. JPA Repository provides PagingAndSortingRepository which can be used for Search.

4. **Authentication and Authorization:** To ensure that only authorized users can access and modify pricing records, authentication and authorization mechanisms should be implemented. This can be achieved by integrating with an existing authentication provider such as LDAP, Active Directory, or OAuth, or by implementing Spring Security.

5. **Scalability:** The system should be designed to be scalable and able to handle increasing workloads as the number of retail stores and pricing feeds grows. This can be achieved by using a distributed architecture with load balancers, caching mechanisms, and horizontal scaling techniques.


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
