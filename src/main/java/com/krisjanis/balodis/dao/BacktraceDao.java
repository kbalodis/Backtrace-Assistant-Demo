package com.krisjanis.balodis.dao;  
  
import java.util.List;  

import com.krisjanis.balodis.model.Backtrace;
import com.krisjanis.balodis.model.Problem;
import com.krisjanis.balodis.model.Version;
import com.krisjanis.balodis.model.Comment;  
  
public interface BacktraceDao {  
   
	public void addBacktrace(Backtrace backtrace);  
  
	public List<Backtrace> listBacktraces();  
    
	public void removeBacktrace(Integer id);
	
	public Backtrace viewBacktrace (Integer id);
	
	public Backtrace getBacktrace (Integer id);
	
	public void updateBacktrace (Backtrace backtrace);
 
	public void addProblem(Problem problem);  
 
	public List<Problem> listProblems();  
    
	public void removeProblem(Integer id);

	public Problem getProblem (Integer id);
	
	public void updateProblem (Problem problem);
 
	public void addVersion(Version version);  
 
	public List<Version> listVersions();  
    
	public void removeVersion(Integer id);
	
	public Version getVersion (Integer id);
	
	public void updateVersion (Version version);
	
	public List<Object> listAllProblemsVersions();
	
	public List<Object> listObject(Class<?> c);
	
	public Boolean duplicateCheck(String str, Class <?> c, String column);
	
	public void addComment(Comment comment);  
	  
	public List<Comment> listComments();  
	
}