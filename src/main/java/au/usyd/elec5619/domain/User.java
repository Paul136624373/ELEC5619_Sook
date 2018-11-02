package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="UserID")
	private long userID;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Suburb")
	private String suburb;
	
	@Column(name="City")
	private String city;
	
	@Column(name="State")
	private String state;
	
	@Column(name="Postcode")
	private String postcode;
	
	@Column(name="Telephone")
	private String telephone;
	
	public User()
	{
		// Constructor
	}
	public User(String name, String email, String password)
	{
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = "";
		this.suburb = "";
		this.city = "";
		this.state = "";
		this.postcode = "";
		this.telephone = "";
	}
	public User(String name, String email, String password, String address, String suburb, String city, String state, String postcode, String telephone)
	{
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.suburb = suburb;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
		this.telephone = telephone;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "User [UserID=" + userID + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", address=" + address + ", suburb=" + suburb + ", city=" + city + ", state=" + state + ", postcode="
				+ postcode + ", telephone=" + telephone + "]";
	}
	
}
