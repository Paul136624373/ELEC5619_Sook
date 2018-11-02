package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;

import au.usyd.elec5619.domain.Product;
import au.usyd.elec5619.service.SimpleProductManager;
import junit.framework.TestCase;

public class SimpleProductManagerTest extends TestCase{
	
	private SimpleProductManager productManager;
	private List<Product> products = new ArrayList<Product>();
	
	protected void setUp() throws Exception {
		productManager = new SimpleProductManager();
		
		Product oneProduct = new Product();
		oneProduct.setSellerID(1);
		oneProduct.setBuyerID(-1);
		oneProduct.setName("Tao");
		oneProduct.setPrice(998);
		products.add(oneProduct);
		
		oneProduct = new Product();
		oneProduct.setProductID(2);
		oneProduct.setSellerID(2);
		oneProduct.setBuyerID(-1);
		oneProduct.setName("Joker");
		oneProduct.setPrice(666);
		products.add(oneProduct);
		
		oneProduct = new Product();
		oneProduct.setSellerID(3);
		oneProduct.setBuyerID(1);
		oneProduct.setName("Tche");
		oneProduct.setPrice(333);
		products.add(oneProduct);
		
		oneProduct = new Product();
		oneProduct.setSellerID(4);
		oneProduct.setBuyerID(-1);
		oneProduct.setName("jojo");
		products.add(oneProduct);

		productManager.setAllProducts(products);
		
	}
	
	public void testGetAllProducts() {
		products = productManager.getAllProducts();
		assertEquals(4,productManager.getAllProducts().size());
	}
	
	public void testAddProduct() {
		Product oneProduct = new Product();
		oneProduct = new Product();
		oneProduct.setSellerID(5);
		oneProduct.setBuyerID(-1);
		oneProduct.setName("taotao");
		productManager.addProduct(oneProduct);
		assertEquals(5,productManager.getAllProducts().size());
	}
	
	public void testGetProductByID() {
		Product product = new Product();
		product = productManager.getProductByID(2);
		assertEquals("Joker",product.getName());
	}
	
	public void testGetProductBySellerID() {
		List<Product> products = new ArrayList<Product>();
		products = productManager.getProductBySellerID(1);
		assertEquals(1 ,products.size());
	}

	public void testGetProductByBuyerID() {
		List<Product> products = new ArrayList<Product>();
		products = productManager.getProductByBuyerID(1);
		assertEquals(1 ,products.size());
	}
	
	public void testDeleteProductByIDyerID() {
		 productManager.deleteProductByID(4);
		 assertEquals(4,productManager.getAllProducts().size());
	}
	
	
}
