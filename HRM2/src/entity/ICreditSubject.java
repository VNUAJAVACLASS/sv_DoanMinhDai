package entity;

public interface ICreditSubject {
	float calSubjectMark();
	float calConversionMark(String grade);
	String calGrade();
	String getSubjectCode();
	String getSubjectName();
	int getCredit() ;

}
