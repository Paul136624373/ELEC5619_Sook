package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;

import au.usyd.elec5619.domain.Record;

public class SimpleRecordManager implements RecordManager{
	
	private List<Record> allRecords;
	
	public List<Record> getAllRecords(){
		return allRecords;
	}
	
	public List<Record> getRecordsByBuyerID(long buyerID){
		
		List<Record> records = new ArrayList<Record>();
		
		for(Record oneRecord : allRecords) 
		{
			if(oneRecord.getBuyerID() == (buyerID))
			{
				records.add(oneRecord);
			}
		}
		
		return records;
	}
	
	public Record getRecordByID(long recordID) {
		
		Record theRecord = new Record();
		
		for(Record oneRecord : allRecords) 
		{
			if(oneRecord.getRecordID() == recordID)
			{
				theRecord = oneRecord;
			}
		}
		
		return theRecord;
	}
	
	@Override
	public void addRecord(Record oneRecord) {
		allRecords.add(oneRecord);		
	}
	

	public void setRecords(List<Record> allRecords) {
		this.allRecords = allRecords;		
	}

}
