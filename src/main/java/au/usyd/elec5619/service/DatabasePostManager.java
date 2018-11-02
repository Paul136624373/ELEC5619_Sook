package au.usyd.elec5619.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.domain.Post;

@Service(value="postManager")
@Transactional
public class DatabasePostManager implements PostManager {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("FROM Post").list();
	}

	@Override
	public void addPost(Post onePost) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(onePost);
	}

	@Override
	public Post getPostByID(long postID) {
		// TODO Auto-generated method stub
		Session currentSession = this.sessionFactory.getCurrentSession();
		Post thePost = (Post) currentSession.get(Post.class, postID);
		return thePost;
	}

	@Override
	public void updatePost(Post onePost) {
		// TODO Auto-generated method stub
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.merge(onePost);
	}

	@Override
	public void deletePostByID(long postID) {
		// TODO Auto-generated method stub
		Session currentSession = this.sessionFactory.getCurrentSession();
		Post thePost = (Post) currentSession.get(Post.class, postID);
		currentSession.delete(thePost);
	}

	@Override
	public void setPosts(List<Post> allPosts) {
		// TODO Auto-generated method stub
		
	}

}
