package au.usyd.elec5619.service;

import java.io.Serializable;
import java.util.List;

import au.usyd.elec5619.domain.Record;

public interface RecordManager extends Serializable {
	
	public List<Record> getAllRecords();
	
	public void addRecord(Record oneRecord);
	
	public Record getRecordByID(long recordID);
	
	public List<Record> getRecordsByBuyerID(long buyerID);

}
