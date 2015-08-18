package datamanagement;

public class ListStudentsControl {
	private StudentManager unitManager;

	public ListStudentsControl() {
		unitManager = StudentManager.get();
	}

	public void listStudents(IStudentLister lister, String unitCode) {
		lister.clearStudents();
		StudentMap students = unitManager.getStudentsByUnit(unitCode);
		for (Integer studentId : students.keySet())
			lister.addStudent(students.get(studentId));
	}
}
