package au.usyd.elec5619.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import au.usyd.elec5619.domain.Record;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.RecordManager;
import au.usyd.elec5619.domain.Product;
import au.usyd.elec5619.service.ProductManager;
import au.usyd.elec5619.service.UserManager;

@Controller
@RequestMapping(value="/myBooks/**")


	
public class mySoldController {
	@Resource(name="recordManager")
	private RecordManager recordManager;
	@Resource(name="productManager")
	private ProductManager productManager;
	@Resource(name="userManager")
	private UserManager userManager;
	
	@RequestMapping(value="/myBooks/mySold", method=RequestMethod.GET)
	public String mySoldBooks(Model uiModel,HttpServletRequest httpServletRequest){
		HttpSession session = httpServletRequest.getSession();
		if(session.getAttribute("USERID")==null) {
			return "register";
		}
		else {
			long theID = (Long) session.getAttribute("USERID");
			List<Product> productSoldList = new ArrayList<Product>();
			List<Product> AllproductList = this.productManager.getAllProducts();

		for(Product temp: AllproductList) {
			if(temp.getSellerID()==theID && temp.getBuyerID()!=-1) {
				productSoldList.add(temp);
			}
		}
		uiModel.addAttribute("productSoldList", productSoldList);
		uiModel.addAttribute("userID", theID);
		return "mySold";
		}
		
	}
	@RequestMapping(value="/myBooks/myInSale", method=RequestMethod.GET)
	public String myInSaleBooks(Model uiModel,HttpServletRequest httpServletRequest){
		HttpSession session = httpServletRequest.getSession();
		if(session.getAttribute("USERID")==null) {
			return "register";
		}
		else {
			long theID = (Long) session.getAttribute("USERID");

			List<Product> productInSaleList = new ArrayList<Product>();
			List<Product> AllproductList = this.productManager.getAllProducts();

			for(Product temp: AllproductList) {
				if(temp.getSellerID()==theID && temp.getBuyerID()==-1) {
					productInSaleList.add(temp);
				}
			}
			uiModel.addAttribute("productInSaleList", productInSaleList);
			uiModel.addAttribute("userID", theID);
			return "myInSale";
		}
	}
	@RequestMapping(value="/myBooks/myInSale/delete/{productID}", method=RequestMethod.GET)
	public String DeletemyInSaleBooks(@PathVariable("productID") long productID,Model uiModel){
		Product toDelete = this.productManager.getProductByID(productID);
		long userID = toDelete.getSellerID();
		this.productManager.deleteProductByID(productID);;
		
		return "redirect:/myBooks/myInSale";
	}


}
