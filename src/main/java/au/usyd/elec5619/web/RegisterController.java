package au.usyd.elec5619.web;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import au.usyd.elec5619.service.UserManager;

public class RegisterController implements Controller {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private UserManager userManager;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception{
		String now = (new java.util.Date()).toString();
		Map<String, Object> userModel = new HashMap<String, Object>();
		userModel.put("now", now);
		try{
			HttpSession session = arg0.getSession();
			if(session.getAttribute("USERID")!=null) {
				session.setAttribute("USERID",null);
			}
		}catch(Exception e){
			
		}
		
		
		return new ModelAndView("register","userModel",userModel);
	}
	
	public void setUserManager(UserManager userManager){
		this.userManager = userManager;
	}
}