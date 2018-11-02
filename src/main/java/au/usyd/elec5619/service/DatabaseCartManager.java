package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.domain.Cart;

@Service(value="cartManager")
@Transactional
public class DatabaseCartManager implements CartManager{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Cart> getAllCarts() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("FROM Cart").list();
	}

	@Override
	public void addCart(Cart oneCart) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(oneCart);
	}
	
	@Override
	public List<Cart> getCartsByBuyer(long buyerID)
	{
		List<Cart> carts = new ArrayList<Cart>();
		List<Cart> allCarts = new ArrayList<Cart>();
		allCarts = sessionFactory.getCurrentSession().createQuery("FROM Cart").list();
	
		for(Cart oneCart : allCarts) 
		{
			if(oneCart.getBuyerID() == (buyerID))
			{
				carts.add(oneCart);
			}
		}
		
		return carts;
	}
	
	public void updateCartByProductID(long productID)
	{
		Session currentSession = this.sessionFactory.getCurrentSession();
		List<Cart> allCarts = new ArrayList<Cart>();
		allCarts = sessionFactory.getCurrentSession().createQuery("FROM Cart").list();
	
		for(Cart oneCart : allCarts) 
		{
			if(oneCart.getProductID() == (productID))
			{
				if(!oneCart.isChoose())
				{
					oneCart.setChoose(true);
				}
				else
				{
					oneCart.setChoose(false);
				}
				
				currentSession.merge(oneCart);
			}
		}
	}
	
	@Override
	public void updateCartByBuyer(long buyerID, String method) {
		
		Session currentSession = this.sessionFactory.getCurrentSession();
		List<Cart> allCarts = new ArrayList<Cart>();
		allCarts = sessionFactory.getCurrentSession().createQuery("FROM Cart").list();
	
		for(Cart oneCart : allCarts) 
		{
			if(oneCart.getBuyerID() == (buyerID) && oneCart.isChoose())
			{
				oneCart.setMethod(method);
				currentSession.merge(oneCart);
			}
		}
		
	}
	
	public void deleteCartByProductID(long productID)
	{
		Session currentSession = this.sessionFactory.getCurrentSession();
		List<Cart> allCarts = new ArrayList<Cart>();
		allCarts = sessionFactory.getCurrentSession().createQuery("FROM Cart").list();
	
		for(Cart oneCart : allCarts) 
		{
			if(oneCart.getProductID() == (productID))
			{
				currentSession.delete(oneCart);
			}
		}
		
	}

}

