package com.amaysim.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.amaysim.entity.Product;
import com.amaysim.entity.Promo;
import com.amaysim.entity.PromoResult;

/**
 * Applies the promotions to all products bought. Marked as final since we don't
 * want anyone messing with how Promos are handled
 * 
 * @author Gino
 *
 */
public final class PromoHandler {
	
	private List<Promo> promos;
	private Map<Product, Long> products;
	private String promoCode;
	
	public PromoHandler() {
		
	}
	
	public PromoHandler(List<Promo> promos, Map<Product, Long> products) {
		this.promos = promos;
		this.products = products;
	}
	
	public PromoResult calculateResult() {
		PromoResult result = new PromoResult();
		Set<Product> prods = products.keySet();
		Set<Product> handled = new HashSet<>();
		if(promos != null && !promos.isEmpty()) {
			for(Promo promo : promos) {
				if(promo.getProduct() != null && prods.contains(promo.getProduct())) {
					handled.add(promo.getProduct());
					long count = products.get(promo.getProduct());
					switch(promo.getType()) {
						case MORE_FOR_LESS :
							long div = count / promo.getCount();
							if(div > 0) {
								result.addTotalCost(promo.getProduct().getPrice() * div * promo.getEquivalent());
							} else {
								result.addTotalCost(promo.getProduct().getPrice() * count);
							}
							result.addProduct(promo.getProduct(), count);
							break;
						case BULK_DISCOUNT:
							//Remember for bulk discount count is the MINIMUM number to buy
							if(count >= promo.getCount()) {
								result.addTotalCost(promo.getDiscountedAmount() * count);
							} else {
								result.addTotalCost(promo.getProduct().getPrice() * count);
							}
							result.addProduct(promo.getProduct(), count);
							break;
						case FREEBIES:
							result.addTotalCost(promo.getProduct().getPrice() * count);
							result.addProduct(promo.getProduct(), count);
							result.addProduct(promo.getFreebie(), count);
							break;
					}
				} else if(promo.getType().equals(Promo.Type.PROMO_CODE)) {
					if(promoCode != null && promoCode.equals(promo.getPromoCode())) {
						result.setDiscount(promo.getDiscountPercentage());
					}
				}
			}
		}
		// For products bought not included in the promo or if there's no promo at all
		for(Product product : prods) {
			if(!handled.contains(product)) {
				long count = products.get(product);
				result.addTotalCost(count * product.getPrice());
				result.addProduct(product, count);
			}
		}
		return result;
	}
	
	public void addPromo(Promo promo) {
		if(promos == null) {
			promos = new ArrayList<>();
		}
		promos.add(promo);
	}
	
	public List<Promo> getPromos() {
		return promos;
	}

	public void setPromos(List<Promo> promos) {
		this.promos = promos;
	}

	public Map<Product, Long> getProducts() {
		return products;
	}
	
	public void setProducts(Map<Product, Long> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		if(this.products == null) {
			this.products = new HashMap<>();
		}
		Long count = this.products.get(product);
		if(count == null) {
			count = 0l;
		}
		count += 1;
		this.products.put(product, count);
	}

	public String getPromoCode() {
		return promoCode;
	}
	
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

}
