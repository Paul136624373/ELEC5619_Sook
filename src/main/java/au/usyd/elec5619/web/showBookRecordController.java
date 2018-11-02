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
@RequestMapping(value="/myOrders/**")

public class showBookRecordController {
	@Resource(name="recordManager")
	private RecordManager recordManager;
	@Resource(name="productManager")
	private ProductManager productManager;
	@Resource(name="userManager")
	private UserManager userManager;


	@RequestMapping(value="/myOrders", method=RequestMethod.GET)
	public String myInfo(Model uiModel,HttpServletRequest httpServletRequest){
		HttpSession session = httpServletRequest.getSession();
		if(session.getAttribute("USERID")==null) {
			return "register";
		}
		else {
			long theID = (Long) session.getAttribute("USERID");
			List<Record> allRecord = new ArrayList<Record>();
			List<Record> allRecords = this.recordManager.getAllRecords();
			for(Record temp: allRecords) {
				if(temp.getBuyerID()==theID) {
					allRecord.add(temp);
				}
			}
			uiModel.addAttribute("userID", theID);
			uiModel.addAttribute("allRecord", allRecord);
			return "myOrders";
		}
		
	}
	@RequestMapping(value="/myOrders/orderDetail/{recordID}", method=RequestMethod.GET)
	public String theOrderDetail(@PathVariable("recordID") long recordID,Model uiModel,HttpServletRequest httpServletRequest){
		HttpSession session = httpServletRequest.getSession();
		if(session.getAttribute("USERID")==null) {
			return "register";
		}
		else {
			Record theRecord = this.recordManager.getRecordByID(recordID);
			List<Product> productList = new ArrayList<Product>();
			String products = theRecord.getProductID();
			double total = 0;
			double fee = 0;
			if(products.contains(";")) {
				String[] product = products.split(";");
				for(String temp: product) {
					productList.add(this.productManager.getProductByID(Long.valueOf(temp)));
					total = total + this.productManager.getProductByID(Long.valueOf(temp)).getPrice();
				}
			}
			else {
				productList.add(this.productManager.getProductByID(Long.valueOf(products)));
				total = total + this.productManager.getProductByID(Long.valueOf(products)).getPrice();
			}
			
			fee = Double.valueOf(theRecord.getTotalPrice()) - total;
			User theBuyer = this.userManager.getUserByID(theRecord.getBuyerID());
			
			uiModel.addAttribute("theRecord",theRecord);
			uiModel.addAttribute("productList", productList);
			uiModel.addAttribute("theBuyer", theBuyer);
			uiModel.addAttribute("fee", fee);
			return "orderDetail";
		}
		
	}
	
	
	
}
