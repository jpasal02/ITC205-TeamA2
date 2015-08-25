package datamanagement;

public interface IStudentUnitRecord {

	public Integer getStudentID();

	public String getUnitCode();

	public void setAssignment1(float mark);

	public float getAssignment1();

	public void setAssignment2(float mark);

	public float getAssignment2();

	public void setExam(float mark);

	public float getExam();

	public float getTotal();
}
