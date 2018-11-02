package au.usyd.elec5619.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import au.usyd.elec5619.domain.Cart;
import au.usyd.elec5619.domain.Product;
import au.usyd.elec5619.service.CartManager;
import au.usyd.elec5619.service.ProductManager;

@Controller
@RequestMapping(value="/viewProducts/**")
public class ProductController {
	
	@Resource(name="productManager")
	private ProductManager productManager;
	@Resource(name="cartManager")
	private CartManager cartManager;
	
	public List<Product> showProducts=new ArrayList<Product>();
	protected final Log logger = LogFactory.getLog(getClass());
	public List<Product> PriceProducts=new ArrayList<Product>();
	public List<Product> CityProducts=new ArrayList<Product>();
	
	public String thelowest="lowest";
	public String thehighest="highest";
	public String theCity="      ------ City ------      ";
	
	//showProducts=this.productManager.getAllProducts();
	
	@RequestMapping(value="/viewAll")
	public String viewAll(Model uniModel,HttpServletRequest httpServletRequest) {
		
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
        
        showProducts=allProducts;
        PriceProducts=showProducts;
        CityProducts=showProducts;
        thelowest="lowest";
        thehighest="highest";
        theCity="      ------ City ------      ";
        
        return "redirect:/viewProducts";
		
	}
	
	@RequestMapping(value="/sort",method=RequestMethod.GET)
	public String sortProduct(Model uiModel,@RequestParam("sort-by") String sortBy,HttpServletRequest httpServletRequest) {
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		
		if(showProducts.isEmpty())
			showProducts=allProducts;
		
		if(sortBy.equals("lowTohigh")) {
		for(int i=0;i<showProducts.size();i++) {
			for(int j=0;j<showProducts.size();j++) {
				if(showProducts.get(i).getPrice()>showProducts.get(j).getPrice()) {
					Product x=showProducts.get(i);
					showProducts.set(i, showProducts.get(j));
					showProducts.set(j,x);
				}
			}
		}
		}
		if(sortBy.equals("highTolow")) {
			for(int i=0;i<showProducts.size();i++) {
				for(int j=0;j<showProducts.size();j++) {
					if(showProducts.get(i).getPrice()<showProducts.get(j).getPrice()) {
						Product x=showProducts.get(i);
						showProducts.set(i, showProducts.get(j));
						showProducts.set(j,x);
					}
				}
			}
		}
		List<Product> seekProducts=showProducts;
		
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user",name);
		
		
		return "seekProduct";
	}
	
	@RequestMapping(value="/price", method=RequestMethod.POST)
	public String sortPrice(Model uiModel,@RequestParam("lowest") String lowest, @RequestParam("highest") String highest, HttpSession session,HttpServletRequest httpServletRequest) {
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts=new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		if(PriceProducts.isEmpty())
			PriceProducts=allProducts;
		
		for(int i=0;i<PriceProducts.size();i++) {
				if(PriceProducts.get(i).getPrice()>=Double.parseDouble(lowest) && PriceProducts.get(i).getPrice()<=Double.parseDouble(highest)) {
                     seekProducts.add(PriceProducts.get(i));
				}
		}
		
		showProducts=seekProducts;
		CityProducts=showProducts;
        thelowest=lowest;
        thehighest=highest;
		
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		
		return "seekProduct";
	}
	@RequestMapping(value="/clear/price", method=RequestMethod.GET)
	public String clearPrice(Model uiModel, HttpSession session,HttpServletRequest httpServletRequest) {
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts=new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		if(PriceProducts.isEmpty())
			PriceProducts=allProducts;
		
		seekProducts=PriceProducts;
		
		showProducts=seekProducts;
		CityProducts=showProducts;
		
        thelowest="lowest";
        thehighest="highest";
		
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		
		return "seekProduct";
	}
	
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(Model uiModel,@RequestParam("search") String keyword,HttpSession session,HttpServletRequest httpServletRequest) {
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts=new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		String[] words=keyword.trim().split(" ");
		
		for(int i=0;i<allProducts.size();i++) {
			boolean containKey=true;
			for(int j=0;j<words.length;j++) {
				if(!allProducts.get(i).getName().contains(words[j])) {
					containKey=false;
					break;
				}
			}
			if(containKey==true) {
				seekProducts.add(allProducts.get(i));
			}
		}
		
		showProducts=seekProducts;
		PriceProducts=showProducts;
		CityProducts=showProducts;
		
        thelowest="lowest";
        thehighest="highest";
        theCity="      ------ City ------      ";
		
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		
		return "seekProduct";
	}
	
	@RequestMapping(value="/city", method=RequestMethod.GET)
	public String sortCity(Model uiModel,@RequestParam("City") String city, HttpSession session,HttpServletRequest httpServletRequest) {
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts=new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		if(CityProducts.isEmpty())
			CityProducts=allProducts;
		
		for(int i=0;i<CityProducts.size();i++) {
				if(CityProducts.get(i).getLocation().equals(city)) {
                     seekProducts.add(CityProducts.get(i));
				}
		}
		
		showProducts=seekProducts;
		PriceProducts=showProducts;
		theCity=city;
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		
		return "seekProduct";
	}
	
	@RequestMapping(value="/clear/city", method=RequestMethod.GET)
	public String clearCity(Model uiModel, HttpSession session,HttpServletRequest httpServletRequest) {
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts=new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		if(CityProducts.isEmpty())
			CityProducts=allProducts;
		
		seekProducts=CityProducts;
		
		showProducts=seekProducts;
		PriceProducts=showProducts;
		theCity="      ------ City ------      ";
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		
		return "seekProduct";
	}
	
	@RequestMapping(value="/Education")
	public String seekEducation(Model uiModel,HttpServletRequest httpServletRequest){
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts = new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		
		for(Product oneProduct : allProducts)
		{
			if(oneProduct.getCategory().equals("Education"))
			{
				seekProducts.add(oneProduct);
			}
		}
		
        thelowest="lowest";
        thehighest="highest";
        theCity="      ------ City ------      ";
		
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		
		showProducts=seekProducts;
		PriceProducts=showProducts;
		CityProducts=showProducts;
		return "seekProduct";
	}
	@RequestMapping(value="/Lifestyle")
	public String seekLifestyle(Model uiModel,HttpServletRequest httpServletRequest){
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts = new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		
		for(Product oneProduct : allProducts)
		{
			if(oneProduct.getCategory().equals("Lifestyle"))
			{
				seekProducts.add(oneProduct);
			}
		}
		
        thelowest="lowest";
        thehighest="highest";
        theCity="      ------ City ------      ";
		
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		showProducts=seekProducts;
		PriceProducts=showProducts;
		CityProducts=showProducts;
		return "seekProduct";
	}
	@RequestMapping(value="/Children")
	public String seekChildren(Model uiModel,HttpServletRequest httpServletRequest){
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts = new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		
		for(Product oneProduct : allProducts)
		{
			if(oneProduct.getCategory().equals("Children"))
			{
				seekProducts.add(oneProduct);
			}
		}
		
        thelowest="lowest";
        thehighest="highest";
        theCity="      ------ City ------      ";
		
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		showProducts=seekProducts;
		PriceProducts=showProducts;
		CityProducts=showProducts;
		return "seekProduct";
	}
	@RequestMapping(value="/Science")
	public String seekScience(Model uiModel,HttpServletRequest httpServletRequest){
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts = new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		
		for(Product oneProduct : allProducts)
		{
			if(oneProduct.getCategory().equals("Science"))
			{
				seekProducts.add(oneProduct);
			}
		}
		
        thelowest="lowest";
        thehighest="highest";
        theCity="      ------ City ------      ";
		
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		showProducts=seekProducts;
		PriceProducts=showProducts;
		CityProducts=showProducts;
		return "seekProduct";
	}
	@RequestMapping(value="/Literature")
	public String seekLiterature(Model uiModel,HttpServletRequest httpServletRequest){
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts = new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		
		for(Product oneProduct : allProducts)
		{
			if(oneProduct.getCategory().equals("Literature"))
			{
				seekProducts.add(oneProduct);
			}
		}
		
        thelowest="lowest";
        thehighest="highest";
        theCity="      ------ City ------      ";
		
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		showProducts=seekProducts;
		PriceProducts=showProducts;
		CityProducts=showProducts;
		return "seekProduct";
	}
	@RequestMapping(value="/Politics")
	public String seekPolitics(Model uiModel,HttpServletRequest httpServletRequest){
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts = new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		
		for(Product oneProduct : allProducts)
		{
			if(oneProduct.getCategory().equals("Politics"))
			{
				seekProducts.add(oneProduct);
			}
		}
		
        thelowest="lowest";
        thehighest="highest";
        theCity="      ------ City ------      ";
		
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		showProducts=seekProducts;
		PriceProducts=showProducts;
		CityProducts=showProducts;
		return "seekProduct";
	}
	@RequestMapping(value="/Fictions")
	public String seekFiction(Model uiModel,HttpServletRequest httpServletRequest){
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts = new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		
		for(Product oneProduct : allProducts)
		{
			if(oneProduct.getCategory().equals("Fictions"))
			{
				seekProducts.add(oneProduct);
			}
		}
		
        thelowest="lowest";
        thehighest="highest";
        theCity="      ------ City ------      ";
		
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		showProducts=seekProducts;
		PriceProducts=showProducts;
		CityProducts=showProducts;
		return "seekProduct";
	}
	@RequestMapping(value="/Comics")
	public String seekComics(Model uiModel,HttpServletRequest httpServletRequest){
        
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts = new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		
		for(Product oneProduct : allProducts)
		{
			if(oneProduct.getCategory().equals("Comics"))
			{
				seekProducts.add(oneProduct);
			}
		}
		
        thelowest="lowest";
        thehighest="highest";
        theCity="      ------ City ------      ";
		
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		showProducts=seekProducts;
		PriceProducts=showProducts;
		CityProducts=showProducts;
		return "seekProduct";
	}
	@RequestMapping(value="/Businesses")
	public String seekBusiness(Model uiModel,HttpServletRequest httpServletRequest){
		
        long name;
        
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
		
		List<Product> seekProducts = new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		
		for(Product oneProduct : allProducts)
		{
			if(oneProduct.getCategory().equals("Businesses"))
			{
				seekProducts.add(oneProduct);
			}
		}
		
        thelowest="lowest";
        thehighest="highest";
        theCity="      ------ City ------      ";
		
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		showProducts=seekProducts;
		PriceProducts=showProducts;
		CityProducts=showProducts;
		return "seekProduct";
	}
	
	@RequestMapping(value="/view/{productID}",method=RequestMethod.GET)
	public String addCart(Model uiModel,@PathVariable("productID") long productID,HttpServletRequest httpServletRequest ){
		
		long name;
		
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			return "redirect:/register ";
		}
		
		boolean containProduct=false;
		
		for(Cart oneCart : this.cartManager.getAllCarts())
		{
			if(oneCart.getProductID()==productID)
			{
				containProduct=true;
				break;
			}
		}
		
		if(containProduct==false) {		
			Cart newCart = new Cart();
			Product newProduct=this.productManager.getProductByID(productID);
			
			newCart.setCartID(this.cartManager.getAllCarts().size());
			newCart.setProductID(productID);
			newCart.setBuyerID(name);
			newCart.setChoose(false);;
			newCart.setProductImg(newProduct.getPicture());
			newCart.setProductName(newProduct.getName());
			newCart.setProductPrice(newProduct.getPrice());
			
			this.cartManager.addCart(newCart);		
		}
		
		return "redirect:/viewProducts";
	}
	
	@RequestMapping(value="/seek/{productID}",method=RequestMethod.GET)
	public String addCartx(Model uiModel,@PathVariable("productID") long productID,HttpServletRequest httpServletRequest){
		
		
		long name;
		
		try
		{
			HttpSession sessions = httpServletRequest.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			return "redirect:/register";
		}
		
		
		boolean containProduct=false;
		
		List<Product> seekProducts = new ArrayList<Product>();
		List<Product> allProducts=new ArrayList<Product>();
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
		
		for(Cart oneCart : this.cartManager.getAllCarts())
		{
			if(oneCart.getProductID()==productID)
			{
				containProduct=true;
				break;
			}
		}
		
		if(containProduct==false) {		
			Cart newCart = new Cart();
			Product newProduct=this.productManager.getProductByID(productID);
			
			newCart.setCartID(this.cartManager.getAllCarts().size());
			newCart.setProductID(productID);
			newCart.setBuyerID(name);
			newCart.setChoose(false);;
			newCart.setProductImg(newProduct.getPicture());
			newCart.setProductName(newProduct.getName());
			newCart.setProductPrice(newProduct.getPrice());
			
			this.cartManager.addCart(newCart);		
		}
		
		seekProducts=showProducts;
		uiModel.addAttribute("seekProducts", seekProducts);
		uiModel.addAttribute("allSize",allProducts.size());
		uiModel.addAttribute("lowest", thelowest);
		uiModel.addAttribute("highest",thehighest);
		uiModel.addAttribute("city", theCity);
		uiModel.addAttribute("user", name);
		
		return "seekProduct";
	}

}
