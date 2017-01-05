package com.amaysim.service;

import java.util.Map;

import com.amaysim.entity.Product;

public interface ShoppingCart {
	
	/**
	 * Get products and their count
	 * @return
	 */
	Map<Product, Long> getItems();
	
	/**
	 * Get the total cost for this shopping cart
	 * @return
	 */
	double getTotalCost();
	
	/**
	 * Add a product
	 * 
	 * @param product
	 */
	void add(Product product);
	
	/**
	 * Add a product with promo code
	 * 
	 * @param product
	 * @param promoCode
	 */
	void add(Product product, String promoCode);

}
