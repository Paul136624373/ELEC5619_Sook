package au.usyd.elec5619.service;

import java.io.Serializable;
import java.util.List;

import au.usyd.elec5619.domain.Comment;

public class SimpleCommentManager implements CommentManager{
	
	private List<Comment> comments;
	
	public List<Comment> getAllComments(){
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public List<Comment> getPostComment(long postID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addComment(Comment oneComment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComments(long postID) {
		// TODO Auto-generated method stub
		
	}
	
	
}
