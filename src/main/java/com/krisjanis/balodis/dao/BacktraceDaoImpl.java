package com.krisjanis.balodis.dao;  
  
import java.util.List;  
  
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;  
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Repository;  

import com.krisjanis.balodis.model.Backtrace;
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
		return sessionFactory.getCurrentSession().createQuery("from Backtrace").list(); 
	}  
  
	public void removeBacktrace (Integer id){
		Backtrace backtrace = (Backtrace) sessionFactory.getCurrentSession().load(Backtrace.class, id);
		if (null != backtrace) {
			sessionFactory.getCurrentSession().delete(backtrace);
		}
	}
	
	public void addProblem(Problem problem) {
		sessionFactory.getCurrentSession().save(problem);
	}
	
	@SuppressWarnings("unchecked")   
	public List<Problem> listProblems() {
		return sessionFactory.getCurrentSession().createQuery("from Problem").list(); 
	}
	    
	public void removeProblem(Integer id) {
		
	}
	 
	public void addVersion(Version version) {
		sessionFactory.getCurrentSession().save(version);
	}
	
	@SuppressWarnings("unchecked")   
	public List<Version> listVersions() {
		return sessionFactory.getCurrentSession().createQuery("from Version").list(); 
	}
	    
	public void removeVersion(Integer id) {
		
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
	
	public Boolean strObjParm(String str, Class <?> c, String column) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(c);
		criteria.add(Restrictions.eq(column, str));
		return criteria.list().isEmpty();
	}
} 