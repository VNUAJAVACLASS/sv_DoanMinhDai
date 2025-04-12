package entity;

public class JavaSubject extends Subject{
	private float attendanceMark;
	private float midExamMark;
	private float finalExamMark;
	
	
	public JavaSubject() {
		super();
	}

	public JavaSubject(String subjectName, String SubjectCode, int credit) {
		super(subjectName,SubjectCode,credit);
	}

	public void setAttendanceMark(float attendanceMark) {
		this.attendanceMark = attendanceMark;
	}

	public void setMidExamMark(float midExamMark) {
		this.midExamMark = midExamMark;
	}
	
	public void setFinalExamMark(float finalExamMark) {
		this.finalExamMark = finalExamMark;
	}

	@Override
	public float calConversionMark() {
		return (attendanceMark * 0.1f) + (midExamMark * 0.3f) + (finalExamMark * 0.6f);
	}

	@Override
	public String toString() {
	    return super.toString() 
	           + "\n        - Chuyen can    : " + attendanceMark
	           + "\n        - Giua ky       : " + midExamMark
	           + "\n        - Cuoi ky       : " + finalExamMark
	           + "\n        - Tong ket(10)  : " + calConversionMark()
	           + "\n        - Tong ket(4)   : " + super.calGrade()
	           + "\n===============================";
	}

}
