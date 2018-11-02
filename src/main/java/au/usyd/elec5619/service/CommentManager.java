package au.usyd.elec5619.service;

import java.io.Serializable;
import java.util.List;

import au.usyd.elec5619.domain.Comment;

public interface CommentManager extends Serializable{
	
	public List<Comment> getAllComments();
	
	public List<Comment> getPostComment(long postID);
	
	public void addComment(Comment oneComment);
	
	public void deleteComments(long postID);
}
