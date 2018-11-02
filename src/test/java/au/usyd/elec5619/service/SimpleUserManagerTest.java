package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import au.usyd.elec5619.domain.User;


public class SimpleUserManagerTest extends TestCase{
	private SimpleUserManager userManager;
	private List<User> Users;
	
	private static long userID1 = 1;

	private String name1 = "xiaoming";
	private String email1 = "xiaoming@qq.com";
	private String password1 = "PWofXiaoming";
	private String address1 = "1 Alexandra Drive";
	private String suburb1 = "Camperdown";
	private String city1 = "Sysney";
	private String state1 = "NSW";
	private String postcode1 ="2050";
	private String telephone1 ="0414858817";
	
	private static long userID2 = 2;
	private String name2 = "xiaohong";
	private String email2 = "xiaohong@qq.com";
	private String password2 = "PWofXiaohong";
	private String address2 = "10 Alexandra Drive";
	private String suburb2 = "Camperdown";
	private String city2 = "Sysney";
	private String state2 = "NSW";
	private String postcode2 ="2050";
	private String telephone2 ="1231231231";
	
	protected void setUp() throws Exception {
		userManager = new SimpleUserManager();
        Users = new ArrayList<User>();
        
        
        User user = new User();
        user.setUserID(userID1); 
        user.setName(name1);
        user.setEmail(email1);
        user.setPassword(password1);
        user.setAddress(address1);
        user.setSuburb(suburb1);
        user.setCity(city1);
        user.setState(state1);
        user.setPostcode(postcode1);
        user.setTelephone(telephone1);
        
        Users.add(user);
        
        userManager.setUsers(Users);
    }
	public void testGetAllUser() {
		userManager = new SimpleUserManager();
        assertNull(userManager.getAllUser());
	}
	public void testgetUserByID() {
		User user = userManager.getUserByID(userID1);
		assertEquals(name1,user.getName());
	}
	public void testaddUser() {
		User newUser = new User();
		newUser.setUserID(userID2);
		newUser.setName(name2);
		newUser.setEmail(email2);
		newUser.setPassword(password2);
		newUser.setAddress(address2);
		newUser.setSuburb(suburb2);
		newUser.setCity(city2);
		newUser.setState(state2);
		newUser.setPostcode(postcode2);
		newUser.setTelephone(telephone2);
	        
		userManager.addUser(newUser);
		
		User temp = userManager.getUserByID(userID2);
		
		assertEquals(name2,temp.getName());
		assertEquals(email2,temp.getEmail());
		assertEquals(password2,temp.getPassword());

		
    }
	public void testupdateUser() {
		
		User user = userManager.getUserByID(userID1);
		
		user.setName("haha");
		
		userManager.updateUser(user);
		
		String name = userManager.getUserByID(userID1).getName();
		
		assertEquals("haha", name);
		
	}
}
