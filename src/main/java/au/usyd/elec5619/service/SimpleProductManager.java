package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;

import au.usyd.elec5619.domain.Product;

public class SimpleProductManager implements ProductManager{
	
	private List<Product> allProducts;
	
	public List<Product> getAllProducts(){
		return allProducts;
	}
	
	public void addProduct(Product oneProduct) {
		allProducts.add(oneProduct);
	}
	
	public void setAllProducts(List<Product> allProducts) {
		this.allProducts=allProducts;
	}
	
	public Product getProductByID(long productID){
			
			Product theProduct = new Product();
			
			for(Product oneProduct : allProducts) 
			{
				if(oneProduct.getProductID() == productID)
				{
					theProduct = oneProduct;
				}
			}
			
			return theProduct;
		}
		
	public List<Product> getProductBySellerID(long SellerID){
		
		List<Product> theProduct = new ArrayList<Product>();
		
		for(Product oneProduct : allProducts) 
		{
			if(oneProduct.getSellerID() == (SellerID))
			{
				theProduct.add(oneProduct);
			}
		}
		
		return theProduct;
	}
	
	public List<Product> getProductByBuyerID(long BuyerID){
			
		List<Product> theProduct = new ArrayList<Product>();
			
			for(Product oneProduct : allProducts) 
			{
				if(oneProduct.getBuyerID() == (BuyerID))
				{
					theProduct.add(oneProduct);
				}
			}
			
			return theProduct;
		}
	
	public void deleteProductByID(long productID) {
			
			for(Product oneProduct : allProducts) 
			{
				if(oneProduct.getProductID() == productID)
				{
					allProducts.remove(oneProduct);
				}
			}
		}
	
	public void updateProduct(long buyerID, long productID)
	{
		for(Product oneProduct: allProducts)
		{
			if(oneProduct.getProductID() == productID)
			{
				oneProduct.setBuyerID(buyerID);
			}
		}
	}
	
	
}
