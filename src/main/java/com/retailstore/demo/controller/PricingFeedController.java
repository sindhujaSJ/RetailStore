/**
 * 
 */
package com.retailstore.demo.controller;

import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.retailstore.demo.domain.PricingFeed;
import com.retailstore.demo.service.PricingFeedService;

/**
 * @author SJ
 *
 */
@RestController
public class PricingFeedController {

	PricingFeedService pricingFeedService;

	/***
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/pricing-feeds")
	public ResponseEntity<String> uploadPricingFeeds(@RequestParam("file") MultipartFile file) {
		try {
			pricingFeedService.uploadPricingFeeds(file);
			return ResponseEntity.ok("File uploaded successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Error uploading file: " + e.getMessage());
		}
	}

	/***
	 * 
	 * @param storeId
	 * @param sku
	 * @param productName
	 * @param price
	 * @param date
	 * @param pageable
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<PricingFeed>> searchPricingFeeds(@RequestParam(required = false) Integer storeId,
			@RequestParam(required = false) String sku, @RequestParam(required = false) String productName,
			@RequestParam(required = false) Long price, @RequestParam(required = false) Date date) 
	{
		List<PricingFeed> pricingFeeds = pricingFeedService.searchPricingFeeds(storeId, sku, productName, price, date);
		return ResponseEntity.ok(pricingFeeds);
	}

	/***
	 * to update a pricing record identified by its unique identifier
	 * 
	 * @param feedId
	 * @param pricingFeed
	 * @return
	 */
	@PutMapping("/pricing-records/{feedId}")
	public HttpStatus updatePricingFeed(@PathParam("feedId") int feedId, PricingFeed pricingFeed) {

		// retrieve the existing pricing feed from the database
		PricingFeed existingFeed = pricingFeedService.getPricingFeedById(feedId);

		// check if the feed exists
		if (existingFeed == null) {
			return HttpStatus.NOT_FOUND;
		}

		// update the properties of the existing feed with the new values
		existingFeed.setStoreId(pricingFeed.getStoreId());
		existingFeed.setSku(pricingFeed.getSku());
		existingFeed.setProductName(pricingFeed.getProductName());
		existingFeed.setPrice(pricingFeed.getPrice());
		existingFeed.setDate(pricingFeed.getDate());

		// update the pricing feed in the database
		pricingFeedService.updatePricingFeed(existingFeed);

		// return the updated pricing feed as a response
		return HttpStatus.OK;
	}

}
