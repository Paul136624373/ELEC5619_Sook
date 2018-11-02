package au.usyd.elec5619.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.domain.Comment;

@Service(value="commentManager")
@Transactional
public class DatabaseCommentManager implements CommentManager{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public void addComment(Comment oneComment) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(oneComment);
	}
	
	public List<Comment> getAllComments(){
		return this.sessionFactory.getCurrentSession().createQuery("FROM Comment").list();
	}

	@Override
	public List<Comment> getPostComment(long postID) {
		// TODO Auto-generated method stub
		Session currentSession = this.sessionFactory.getCurrentSession();
		List<Comment> comments = currentSession.createQuery("FROM Comment WHERE PostID=" + postID).list();
		return comments;
	}

	@Override
	public void deleteComments(long postID) {
		// TODO Auto-generated method stub
		List<Comment> allComments = this.getAllComments();
		for(Comment oneComment : allComments)
		{
			if(oneComment.getPostID() == postID)
			{
				this.sessionFactory.getCurrentSession().delete(oneComment);
			}
		}
	}
	
}
