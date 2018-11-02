package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import au.usyd.elec5619.domain.Record;

public class SimpleRecordManagerTest extends TestCase{
	
	private SimpleRecordManager recordManager;
	private List<Record> records;
	
	private static int RECORD_COUNT = 3;
	
	private static long ONE_RECORDID = 1;
	private static long ONE_BUYERID = 11;
	private static String ONE_PRODUCTID = "1";
	private static String ONE_DATE = "1";
	private static String ONE_ADDRESS = "1";
	private static String ONE_PRICE = "1";
	
	private static long TWO_RECORDID = 2;
	private static long TWO_BUYERID = 11;
	private static String TWO_PRODUCTID = "2";
	private static String TWO_DATE = "2";
	private static String TWO_ADDRESS = "2";
	private static String TWO_PRICE = "2";
	
	private static long THREE_RECORDID = 3;
	private static long THREE_BUYERID = 12;
	private static String THREE_PRODUCTID = "3";
	private static String THREE_DATE = "3";
	private static String THREE_ADDRESS = "3";
	private static String THREE_PRICE = "3";
	
	protected void setUp() throws Exception
	{
		recordManager = new SimpleRecordManager();
		records = new ArrayList<Record>();
		
		Record record = new Record();
		record.setRecordID(ONE_RECORDID);
		record.setDate(ONE_DATE);
		record.setBuyerID(ONE_BUYERID);
		record.setTotalPrice(ONE_PRICE);
		record.setProductID(ONE_PRODUCTID);
		record.setAddress(ONE_ADDRESS);
		records.add(record);
		
		record = new Record();
		record.setRecordID(TWO_RECORDID);
		record.setDate(TWO_DATE);
		record.setBuyerID(TWO_BUYERID);
		record.setTotalPrice(TWO_PRICE);
		record.setProductID(TWO_PRODUCTID);
		record.setAddress(TWO_ADDRESS);
		records.add(record);
		
		record = new Record();
		record.setRecordID(THREE_RECORDID);
		record.setDate(THREE_DATE);
		record.setBuyerID(THREE_BUYERID);
		record.setTotalPrice(THREE_PRICE);
		record.setProductID(THREE_PRODUCTID);
		record.setAddress(THREE_ADDRESS);
		records.add(record);
		
		recordManager.setRecords(records);
	}
	
	public void testgetRecordsWithNoRecords()
	{
		recordManager = new SimpleRecordManager();
		assertNull(recordManager.getAllRecords());
	}
	
	public void testGetRecords()
	{
		List<Record> records = recordManager.getAllRecords();
		assertNotNull(records);
		assertEquals(RECORD_COUNT, recordManager.getAllRecords().size());
		
		Record record = records.get(0);
		assertEquals(ONE_RECORDID,record.getRecordID());
		assertEquals(ONE_DATE,record.getDate());
		assertEquals(ONE_BUYERID,record.getBuyerID());
		assertEquals(ONE_PRICE,record.getTotalPrice());
		assertEquals(ONE_PRODUCTID,record.getProductID());
		assertEquals(ONE_ADDRESS,record.getAddress());
		
		record = records.get(1);
		assertEquals(TWO_RECORDID,record.getRecordID());
		assertEquals(TWO_DATE,record.getDate());
		assertEquals(TWO_BUYERID,record.getBuyerID());
		assertEquals(TWO_PRICE,record.getTotalPrice());
		assertEquals(TWO_PRODUCTID,record.getProductID());
		assertEquals(TWO_ADDRESS,record.getAddress());
		
		record = records.get(2);
		assertEquals(THREE_RECORDID,record.getRecordID());
		assertEquals(THREE_DATE,record.getDate());
		assertEquals(THREE_BUYERID,record.getBuyerID());
		assertEquals(THREE_PRICE,record.getTotalPrice());
		assertEquals(THREE_PRODUCTID,record.getProductID());
		assertEquals(THREE_ADDRESS,record.getAddress());
		
	}
	
	public void testGetRecordByBuyerID() 
	{
		List<Record> buyerRecords = new ArrayList<Record>();
		buyerRecords = recordManager.getRecordsByBuyerID(11);
		
		Record record = buyerRecords.get(0);
		assertEquals(ONE_RECORDID,record.getRecordID());
		assertEquals(ONE_DATE,record.getDate());
		assertEquals(ONE_BUYERID,record.getBuyerID());
		assertEquals(ONE_PRICE,record.getTotalPrice());
		assertEquals(ONE_PRODUCTID,record.getProductID());
		assertEquals(ONE_ADDRESS,record.getAddress());
		
		record = buyerRecords.get(1);
		assertEquals(TWO_RECORDID,record.getRecordID());
		assertEquals(TWO_DATE,record.getDate());
		assertEquals(TWO_BUYERID,record.getBuyerID());
		assertEquals(TWO_PRICE,record.getTotalPrice());
		assertEquals(TWO_PRODUCTID,record.getProductID());
		assertEquals(TWO_ADDRESS,record.getAddress());
		
	}
	
	public void testGetRecordByID() 
	{		
		Record record = recordManager.getRecordByID(1);
		
		assertEquals(ONE_RECORDID,record.getRecordID());
		assertEquals(ONE_DATE,record.getDate());
		assertEquals(ONE_BUYERID,record.getBuyerID());
		assertEquals(ONE_PRICE,record.getTotalPrice());
		assertEquals(ONE_PRODUCTID,record.getProductID());
		assertEquals(ONE_ADDRESS,record.getAddress());		
	}
	
	

}
