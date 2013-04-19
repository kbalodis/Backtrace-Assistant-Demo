package com.krisjanis.balodis.model;  

import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;  
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;  
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
  
@Entity  
@Table(name="BACKTRACE")  
public class Backtrace{  
    
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name = "ID", nullable = false, unique = true)  
	private Integer id;  
	
	@NotNull
	@Size(min = 1, max = 150, message = "Please enter the process name!")
	@Column(name="NAME", nullable = false)  
	private String name; 
	
	@NotNull
	@Size(min = 1, max = 2500, message = "Please enter the backtrace!")
	@Column(name="BACKTRACE", nullable = false)  
	private String backtrace; 
	
	@Column(name="DATE_ADDED", nullable = false)
	private String date;
	
	@ManyToOne
	@JoinColumn(name="PROBLEM_ID", insertable = false, updatable = false)
	private Problem problemId;
	
	@NotNull(message = "Please select the corresponding problem of the backtrace!")
	@Column(name="PROBLEM_ID", nullable = false)
	private Integer probId;
	 
	public Integer getId() {  
		return id;  
	}  
	  
	public void setId(Integer id) {  
		this.id = id;  
	}  
	  
	public String getName() {  
		return name;  
	}  
	  
	public void setName(String name) {  
		this.name = name;  
	}  
	
	public String getBacktrace() {  
		return backtrace;  
	}  
	  
	public void setBacktrace(String backtrace) {  
		this.backtrace = backtrace;  
	}
	 
	public String getDate() {
		return date;
	}
	 
	public void setDate(String date) {
		this.date = date;
	}
	
	public Problem getProblemId() {
		 return problemId;
	 }
	 
	 public void setProblemId(Problem problemId) {
		 this.problemId = problemId;
	 } 
	 
	 public Integer getProbId() {
		 return probId;
	 }
		 
	 public void setProbId(Integer probId) {
		 this.probId = probId;
	 } 
}  