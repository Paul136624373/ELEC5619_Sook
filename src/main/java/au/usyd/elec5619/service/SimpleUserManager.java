package au.usyd.elec5619.service;

import java.util.List;

import au.usyd.elec5619.domain.User;

public class SimpleUserManager implements UserManager{
	private List<User> Users;
	
	public User getUserByID(long userID) {
		User theUser = new User();
		for(User temp: Users) {
			if(temp.getUserID() == userID) {
				theUser = temp;
			}
		}
		return theUser;
	}
	public void setUsers(List<User> Users) {
		this.Users = Users;
	}
	
	public List<User> getAllUser(){
		return Users;
	}
	@Override
	public void addUser(User user) {
		Users.add(user);
	}
	@Override
	public void updateUser(User user) {
		long theID = user.getUserID();
		for(int i = 0;i<Users.size();i++) {
			if(Users.get(i).getUserID()==theID) {
				Users.set(i, user);
			}
		}
	}
	

}
