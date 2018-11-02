package au.usyd.elec5619.domain;

import junit.framework.TestCase;

public class RecordTest extends TestCase{
	
	private Record record;
	
	protected void setUp() throws Exception 
	{
		record = new Record();
	}
	
	public void testSetAndGetRecordID()
	{
		long testRecordID = 1234;
		assertEquals(0,0,0);
		record.setRecordID(testRecordID);
		assertEquals(testRecordID, record.getRecordID(),0);
	}
	
	public void testSetAndGetDate()
	{
		String testDate = "2018-10-24";
		assertNull(record.getDate());
		record.setDate(testDate);
		assertEquals(testDate, record.getDate());
	}
	
	public void testSetAndGeTotalPrice()
	{
		String testTotalPrice = "$100";
		assertNull(record.getTotalPrice());
		record.setTotalPrice(testTotalPrice);
		assertEquals(testTotalPrice, record.getTotalPrice());
	}
	
	public void testSetAndGetBuyerID()
	{
		long testBuyerID = 2333;
		assertEquals(0,0,0);
		record.setBuyerID(testBuyerID);
		assertEquals(testBuyerID, record.getBuyerID(),0);
	}
	
	public void testSetAndGetProductID()
	{
		String testProductID = "123;345";
		assertNull(record.getProductID());
		record.setProductID(testProductID);
		assertEquals(testProductID, record.getProductID());
	}
	
	public void testSetAndGetAddress()
	{
		String testAddress = "pnr building";
		assertNull(record.getAddress());
		record.setAddress(testAddress);
		assertEquals(testAddress, record.getAddress());
	}
	

}
