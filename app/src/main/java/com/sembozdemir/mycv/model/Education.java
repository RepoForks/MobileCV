
package com.sembozdemir.mycv.model;

import java.util.List;

public class Education{
   	private String area;
   	private List<String> courses;
   	private String endDate;
   	private String gpa;
   	private String institution;
   	private String startDate;
   	private String studyType;

 	public String getArea(){
		return this.area;
	}
	public void setArea(String area){
		this.area = area;
	}
 	public List<String> getCourses(){
		return this.courses;
	}
	public void setCourses(List<String> courses){
		this.courses = courses;
	}
 	public String getEndDate(){
		return this.endDate;
	}
	public void setEndDate(String endDate){
		this.endDate = endDate;
	}
 	public String getGpa(){
		return this.gpa;
	}
	public void setGpa(String gpa){
		this.gpa = gpa;
	}
 	public String getInstitution(){
		return this.institution;
	}
	public void setInstitution(String institution){
		this.institution = institution;
	}
 	public String getStartDate(){
		return this.startDate;
	}
	public void setStartDate(String startDate){
		this.startDate = startDate;
	}
 	public String getStudyType(){
		return this.studyType;
	}
	public void setStudyType(String studyType){
		this.studyType = studyType;
	}
}
