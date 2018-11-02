package au.usyd.elec5619.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.usyd.elec5619.domain.Cart;
import au.usyd.elec5619.domain.Product;
import au.usyd.elec5619.domain.Record;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.CartManager;
import au.usyd.elec5619.service.ProductManager;
import au.usyd.elec5619.service.RecordManager;
import au.usyd.elec5619.service.UserManager;

@Controller
@RequestMapping(value="/shoppingCart/**")
public class ShoppingController {
	
	@Resource(name="cartManager")
	private CartManager cartManager;
	
	@Resource(name="productManager")
	private ProductManager productManager;
	
	@Resource(name="recordManager")
	private RecordManager recordManager;

	@Resource(name="userManager")
	private UserManager userManager;
	
	@RequestMapping(value="/deleteCart/{productID}", method=RequestMethod.GET)
	public String deleteCart(@PathVariable("productID") long productID,Model uiModel){
		
		this.cartManager.deleteCartByProductID(productID);

		return "redirect:/shoppingCart";
	}

	@RequestMapping(value="/selectCart/{productID}", method=RequestMethod.GET)
	public String selectCart(@PathVariable("productID") long productID,Model uiModel){
		
		this.cartManager.updateCartByProductID(productID);

		return "redirect:/shoppingCart";
	}
	
	@RequestMapping(value="/checkout1", method=RequestMethod.GET)
	public String checkout1(Model uiModel,HttpServletRequest arg0){
		
		long USERID;
		try
		{
			HttpSession session = arg0.getSession(false);
			USERID = (Long) session.getAttribute("USERID");
		}
		catch(Exception e){
			return "register";
		}
		
		User buyer = this.userManager.getUserByID(USERID);
		
		uiModel.addAttribute("Buyer", buyer);
		
		return "checkout1";
	}
	
	@RequestMapping(value="/checkout1", method=RequestMethod.POST)
	public String checkout1(HttpServletRequest httpServletRequest){

		User theUser = new User();
		
		theUser.setUserID(Long.parseLong(httpServletRequest.getParameter("userID")));
		theUser.setName(httpServletRequest.getParameter("name"));
		theUser.setEmail(httpServletRequest.getParameter("email"));
		theUser.setPassword(httpServletRequest.getParameter("password"));
		theUser.setState(httpServletRequest.getParameter("state"));
		theUser.setAddress(httpServletRequest.getParameter("address"));
		theUser.setCity(httpServletRequest.getParameter("city"));
		theUser.setPostcode(httpServletRequest.getParameter("postcode"));
		theUser.setSuburb(httpServletRequest.getParameter("suburb"));
		theUser.setTelephone(httpServletRequest.getParameter("telephone"));
					
		this.userManager.updateUser(theUser);
		
		return "redirect:/shoppingCart/checkout2";	
	}
	
	@RequestMapping(value="/checkout2", method=RequestMethod.GET)
	public String checkout2(Model uiModel,HttpServletRequest arg0){
		
		long USERID;
		try
		{
			HttpSession session = arg0.getSession(false);
			USERID = (Long) session.getAttribute("USERID");
		}
		catch(Exception e){
			return "register";
		}
		
		uiModel.addAttribute("buyerID", USERID);
		
		return "checkout2";
	}
	
	@RequestMapping(value="/checkout2", method=RequestMethod.POST)
	public String checkout2(HttpServletRequest httpServletRequest){

		long userID = Long.parseLong(httpServletRequest.getParameter("userID"));
		
		String method = httpServletRequest.getParameter("delivery");
							
		this.cartManager.updateCartByBuyer(userID,method);
		
		return "redirect:/shoppingCart/checkout3";	
	}
	
	@RequestMapping(value="/checkout3", method=RequestMethod.GET)
	public String checkout3(Model uiModel,HttpServletRequest arg0){
		
		long USERID;
		try
		{
			HttpSession session = arg0.getSession(false);
			USERID = (Long) session.getAttribute("USERID");
		}
		catch(Exception e){
			return "register";
		}
		
		uiModel.addAttribute("buyerID", USERID);
		
		return "checkout3";
	}
	
	@RequestMapping(value="/checkout3", method=RequestMethod.POST)
	public String checkout3(HttpServletRequest httpServletRequest){

		return "redirect:/shoppingCart/checkout4";	
	}
	
	@RequestMapping(value="/checkout4", method=RequestMethod.GET)
	public String checkout4(Model uiModel,HttpServletRequest arg0){
		
		long USERID;
		try
		{
			HttpSession session = arg0.getSession(false);
			USERID = (Long) session.getAttribute("USERID");
		}
		catch(Exception e){
			return "register";
		}
		
		uiModel.addAttribute("buyerID", USERID);
				
		List<Cart> carts = this.cartManager.getCartsByBuyer(USERID);
		List<Cart> cartDisplay = new ArrayList<Cart>();
		double total = 0;
		double fee = 0;
		String method = "";
		double alltotal = 0;
		String product = "";
		String address = "";
		
		for(Cart oneCart : carts)
		{
			Product oneProduct = this.productManager.getProductByID(oneCart.getProductID());
			if(oneProduct.getBuyerID() == -1)
			{
				if(oneCart.isChoose())
				{
					cartDisplay.add(oneCart);
					total = total + oneCart.getProductPrice();
					method = oneCart.getMethod();
					if(product.equals(""))
					{
						product = Long.toString(oneCart.getProductID());
					}
					else
					{
						product = product + ";" + Long.toString(oneCart.getProductID());
					}
				}
			}	
		}
		
		if(method.equals("face"))
		{
			fee = 0;
		}
		if(method.equals("post"))
		{
			if(total > 200)
				fee = 0;
			else
				fee = 10;
		}	
		if(method.equals("express"))
		{
			if(total > 300)
				fee = 0;
			else
				fee = 15;
		}
			
		alltotal = total + fee;
		
		User buyer = this.userManager.getUserByID(USERID);
		address = buyer.getAddress() + ", " + buyer.getSuburb() + ", " + buyer.getCity() + ", " + buyer.getState() + ", " + buyer.getPostcode();

		String date = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date());
		
		uiModel.addAttribute("shoppingCart", cartDisplay);
		uiModel.addAttribute("fee",fee);
		uiModel.addAttribute("total", total);
		uiModel.addAttribute("alltotal", alltotal);
		uiModel.addAttribute("product", product);
		uiModel.addAttribute("address", address);
		uiModel.addAttribute("date", date);
		
		return "checkout4";
	}
	
	@RequestMapping(value="/checkout4", method=RequestMethod.POST)
	public String orderResult(HttpServletRequest httpServletRequest){

		long userID = Long.parseLong(httpServletRequest.getParameter("userID"));
		String date = httpServletRequest.getParameter("date");
		String totalPrice = httpServletRequest.getParameter("alltotal");
		String productID = httpServletRequest.getParameter("product");
		String address = httpServletRequest.getParameter("address");
		
		Record theRecord = new Record(date,totalPrice,userID,productID,address);
		
		this.recordManager.addRecord(theRecord);
		
		long recordID = -1;
		
		List<Record> records = this.recordManager.getAllRecords();
		for(Record oneRecord : records)
		{
			if(oneRecord.getDate().equals(date) && oneRecord.getBuyerID() == userID)
			{
				recordID = oneRecord.getRecordID();
			}
		}
		List<Cart> carts = this.cartManager.getCartsByBuyer(userID);
		for(Cart oneCart : carts)
		{			
			if(oneCart.isChoose())
			{
				this.cartManager.deleteCartByProductID(oneCart.getProductID());
				this.productManager.updateProduct(userID, oneCart.getProductID());
			}				
		}

		return "redirect:/shoppingCart/orderResult/" + recordID;	
	}
	
	@RequestMapping(value="/orderResult/{recordID}", method=RequestMethod.GET)
	public String orderResult(@PathVariable("recordID") long recordID,Model uiModel,HttpServletRequest arg0){

		try
		{
			HttpSession session = arg0.getSession(false);
			long USERID = (Long) session.getAttribute("USERID");
		}
		catch(Exception e){
			return "register";
		}
		
		uiModel.addAttribute("recordID", recordID);
		
		Record theRecord = this.recordManager.getRecordByID(recordID);
		List<Product> productDisplay = new ArrayList<Product>();
		String[] products = theRecord.getProductID().split(";");
		double total = 0;
		double fee = 0;
		for(int i=0; i< products.length; i++)
		{
			long productID = Long.parseLong(products[i]);
			productDisplay.add(this.productManager.getProductByID(productID));
			total = total + this.productManager.getProductByID(productID).getPrice();
		}
		
		fee = Double.parseDouble(theRecord.getTotalPrice()) - total;	
		
		uiModel.addAttribute("products", productDisplay);
		uiModel.addAttribute("fee",fee);
		uiModel.addAttribute("total", total);
		uiModel.addAttribute("alltotal", Double.parseDouble(theRecord.getTotalPrice()));
		uiModel.addAttribute("address", theRecord.getAddress());
		uiModel.addAttribute("date", theRecord.getDate());
		
		return "orderResult";
	}
	
	
	
}
