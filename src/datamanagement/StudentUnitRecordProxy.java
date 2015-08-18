package datamanagement;

public class StudentUnitRecordProxy implements IStudentUnitRecord {
	private Integer studentId;
	private String unitCode;
	private StudentUnitRecordManager recordManager;

	public StudentUnitRecordProxy(Integer id, String code) {
		this.studentId = id;
		this.unitCode = code;
		this.recordManager = StudentUnitRecordManager.instance();
	}

	public Integer getStudentId() {
		return studentId;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setAssignment1(float assignment1Mark) {
		recordManager.getStudentUnitRecord(studentId, unitCode).setAssignment1(assignment1Mark);
	}

	public float getAssignment1() {
		return recordManager.getStudentUnitRecord(studentId, unitCode).getAssignment1();
	}

	public void setAssignment2(float assignment2Mark) {
		recordManager.getStudentUnitRecord(studentId, unitCode).setAssignment2(assignment2Mark);
	}

	public float getAssignment2() {
		return recordManager.getStudentUnitRecord(studentId, unitCode).getAssignment2();
	}

	public void setExam(float examMark) {
		recordManager.getStudentUnitRecord(studentId, unitCode).setExam(examMark);
	}

	public float getExam() {
		return recordManager.getStudentUnitRecord(studentId, unitCode).getExam();
	}

	public float getTotal() {
		return recordManager.getStudentUnitRecord(studentId, unitCode).getTotal();
	}
}
