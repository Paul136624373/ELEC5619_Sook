package au.usyd.elec5619.web;

import java.util.ArrayList;
import java.util.Map;
import junit.framework.TestCase;
import org.springframework.web.servlet.ModelAndView;
import au.usyd.elec5619.service.SimpleProductManager;
import au.usyd.elec5619.domain.Product;

public class ViewProductControllerTest extends TestCase{
	
	public void testHandleRequestView() throws Exception{
		ViewProductController controller = new ViewProductController();
		
		SimpleProductManager simpleNew= new SimpleProductManager();
		Product aProduct= new Product("a",1,"a",1,1,"a","a","a","a");
		ArrayList<Product> testProducts=new ArrayList<Product>();
		testProducts.add(aProduct);
		simpleNew.setAllProducts(testProducts);
		
		controller.setProductManager(simpleNew);
		ModelAndView modelAndView = controller.handleRequest(null,null);
		
		assertEquals("viewProducts",modelAndView.getViewName());
		
		assertNotNull(modelAndView.getModel());
		Map modelMap = (Map)modelAndView.getModel().get("ProductModel");
		int theSize=(Integer) modelMap.get("size");
		assertNotNull(theSize);
		
	}

}
