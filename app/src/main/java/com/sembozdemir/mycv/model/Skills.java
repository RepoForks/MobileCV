
package com.sembozdemir.mycv.model;

import java.util.List;

public class Skills{
   	private List<String> keywords;
   	private String level;
   	private String name;

 	public List<String> getKeywords(){
		return this.keywords;
	}
	public void setKeywords(List<String> keywords){
		this.keywords = keywords;
	}
 	public String getLevel(){
		return this.level;
	}
	public void setLevel(String level){
		this.level = level;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
}
