
package com.sembozdemir.mycv.model;

import java.util.List;

public class Interests{
   	private List<String> keywords;
   	private String name;

 	public List<String> getKeywords(){
		return this.keywords;
	}
	public void setKeywords(List<String> keywords){
		this.keywords = keywords;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
}
