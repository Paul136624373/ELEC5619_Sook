package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import junit.framework.TestCase;
import au.usyd.elec5619.domain.Cart;
import au.usyd.elec5619.domain.Product;

public class SimpleCartManagerTest extends TestCase{
	
	private SimpleCartManager cartManager;
	
	private List<Cart> carts;
	
	private static int CART_COUNT = 1;
	
    private long cartID=1;
	private long productID=1;
	private String productName="book";
	private String productImg="book";
	private double productPrice=1;
	private long buyerID=1;
	private boolean choose=true;
	private String method="book";
	
    protected void setUp() throws Exception {
        cartManager = new SimpleCartManager();
        carts = new ArrayList<Cart>();
        
        Cart cart = new Cart();
        cart.setCartID(1);
        cart.setProductID(productID);
        cart.setProductName(productName);
        cart.setProductImg(productImg);
        cart.setProductPrice(productPrice);
        cart.setBuyerID(buyerID);
        cart.setChoose(choose);
        cart.setMethod(method);
        
        carts.add(cart);
        
        cartManager.setCarts(carts);
    }
    
    public void testGetAllCartsWithNoCarts() {
        cartManager = new SimpleCartManager();
        assertNull(cartManager.getAllCarts());
    }
	
    public void testGetAllCarts() {
        List<Cart> carts = cartManager.getAllCarts();
        assertNotNull(carts);        
        assertEquals(CART_COUNT, cartManager.getAllCarts().size());
    
        Cart cart = carts.get(0);
        assertEquals(productID, cart.getProductID());
        assertEquals(productName, cart.getProductName()); 
        assertEquals(productImg, cart.getProductImg());
        assertEquals(productPrice, cart.getProductPrice());
        assertEquals(buyerID, cart.getBuyerID());
        assertEquals(choose, cart.isChoose());
        assertEquals(method, cart.getMethod());
    } 
    
    public void testGetCartsWithBuyerID() {
    	
    	List<Cart> carts = cartManager.getCartsByBuyer(buyerID);
    	assertNotNull(carts);
    	assertEquals(CART_COUNT,cartManager.getCartsByBuyer(buyerID).size());
    	
    	Cart cart = carts.get(0);
        assertEquals(productID, cart.getProductID());
        assertEquals(productName, cart.getProductName()); 
        assertEquals(productImg, cart.getProductImg());
        assertEquals(productPrice, cart.getProductPrice());
        assertEquals(cartID, cart.getCartID());
        assertEquals(choose, cart.isChoose());
        assertEquals(method, cart.getMethod());
    	
    }
	
    public void testAddCart() {
    	
    	Cart aCart=new Cart(2,"books","books",2,2,true,"books");
    	cartManager.addCart(aCart);
    	assertEquals(2,cartManager.getAllCarts().size());
    	
    }
    
    
    public void testDeleteCartWithProductID() {
    	
    	cartManager.deleteCartByProductID(productID);
    	assertEquals(0,cartManager.getAllCarts().size());
    }
    
    public void testUpdateCartByProductID() {
    	cartManager.updateCartByProductID(productID);
    	Cart cart=cartManager.getAllCarts().get(0);
    	assertFalse(cart.isChoose());
    }
    
    public void testUpdateCartByBuyer() {
    	String newMethod="aa";
    	cartManager.updateCartByBuyer(buyerID,newMethod);
    	Cart cart=cartManager.getAllCarts().get(0);
    	assertEquals(newMethod,cart.getMethod());
    }
    

}
