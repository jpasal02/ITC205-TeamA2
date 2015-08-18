package datamanagement;

public class ChangeGradeControl {

	ChangeGradeInterface changeGradeUserInterface;
	String cuc = null;
	Integer currentStudentId = null;
	boolean changed = false;

	public ChangeGradeControl() {
	}

	public void execute() {
		changeGradeUserInterface = new ChangeGradeInterface(this);
		changeGradeUserInterface.setState1(false);

		changeGradeUserInterface.setState2(false);
		changeGradeUserInterface.setState3(false);
		changeGradeUserInterface.setState4(false);
		changeGradeUserInterface.setState5(false);
		changeGradeUserInterface.setState6(false);
		changeGradeUserInterface.Refresh3();

		ListUnitsControl listUnitsControl = new ListUnitsControl();
		listUnitsControl.listUnits(changeGradeUserInterface);
		changeGradeUserInterface.setVisible(true);
		changeGradeUserInterface.setState1(true);
	}

	public void unitSelected(String code) {

		if (code.equals("NONE"))
			changeGradeUserInterface.setState2(false);
		else {
			ListStudentsControl listStudentControl = new ListStudentsControl();
			listStudentControl.listStudents(changeGradeUserInterface, code);
			cuc = code;
			changeGradeUserInterface.setState2(true);
		}
		changeGradeUserInterface.setState3(false);
	}

	public void studentSelected(Integer id) {
		currentStudentId = id;
		if (currentStudentId.intValue() == 0) {
			changeGradeUserInterface.Refresh3();
			changeGradeUserInterface.setState3(false);
			changeGradeUserInterface.setState4(false);
			changeGradeUserInterface.setState5(false);
			changeGradeUserInterface.setState6(false);
		}

		else {
			IStudent student = StudentManager.get().getStudent(id);

			IStudentUnitRecord record = student.getUnitRecord(cuc);

			changeGradeUserInterface.setRecord(record);
			changeGradeUserInterface.setState3(true);
			changeGradeUserInterface.setState4(true);
			changeGradeUserInterface.setState5(false);
			changeGradeUserInterface.setState6(false);
			changed = false;

		}
	}

	public String checkGrade(float assignment1Mark, float assignment2Mark,
			float examMark) {
		IUnit unit = UnitManager.unitManager().getUnit(cuc);
		String student = unit.getGrade(assignment1Mark, assignment2Mark,
				examMark);
		changeGradeUserInterface.setState4(true);
		changeGradeUserInterface.setState5(false);
		if (changed) {
			changeGradeUserInterface.setState6(true);
		}
		return student;
	}

	public void enableChangeMarks() {
		changeGradeUserInterface.setState4(false);
		changeGradeUserInterface.setState6(false);
		changeGradeUserInterface.setState5(true);
		changed = true;
	}

	public void saveGrade(float assignment1, float assignment2, float exam) {

		IUnit unit = UnitManager.unitManager().getUnit(cuc);
		IStudent student = StudentManager.get().getStudent(currentStudentId);

		IStudentUnitRecord record = student.getUnitRecord(cuc);
		record.setAssignment1(assignment1);
		record.setAssignment2(assignment2);
		record.setExam(exam);
		StudentUnitRecordManager.instance().saveRecord(record);
		changeGradeUserInterface.setState4(true);
		changeGradeUserInterface.setState5(false);
		changeGradeUserInterface.setState6(false);
	}
}
