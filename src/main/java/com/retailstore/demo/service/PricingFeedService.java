/**
 * 
 */
package com.retailstore.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.retailstore.demo.domain.PricingFeed;
import com.retailstore.demo.repository.PricingFeedRepository;

/**
 * @author SJ
 *
 */
@Service
public class PricingFeedService {

	@Autowired
	private PricingFeedRepository pricingFeedRepository;
	
	
	/***
	 * 
	 * @param file
	 * @throws IOException
	 */
	public void uploadPricingFeeds(MultipartFile file) throws IOException {
		List<PricingFeed> pricingFeeds = new ArrayList<>();

		try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			CsvToBean<PricingFeed> csvToBean = new CsvToBeanBuilder<PricingFeed>(reader).withType(PricingFeed.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			pricingFeeds = csvToBean.parse();
		}

		pricingFeedRepository.saveAll(pricingFeeds);
	}
	/***
	 * 
	 * @param feedId
	 * @param storeId
	 * @param sku
	 * @param productName
	 * @param price
	 * @param date
	 * @return
	 */
	public List<PricingFeed> searchPricingFeeds(Integer storeId, String sku, String productName,
			Long price, Date date) {
        return pricingFeedRepository.findByColumns(storeId, sku, productName, price, date);
    }


	/***
	 * 
	 * @param feedId
	 * @return
	 */
	public PricingFeed getPricingFeedById(int feedId) {
		return pricingFeedRepository.findByFeedId(feedId);
	}
	/***
	 * 
	 * @param pricingFeed
	 */
	public void updatePricingFeed(PricingFeed pricingFeed) {

		pricingFeedRepository.save(pricingFeed);
	}

}
