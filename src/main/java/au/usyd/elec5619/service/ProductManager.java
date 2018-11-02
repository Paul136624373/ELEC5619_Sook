package au.usyd.elec5619.service;

import java.io.Serializable;
import java.util.List;

import au.usyd.elec5619.domain.Product;

public interface ProductManager extends Serializable {
	public List<Product> getAllProducts();
	
	public void addProduct(Product oneProduct);
	
	public Product getProductByID(long productID);
	
	public List<Product> getProductBySellerID(long sellerID);
	
	public List<Product> getProductByBuyerID(long buyerID);
	
	public void deleteProductByID(long productID);
	
	public void updateProduct(long buyerID, long productID);
	
}
