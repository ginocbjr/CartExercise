package com.amaysim.entity;

import java.util.HashMap;
import java.util.Map;

public class PromoResult {

	/**
	 * Total cost
	 */
	private double totalCost;
	/**
	 * Product bought with their corresponding count
	 */
	private Map<Product, Long> products;
	/**
	 * The discount if any
	 */
	private double discount;

	public void addTotalCost(double cost) {
		this.totalCost += cost;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * The final cost which include the discount
	 * 
	 * @return
	 */
	public double getFinalCost() {
		return (1.0 - discount) * totalCost;
	}

	public void addProduct(Product product, long additional) {
		if (this.products == null) {
			this.products = new HashMap<>();
		}
		Long count = products.get(product);
		if (count == null) {
			count = 0l;
		}
		count += additional;
		products.put(product, count);
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Map<Product, Long> getProducts() {
		return products;
	}

}
