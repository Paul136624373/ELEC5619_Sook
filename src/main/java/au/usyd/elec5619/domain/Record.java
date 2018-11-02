package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Record")
public class Record implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="RecordID")
	private long recordID;
	
	@Column(name="Date")
	private String date;
	
	@Column(name="TotalPrice")
	private String totalPrice;
	
	@Column(name="BuyerID")
	private long buyerID;
	
	@Column(name="ProductID")
	private String productID;
	
	@Column(name="Address")
	private String address;
	
	public Record()
	{
		// Constructor
	}
	
	public Record(String date, String totalPrice, long buyerID, String productID, String address)
	{
		this.date = date;
		this.totalPrice = totalPrice;
		this.buyerID = buyerID;
		this.productID = productID;
		this.address = address;
	}

	public long getRecordID() {
		return recordID;
	}

	public void setRecordID(long recordID) {
		this.recordID = recordID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public long getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(long buyerID) {
		this.buyerID = buyerID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Record [recordID=" + recordID + ", date=" + date + ", totalPrice=" + totalPrice + ", buyerID=" + buyerID
				+ ", productID=" + productID + ", address=" + address + "]";
	}

}

