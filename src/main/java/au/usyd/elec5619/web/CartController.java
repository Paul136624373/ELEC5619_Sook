package au.usyd.elec5619.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import au.usyd.elec5619.domain.Cart;
import au.usyd.elec5619.domain.Product;
import au.usyd.elec5619.service.CartManager;
import au.usyd.elec5619.service.ProductManager;

public class CartController implements Controller {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private CartManager cartManager;
	private ProductManager productManager;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception{
		
		Map<String, Object> cartModel = new HashMap<String, Object>();
		
		long USERID;
		try
		{
			HttpSession session = arg0.getSession(false);
			USERID = (Long) session.getAttribute("USERID");
		}
		catch(Exception e){
			return new ModelAndView("register");
		}
		cartModel.put("buyerID",USERID);
		
		List<Cart> carts = this.cartManager.getCartsByBuyer(USERID);
		List<Cart> cartDisplay = new ArrayList<Cart>();
		double total = 0;
		
		for(Cart oneCart : carts)
		{
			Product oneProduct = this.productManager.getProductByID(oneCart.getProductID());
			if(oneProduct.getBuyerID() == -1)
			{
				cartDisplay.add(oneCart);
				if(oneCart.isChoose())
				{
					total = total + oneProduct.getPrice();
				}
			}	
		}
				
		cartModel.put("number", cartDisplay.size());
		cartModel.put("shoppingCart", cartDisplay);
		cartModel.put("total", total);
		
		return new ModelAndView("basket", "cartModel", cartModel);		
		
	}
	
	public void setCartManager(CartManager cartManager){
		this.cartManager = cartManager;
	}
	
	public void setProductManager(ProductManager productManager){
		this.productManager = productManager;
	}
	
}
