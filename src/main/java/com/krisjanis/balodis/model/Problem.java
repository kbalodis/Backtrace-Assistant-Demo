package com.krisjanis.balodis.model;

import java.util.Set;

import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;  
import javax.persistence.OneToMany;
import javax.persistence.Table; 
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity  
@Table(name="PROBLEM") 
public class Problem {
	
	 @Id  
	 @GeneratedValue(strategy=GenerationType.AUTO)  
	 @Column(name = "ID", nullable = false, unique = true)  
	 private Integer id;  
	 
	 @NotNull
	 @Size(min = 1, max = 150, message = "Problem name is a required field!")
	 @Column(name="PROBLEM", nullable = false, unique = true)  
	 private String problem; 
	 
	 @NotNull
	 @Size(min = 10, max = 10, message = "Date the problem was reported is a required field!")
	 @Column(name="DATE_REPORTED", nullable = false)
	 private String dateReported;
	 
	 @ManyToOne
	 @JoinColumn(name="VERSION_ID", insertable = false, updatable = false)
	 private Version versionId;
	 
	 @NotNull(message = "Please select a version for the reported problem!")
	 @Column(name="VERSION_ID", nullable = false)
	 private Integer versId;
	 
		
	 @OneToMany(mappedBy="problemId")
	 private Set <Backtrace> backtraces;
		
	 
	 public Problem() {
         
	 }
	     
	 public Problem(String problem, String dateReported) {
		 setProblem(problem);
		 setDateReported(dateReported);
	 }	 
	 
	 public Integer getId() {  
		 return id;  
	 }  
	  
	 public void setId(Integer id) {  
		 this.id = id;  
	 }  
	  
	 public String getProblem() {  
		 return problem;  
	 }  
	  
	 public void setProblem(String problem) {  
		 this.problem = problem;  
	 }
	 
	 public String getDateReported() {
		return dateReported;
	 }
	 
	 public void setDateReported(String dateReported) {
		 this.dateReported = dateReported;
	 }
	 
	 public Version getVersionId() {
		 return versionId;
	 }
	 
	 public void setVersionId(Version versionId) {
		 this.versionId = versionId;
	 } 
	 
	 public Integer getVersId() {
		 return versId;
	 }
		 
	 public void setVersId(Integer versId) {
		 this.versId = versId;
	 } 
}
