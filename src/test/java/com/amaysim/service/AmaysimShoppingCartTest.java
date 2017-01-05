package com.amaysim.service;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amaysim.entity.Product;
import com.amaysim.entity.Promo;
import com.amaysim.entity.Promo.Type;

public class AmaysimShoppingCartTest {
	
	private PromoHandler promoHandler;
	private Product small;
	private Product medium;
	private Product large;
	private Product oneGb;
	
	@Before
	public void init() {
		promoHandler = new PromoHandler();
		
		small = new Product("ult_small", "Unlimited 1GB", 24.9);
		medium = new Product("ult_medium", "Unlimited 2GB", 29.9);
		large = new Product("ult_large", "Unlimited 5GB", 44.9);
		oneGb = new Product("1gb", "1GB Data-pack", 9.9);
		
		Promo promo1 = new Promo();
		promo1.setProduct(small);
		promo1.setType(Type.MORE_FOR_LESS);
		promo1.setCount(3);
		promo1.setEquivalent(2);
		
		Promo promo2 = new Promo();
		promo2.setProduct(large);
		promo2.setType(Type.BULK_DISCOUNT);
		promo2.setCount(4);
		promo2.setDiscountedAmount(39.9);
		
		Promo promo3 = new Promo();
		promo3.setProduct(medium);
		promo3.setType(Type.FREEBIES);
		promo3.setFreebie(oneGb);
		
		Promo promo4 = new Promo();
		promo4.setType(Type.PROMO_CODE);
		promo4.setPromoCode("I<3AMAYSIM");
		promo4.setDiscountPercentage(0.1);
		
		promoHandler.setPromos(Arrays.asList(promo1, promo2, promo3, promo4));
		
	}
	
	@Test
	public void test1() {
		AmaysimShoppingCart shoppingCart = new AmaysimShoppingCart(promoHandler);
		shoppingCart.add(small);
		shoppingCart.add(small);
		shoppingCart.add(small);
		shoppingCart.add(large);
		Assert.assertEquals(94.7, shoppingCart.getTotalCost(), 0.01);
		Assert.assertEquals(3l, shoppingCart.getItems().get(small).longValue());
		Assert.assertEquals(1l, shoppingCart.getItems().get(large).longValue());
	}
	
	@Test
	public void test2() {
		AmaysimShoppingCart shoppingCart = new AmaysimShoppingCart(promoHandler);
		shoppingCart.add(small);
		shoppingCart.add(small);
		shoppingCart.add(large);
		shoppingCart.add(large);
		shoppingCart.add(large);
		shoppingCart.add(large);
		Assert.assertEquals(209.4, shoppingCart.getTotalCost(), 0.01);
		Assert.assertEquals(2l, shoppingCart.getItems().get(small).longValue());
		Assert.assertEquals(4l, shoppingCart.getItems().get(large).longValue());
	}
	
	@Test
	public void test3() {
		AmaysimShoppingCart shoppingCart = new AmaysimShoppingCart(promoHandler);
		shoppingCart.add(small);
		shoppingCart.add(medium);
		shoppingCart.add(medium);
		Assert.assertEquals(84.7, shoppingCart.getTotalCost(), 0.01);
		Assert.assertEquals(2l, shoppingCart.getItems().get(medium).longValue());
		Assert.assertEquals(2l, shoppingCart.getItems().get(oneGb).longValue());
	}
	
	@Test
	public void test4() {
		AmaysimShoppingCart shoppingCart = new AmaysimShoppingCart(promoHandler);
		shoppingCart.add(oneGb);
		shoppingCart.add(small, "I<3AMAYSIM");
		Assert.assertEquals(31.32, shoppingCart.getTotalCost(), 0.01);
		Assert.assertEquals(1l, shoppingCart.getItems().get(small).longValue());
		Assert.assertEquals(1l, shoppingCart.getItems().get(oneGb).longValue());
	}
	
	@Test
	public void test5NoPromos() {
		AmaysimShoppingCart shoppingCart = new AmaysimShoppingCart(new PromoHandler());
		shoppingCart.add(small);
		shoppingCart.add(small);
		shoppingCart.add(small);
		Assert.assertEquals(24.9 * 3, shoppingCart.getTotalCost(), 0.01);
		Assert.assertEquals(3l, shoppingCart.getItems().get(small).longValue());
	}
	
	@Test
	public void test6NotEnoughToBeIncludedAsPromo() {
		AmaysimShoppingCart shoppingCart = new AmaysimShoppingCart(promoHandler);
		shoppingCart.add(small);
		shoppingCart.add(small);
		shoppingCart.add(large);
		shoppingCart.add(large);
		shoppingCart.add(large);
		Assert.assertEquals(184.5, shoppingCart.getTotalCost(), 0.01);
		Assert.assertEquals(2l, shoppingCart.getItems().get(small).longValue());
		Assert.assertEquals(3l, shoppingCart.getItems().get(large).longValue());
	}

}
