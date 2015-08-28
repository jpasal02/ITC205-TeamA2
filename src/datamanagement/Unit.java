package datamanagement;

public class Unit implements IUnit {
	private String unitCode;
	private String unitName;
	private float passCutOff;
	private float creditCutOff;
	private float distinctCutOff;
	private float HDCutOff;
	private float additionalExamCutOff;
	private int assignment1, assignment2, examination;

	private StudentUnitRecordList studentRecord;

	public Unit(String uc, String un, float ps, float cr, float di, float hd,
			float ae, int asg1, int asg2, int exam, StudentUnitRecordList rl) {

		this.unitCode = uc;
		this.unitName = un;
		this.passCutOff = ps;
		this.creditCutOff = cr;
		this.distinctCutOff = di;
		this.HDCutOff = hd;
		this.additionalExamCutOff = ae;
		this.setAssessmentWeights(asg1, asg2, exam);
		this.studentRecord = rl == null ? new StudentUnitRecordList() : rl;
	}

	public String getUnitCode() {
		return this.unitCode;
	}

	public String getUnitName() {

		return this.unitName;
	}

	public void setPsCutoff1(float cutoff) {
		this.passCutOff = cutoff;
	}

	public float getPsCutoff() {
		return this.passCutOff;
	}

	public void setCrCutoff(float cutoff) {
		this.creditCutOff = cutoff;
	}

	public float getCrCutoff() {
		return this.creditCutOff;
	}

	public void setDiCutoff(float cutoff) {
		this.distinctCutOff = cutoff;
	}

	public float getDiCuttoff() {
		return this.distinctCutOff;
	}

	public void setHdCutoff(float cutoff) {
		this.HDCutOff = cutoff;
	}

	public float getHdCutoff() {
		return this.HDCutOff;

	}

	public void setAeCutoff(float cutoff) {
		this.additionalExamCutOff = cutoff;
	}

	public float getAeCutoff() {
		return this.additionalExamCutOff;
	}

	public void addStudentRecord(IStudentUnitRecord record) {
		studentRecord.add(record);
	}

	public IStudentUnitRecord getStudentRecord(int studentID) {
		for (IStudentUnitRecord r : studentRecord) {
			if (r.getStudentID() == studentID)
				return r;
		}
		return null;
	}

	public StudentUnitRecordList listStudentRecords() {
		return studentRecord;
	}

	@Override
	public int getAsg1Weight() {
		return assignment1;
	}

	@Override
	public int getAsg2Weight() {
		return assignment2;
	}

	@Override
	public int getExamWeight() {
		return examination;
	}

	@Override
	public void setAssessmentWeights(int a1, int a2, int ex) {
		if (a1 < 0 || a1 > 100 || a2 < 0 || a2 > 100 || ex < 0 || ex > 100) {
			throw new RuntimeException(
					"Assessment weights cant be less than zero or greater than 100");
		}
		if (a1 + a2 + ex != 100) {
			throw new RuntimeException("Assessment weights must add to 100");
		}
		this.assignment1 = a1;
		this.assignment2 = a2;
		this.examination = ex;
	}

	private void setCutoffs(float ps, float cr, float di, float hd, float ae) {
		if (ps < 0 || ps > 100 || cr < 0 || cr > 100 || di < 0 || di > 100
				|| hd < 0 || hd > 100 || ae < 0 || ae > 100) {
			throw new RuntimeException(
					"Assessment cutoffs cant be less than zero or greater than 100");
		}
		if (ae >= ps) {
			throw new RuntimeException("AE cutoff must be less than PS cutoff");
		}
		if (ps >= cr) {
			throw new RuntimeException("PS cutoff must be less than CR cutoff");
		}
		if (cr >= di) {
			throw new RuntimeException("CR cutoff must be less than DI cutoff");
		}
		if (di >= hd) {
			throw new RuntimeException("DI cutoff must be less than HD cutoff");
		}

	}

	public String getGrade(float f1, float f2, float f3) {
		float total = f1 + f2 + f3;

		if (f1 < 0 || f1 > assignment1 || f2 < 0 || f2 > assignment2 || f3 < 0
				|| f3 > examination) {
			throw new RuntimeException(
					"marks cannot be less than zero or greater than assessment weights");
		}

		if (total > HDCutOff)
			return "HD";
		else if (total > distinctCutOff)
			return "DI";
		else if (total > creditCutOff)
			return "CR";
		else if (total > passCutOff)
			return "PS";
		else if (total > additionalExamCutOff)
			return "AE";
		else
			return "FL";
	}

}