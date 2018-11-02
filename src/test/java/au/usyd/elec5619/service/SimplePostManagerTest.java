package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;
import au.usyd.elec5619.domain.Post;
import junit.framework.TestCase;

public class SimplePostManagerTest extends TestCase {

	private SimplePostManager postManager;
	private List<Post> allPosts;
	
	private static int POST_COUNT = 3;
	
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
	
	protected void setUp() throws Exception {
		postManager = new SimplePostManager();
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
	}
	
	public void testGetPostsWithNoPosts() {
		postManager = new SimplePostManager();
		assertNull(postManager.getAllPosts());
	}
	
	public void testGetPosts() {
		List<Post> allPosts = postManager.getAllPosts();
		assertNotNull(allPosts);
		assertEquals(POST_COUNT, postManager.getAllPosts().size());
		
		Post firstPost = allPosts.get(0);
		assertEquals(firstPostID, firstPost.getPostID());
		assertEquals(firstAuthor, firstPost.getAuthor());
		assertEquals(firstTitle, firstPost.getTitle());
		assertEquals(firstType, firstPost.getType());
		assertEquals(firstCreationTime, firstPost.getCreationTime());
		assertEquals(firstContent, firstPost.getContent());
		assertEquals(firstImgPath, firstPost.getImgPath());
		
		Post secondPost = allPosts.get(1);
		assertEquals(secondPostID, secondPost.getPostID());
		assertEquals(secondAuthor, secondPost.getAuthor());
		assertEquals(secondTitle, secondPost.getTitle());
		assertEquals(secondType, secondPost.getType());
		assertEquals(secondCreationTime, secondPost.getCreationTime());
		assertEquals(secondContent, secondPost.getContent());
		assertEquals(secondImgPath, secondPost.getImgPath());
		
		Post thirdPost = allPosts.get(2);
		assertEquals(thirdPostID, thirdPost.getPostID());
		assertEquals(thirdAuthor, thirdPost.getAuthor());
		assertEquals(thirdTitle, thirdPost.getTitle());
		assertEquals(thirdType, thirdPost.getType());
		assertEquals(thirdCreationTime, thirdPost.getCreationTime());
		assertEquals(thirdContent, thirdPost.getContent());
		assertEquals(thirdImgPath, thirdPost.getImgPath());
	}

}
