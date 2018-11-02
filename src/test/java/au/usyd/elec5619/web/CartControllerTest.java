package au.usyd.elec5619.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import org.springframework.web.servlet.ModelAndView;
import au.usyd.elec5619.domain.Cart;
import au.usyd.elec5619.domain.Product;
import au.usyd.elec5619.service.SimpleCartManager;
import au.usyd.elec5619.service.SimpleProductManager;

public class CartControllerTest extends TestCase
{
	public void testHandleRequestView() throws Exception
	{
		SimpleProductManager simpleProduct= new SimpleProductManager();
		Product aProduct= new Product("a",1,"a",1,-1,"a","a","a","a");
		aProduct.setProductID(666);
		ArrayList<Product> testProducts=new ArrayList<Product>();
		testProducts.add(aProduct);
		simpleProduct.setAllProducts(testProducts);
		
		SimpleCartManager simpleCart= new SimpleCartManager();
		Cart aCart= new Cart(666,"name","img",11,111,false,"a");
		ArrayList<Cart> testCarts=new ArrayList<Cart>();
		testCarts.add(aCart);
		simpleCart.setCarts(testCarts);
		simpleCart.getCartsByBuyer(111);
				
		CartController controller = new CartController();
		controller.setCartManager(simpleCart);
		controller.setProductManager(simpleProduct);
		ModelAndView modelAndView = controller.handleRequest(null, null);
		assertEquals("register",modelAndView.getViewName());
		assertNotNull(modelAndView.getModel());

		/*
		Map modelMap = (Map) modelAndView.getModel().get("cartModel");
		List<Cart> cartValue = (List<Cart>) modelMap.get("shoppingCart");
		assertNotNull(cartValue);
		*/
		
	}
}
