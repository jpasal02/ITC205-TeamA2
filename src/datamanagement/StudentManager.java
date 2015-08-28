package datamanagement;

import org.jdom.*;
import java.util.List;

public class StudentManager {
	private static StudentManager studManager_ = null;
	private StudentMap studMap;
	private java.util.HashMap<String, StudentMap> hashMap;

	public static StudentManager getInstance() {
		if (studManager_ == null)
			studManager_ = new StudentManager();

		return studManager_;

	}

	private StudentManager() {
		studMap = new StudentMap();
		hashMap = new java.util.HashMap<>();

	}

	public IStudent getStudent(int id) {
		IStudent student = studMap.get(id);
		return student != null ? student : createStudent(id);

	}

	private Element getStudentElement(Integer id) {
		for (Element element : (List<Element>) XMLManager.getXML()
				.getDocument().getRootElement().getChild("studentTable")
				.getChildren("student"))
			if (id.toString().equals(element.getAttributeValue("sid")))
				return element;
		return null;

	}

	private IStudent createStudent(int id) {
		IStudent student;
		Element studElement = getStudentElement(id);
		if (studElement != null) {
			StudentUnitRecordList recordList = StudentUnitRecordManager
					.instance().getRecordsByStudent(id);
			student = new Student(new Integer(
					studElement.getAttributeValue("sid")),
					studElement.getAttributeValue("fname"),
					studElement.getAttributeValue("lname"), recordList);
			studMap.put(student.getID(), student);
			return student;
		}

		throw new RuntimeException("DBMD: createStudent : student not in file");
	}

	private IStudent createStudentProxy(int id) {
		Element element = getStudentElement(id);
		if (element != null)
			return new StudentProxy(id, element.getAttributeValue("fname"),
					element.getAttributeValue("lname"));
		throw new RuntimeException("DBMD: createStudent : student not in file");

	}

	public StudentMap getStudentsByUnit(String unit) {
		StudentMap stud = hashMap.get(unit);
		if (stud != null) {
			return stud;

		}
		stud = new StudentMap();
		IStudent student;
		StudentUnitRecordList unitRecord = StudentUnitRecordManager.instance()
				.getRecordsByUnit(unit);
		for (IStudentUnitRecord S : unitRecord) {
			student = createStudentProxy(new Integer(S.getStudentID()));
			stud.put(student.getID(), student);
		}

		hashMap.put(unit, stud);
		return stud;
	}

}
