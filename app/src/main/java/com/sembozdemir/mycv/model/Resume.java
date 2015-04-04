
package com.sembozdemir.mycv.model;

import java.util.List;

public class Resume{
   	private List<Awards> awards; //
   	private Basics basics;
   	private List<Education> education;
   	private List<Interests> interests;
   	private List<Languages> languages;
   	private List<Publications> publications;
   	private List<References> references;
   	private List<Skills> skills;
   	private List<Volunteer> volunteer;
   	private List<Work> work;

 	public List<Awards> getAwards(){
		return this.awards;
	}
	public void setAwards(List<Awards> awards){
		this.awards = awards;
	}
 	public Basics getBasics(){
		return this.basics;
	}
	public void setBasics(Basics basics){
		this.basics = basics;
	}
 	public List<Education> getEducation(){
		return this.education;
	}
	public void setEducation(List<Education> education){
		this.education = education;
	}
 	public List<Interests> getInterests(){
		return this.interests;
	}
	public void setInterests(List<Interests> interests){
		this.interests = interests;
	}
 	public List<Languages> getLanguages(){
		return this.languages;
	}
	public void setLanguages(List<Languages> languages){
		this.languages = languages;
	}
 	public List<Publications> getPublications(){
		return this.publications;
	}
	public void setPublications(List<Publications> publications){
		this.publications = publications;
	}
 	public List<References> getReferences(){
		return this.references;
	}
	public void setReferences(List<References> references){
		this.references = references;
	}
 	public List<Skills> getSkills(){
		return this.skills;
	}
	public void setSkills(List<Skills> skills){
		this.skills = skills;
	}
 	public List<Volunteer> getVolunteer(){
		return this.volunteer;
	}
	public void setVolunteer(List<Volunteer> volunteer){
		this.volunteer = volunteer;
	}
 	public List<Work> getWork(){
		return this.work;
	}
	public void setWork(List<Work> work){
		this.work = work;
	}
}
