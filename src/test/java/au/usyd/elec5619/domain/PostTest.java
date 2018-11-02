package au.usyd.elec5619.domain;

import junit.framework.TestCase;

public class PostTest extends TestCase{
	
	private Post onePost;
	
	protected void setUp() throws Exception	{
		onePost = new Post();
	}
	
	// Test the set and get function of PostID
	public void testSetAndGetPostID() {
		long testPostID = 200;
		// The third parameter is 'delta'¡£ Represents precision
		assertEquals(0, 0, 0);
		onePost.setPostID(testPostID);
		assertEquals(testPostID, onePost.getPostID(), 0);
	}
	
	// Test the set and get function of Author
	public void testSetAndGetAuthor() {
		String testAuthor = "Polo Wang";
		assertNull(onePost.getAuthor());
		onePost.setAuthor(testAuthor);
		assertEquals(testAuthor, onePost.getAuthor());
	}
	
	// Test the set and get function of Title
	public void testSetAndGetTitle() {
		String testTitle = "This is a test";
		assertNull(onePost.getTitle());
		onePost.setTitle(testTitle);
		assertEquals(testTitle, onePost.getTitle());
	}
	
	// Test the set and get function of Type
	public void testSetAndGetType() {
		String testType = "Share";
		assertNull(onePost.getType());
		onePost.setType(testType);
		assertEquals(testType, onePost.getType());
	}
	
	// Test the set and get function of CreationTime
	public void testSetAndGetCreationTime() {
		String testCreationTime = "2018/10/10 10:00";
		assertNull(onePost.getCreationTime());
		onePost.setCreationTime(testCreationTime);
		assertEquals(testCreationTime, onePost.getCreationTime());
	}
	
	// Test the set and get function of Content
	public void testSetAndGetContent() {
		String testContent = "This is a testing for content";
		assertNull(onePost.getContent());
		onePost.setContent(testContent);
		assertEquals(testContent, onePost.getContent());
	}
	
	// Test the set and get function of ImgPath
	public void testSetAndGetImgPath() {
		String testImgPath = "blog.jpg";
		assertNull(onePost.getImgPath());
		onePost.setImgPath(testImgPath);
		assertEquals(testImgPath, onePost.getImgPath());
	}
}
