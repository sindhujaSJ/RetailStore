/**
 * 
 */
package com.retailstore.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retailstore.demo.domain.PricingFeed;

/**
 * @author SJ
 *
 */
@Repository
public interface PricingFeedRepository extends JpaRepository<PricingFeed,String>{

	PricingFeed findByFeedId(int id);
	
	
	 @Query("SELECT p FROM PricingFeed p "
	 		+ "WHERE (:storeId IS NULL OR p.storeId = :storeId) "
	 		+ "AND (:sku IS NULL OR p.sku = :sku) "
	 		+ "AND (:productName IS NULL OR p.productName = :productName) "
	 		+ "AND (:price IS NULL OR p.price = :price) "
	 		+ "AND (:date IS NULL OR p.date = :date)")
	    List<PricingFeed> findByColumns(@Param("storeId") Integer storeId, 
	    		@Param("sku") String sku, @Param("productName") String productName, @Param("price") Long price, 
	    		@Param("date") Date date);
}
