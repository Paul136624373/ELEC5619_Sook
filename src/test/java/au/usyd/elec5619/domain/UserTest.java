package au.usyd.elec5619.domain;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    private User user;

    protected void setUp() throws Exception {
    	user = new User();
    }

    public void testSetAndGetName() {
        String testName = "aName";
        assertNull(user.getName());
        user.setName(testName);
        assertEquals(testName, user.getName());
    }

    public void testSetAndGetEmail() {
        String testEmail = "aEmail";
        assertNull(user.getEmail());
        user.setEmail(testEmail);
        assertEquals(testEmail, user.getEmail());
    }
    public void testSetAndGetPassword() {
        String testPassword = "aPassword";
        assertNull(user.getPassword());  
        user.setPassword(testPassword);
        assertEquals(testPassword, user.getPassword());
    }
    public void testSetAndGetAddress() {
        String testAddress = "anAddress";
        assertNull(user.getAddress());  
        user.setAddress(testAddress);
        assertEquals(testAddress, user.getAddress());
    }
    public void testSetAndGetSuburb() {
        String testSuburb = "aSuburb";
        assertNull(user.getSuburb());  
        user.setSuburb(testSuburb);
        assertEquals(testSuburb, user.getSuburb());
    }
    public void testSetAndGetCity() {
        String testCity = "aCity";
        assertNull(user.getCity());  
        user.setCity(testCity);
        assertEquals(testCity, user.getCity());
    }
    public void testSetAndGetState() {
        String testState = "aState";
        assertNull(user.getState());  
        user.setState(testState);
        assertEquals(testState, user.getState());
    }
    public void testSetAndGetPostcode() {
        String testPostcode = "aPostcode";
        assertNull(user.getPostcode());  
        user.setPostcode(testPostcode);
        assertEquals(testPostcode, user.getPostcode());
    }
    public void testSetAndGetTelephone() {
        String testTelephone = "aTelephone";
        assertNull(user.getTelephone());  
        user.setTelephone(testTelephone);
        assertEquals(testTelephone, user.getTelephone());
    }

}
