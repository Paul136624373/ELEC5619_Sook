package au.usyd.elec5619.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import au.usyd.elec5619.domain.Product;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.UserManager;
import au.usyd.elec5619.service.ProductManager;

public class ToHomeController implements Controller {
	protected final Log logger = LogFactory.getLog(getClass());

	private ProductManager productManager;
	private UserManager userManager;

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		long userID;
		try {
			HttpSession session = arg0.getSession(false);
			userID = (Long) session.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(userID);
		} catch (Exception e) {
			userID = 0;
		}

		Map<String, Object> ToHomeModel = new HashMap<String, Object>();

		List<Product> newArrival = new ArrayList<Product>();
		
		int size = this.productManager.getAllProducts().size();
		ToHomeModel.put("size", size);

		if (this.productManager.getAllProducts().size() < 4) {
			for (Product oneProduct : this.productManager.getAllProducts()) {
				if(oneProduct.getBuyerID() == -1)
				{
					newArrival.add(oneProduct);
				}
			}
		} else {
			for (int i = this.productManager.getAllProducts().size() - 1; i >= 0; i--) {
				if(newArrival.size() == 4)
				{
					break;
				}
				if(productManager.getAllProducts().get(i).getBuyerID() == -1)
				{
					newArrival.add(this.productManager.getAllProducts().get(i));
				}
			}
		}

		ToHomeModel.put("newArrival", newArrival);
		if (userID != 0) {
			for (User oneUser : this.userManager.getAllUser()) {
				if (oneUser.getUserID() == userID) {
					ToHomeModel.put("userName", oneUser.getName());
				}
			}
		}

		return new ModelAndView("home", "ToHomeModel", ToHomeModel);
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}