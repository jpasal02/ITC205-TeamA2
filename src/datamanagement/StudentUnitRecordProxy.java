package datamanagement;

public class StudentUnitRecordProxy implements IStudentUnitRecord {
	private Integer studentID;
	private String unitCode;
	private StudentUnitRecordManager mngr;

	public StudentUnitRecordProxy(Integer id, String code) {
		this.studentID = id;
		this.unitCode = code;
		this.mngr = StudentUnitRecordManager.instance();
	}

	public Integer getStudentID() {
		return studentID;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setAsg1(float mark) {
		mngr.getStudentUnitRecord(studentID, unitCode).setAssignment1(mark);
	}

	public float getAsg1() {
		return mngr.getStudentUnitRecord(studentID, unitCode).getAssignment1();
	}

	public void setAsg2(float mark) {
		mngr.getStudentUnitRecord(studentID, unitCode).setAssignment2(mark);
	}

	public float getAsg2() {
		return mngr.getStudentUnitRecord(studentID, unitCode).getAssignment2();
	}

	public void setExam(float mark) {
		mngr.getStudentUnitRecord(studentID, unitCode).setExam(mark);
	}

	public float getExam() {
		return mngr.getStudentUnitRecord(studentID, unitCode).getExam();
	}

	public float getTotal() {
		return mngr.getStudentUnitRecord(studentID, unitCode).getTotal();
	}

	@Override
	public void setAssignment1(float mark) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getAssignment1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAssignment2(float mark) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getAssignment2() {
		// TODO Auto-generated method stub
		return 0;
	}
}
