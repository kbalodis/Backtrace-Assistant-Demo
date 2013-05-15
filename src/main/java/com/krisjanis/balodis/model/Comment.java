package com.krisjanis.balodis.model;

import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;  
import javax.persistence.Table;  
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity  
@Table(name="COMMENT") 
public class Comment {
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name = "ID", nullable = false, unique = true)  
	private Integer id;  
	
	@NotNull
	@Size(min = 0, max = 150)
	@Column(name="AUTHOR")  
	private String author; 
	
	@NotNull
	@Size(min = 1, max = 2500, message = "Please, enter some comment!")
	@Column(name="COMMENT", nullable = false)  
	private String comment; 
	
	@Column(name="DATE_ADDED", nullable = false)
	private String date;
	
	@Column(name="IS_DELETED", nullable = false)
	private Boolean isDeleted;
	
	public Integer getId() {  
		return id;  
	}  
	  
	public void setId(Integer id) {  
		this.id = id;  
	}  
	  
	public String getAuthor() {  
		return author;  
	}  
	  
	public void setAuthor(String author) {  
		this.author = author;  
	}  
	
	public String getComment() {  
		return comment;  
	}  
	  
	public void setComment(String comment) {  
		this.comment = comment;  
	}
	 
	public String getDate() {
		return date;
	}
	 
	public void setDate(String date) {
		this.date = date;
	}
	
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	 
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
