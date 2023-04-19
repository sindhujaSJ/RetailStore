/**
 * 
 */
package com.retailstore.demo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author SJ
 *
 */
@Entity
@Table(name = "pricingfeed")
public class PricingFeed {
	
	@Id
	@GeneratedValue
    private int feedId;
    private int storeId;
    private String sku;
    private String productName;
    private Long price;
    private Date date;

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PricingFeed{" +
                "feedId=" + feedId +
                ", storeId=" + storeId +
                ", sku='" + sku + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}

