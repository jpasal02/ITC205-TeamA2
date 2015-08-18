package datamanagement;

public class Student implements IStudent {
	private Integer studentId;
	private String firstName;
	private String lastName;
	private StudentUnitRecordList studentUnitRecord;

	public Student(Integer studentId, String firstName, String lastName, StudentUnitRecordList studentUnitRecord) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentUnitRecord = studentUnitRecord == null ? new StudentUnitRecordList() : studentUnitRecord;
	}

	public Integer getId() {
		return this.studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {

		this.lastName = lastName;
	}

	public void addUnitRecord(IStudentUnitRecord record) {
		studentUnitRecord.add(record);
	}

	public IStudentUnitRecord getUnitRecord(String unitCode) {
		for (IStudentUnitRecord record : studentUnitRecord)
			if (record.getUnitCode().equals(unitCode))
				return record;

		return null;

	}

	public StudentUnitRecordList getUnitRecords() {
		return studentUnitRecord;
	}
}
