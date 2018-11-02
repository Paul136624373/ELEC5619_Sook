package au.usyd.elec5619.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.usyd.elec5619.domain.Cart;
import au.usyd.elec5619.domain.Comment;
import au.usyd.elec5619.domain.Post;
import au.usyd.elec5619.domain.Product;
import au.usyd.elec5619.domain.Record;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.CartManager;
import au.usyd.elec5619.service.CommentManager;
import au.usyd.elec5619.service.PostManager;
import au.usyd.elec5619.service.ProductManager;
import au.usyd.elec5619.service.RecordManager;
import au.usyd.elec5619.service.UserManager;

@Controller
@RequestMapping("/api")
public class APIController {
	
	@Resource(name="postManager")
	private PostManager postManager;
	
	@Resource(name="productManager")
	private ProductManager productManager;
	
	@Resource(name="commentManager")
	private CommentManager commentManager;
	
	@Resource(name="recordManager")
	private RecordManager recordManager;
	
	@Resource(name="userManager")
	private UserManager userManager;
	
	@Resource(name="cartManager")
	private CartManager cartManager;
	
	//---------------------------------------- APIs for post ----------------------------------------
	@RequestMapping(value="/allPosts", method = RequestMethod.GET)
	public @ResponseBody List<Post> getPostsInJSON() {

		List<Post> allPosts = this.postManager.getAllPosts();		
		return allPosts;
	}
	
	@RequestMapping(value="/post/{postID}", method = RequestMethod.GET)
	public @ResponseBody List<Post> getPostInJSON(@PathVariable String postID) {

		List<Post> allPosts = this.postManager.getAllPosts();
		
		// Use List to store and return the post to avoid returning a post that postID=0 when the id is not found
		List<Post> thePost = new ArrayList<Post>();
		for(Post onePost : allPosts)
		{
			if(onePost.getPostID() == Integer.parseInt(postID))
			{
				thePost.add(onePost);
				break;
			}
		}
		return thePost;
	}
	
	@RequestMapping(value="/allComments", method = RequestMethod.GET)
	public @ResponseBody List<Comment> getCommentsInJSON() {

		List<Comment> allComments = this.commentManager.getAllComments();		
		return allComments;
	}
	
	@RequestMapping(value="/comments/{postID}", method = RequestMethod.GET)
	public @ResponseBody List<Comment> getCommentsInJSON(@PathVariable String postID) {
		List<Comment> allComments = this.commentManager.getAllComments();
		List<Comment> theComments = new ArrayList<Comment>();
		for(Comment oneComment : allComments)
		{
			if(oneComment.getPostID() == Integer.parseInt(postID))
			{
				theComments.add(oneComment);
			}
		}
		return theComments;
	}
	
	//---------------------------------------- End ----------------------------------------
	
	//---------------------------------------- APIs for product ----------------------------------------
	@RequestMapping(value="/allProducts", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProductsInJSON() {

		List<Product> allProducts = this.productManager.getAllProducts();
		return allProducts;
	}
	
	@RequestMapping(value="/product/{productID}", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProductInJSON(@PathVariable String productID) {

		List<Product> allProducts = this.productManager.getAllProducts();
		
		// Use List to store and return the product to avoid returning a product that productID=0 when the id is not found
		List<Product> theProduct = new ArrayList<Product>();
		for(Product oneProduct : allProducts)
		{
			if(oneProduct.getProductID() == Integer.parseInt(productID))
			{
				theProduct.add(oneProduct);
				break;
			}
		}
		return theProduct;
	}
	
	//---------------------------------------- End ----------------------------------------
	
	//---------------------------------------- APIs for record ----------------------------------------
	@RequestMapping(value="/allRecords", method = RequestMethod.GET)
	public @ResponseBody List<Record> getRecordsInJSON() {

		List<Record> allRecords = this.recordManager.getAllRecords();
		return allRecords;
	}
	
	@RequestMapping(value="/record/{recordID}", method = RequestMethod.GET)
	public @ResponseBody List<Record> getRecordInJSON(@PathVariable String recordID) {

		List<Record> allRecords = this.recordManager.getAllRecords();
		
		// Use List to store and return the record to avoid returning a record that recordID=0 when the id is not found
		List<Record> theRecord = new ArrayList<Record>();
		for(Record oneRecord : allRecords)
		{
			if(oneRecord.getRecordID() == Integer.parseInt(recordID))
			{
				theRecord.add(oneRecord);
				break;
			}
		}
		return theRecord;
	}
	
	//---------------------------------------- End ----------------------------------------
	
	//---------------------------------------- APIs for user ----------------------------------------
	@RequestMapping(value="/allUsers", method = RequestMethod.GET)
	public @ResponseBody List<User> getUsersInJSON() {

		List<User> allUsers = this.userManager.getAllUser();
		for(User oneUser : allUsers)
		{
			oneUser.setPassword("***");
		}
		return allUsers;
	}
	
	@RequestMapping(value="/user/{userID}", method = RequestMethod.GET)
	public @ResponseBody List<User> getUserInJSON(@PathVariable String userID) {

		List<User> allUsers = this.userManager.getAllUser();
		
		// Use List to store and return the user to avoid returning a user that userID=0 when the id is not found
		List<User> theUser = new ArrayList<User>();
		for(User oneUser : allUsers)
		{
			if(oneUser.getUserID() == Integer.parseInt(userID))
			{
				oneUser.setPassword("***");
				theUser.add(oneUser);
				break;
			}
		}
		return theUser;
	}
	
	//---------------------------------------- End ----------------------------------------
	
	
	//---------------------------------------- APIs for cart ----------------------------------------
	@RequestMapping(value="/allCarts", method = RequestMethod.GET)
	public @ResponseBody List<Cart> getCartsInJSON() {

		List<Cart> allCarts = this.cartManager.getAllCarts();
		return allCarts;
	}
	
	@RequestMapping(value="/cart/{cartID}", method = RequestMethod.GET)
	public @ResponseBody List<Cart> getCartInJSON(@PathVariable String cartID) {

		List<Cart> allCarts = this.cartManager.getAllCarts();
		
		// Use List to store and return the cart to avoid returning a cart that cartID=0 when the id is not found
		List<Cart> theCart = new ArrayList<Cart>();
		for(Cart oneCart : allCarts)
		{
			if(oneCart.getCartID() == Integer.parseInt(cartID))
			{
				theCart.add(oneCart);
				break;
			}
		}
		return theCart;
	}
	
	//---------------------------------------- End ----------------------------------------
	
}