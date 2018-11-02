package au.usyd.elec5619.domain;

import junit.framework.TestCase;

public class CommentTest extends TestCase{
	
	private Comment oneComment;
	
	protected void setUp() throws Exception	{
		oneComment = new Comment();
	}
	
	// Test the set and get function of CommentID
	public void testSetAndGetCommentID() {
		long testCommentID = 999;
		// The third parameter is 'delta'¡£ Represents precision
		assertEquals(0, 0, 0);
		oneComment.setCommentID(testCommentID);
		assertEquals(testCommentID, oneComment.getCommentID(), 0);
	}
	
	// Test the set and get function of PostID
	public void testSetAndGetPostID() {
		long testPostID = 200;
		// The third parameter is 'delta'¡£ Represents precision
		assertEquals(0, 0, 0);
		oneComment.setPostID(testPostID);
		assertEquals(testPostID, oneComment.getPostID(), 0);
	}
	
	// Test the set and get function of Writer
	public void testSetAndGetWriter() {
		String testWriter = "Polo Wang";
		assertNull(oneComment.getWriter());
		oneComment.setWriter(testWriter);
		assertEquals(testWriter, oneComment.getWriter());
	}
	
	// Test the set and get function of CreationTime
	public void testSetAndGetCreationTime() {
		String testCreationTime = "2018/10/10 10:30";
		assertNull(oneComment.getCreationTime());
		oneComment.setCreationTime(testCreationTime);
		assertEquals(testCreationTime, oneComment.getCreationTime());
	}
	
	// Test the set and get function of Content
	public void testSetAndGetContent() {
		String testContent = "This is a testing for reply content";
		assertNull(oneComment.getContent());
		oneComment.setContent(testContent);
		assertEquals(testContent, oneComment.getContent());
	}
}
