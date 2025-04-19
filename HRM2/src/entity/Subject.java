package entity;

public abstract class Subject implements ICreditSubject{
	private String subjectName;
	private String subjectCode;
	private int credit;
	
	public Subject() {
		
	}
	
	public Subject(String subjectCode, String subjectName,int credit) {
		this.subjectName = subjectName;
		this.subjectCode = subjectCode;
		this.credit = credit;
	}	
	
	public String getSubjectCode() {
		return subjectCode;
	}
	
	public String getSubjectName() {
		return subjectName;
	}

	public int getCredit() {
		return credit;
	}
	
	public float calConversionMark(String grade) {
        switch (grade) {
        case "A": return 4.0f;
        case "B+": return 3.5f;
        case "B": return 3.0f;
        case "C+": return 2.5f;
        case "C": return 2.0f;
        case "D+": return 1.5f;
        case "D": return 1.0f;
        default: return 0.0f;
        }
	}
	
	public String calGrade() {
        float subjectMark = calSubjectMark();
        if (subjectMark >= 8.5) return "A";
        if (subjectMark >= 7.5) return "B+";
        if (subjectMark >= 7.0) return "B";
        if (subjectMark >= 6.5) return "C+";
        if (subjectMark >= 6.0) return "C";
        if (subjectMark >= 5.5) return "D+";
        if (subjectMark >= 5.0) return "D";
        return "F";
	}

	@Override
	public String toString() {
	    return "\n    - Mã môn học   : " + subjectCode
	    		+ "\n    - Tên môn học  : " + subjectName
	    		+ "\n    - Số tín chỉ   : " + credit;
	    }

}
