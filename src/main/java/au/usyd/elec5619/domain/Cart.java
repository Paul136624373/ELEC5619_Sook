package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cart")
public class Cart implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="CartID")
	private long cartID;
	
	@Column(name="ProductID")
	private long productID;

	@Column(name="ProductName")
	private String productName;
	
	@Column(name="ProductImg")
	private String productImg;
	
	@Column(name="ProductPrice")
	private double productPrice;
	
	@Column(name="BuyerID")
	private long buyerID;
	
	@Column(name="Choose")
	private boolean choose;
	
	@Column(name="Method")
	private String method;

	public Cart()
	{
		// Constructor
	}

	public Cart(long productID, String productName, String productImg, double productPrice, long buyerID, boolean choose, String method) 
	{
		this.productID = productID;
		this.productName = productName;
		this.productImg = productImg;
		this.productPrice = productPrice;
		this.buyerID = buyerID;
		this.choose = choose;
		this.method = method;
	}

	public long getCartID() {
		return cartID;
	}

	public void setCartID(long cartID) {
		this.cartID = cartID;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public long getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(long buyerID) {
		this.buyerID = buyerID;
	}

	public boolean isChoose() {
		return choose;
	}

	public void setChoose(boolean choose) {
		this.choose = choose;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String toString() {
		return "Cart [cartID=" + cartID + ", productID=" + productID + ", productName=" + productName + ", productImg="
				+ productImg + ", productPrice=" + productPrice + ", buyerID=" + buyerID + ", choose=" + choose
				+ ", method=" + method + "]";
	}

}
