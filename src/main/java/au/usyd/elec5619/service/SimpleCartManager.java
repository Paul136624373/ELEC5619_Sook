package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;

import au.usyd.elec5619.domain.Cart;
import au.usyd.elec5619.domain.Product;
import au.usyd.elec5619.domain.Record;

public class SimpleCartManager implements CartManager{
	
	private List<Cart> allCarts;
	
	public List<Cart> getAllCarts(){
		return allCarts;
	}
	
	public List<Cart> getCartsByBuyer(long buyerID){
		
		List<Cart> carts = new ArrayList<Cart>();
		
		for(Cart oneCart : allCarts) 
		{
			if(oneCart.getBuyerID() == (buyerID))
			{
				carts.add(oneCart);
			}
		}
		
		return carts;
	}
	
	@Override
	public void addCart(Cart oneCart) {
		allCarts.add(oneCart);
		
	}
	
	@Override
	public void deleteCartByProductID(long productID) {
		
		List<Cart> newCartList=new ArrayList<Cart>();
		
		for(Cart oneCart : allCarts) 
		{
			if(oneCart.getProductID() != productID)
			{
				newCartList.add(oneCart);
			}
		}
		
		allCarts=newCartList;
	}
	
	@Override
	public void updateCartByProductID(long productID) {
		for(Cart oneCart : allCarts) 
		{
			if(oneCart.getProductID() == productID)
			{
				if(!oneCart.isChoose())
				{
					oneCart.setChoose(true);
				}
				else
				{
					oneCart.setChoose(false);
				}
			}
		}
	}
	
	@Override
	public void updateCartByBuyer(long buyerID, String method) {
		for(Cart oneCart : allCarts) 
		{
			if(oneCart.getBuyerID() == (buyerID) && oneCart.isChoose())
			{
				oneCart.setMethod(method);
			}
		}		
	}
	
	public void setCarts(List<Cart> allCarts) {
		this.allCarts = allCarts;		
	}

}
