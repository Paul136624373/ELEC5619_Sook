package au.usyd.elec5619.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import au.usyd.elec5619.domain.Product;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.ProductManager;
import au.usyd.elec5619.service.UserManager;

@Controller
@RequestMapping(value="/toSell/**")

public class SellingController {
	
	@Resource(name="productManager")
	private ProductManager productManager;
	
	@Resource(name="userManager")
	private UserManager userManager;
	
	@RequestMapping(value="/toSell" , method=RequestMethod.GET)
	public String showDetail(Model uiModel, HttpServletRequest httpServletRequest){	
		
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
		else
		{
			return "redirect:/register";
		}
		
		return "toSell";
	}

	@RequestMapping(value="/selling", method=RequestMethod.POST)
	public String selling(@RequestParam(value = "picFile",required = false) MultipartFile file, HttpServletRequest httpServletRequest){
		
		String uploadRootPath = httpServletRequest.getSession().getServletContext().getRealPath("product_pic/");
		File uploadRootDir = new File(uploadRootPath);
		if(!uploadRootDir.exists()) {
			uploadRootDir.mkdirs(); 
		}
		String fileName = file.getOriginalFilename(); 
		File targetFile = new File(uploadRootPath+ fileName);
		if(fileName!=null&&fileName.length()>0){ 
			try { 
				file.transferTo(targetFile); 
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}

		Product oneProduct = new Product();
		
		oneProduct.setSellerID((Long)httpServletRequest.getSession().getAttribute("USERID"));
		oneProduct.setBuyerID(-1);
		oneProduct.setName(httpServletRequest.getParameter("title"));
		oneProduct.setPrice(Double.parseDouble(httpServletRequest.getParameter("price")));
		oneProduct.setMean(httpServletRequest.getParameter("mean"));
		oneProduct.setCategory(httpServletRequest.getParameter("category"));
		oneProduct.setLocation(httpServletRequest.getParameter("city"));
		oneProduct.setDescription(httpServletRequest.getParameter("description"));
		oneProduct.setPicture(fileName);
		this.productManager.addProduct(oneProduct);
		return "redirect:/detail/" + oneProduct.getProductID();
	}
}
