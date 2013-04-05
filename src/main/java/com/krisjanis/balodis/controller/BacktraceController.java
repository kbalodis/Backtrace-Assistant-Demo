package com.krisjanis.balodis.controller;

import java.util.Map; 
//import java.util.Date;
//import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.validation.BindingResult;  
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;   
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.krisjanis.balodis.model.Backtrace;
import com.krisjanis.balodis.model.Problem;
import com.krisjanis.balodis.model.Version;
import com.krisjanis.balodis.service.BacktraceService;
  
@Controller  
public class BacktraceController {
	
	@Autowired
    private BacktraceService backtraceService;
 
	@RequestMapping("/index")
    public String home() {
		
        return "home";
    }
	
    @RequestMapping("/listBacktraces")
    public String listBacktrace(Map<String, Object> map) {
 
        map.put("backtraceList", backtraceService.listBacktraces());
 
        return "listBacktraces";
    }
 
    @RequestMapping(value = "/addBacktraceForm", method = RequestMethod.GET)
    public String backtraceForm(Map<String, Object> map) {
    	
    	map.put("newBacktrace", new Backtrace());
    	map.put("problemList", backtraceService.listProblems());
    	return "addBacktrace";
    }
    
    @RequestMapping(value = "/addNewBacktrace", method = RequestMethod.POST)
    public String addBacktrace(
    		@Valid @ModelAttribute("newBacktrace") Backtrace newBacktrace,
    		BindingResult result,
    		Map<String, Object> map,
    		RedirectAttributes attributes) {
    	
    	if (!result.hasErrors()){
    		String formInputCoredump = newBacktrace.getName();
    		if (backtraceService.duplicateCheck(formInputCoredump, Backtrace.class, "name")) {
    			String now = (new java.util.Date()).toString();
            	newBacktrace.setDate(now);
            	backtraceService.addBacktrace(newBacktrace);
        		String success = "SUCCESS! You have added a new backtrace. Backtrace count: " + backtraceService.listBacktraces().size();
        		attributes.addFlashAttribute("message", success);
        		return "redirect:/listBacktraces.html";
    		} else {
    			String errorGlobal = "OOPS! Error occured!";
    			map.put("message", errorGlobal);
    			String errorDuplicate = "Duplicate record found!";
        		map.put("messageDuplicate", errorDuplicate);
    			map.put("problemList", backtraceService.listProblems());
        		return "addBacktrace";
    		}
    	} else {
    		String error = "OOPS! Error occured!";
    		map.put("problemList", backtraceService.listProblems());
    		map.put("message", error);
    		return "addBacktrace";
    	}
    }
 
    @RequestMapping("/delete/{backtraceId}")
    public String deleteContact(@PathVariable("backtraceId") Integer backtraceId) {
 
        backtraceService.removeBacktrace(backtraceId);
 
        return "redirect:/listBacktraces.html";
    }
    
    @RequestMapping("/listVersions")
    public String listVersion(Map<String, Object> map) {

        map.put("versionList", backtraceService.listVersions());
 
        return "listVersions";
    }
    
    @RequestMapping(value = "/addVersionForm", method = RequestMethod.GET)
    public String versionForm(Map<String, Object> map) {
    	
    	map.put("newVersion", new Version());
    	return "addVersion";
    }
    
    @RequestMapping(value = "/addNewVersion", method = RequestMethod.POST)
    public String addVersion(
    		@Valid @ModelAttribute("newVersion") Version newVersion, 
    		BindingResult result,
    		Map<String, Object> map,
    		RedirectAttributes attributes) {
    	
    	if (!result.hasErrors()){
    		String formInputVersion = newVersion.getVersion();
    		if (backtraceService.duplicateCheck(formInputVersion, Version.class, "version")){
    			backtraceService.addVersion(newVersion);
        		String success = "SUCCESS! You have added a new software version. Version count: " + backtraceService.listVersions().size();
        		attributes.addFlashAttribute("message", success);
        		return "redirect:/listVersions.html";
    		} else {
    			String errorGlobal = "OOPS! Error occured!";
        		map.put("message", errorGlobal);
        		String errorDuplicate = "Duplicate record found!";
        		map.put("messageDuplicate", errorDuplicate);
        		return "addVersion";
    		}
    	} else {
    		String errorGlobal = "OOPS! Error occured!";
    		map.put("message", errorGlobal);
    		return "addVersion";
    	}
    }
    
    
    @RequestMapping("/listProblems")
    public String listProblems(Map<String, Object> map) {
    	
        map.put("problemList", backtraceService.listProblems());       
        
        return "listProblems";
    }
    
    @RequestMapping(value = "/addProblemForm", method = RequestMethod.GET)
    public String problemForm(Map<String, Object> map) {
    	
    	map.put("newProblem", new Problem());
    	map.put("versionList", backtraceService.listVersions());
    	return "addProblem";
    }
    
    @RequestMapping(value = "/addNewProblem", method = RequestMethod.POST)
    public String addProblem(
    		@Valid @ModelAttribute("newProblem") Problem newProblem, 
    		BindingResult result,
    		Map<String, Object> map,
    		RedirectAttributes attributes) {
    	
    	if (!result.hasErrors()){
    		String formInputProblem = newProblem.getProblem();
    		if (backtraceService.duplicateCheck(formInputProblem, Problem.class, "problem")){
    			backtraceService.addProblem(newProblem);
        		String success = "SUCCESS! You have added a new problem. Problem count: " + backtraceService.listProblems().size();
        		attributes.addFlashAttribute("message", success);
        		return "redirect:/listProblems.html";
    		} else {
    			String errorGlobal = "OOPS! Error occured!";
        		map.put("message", errorGlobal);
        		String errorDuplicate = "Duplicate record found!";
        		map.put("messageDuplicate", errorDuplicate);
        		map.put("versionList", backtraceService.listVersions());
        		return "addProblem";
    		}
    	} else {
    		String error = "OOPS! Error occured!";
    		map.put("message", error);
    		map.put("versionList", backtraceService.listVersions());
    		return "addProblem";
    	}
    }
}
