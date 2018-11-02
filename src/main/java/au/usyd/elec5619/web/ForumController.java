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

import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.PostManager;
import au.usyd.elec5619.service.UserManager;

public class ForumController implements Controller {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private PostManager postManager;
	private UserManager userManager;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception{
		String now = (new Date()).toString();
		logger.info("Returning forum view with " + now);
		
		Map<String, Object> forumModel = new HashMap<String, Object>();
		forumModel.put("allPosts", this.postManager.getAllPosts());
		
		long userID;
		try
		{
			HttpSession session = arg0.getSession(false);
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
					forumModel.put("userName", oneUser.getName());
				}
			}
		}
		return new ModelAndView("forum", "forumModel", forumModel);
	}
	
	public void setPostManager(PostManager postManager){
		this.postManager = postManager;
	}
	
	public void setUserManager(UserManager userManager){
		this.userManager = userManager;
	}
}