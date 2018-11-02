package au.usyd.elec5619.domain;

import junit.framework.TestCase;

import au.usyd.elec5619.domain.Cart;

public class CartTest extends TestCase{
	
	private Cart cart;
	
	protected void setUp() throws Exception {
        cart = new Cart();
    }
	
	public void testSetAndGetCartId() {
        long testCartID = 1;
        assertEquals(0, 0, 0);    
        cart.setCartID(testCartID);
        assertEquals(testCartID, cart.getCartID(), 0);
    }
	
	public void testSetAndGetProductId() {
        long testProductID = 1;
        assertEquals(0, 0, 0);    
        cart.setProductID(testProductID);
        assertEquals(testProductID, cart.getProductID(), 0);
    }

    public void testSetAndGetProductName() {
    	 String testProductName = "aName";
         assertNull(cart.getProductName());
         cart.setProductName(testProductName);
         assertEquals(testProductName, cart.getProductName());
    }
    
    public void testSetAndGetProductImg() {
   	 String testProductImg = "aImg";
        assertNull(cart.getProductImg());
        cart.setProductImg(testProductImg);
        assertEquals(testProductImg, cart.getProductImg());
   }
    
	public void testSetAndGetProductPrice() {
        long testProductPrice = 1;
        assertEquals(0, 0, 0);    
        cart.setProductPrice(testProductPrice);
        assertEquals(testProductPrice, cart.getProductPrice(), 0);
    }
	
	public void testSetAndGetBuyerId() {
        long testBuyerID = 1;
        assertEquals(0, 0, 0);    
        cart.setBuyerID(testBuyerID);
        assertEquals(testBuyerID, cart.getBuyerID(), 0);
    }
	
	public void testSetAndGetMethod() {
	   	 String testMethod = "aMethod";
	     assertNull(cart.getMethod());
	     cart.setMethod(testMethod);
	     assertEquals(testMethod, cart.getMethod());
	}
	
	public void testSetAndGetChoose() {
	   	 boolean testChoose = true;
	     assertFalse(cart.isChoose());
	     cart.setChoose(testChoose);
	     assertEquals(testChoose, cart.isChoose());
	} 

}
