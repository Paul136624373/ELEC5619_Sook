package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class Product implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="ProductID")
	private long productID;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Price")
	private double price;
	
	@Column(name="Picture")
	private String picture;
	
	@Column(name="SellerID")
	private long sellerID;
	
	@Column(name="BuyerID")
	private long buyerID;
	
	@Column(name="Location")
	private String location;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Category")
	private String category;
	
	@Column(name="Mean")
	private String mean;
	
	public Product()
	{
		// Constructor
	}
	
	public Product(String name, double price, String picture, long sellerID, long buyerID, String location, String description, String category, String mean) {
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.sellerID = sellerID;
		this.buyerID = buyerID;
		this.location = location;
		this.description = description;
		this.category = category;
		this.mean = mean;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public long getSellerID() {
		return sellerID;
	}

	public void setSellerID(long sellerID) {
		this.sellerID = sellerID;
	}

	public long getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(long buyerID) {
		this.buyerID = buyerID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", name=" + name + ", price=" + price + ", picture=" + picture
				+ ", sellerID=" + sellerID + ", buyerID=" + buyerID + ", location=" + location + ", description="
				+ description + ", category=" + category + ", mean=" + mean + "]";
	}
	
	

}