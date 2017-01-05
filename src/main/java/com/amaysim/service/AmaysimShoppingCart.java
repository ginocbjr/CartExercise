package com.amaysim.service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amaysim.entity.Product;
import com.amaysim.entity.PromoResult;

public class AmaysimShoppingCart implements ShoppingCart {
	
	/**
	 * List of products bought
	 */
	private List<Product> products;
	
	/**
	 * The promo code
	 */
	private String promoCode;
	
	/**
	 * PromoHandler instance that will handle the promos
	 */
	private PromoHandler promoHandler;

	public AmaysimShoppingCart(PromoHandler promoHandler) {
		this.promoHandler = promoHandler;
	}

	@Override
	public Map<Product, Long> getItems() {
		PromoResult result = getPromoResult();
		return result.getProducts();
	}

	@Override
	public double getTotalCost() {
		PromoResult result = getPromoResult();
		return result.getFinalCost();
	}
	
	private PromoResult getPromoResult() {
		promoHandler.setProducts(new HashMap<>());
		promoHandler.setPromoCode(promoCode);
		for(Product product : products) {
			promoHandler.addProduct(product);
		}
		PromoResult result = promoHandler.calculateResult();
		return result;
	}

	@Override
	public void add(Product product) {
		if(products == null) {
			products = new ArrayList<>();
		}
		products.add(product);
	}

	@Override
	public void add(Product product, String promoCode) {
		if(products == null) {
			products = new ArrayList<>();
		}
		products.add(product);
		this.promoCode = promoCode;
	}

}
