package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.domain.Record;

@Service(value="recordManager")
@Transactional
public class DatabaseRecordManager implements RecordManager{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Record> getAllRecords() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("FROM Record").list();
	}

	@Override
	public void addRecord(Record oneRecord) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(oneRecord);
	}

	@Override
	public Record getRecordByID(long recordID) {
		// TODO Auto-generated method stub
		Session currentSession = this.sessionFactory.getCurrentSession();
		Record theRecord = (Record) currentSession.get(Record.class, recordID);
		return theRecord;
	}
	
	@Override
	public List<Record> getRecordsByBuyerID(long buyerID)
	{
		List<Record> records = new ArrayList<Record>();
		List<Record> allRecords = new ArrayList<Record>();
		allRecords = sessionFactory.getCurrentSession().createQuery("FROM Record").list();

		
		for(Record oneRecord : allRecords) 
		{
			if(oneRecord.getBuyerID() == (buyerID))
			{
				records.add(oneRecord);
			}
		}
		
		return records;
	}

}

