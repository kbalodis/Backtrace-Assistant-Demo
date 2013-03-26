package com.krisjanis.balodis.model;

import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;  
import javax.persistence.Table; 

@Entity  
@Table(name="TAG") 
public class Tag {
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name = "ID")  
	private Integer id;  
	   
	@Column(name="TAG")  
	private String tag; 
	
	public Integer getId() {  
		return id;  
	}  
	  
	public void setId(Integer id) {  
		this.id = id;  
	}  
	  
	public String getTag() {  
		return tag;  
	}  
	  
	public void setTag(String tag) {  
		this.tag = tag;  
	}	

}
