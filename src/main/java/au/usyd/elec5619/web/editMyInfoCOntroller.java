package au.usyd.elec5619.web;
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!The o of controller should be lower case!!

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

import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.UserManager;

@Controller
@RequestMapping(value="/myInfo/**")

public class editMyInfoCOntroller {
	@Resource(name="userManager")
	private UserManager userManager;
	

	@RequestMapping(value="/myInfo", method=RequestMethod.GET)
	public String myInfo(Model uiModel, HttpServletRequest httpServletRequest){
		HttpSession session = httpServletRequest.getSession();
		if(session.getAttribute("USERID")==null) {
			return "register";
		}
		else {
			long theID = (Long) session.getAttribute("USERID");
		
			User theUser = this.userManager.getUserByID(theID);
		
			uiModel.addAttribute("theUser", theUser);
			uiModel.addAttribute("idid", httpServletRequest.getSession().getAttribute("userID"));
			return "myInfo";
		}
	}
	@RequestMapping(value="/myInfo/edit/{UserID}", method=RequestMethod.POST)
	public String editmyInfo(@PathVariable("UserID") long theID,Model uiModel,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		
		User theUser = this.userManager.getUserByID(theID);
		theUser.setName(httpServletRequest.getParameter("name"));
		theUser.setAddress(httpServletRequest.getParameter("address"));
		theUser.setCity(httpServletRequest.getParameter("city"));
		theUser.setSuburb(httpServletRequest.getParameter("suburb"));
		theUser.setPostcode(httpServletRequest.getParameter("postcode"));
		theUser.setTelephone(httpServletRequest.getParameter("telephone"));
		
				
		this.userManager.updateUser(theUser);
		uiModel.addAttribute("theUser", theUser);

		return "redirect:/myInfo";
	}
	@RequestMapping(value="/myInfo/password/{UserID}", method=RequestMethod.POST)
	public String editMyPassword(@PathVariable("UserID") long theID,Model uiModel,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		
		User theUser = this.userManager.getUserByID(theID);
		theUser.setPassword(httpServletRequest.getParameter("new_pw"));
		this.userManager.updateUser(theUser);
		uiModel.addAttribute("theUser", theUser);
		return "redirect:/myInfo";
	}
	@RequestMapping(value="/myInfo/logout", method=RequestMethod.GET)
	public String createNewUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		HttpSession session = httpServletRequest.getSession();
		session.setAttribute("USERID",null);
		
		return "redirect:/register";
	}
	
}
