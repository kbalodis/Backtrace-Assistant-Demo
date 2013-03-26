package com.krisjanis.balodis.dao;  
  
import java.util.List;  

import com.krisjanis.balodis.model.Backtrace;
import com.krisjanis.balodis.model.Problem;
import com.krisjanis.balodis.model.Version;
  
  
public interface BacktraceDao {  
   
	public void addBacktrace(Backtrace backtrace);  
  
	public List<Backtrace> listBacktraces();  
    
	public void removeBacktrace(Integer id);
 
	public void addProblem(Problem problem);  
 
	public List<Problem> listProblems();  
    
	public void removeProblem(Integer id); 
 
	public void addVersion(Version version);  
 
	public List<Version> listVersions();  
    
	public void removeVersion(Integer id);
	
	public List<Object> listAllProblemsVersions();
	
	public List<Object> listObject(Class<?> c);
	
	public Boolean strObjParm(String str, Class <?> c, String column);
	
}