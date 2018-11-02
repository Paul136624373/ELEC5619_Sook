package au.usyd.elec5619.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import au.usyd.elec5619.domain.Post;
import au.usyd.elec5619.service.PostManager;
import au.usyd.elec5619.service.SimplePostManager;
import junit.framework.TestCase;

public class ForumControllerTest extends TestCase {
	
	private List<Post> allPosts;
	
	private static long firstPostID = 200;
	private static long secondPostID = 201;
	private static long thirdPostID = 202;
	
	private static String firstAuthor = "Polo Wang";
	private static String secondAuthor = "Paul Zhang";
	private static String thirdAuthor = "Mark Li";
	
	private static String firstTitle = "First post title";
	private static String secondTitle = "Second post title";
	private static String thirdTitle = "Third post title";
	
	private static String firstType = "Share";
	private static String secondType = "Seek";
	private static String thirdType = "Seek";
	
	private static String firstCreationTime = "2018/10/10 10:00";
	private static String secondCreationTime = "2018/02/10 10:00";
	private static String thirdCreationTime = "2118/11/13 10:00";
	
	private static String firstContent = "This is the content of first post";
	private static String secondContent = "This is the content of second post";
	private static String thirdContent = "This is the content of third post";
	
	private static String firstImgPath = "blog.jpg";
	private static String secondImgPath = "cat.jpg";
	private static String thirdImgPath = "dog.jpg";
	
	public void testHandleRequestView() throws Exception {
		ForumController controller = new ForumController();
		
		
		PostManager postManager = new SimplePostManager();
		allPosts = new ArrayList<Post>();
		
		Post firstPost = new Post();
		firstPost.setPostID(firstPostID);
		firstPost.setAuthor(firstAuthor);
		firstPost.setTitle(firstTitle);
		firstPost.setType(firstType);
		firstPost.setCreationTime(firstCreationTime);
		firstPost.setContent(firstContent);
		firstPost.setImgPath(firstImgPath);
		allPosts.add(firstPost);
		
		Post secondPost = new Post();
		secondPost.setPostID(secondPostID);
		secondPost.setAuthor(secondAuthor);
		secondPost.setTitle(secondTitle);
		secondPost.setType(secondType);
		secondPost.setCreationTime(secondCreationTime);
		secondPost.setContent(secondContent);
		secondPost.setImgPath(secondImgPath);
		allPosts.add(secondPost);
		
		Post thirdPost = new Post();
		thirdPost.setPostID(thirdPostID);
		thirdPost.setAuthor(thirdAuthor);
		thirdPost.setTitle(thirdTitle);
		thirdPost.setType(thirdType);
		thirdPost.setCreationTime(thirdCreationTime);
		thirdPost.setContent(thirdContent);
		thirdPost.setImgPath(thirdImgPath);
		allPosts.add(thirdPost);
		
		postManager.setPosts(allPosts);
		
		controller.setPostManager(postManager);
		ModelAndView modelAndView = controller.handleRequest(null, null);
		assertEquals("forum", modelAndView.getViewName());
		assertNotNull(modelAndView.getModel());
		Map modelMap = (Map) modelAndView.getModel().get("forumModel");
		List<Post> allPostsValue = (List<Post>) modelMap.get("allPosts");
		assertNotNull(allPostsValue);
	}
}
