package au.usyd.elec5619.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
@RequestMapping(value="/register/**")

public class VerifyUserController {
	@Resource(name="userManager")
	private UserManager userManager;
	
	@RequestMapping(value="/newUser", method=RequestMethod.POST)
	public String createNewUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		List<User> allUsers = this.userManager.getAllUser();
		boolean newEmail = true;
		for(User temp: allUsers) {
			if(temp.getEmail().equals(httpServletRequest.getParameter("email"))) {
				newEmail = false;
			}
		}
		
		if(newEmail) {
			User newUser = new User();
			newUser.setName(httpServletRequest.getParameter("name"));
			newUser.setEmail(httpServletRequest.getParameter("email"));
			newUser.setPassword(httpServletRequest.getParameter("password"));
			this.userManager.addUser(newUser);
			HttpSession session = httpServletRequest.getSession();
			session.setAttribute("USERID", newUser.getUserID());
			return "redirect:/myInfo";
		}
		else {
			return "redirect:/register?error=yes1";
		}
	}
	@RequestMapping(value="/Login", method=RequestMethod.POST)
	public String userLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		List<User> allUsers = this.userManager.getAllUser();
		boolean rightPW = false;
		User theUser = new User();
		for(User temp: allUsers) {
			if(temp.getEmail().equals(httpServletRequest.getParameter("email")) && temp.getPassword().equals(httpServletRequest.getParameter("password"))) {
				rightPW = true;
				theUser = temp;
			}
		}
		
		if(rightPW == true) {
			HttpSession session = httpServletRequest.getSession();
			session.setAttribute("USERID", theUser.getUserID());
			return "redirect:/myInfo";
		}
		else {
			return "redirect:/register?error=yes2";
		}
	}
	@RequestMapping(value="/Login/Box", method=RequestMethod.POST)
	public String userLoginBox(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		List<User> allUsers = this.userManager.getAllUser();
		boolean rightPW = false;
		User theUser = new User();
		for(User temp: allUsers) {
			if(temp.getEmail().equals(httpServletRequest.getParameter("email1")) && temp.getPassword().equals(httpServletRequest.getParameter("password1"))) {
				rightPW = true;
				theUser = temp;
			}
		}
		
		if(rightPW == true) {
			HttpSession session = httpServletRequest.getSession();
			session.setAttribute("USERID", theUser.getUserID());
			
			return "redirect:/myInfo";
		}
		else {
			return "redirect:/register?error=yes2";
		}
	}
	
}
