package com.krisjanis.balodis.model;

import java.util.Set;

import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;  
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity  
@Table(name="VERSION")
public class Version {
	 
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name = "ID", nullable = false, unique = true)  
	private Integer id;  
	
	@NotNull
    @Size(min = 1, max = 150, message = "Please, enter the software version name!")
	@Column(name="VERSION", nullable = false, unique = true)  
	private String version; 
	
	@NotNull
	@Size(min = 10, max = 10, message = "Please, select the date the software version was released!")
	@Column(name="DATE_RELEASED", nullable = false)
	private String dateReleased;
	
	@Column(name="DATE_ADDED", nullable = false)
	private String date;
	
	@Column(name="DATE_MODIFIED", nullable = false)
	private String dateModified;
	
	@Column(name="IS_DELETED", nullable = false)
	private Boolean isDeleted;
	
	@OneToMany(mappedBy="versionId")
    private Set <Problem> problems;
	
	public Integer getId() {  
		return id;  
	}  
	  
	public void setId(Integer id) {  
		this.id = id;  
	}  
	  
	public String getVersion() {  
		return version;  
	}  
	  
	public void setVersion(String version) {  
		this.version = version;  
	}	
	
	public String getDateReleased() {  
		return dateReleased;  
	}  
	  
	public void setDateReleased(String dateReleased) {  
		this.dateReleased = dateReleased;  
	}	
	
	public String getDate() {
		return date;
	}
	 
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDateModified() {
		return dateModified;
	}
	 
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}
	
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	 
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
