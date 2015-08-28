package datamanagement;

public class StudentUnitRecord implements IStudentUnitRecord {
	private Integer studentID;
	private String unitCode;
	private float assignment1, assignment2, exam;

	public StudentUnitRecord(Integer id, String code, float asg1, float asg2, float exam) {
		this.studentID = id;
		this.unitCode = code;
		this.setAsg1(asg1);
		this.setAsg2(asg2);
		this.setExam(exam);
	}

	public Integer getStudentID() {
		return this.studentID;
	}

	public String getUnitCode() {
		return this.unitCode;
	}

	public void setAsg1(float assignment1) {
		if (assignment1 < 0 ||
				assignment1 > UnitManager.unitManager().getUnit(unitCode).getAsg1Weight()) {
			throw new RuntimeException("Mark cannot be less than zero or greater than assessment weight");
		}
		this.assignment1 = assignment1;
	}

	public float getAsg1() {

		return assignment1;
	}

	public void setAsg2(float a2) {
		if (a2 < 0 ||
			a2 > UnitManager.unitManager().getUnit(unitCode).getAsg2Weight()) {
			throw new RuntimeException("Mark cannot be less than zero or greater than assessment weight");
		}
		this.assignment2 = assignment2;

	}

	public float getAsg2() {
		return assignment2;
	}

	public void setExam(float exam) {
		if (exam < 0 ||
				exam > UnitManager.unitManager().getUnit(unitCode).getExamWeight()) {
				throw new RuntimeException("Mark cannot be less than zero or greater than assessment weight");
			}
		this.exam = exam;
	}

	public float getExam() {
		return exam;
	}

	public float getTotal() {
		return assignment1 + assignment2 + exam;

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
