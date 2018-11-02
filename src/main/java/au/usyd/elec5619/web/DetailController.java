package au.usyd.elec5619.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.usyd.elec5619.domain.Product;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.UserManager;
import au.usyd.elec5619.service.ProductManager;

@Controller
@RequestMapping(value="/detail/**")

public class DetailController {
	@Resource(name="productManager")
	private ProductManager productManager;
	
	@Resource(name="userManager")
	private UserManager userManager;
	
	@RequestMapping(value="/detail/{productID}" , method=RequestMethod.GET)
	public String showDetail(@PathVariable("productID") long productID,Model uiModel, HttpServletRequest httpServletRequest){	
		long userID;
		try
		{
			HttpSession session = httpServletRequest.getSession(false);
			userID = (Long) session.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(userID);
		}
		catch(Exception e){
			userID = 0;
		}
		
		if(userID != 0)
		{
			for(User oneUser : this.userManager.getAllUser())
			{
				if(oneUser.getUserID() == userID)
				{
					uiModel.addAttribute("userName", oneUser.getName());
				}
			}
		}


		
		Product oneProduct = this.productManager.getProductByID(productID);
		
		uiModel.addAttribute("oneProduct", oneProduct);
		
		return "detail";
	}
	
}
	
