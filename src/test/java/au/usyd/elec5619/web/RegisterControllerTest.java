package au.usyd.elec5619.web;
import java.util.Map;

import junit.framework.TestCase;

import org.springframework.web.servlet.ModelAndView;

import au.usyd.elec5619.service.SimpleUserManager;

public class RegisterControllerTest extends TestCase {
	 public void testHandleRequestView() throws Exception{
		 RegisterController controller = new RegisterController();
	        controller.setUserManager(new SimpleUserManager());
	        ModelAndView modelAndView = controller.handleRequest(null, null);
	        assertEquals("register", modelAndView.getViewName());
	        assertNotNull(modelAndView.getModel());
	        Map modelMap = (Map) modelAndView.getModel().get("userModel");
	        String nowValue = (String) modelMap.get("now");
	        assertNotNull(nowValue);
	    }
}
