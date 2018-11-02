package au.usyd.elec5619.service;

import java.io.Serializable;
import java.util.List;

import au.usyd.elec5619.domain.Cart;

public interface CartManager extends Serializable{
	
	public List<Cart> getAllCarts();
	
	public void addCart(Cart oneCart);
	
	public List<Cart> getCartsByBuyer(long buyerID);
	
	public void deleteCartByProductID(long productID);
	
	public void updateCartByProductID(long productID);
	
	public void updateCartByBuyer(long buyerID, String method);

}
