package au.usyd.elec5619.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 
import org.springframework.web.servlet.ModelAndView; 
import org.springframework.web.servlet.mvc.Controller;

import au.usyd.elec5619.domain.Product;
import au.usyd.elec5619.service.ProductManager; 

public class ViewProductController implements Controller {  

    private ProductManager productManager;
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Map<String, Object> ProductModel = new HashMap<String, Object>();
        List<Product> allProducts=new ArrayList<Product>();
        
        long name;
        
		try
		{
			HttpSession sessions = request.getSession(false);
			name = (Long)sessions.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(name);
		}
		catch(Exception e){
			name=-1;
		}
        
        for(Product oneProduct : this.productManager.getAllProducts())
		{
			if(oneProduct.getBuyerID()==-1)
			{
				allProducts.add(oneProduct);
			}
		}
        ProductModel.put("products", allProducts);
        ProductModel.put("size",allProducts.size());
        ProductModel.put("user",name);

        return new ModelAndView("viewProducts", "ProductModel", ProductModel);
    }


    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }


} 