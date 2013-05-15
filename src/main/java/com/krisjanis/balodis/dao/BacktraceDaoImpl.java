package com.krisjanis.balodis.dao;  
  
import java.util.List;  
  
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;  
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Repository;  

import com.krisjanis.balodis.model.Backtrace;
import com.krisjanis.balodis.model.Comment;
import com.krisjanis.balodis.model.Problem;
import com.krisjanis.balodis.model.Version;
  
  
@Repository 
public class BacktraceDaoImpl implements BacktraceDao {  
  
	@Autowired
	private SessionFactory sessionFactory;  
   
	public void addBacktrace(Backtrace backtrace) {  
		sessionFactory.getCurrentSession().save(backtrace);  
	} 
	
	@SuppressWarnings("unchecked")  
	public List<Backtrace> listBacktraces() { 
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Backtrace.class);
		criteria.add(Restrictions.like("isDeleted", false))
				.addOrder(Order.desc("dateModified"));
		return criteria.list();
	}  
  
	public void removeBacktrace (Integer id){
		Backtrace backtrace = (Backtrace) sessionFactory.getCurrentSession().load(Backtrace.class, id);
		if (null != backtrace) {
			backtrace.setIsDeleted(true);
			sessionFactory.getCurrentSession().update(backtrace);
		}
	}
	
	public Backtrace viewBacktrace (Integer id){
		Backtrace backtrace = (Backtrace) sessionFactory.getCurrentSession().load(Backtrace.class, id);
		if (null != backtrace && backtrace.getIsDeleted() == false) {
			return backtrace;
		} else {
			return null;
		}
	}
	
	public Backtrace getBacktrace (Integer id){
		Backtrace backtrace = (Backtrace) sessionFactory.getCurrentSession().get(Backtrace.class, id);
		if (null != backtrace && backtrace.getIsDeleted() == false) {
			return backtrace;
		} else {
			return null;
		}
	}
	
	public void updateBacktrace (Backtrace backtrace){
		if (null != backtrace) {
			if	(backtrace.getIsDeleted() == false) {
				Backtrace backtraceToUpdate = getBacktrace(backtrace.getId());
				backtraceToUpdate.setName(backtrace.getName());
				backtraceToUpdate.setBacktrace(backtrace.getBacktrace());
				backtraceToUpdate.setDate(backtrace.getDate());
				backtraceToUpdate.setDateModified(backtrace.getDateModified());
				backtraceToUpdate.setIsDeleted(backtrace.getIsDeleted());
				backtraceToUpdate.setProbId(backtrace.getProbId());
				backtraceToUpdate.setProblemId(backtrace.getProblemId());
				sessionFactory.getCurrentSession().update(backtraceToUpdate);				
			}
		}
	}
	
	public void addProblem(Problem problem) {
		sessionFactory.getCurrentSession().save(problem);
	}
	
	@SuppressWarnings("unchecked")   
	public List<Problem> listProblems() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Problem.class);
		criteria.add(Restrictions.like("isDeleted", false))
				.addOrder(Order.desc("dateModified"));
		return criteria.list(); 
	}
	    
	public void removeProblem (Integer id){
		Problem problem = (Problem) sessionFactory.getCurrentSession().load(Problem.class, id);
		if (null != problem) {
			problem.setIsDeleted(true);
			sessionFactory.getCurrentSession().update(problem);
		}
	}
	
	public Problem getProblem (Integer id){
		Problem problem = (Problem) sessionFactory.getCurrentSession().get(Problem.class, id);
		if (null != problem && problem.getIsDeleted() == false) {
			return problem;
		} else {
			return null;
		}
	}
	
	public void updateProblem (Problem problem){
		if (null != problem) {
			if	(problem.getIsDeleted() == false) {
				Problem problemToUpdate = getProblem(problem.getId());
				problemToUpdate.setDate(problem.getDate());
				problemToUpdate.setDateModified(problem.getDateModified());
				problemToUpdate.setProblem(problem.getProblem());
				problemToUpdate.setIsDeleted(problem.getIsDeleted());
				problemToUpdate.setDateReported(problem.getDateReported());
				problemToUpdate.setVersId(problem.getVersId());
				problemToUpdate.setVersionId(problem.getVersionId());
				sessionFactory.getCurrentSession().update(problemToUpdate);				
			}
		}
	}
	 
	public void addVersion(Version version) {
		sessionFactory.getCurrentSession().save(version);
	}
	
	@SuppressWarnings("unchecked")   
	public List<Version> listVersions() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Version.class);
		criteria.add(Restrictions.like("isDeleted", false))
				.addOrder(Order.desc("dateModified"));
		return criteria.list(); 
	}
	    
	public void removeVersion (Integer id){
		Version version = (Version) sessionFactory.getCurrentSession().load(Version.class, id);
		if (null != version) {
			version.setIsDeleted(true);
			sessionFactory.getCurrentSession().update(version);
		}
	}
	
	public Version getVersion (Integer id){
		Version version = (Version) sessionFactory.getCurrentSession().get(Version.class, id);
		if (null != version && version.getIsDeleted() == false) {
			return version;
		} else {
			return null;
		}
	}
	
	public void updateVersion (Version version){
		if (null != version) {
			if	(version.getIsDeleted() == false) {
				Version versionToUpdate = getVersion(version.getId());
				versionToUpdate.setDate(version.getDate());
				versionToUpdate.setDateModified(version.getDateModified());
				versionToUpdate.setVersion(version.getVersion());
				versionToUpdate.setDateReleased(version.getDateReleased());
				versionToUpdate.setIsDeleted(version.getIsDeleted());
				sessionFactory.getCurrentSession().update(versionToUpdate);				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> listAllProblemsVersions() {
		Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(Version.class);
	    criteria.setFetchMode("Problem", FetchMode.JOIN);
	    List <Object> list = criteria.list();
	    return list;
	}
	
	public List<Object> listObject(Class<?> c) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(c);
		@SuppressWarnings("unchecked")
		List <Object> list = criteria.list();
		return list;
	}
	
	public Boolean duplicateCheck(String str, Class <?> c, String column) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(c);
		criteria.add(Restrictions.eq(column, str));
		return criteria.list().isEmpty();
	}
	
	public void addComment(Comment comment) {
		sessionFactory.getCurrentSession().save(comment); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> listComments() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Comment.class);
		criteria.addOrder(Order.asc("date"));
		return criteria.list();
	}
} 