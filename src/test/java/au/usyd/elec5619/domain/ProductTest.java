package au.usyd.elec5619.domain;

import static org.junit.Assert.*;

import au.usyd.elec5619.domain.Product;
import junit.framework.TestCase;

public class ProductTest extends TestCase{
	private Product product;
	
	protected void setUp() throws Exception{
		product = new Product();
	}

	public void testSetAndGetProductID() {
		long testProductID = 100;
		assertEquals(0,0,0);
		product.setProductID(testProductID);
		assertEquals(testProductID, product.getProductID(), 0);
	}
	
	public void testSetAndGetName() {
		String testName = "aName";
		assertNull(product.getName());
		product.setName(testName);
		assertEquals(testName, product.getName());
	}
	
	public void testSetAndGetPrice() {
		double testPrice = 100.00;
		assertEquals(0,0,0);
		product.setPrice(testPrice);
		assertEquals(testPrice, product.getPrice(), 0);
	}
	
	public void testSetAndGetPicture() {
		String testPicture = "aPicture";
		assertNull(product.getPicture());
		product.setPicture(testPicture);
		assertEquals(testPicture, product.getPicture());
	}
	
	public void testSetAndGetSellerID() {
		long testSellerID = 100;
		assertEquals(0,0,0);
		product.setSellerID(testSellerID);
		assertEquals(testSellerID, product.getSellerID(), 0);
	}
	
	public void testSetAndGetBuyerID() {
		long testBuyerID = 100;
		assertEquals(0,0,0);
		product.setBuyerID(testBuyerID);
		assertEquals(testBuyerID, product.getBuyerID(), 0);
	}
	
	public void testSetAndGetLocation() {
		String testLocation = "aLocation";
		assertNull(product.getLocation());
		product.setLocation(testLocation);
		assertEquals(testLocation, product.getLocation());
	}
	
	public void testSetAndGetDescription() {
		String testDescription = "aDescription";
		assertNull(product.getDescription());
		product.setDescription(testDescription);
		assertEquals(testDescription, product.getDescription());
	}
	
	public void testSetAndGetCategory() {
		String testCategory = "aCategory";
		assertNull(product.getCategory());
		product.setCategory(testCategory);
		assertEquals(testCategory, product.getCategory());
	}
	
	public void testSetAndGetMean() {
		String testMean = "aMean";
		assertNull(product.getMean());
		product.setMean(testMean);
		assertEquals(testMean, product.getMean());
	}
	
}
