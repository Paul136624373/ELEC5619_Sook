package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.domain.Product;


@Service(value="productManager")
@Transactional
public class DatabaseProductManager implements ProductManager {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("FROM Product").list();
	}
	
	@Override
	public void addProduct(Product oneProduct) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(oneProduct);
	}
	
	@Override
	public Product getProductByID(long productID) {
		// TODO Auto-generated method stub
		Session currentSession = this.sessionFactory.getCurrentSession();
		Product theProduct = (Product) currentSession.get(Product.class, productID);
		return theProduct;
	}
	
	@Override
	public List<Product> getProductBySellerID(long SellerID) {
		// TODO Auto-generated method stub
		List<Product> theProduct = new ArrayList<Product>();
		List<Product> allProducts = sessionFactory.getCurrentSession().createQuery("FROM Product").list();
		for(Product oneProduct : allProducts) 
		{
			if(oneProduct.getSellerID() == (SellerID))
			{
				theProduct.add(oneProduct);
			}
		}
		return theProduct;
	}
	
	@Override
	public List<Product> getProductByBuyerID(long BuyerID) {
		// TODO Auto-generated method stub
		List<Product> theProduct = new ArrayList<Product>();
		List<Product> allProducts = sessionFactory.getCurrentSession().createQuery("FROM Product").list();
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
		// TODO Auto-generated method stub
		Session currentSession = this.sessionFactory.getCurrentSession();
		Product theProduct = (Product) currentSession.get(Product.class, productID);
		currentSession.delete(theProduct);
	}
	
	public void updateProduct(long buyerID, long productID)
	{
		Session currentSession = this.sessionFactory.getCurrentSession();
		Product theProduct = (Product) currentSession.get(Product.class, productID);
		theProduct.setBuyerID(buyerID);
		currentSession.merge(theProduct);
	}
}
