package au.usyd.elec5619.service;


import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.domain.Post;
import au.usyd.elec5619.domain.User;

@Service(value="userManager")
@Transactional
public class DatabaseUserManager implements UserManager {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("FROM User").list();
	}
	
	@Override
	public void addUser(User oneUser) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(oneUser);
	}
	
	@Override
	public User getUserByID(long userID) {
		// TODO Auto-generated method stub
		Session currentSession = this.sessionFactory.getCurrentSession();
		User theUser = (User) currentSession.get(User.class, userID);
		return theUser;
	}

	@Override
	public void updateUser(User oneUser) {
		// TODO Auto-generated method stub
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.merge(oneUser);
	}
}
