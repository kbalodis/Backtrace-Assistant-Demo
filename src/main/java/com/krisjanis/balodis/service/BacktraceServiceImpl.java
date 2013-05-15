package com.krisjanis.balodis.service;  
  
import java.util.List;  
import java.util.Map;
import java.util.HashMap;
  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;   
import org.springframework.transaction.annotation.Transactional;  

import com.krisjanis.balodis.dao.BacktraceDao;
import com.krisjanis.balodis.model.Backtrace;
import com.krisjanis.balodis.model.Comment;
import com.krisjanis.balodis.model.Problem;
import com.krisjanis.balodis.model.Version;
    
@Service  
public class BacktraceServiceImpl implements BacktraceService {  
  
	@Autowired  
	private BacktraceDao backtraceDao;  
   
	@Transactional
	public void addBacktrace(Backtrace backtrace) {  
		backtraceDao.addBacktrace(backtrace);  
	}  
 
	@Transactional
	public List<Backtrace> listBacktraces() {  
		return backtraceDao.listBacktraces();  
	}  
 
	@Transactional
	public void removeBacktrace(Integer id) {  
		backtraceDao.removeBacktrace(id);  
	}   
	
	@Transactional
	public Backtrace viewBacktrace (Integer id){
		return backtraceDao.viewBacktrace(id);
	}
	
	@Transactional	
	public Backtrace getBacktrace (Integer id) {
		return backtraceDao.getBacktrace(id);
	}
	
	@Transactional
	public void updateBacktrace (Backtrace backtrace){
		backtraceDao.updateBacktrace(backtrace);
	}
	
	@Transactional
	public void addProblem(Problem problem) {  
		backtraceDao.addProblem(problem); 
	}
	
	@Transactional
	public List<Problem> listProblems() {  
		return backtraceDao.listProblems();
	}
   
	@Transactional
	public void removeProblem(Integer id) {  
		backtraceDao.removeProblem(id);  
	}   

	@Transactional	
	public Problem getProblem (Integer id) {
		return backtraceDao.getProblem(id);
	}
	
	@Transactional
	public void updateProblem (Problem problem){
		backtraceDao.updateProblem(problem);
	}
	
	@Transactional
	public void addVersion(Version version) {
		backtraceDao.addVersion(version); 
	}
	
	@Transactional
	public List<Version> listVersions() {
		return backtraceDao.listVersions();
	}
	
	public Map<Integer, String> listVersionIdsAndNames(List <Version> versions){
		Map<Integer, String> map = new HashMap<Integer, String>();
		if (versions != null){
			for (Version v : versions) {
				map.put(v.getId(), v.getVersion());
			}
			return map;
		} else {
			return null;
		}
	}
   
	@Transactional
	public void removeVersion(Integer id) {  
		backtraceDao.removeVersion(id);  
	}   

	@Transactional	
	public Version getVersion (Integer id) {
		return backtraceDao.getVersion(id);
	}
	
	@Transactional
	public void updateVersion (Version version){
		backtraceDao.updateVersion(version);
	}
	
	@Transactional
	public List<Object> listAllProblemsVersions() {
		return backtraceDao.listAllProblemsVersions();
	}
	
	@Transactional
	public List<Object> listObject(Class<?> c) {  
		return backtraceDao.listObject(c);  
	}  
	
	@Transactional
	public Boolean duplicateCheck(String str, Class <?> c, String column) {
		return backtraceDao.duplicateCheck(str, c, column);
	}
	
	@Transactional
	public void addComment(Comment comment) {
		backtraceDao.addComment(comment);
	}
	
	@Transactional
	public List<Comment> listComments() {
		return backtraceDao.listComments();
	}
}  