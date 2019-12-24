package com.sevi.sptingtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sevi.sptingtest.Product;

public class ProductServiceControllerTest extends AbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getProductsList() throws Exception {
//      String uri = "/grproduct/5001";
		String uri = "/grproducts";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Product[] productlist = super.mapFromJson(content, Product[].class);
		assertTrue(productlist.length > 0);
	}

	@Test
	public void getProducById() throws Exception {
		String uri = "/grproduct/5001";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Product product = super.mapFromJson(content, Product.class);
		assertEquals("5001", product.getId());
		assertEquals("Coin", product.getName());
		assertTrue("Not equals", 1000.99 == product.getPrice());
	}

	@Test
	public void priceIncreaseCheck() throws Exception {
		String uri = "/grproduct/5003";
		for (int i = 0; i < ProductServiceControllerGildedRose.PRICE_INCREASE_LIMIT; i++) {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();

			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);
			String content = mvcResult.getResponse().getContentAsString();
			Product product = super.mapFromJson(content, Product.class);
			assertEquals("5003", product.getId());
			assertEquals("Emerald pendant", product.getName());
			assertTrue("Not equals", 3000 == product.getPrice());
		}
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Product product = super.mapFromJson(content, Product.class);
		assertEquals("5003", product.getId());
		assertEquals("Emerald pendant", product.getName());
		assertTrue("Not equals", 3300 == product.getPrice());
	}
// TODO in progress
//   @Test
//   public void createProduct() throws Exception {
//      String uri = "/products";
//      Product product = new Product();
//      product.setId("5");
//      product.setName("Product001");
//      String inputJson = super.mapToJson(product);
//      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//         .contentType(MediaType.APPLICATION_JSON_VALUE)
//         .content(inputJson)).andReturn();
//      
//      int status = mvcResult.getResponse().getStatus();
//      assertEquals(201, status);
//      String content = mvcResult.getResponse().getContentAsString();
//      assertEquals(content, "Product is created successfully");
//   }
//   @Test
//   public void updateProduct() throws Exception {
//      String uri = "/products/2";
//      Product product = new Product();
//      product.setPrice(500.77);
//      String inputJson = super.mapToJson(product);
//      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
//         .contentType(MediaType.APPLICATION_JSON_VALUE)
//         .content(inputJson)).andReturn();
//      
//      int status = mvcResult.getResponse().getStatus();
//      assertEquals(200, status);
//      String content = mvcResult.getResponse().getContentAsString();
//      assertEquals(content, "Product is updated successsfully");
//   }
//   @Test
//   public void deleteProduct() throws Exception {
//      String uri = "/products/2";
//      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
//      int status = mvcResult.getResponse().getStatus();
//      assertEquals(200, status);
//      String content = mvcResult.getResponse().getContentAsString();
//      assertEquals(content, "Product is deleted successsfully");
//   }
}
