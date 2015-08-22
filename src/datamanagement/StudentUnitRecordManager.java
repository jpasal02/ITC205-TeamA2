package datamanagement;

import java.util.List;
import org.jdom.*;

public class StudentUnitRecordManager {

	private static StudentUnitRecordManager student = null;
	private StudentUnitRecordMap recordMap;
    private java.util.HashMap<String,StudentUnitRecordList> unitRecord;
    private java.util.HashMap<Integer,StudentUnitRecordList> studentRecord;
    
    public static StudentUnitRecordManager instance() {
    	if (student == null ) student = new StudentUnitRecordManager();
    	return student;
    	
    }
    
    private StudentUnitRecordManager() {
    	recordMap = new StudentUnitRecordMap();
    	unitRecord = new java.util.HashMap<>();
    	studentRecord = new java.util.HashMap<>();
    
    }
    
    public IStudentUnitRecord getStudentUnitRecord( Integer studentID, String unitCode ) {
    	IStudentUnitRecord ir = recordMap.get(studentID.toString()+unitCode);
    	return ir != null ? ir : createStudentUnitRecord(studentID, unitCode);
    	
    }

    private IStudentUnitRecord createStudentUnitRecord( Integer uid, String sid ) {
    	IStudentUnitRecord ir;
    	for (Element el : (List<Element>) XMLManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record")) {
    		if (uid.toString().equals(el.getAttributeValue("sid")) && sid.equals(el.getAttributeValue("uid"))) {
    			ir = new StudentUnitRecord( new Integer(el.getAttributeValue("sid")),el.getAttributeValue("uid"),new Float(el.getAttributeValue("asg1")).floatValue(),new Float(el.getAttributeValue("asg2")).floatValue(),new Float(el.getAttributeValue("exam")).floatValue() );
    			recordMap.put(ir.getStudentID().toString()+ir.getUnitCode(), ir);return ir;
    		}
    	}
    	
    	throw new RuntimeException("DBMD: createStudent : student unit record not in file");}
      	public StudentUnitRecordList getRecordsByUnit( String unitCode ) {
      		
      		StudentUnitRecordList recs = unitRecord.get(unitCode);
      		
      		if ( recs != null ) return recs; 
      		recs = new StudentUnitRecordList();
      		
      		for (Element el : (List<Element>) XMLManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record")) {
      			if (unitCode.equals(el.getAttributeValue("uid"))) recs.add(new StudentUnitRecordProxy( new Integer(el.getAttributeValue("sid")), el.getAttributeValue("uid")));
      		}
      		
      		if ( recs.size() > 0 ) 
      			unitRecord.put(unitCode, recs); // be careful - this could be empty
      		return recs;
      		
      	}

      	public StudentUnitRecordList getRecordsByStudent( Integer studentID ) {
      		
      		StudentUnitRecordList recs = studentRecord.get(studentID);
      		
      		if ( recs != null ) 
      			return recs; recs = new StudentUnitRecordList();
      			
      		for (Element el : (List<Element>) XMLManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record")) 
      			if (studentID.toString().equals(el.getAttributeValue("sid"))) 
      				recs.add(new StudentUnitRecordProxy( new Integer(el.getAttributeValue("sid")), el.getAttributeValue("uid")));
      		
      		if ( recs.size() > 0 ) 
      			studentRecord.put(studentID, recs); // be careful - this could be empty
      		
      		return recs;
      	}

      	public void saveRecord( IStudentUnitRecord irec ) {
      		
      		for (Element el : (List<Element>) XMLManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record")) {
      			if (irec.getStudentID().toString().equals(el.getAttributeValue("sid")) && irec.getUnitCode().equals(el.getAttributeValue("uid"))) {
      				
      				el.setAttribute("asg1", new Float(irec.getAsg1()).toString());
      				el.setAttribute("asg2", new Float(irec.getAsg2()).toString());
      				el.setAttribute("exam", new Float(irec.getExam()).toString());
      				XMLManager.getXML().saveDocument(); //write out the XML file for continuous save
      				return;
      		
      			}

      		}

      		throw new RuntimeException("DBMD: saveRecord : no such student record in data");
      		
      	}
}
