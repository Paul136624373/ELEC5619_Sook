package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;
import au.usyd.elec5619.domain.Comment;
import junit.framework.TestCase;

public class SimpleCommentManagerTest extends TestCase {

	private SimpleCommentManager commentManager;
	private List<Comment> allComments;
	
	private static int COMMENT_COUNT = 3;
	
	private static long firstCommentID = 100;
	private static long secondCommentID = 101;
	private static long thirdCommentID = 102;
	
	private static long firstPostID = 200;
	private static long secondPostID = 200;
	private static long thirdPostID = 202;
	
	private static String firstWriter = "Polo Wang";
	private static String secondWriter = "Paul Zhang";
	private static String thirdWriter = "Mark Li";
	
	private static String firstCreationTime = "2018/10/10 10:00";
	private static String secondCreationTime = "2018/02/10 10:00";
	private static String thirdCreationTime = "2118/11/13 10:00";
	
	private static String firstContent = "This is the content of first Comment";
	private static String secondContent = "This is the content of second Comment";
	private static String thirdContent = "This is the content of third Comment";

	protected void setUp() throws Exception {
		commentManager = new SimpleCommentManager();
		allComments = new ArrayList<Comment>();
		
		Comment firstComment = new Comment();
		firstComment.setCommentID(firstCommentID);
		firstComment.setPostID(firstPostID);
		firstComment.setWriter(firstWriter);
		firstComment.setCreationTime(firstCreationTime);
		firstComment.setContent(firstContent);
		allComments.add(firstComment);
		
		Comment secondComment = new Comment();
		secondComment.setCommentID(secondCommentID);
		secondComment.setPostID(secondPostID);
		secondComment.setWriter(secondWriter);
		secondComment.setCreationTime(secondCreationTime);
		secondComment.setContent(secondContent);
		allComments.add(secondComment);
		
		Comment thirdComment = new Comment();
		thirdComment.setCommentID(thirdCommentID);
		thirdComment.setPostID(thirdPostID);
		thirdComment.setWriter(thirdWriter);
		thirdComment.setCreationTime(thirdCreationTime);
		thirdComment.setContent(thirdContent);
		allComments.add(thirdComment);
		
		commentManager.setComments(allComments);
	}
	
	public void testGetCommentsWithNoComments() {
		commentManager = new SimpleCommentManager();
		assertNull(commentManager.getAllComments());
	}
	
	public void testGetComments() {
		List<Comment> allComments = commentManager.getAllComments();
		assertNotNull(allComments);
		assertEquals(COMMENT_COUNT, commentManager.getAllComments().size());
		
		Comment firstComment = allComments.get(0);
		assertEquals(firstCommentID, firstComment.getCommentID());
		assertEquals(firstPostID, firstComment.getPostID());
		assertEquals(firstWriter, firstComment.getWriter());
		assertEquals(firstCreationTime, firstComment.getCreationTime());
		assertEquals(firstContent, firstComment.getContent());
		
		Comment secondComment = allComments.get(1);
		assertEquals(secondCommentID, secondComment.getCommentID());
		assertEquals(secondPostID, secondComment.getPostID());
		assertEquals(secondWriter, secondComment.getWriter());
		assertEquals(secondCreationTime, secondComment.getCreationTime());
		assertEquals(secondContent, secondComment.getContent());
		
		Comment thirdComment = allComments.get(2);
		assertEquals(thirdCommentID, thirdComment.getCommentID());
		assertEquals(thirdPostID, thirdComment.getPostID());
		assertEquals(thirdWriter, thirdComment.getWriter());
		assertEquals(thirdCreationTime, thirdComment.getCreationTime());
		assertEquals(thirdContent, thirdComment.getContent());
	}

}
