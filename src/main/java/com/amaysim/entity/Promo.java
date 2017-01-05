package com.amaysim.entity;

/**
 * Represents a promo. The main idea is to make the promo configurable. So you can add more
 * promos if you want.
 * 
 * @author Gino
 *
 */
public class Promo {
	
	public enum Type {
		MORE_FOR_LESS,
		BULK_DISCOUNT,
		FREEBIES,
		PROMO_CODE
	}
	
	private Product product;
	
	private Type type;
	
	private String promoCode;
	
	/**
	 * For more-for-less and freebies the number of products needed to buy,
	 * For bulk-discount the minimum number of items to buy
	 */
	private int count;
	
	/**
	 * Use by more for less
	 */
	private int equivalent;
	
	/**
	 * Discounted amount for PROMO_CODE
	 */
	private double discountedAmount;
	
	/**
	 * Used by promo code. Should be less than or equal to 1
	 */
	private double discountPercentage;
	
	/**
	 * The free product for FREEBIES promo
	 */
	private Product freebie;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public String getPromoCode() {
		return promoCode;
	}
	
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getEquivalent() {
		return equivalent;
	}

	public void setEquivalent(int equivalent) {
		this.equivalent = equivalent;
	}

	public double getDiscountedAmount() {
		return discountedAmount;
	}

	public void setDiscountedAmount(double discountedAmount) {
		this.discountedAmount = discountedAmount;
	}

	public Product getFreebie() {
		return freebie;
	}

	public void setFreebie(Product freebie) {
		this.freebie = freebie;
	}
	
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	

}
