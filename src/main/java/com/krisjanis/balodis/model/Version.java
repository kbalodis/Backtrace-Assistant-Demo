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
    @Size(min = 1, max = 150, message = "Software version name is a required field!")
	@Column(name="VERSION", nullable = false, unique = true)  
	private String version; 
	
	@NotNull
	@Size(min = 10, max = 10, message = "Date the software version was released is a required field!")
	@Column(name="DATE_RELEASED", nullable = false)
	private String dateReleased;
	
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
}
