import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import static org.junit.Assert.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.krisjanis.balodis.controller.BacktraceController;
import com.krisjanis.balodis.model.Backtrace;
import com.krisjanis.balodis.model.Comment;
import com.krisjanis.balodis.model.Problem;
import com.krisjanis.balodis.model.Version;
import com.krisjanis.balodis.service.BacktraceService;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BacktraceController.class)
public class BacktraceControllerTest {

	@InjectMocks
	private BacktraceController backtraceController;
	   
    @Mock
    private BacktraceService mockBacktraceService;
	   
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);  
    }

    @SuppressWarnings("unchecked")
	@Test
    public void testIndex() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Backtrace> backtraces = new ArrayList<Backtrace>();
    	final List<Problem> problems = new ArrayList<Problem>();
    	final List<Version> versions = new ArrayList<Version>();
        
        final Version version1 = Mockito.mock(Version.class);
        final Version version2 = Mockito.mock(Version.class);
 
        versions.add(version1);
        versions.add(version2);
        
        final Problem problem1 = Mockito.mock(Problem.class);
        final Problem problem2 = Mockito.mock(Problem.class);
        
        problems.add(problem1);
        problems.add(problem2);

        final Backtrace backtrace1 = Mockito.mock(Backtrace.class);       
        final Backtrace backtrace2 = Mockito.mock(Backtrace.class); 
        
        backtraces.add(backtrace1);
        backtraces.add(backtrace2);
       
        Map<String, Object> map = Mockito.mock(Map.class);
        
        //configure the mocked objects
        when(version1.getId()).thenReturn(new Integer(1));
        when(version2.getId()).thenReturn(new Integer(2));
        when(problem1.getId()).thenReturn(new Integer(1));
        when(problem2.getId()).thenReturn(new Integer(2));
        when(backtrace1.getId()).thenReturn(new Integer(1));
        when(backtrace2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listVersions()).thenReturn(versions);
        when(mockBacktraceService.listProblems()).thenReturn(problems);
        when(mockBacktraceService.listBacktraces()).thenReturn(backtraces);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.home(map);
        
        //testing
        Mockito.verify(map).put("versionCount", versions.size());
        Mockito.verify(map).put("problemCount", problems.size());
        Mockito.verify(map).put("backtraceCount", backtraces.size());
        assertNotNull(returned);
        assertEquals("home", returned);
}
    
    @SuppressWarnings("unchecked")
   	@Test
    public void testListBacktrace() throws Exception {
    	
    	//prepare mock objects/data
       	final List<Backtrace> backtraces = new ArrayList<Backtrace>();

        final Backtrace backtrace1 = Mockito.mock(Backtrace.class);       
        final Backtrace backtrace2 = Mockito.mock(Backtrace.class);
        backtraces.add(backtrace1);
        backtraces.add(backtrace2);
          
        Map<String, Object> map = Mockito.mock(Map.class);
    
        when(backtrace1.getId()).thenReturn(new Integer(1));          
        when(backtrace2.getId()).thenReturn(new Integer(2));                  
        when(mockBacktraceService.listBacktraces()).thenReturn(backtraces);    
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.listBacktrace(map);
           
        //testing
        Mockito.verify(map).put("backtraceList", backtraces);
        assertNotNull(returned);
        assertEquals("listBacktraces", returned);
    }
    
    @SuppressWarnings("unchecked")
   	@Test
    public void testViewBacktrace() throws Exception {
    	
    	//prepare mock objects/data
       	final List<Backtrace> backtraces = new ArrayList<Backtrace>();

        final Backtrace backtrace1 = Mockito.mock(Backtrace.class);       
        final Backtrace backtrace2 = Mockito.mock(Backtrace.class);
        backtraces.add(backtrace1);
        backtraces.add(backtrace2);
          
        Map<String, Object> map = Mockito.mock(Map.class);
    
        when(backtrace1.getId()).thenReturn(new Integer(1));          
        when(backtrace2.getId()).thenReturn(new Integer(2));                  
        when(mockBacktraceService.listBacktraces()).thenReturn(backtraces);    
        when(mockBacktraceService.viewBacktrace(new Integer(1))).thenReturn(backtrace1);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.viewBacktrace(backtrace1.getId(), map);
           
        //testing
        Mockito.verify(map).put("backtrace", backtrace1);
        assertNotNull(returned);
        assertEquals("viewBacktrace", returned);
    }

    @SuppressWarnings("unchecked")
   	@Test
    public void testListProblem() throws Exception {
    	
    	//prepare mock objects/data
       	final List<Problem> problems = new ArrayList<Problem>();

        final Problem problem1 = Mockito.mock(Problem.class);  
        final Problem problem2 = Mockito.mock(Problem.class);
        problems.add(problem1);
        problems.add(problem2);
        
        Map<String, Object> map = Mockito.mock(Map.class);
                     
        when(problem1.getId()).thenReturn(new Integer(1));           
        when(problem2.getId()).thenReturn(new Integer(2));           
        when(mockBacktraceService.listProblems()).thenReturn(problems);          
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.listProblems(map);
           
        //testing
        Mockito.verify(map).put("problemList", problems);
        assertNotNull(returned);
        assertEquals("listProblems", returned);
    }

    @SuppressWarnings("unchecked")
   	@Test
    public void testListVersion() throws Exception {

    	//prepare mock objects/data
       	final List<Version> versions = new ArrayList<Version>();
           
        final Version version1 = Mockito.mock(Version.class);
        final Version version2 = Mockito.mock(Version.class);
        versions.add(version1);
        versions.add(version2);

        Map<String, Object> map = Mockito.mock(Map.class);
                     
        when(version1.getId()).thenReturn(new Integer(1));           
        when(version2.getId()).thenReturn(new Integer(2));           
        when(mockBacktraceService.listVersions()).thenReturn(versions);                      
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.listVersion(map);
           
        //testing
        Mockito.verify(map).put("versionList", versions);
        assertNotNull(returned);
        assertEquals("listVersions", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testBacktraceForm() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Problem> problems = new ArrayList<Problem>();
        
        final Problem problem1 = Mockito.mock(Problem.class);
        final Problem problem2 = Mockito.mock(Problem.class);
    
        problems.add(problem1);
        problems.add(problem2);

        final Backtrace backtrace = Mockito.mock(Backtrace.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        //configure the mocked objects
        when(problem1.getId()).thenReturn(new Integer(1));
        when(problem2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listProblems()).thenReturn(problems);
        whenNew(Backtrace.class).withNoArguments().thenReturn(backtrace);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.backtraceForm(map);
        
        //testing
        Mockito.verify(map).put("newBacktrace", backtrace);
        Mockito.verify(map).put("problemList", problems);
        assertNotNull(returned);
        assertEquals("addBacktrace", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testAddBacktraceFails() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Problem> problems = new ArrayList<Problem>();
        
        final Problem problem1 = Mockito.mock(Problem.class);
        final Problem problem2 = Mockito.mock(Problem.class);
    
        problems.add(problem1);
        problems.add(problem2);

        final Backtrace backtrace = Mockito.mock(Backtrace.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attribtes = Mockito.mock(RedirectAttributes.class);
        
        String error = "OOPS! Error occured!";
        
        //configure the mocked objects
        when(problem1.getId()).thenReturn(new Integer(1));
        when(problem2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listProblems()).thenReturn(problems);
        whenNew(Backtrace.class).withNoArguments().thenReturn(backtrace);
        when(result.hasErrors()).thenReturn(true);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.addBacktrace(backtrace, result, map, attribtes);
        
        //testing
        Mockito.verify(map).put("problemList", problems);
        Mockito.verify(map).put("message", error);
        assertNotNull(returned);
        assertEquals("addBacktrace", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testAddBacktracePasses() throws Exception {
    	
    	//prepare mock objects/data
       	final List<Backtrace> backtraces = new ArrayList<Backtrace>();

        final Backtrace backtrace1 = Mockito.mock(Backtrace.class);       
        final Backtrace backtrace2 = Mockito.mock(Backtrace.class);
        backtraces.add(backtrace1);
        backtraces.add(backtrace2);
        
        final Backtrace backtrace = Mockito.mock(Backtrace.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attributes = Mockito.mock(RedirectAttributes.class);
        
        String success = "SUCCESS! You have added a new backtrace. Backtrace count: ";
        
        //configure the mocked objects
        when(result.hasErrors()).thenReturn(false);
        when(mockBacktraceService.listBacktraces()).thenReturn(backtraces);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.addBacktrace(backtrace, result, map, attributes);
        
        //testing
        Mockito.verify(attributes).addFlashAttribute("message", success + backtraces.size());
        assertNotNull(returned);
        assertEquals("redirect:/listBacktraces.html", returned);
    }
    
    @Test
    public void testDeleteBacktrace() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Backtrace> backtraces = new ArrayList<Backtrace>();

        final Backtrace backtrace1 = Mockito.mock(Backtrace.class);       
        final Backtrace backtrace2 = Mockito.mock(Backtrace.class);
        backtraces.add(backtrace1);
        backtraces.add(backtrace2);
        
        RedirectAttributes attributes = Mockito.mock(RedirectAttributes.class);
        
    	String success = "You have successfully deleted a backtrace. Backtrace count: ";
    	
    	//configure the mocked objects       
        when(backtrace1.getId()).thenReturn(new Integer(1));          
        when(backtrace2.getId()).thenReturn(new Integer(2));                  
        when(mockBacktraceService.listBacktraces()).thenReturn(backtraces);    
        when(mockBacktraceService.viewBacktrace(new Integer(1))).thenReturn(backtrace1);

        //method under test
        final String returned = backtraceController.deleteBacktrace(new Integer(0), attributes);

        //testing
        Mockito.verify(attributes).addFlashAttribute("message", success + backtraces.size());
        assertNotNull(returned);
        assertEquals("redirect:/listBacktraces.html", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testBacktraceEditForm() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Problem> problems = new ArrayList<Problem>();
    	final List<Backtrace> backtraces = new ArrayList<Backtrace>();

        final Backtrace backtrace1 = Mockito.mock(Backtrace.class);       
        final Backtrace backtrace2 = Mockito.mock(Backtrace.class);
        backtraces.add(backtrace1);
        backtraces.add(backtrace2);
        
        final Problem problem1 = Mockito.mock(Problem.class);
        final Problem problem2 = Mockito.mock(Problem.class);
    
        problems.add(problem1);
        problems.add(problem2);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        //configure the mocked objects
        when(problem1.getId()).thenReturn(new Integer(1));
        when(problem2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listProblems()).thenReturn(problems);
        when(backtrace1.getId()).thenReturn(new Integer(1));          
        when(backtrace2.getId()).thenReturn(new Integer(2));                  
        when(mockBacktraceService.listBacktraces()).thenReturn(backtraces);    
        when(mockBacktraceService.getBacktrace(new Integer(1))).thenReturn(backtrace1);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.backtraceEditForm(new Integer(1), map);
        
        //testing
        Mockito.verify(map).put("backtrace", backtrace1);
        Mockito.verify(map).put("problemList", problems);
        assertNotNull(returned);
        assertEquals("editBacktrace", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testEditBacktraceFails() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Problem> problems = new ArrayList<Problem>();
        
        final Problem problem1 = Mockito.mock(Problem.class);
        final Problem problem2 = Mockito.mock(Problem.class);
    
        problems.add(problem1);
        problems.add(problem2);

        final Backtrace backtrace = Mockito.mock(Backtrace.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attribtes = Mockito.mock(RedirectAttributes.class);
        
        String error = "OOPS! Error occured!";
        
        //configure the mocked objects
        when(problem1.getId()).thenReturn(new Integer(1));
        when(problem2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listProblems()).thenReturn(problems);
        when(backtrace.getId()).thenReturn(new Integer(1));
        when(result.hasErrors()).thenReturn(true);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.editBacktrace(backtrace, result, backtrace.getId(), map, attribtes);
        
        //testing
        Mockito.verify(map).put("problemList", problems);
        Mockito.verify(map).put("message", error);
        assertNotNull(returned);
        assertEquals("editBacktrace", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testEditBacktracePasses() throws Exception {
    	
    	//prepare mock objects/data
       	final List<Backtrace> backtraces = new ArrayList<Backtrace>();

        final Backtrace backtrace1 = Mockito.mock(Backtrace.class);       
        final Backtrace backtrace2 = Mockito.mock(Backtrace.class);
        backtraces.add(backtrace1);
        backtraces.add(backtrace2);
        
        final Backtrace backtrace = Mockito.mock(Backtrace.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attributes = Mockito.mock(RedirectAttributes.class);
        
        String success = "SUCCESS! You have updated the backtrace. Backtrace count: ";
        
        //configure the mocked objects
        when(result.hasErrors()).thenReturn(false);
        when(mockBacktraceService.listBacktraces()).thenReturn(backtraces);
        when(backtrace.getId()).thenReturn(new Integer(1));
        when(mockBacktraceService.getBacktrace(new Integer(1))).thenReturn(backtrace);
        when(backtrace.getDate()).thenReturn("1987-02-26");
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.editBacktrace(backtrace, result, backtrace.getId(), map, attributes);
        
        //testing
        Mockito.verify(attributes).addFlashAttribute("message", success + backtraces.size());
        assertNotNull(returned);
        assertEquals("redirect:/listBacktraces.html", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testProblemForm() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Version> versions = new ArrayList<Version>();
        
        final Version version1 = Mockito.mock(Version.class);
        final Version version2 = Mockito.mock(Version.class);
    
        versions.add(version1);
        versions.add(version2);

        final Problem problem = Mockito.mock(Problem.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        //configure the mocked objects
        when(version1.getId()).thenReturn(new Integer(1));
        when(version2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listVersions()).thenReturn(versions);
        whenNew(Problem.class).withNoArguments().thenReturn(problem);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.problemForm(map);
        
        //testing
        Mockito.verify(map).put("newProblem", problem);
        Mockito.verify(map).put("versionList", versions);
        assertNotNull(returned);
        assertEquals("addProblem", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testAddProblemHibernateValidationFails() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Version> versions = new ArrayList<Version>();
        
        final Version version1 = Mockito.mock(Version.class);
        final Version version2 = Mockito.mock(Version.class);
    
        versions.add(version1);
        versions.add(version2);

        final Problem problem = Mockito.mock(Problem.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attribtes = Mockito.mock(RedirectAttributes.class);
        
        String error = "OOPS! Error occured!";
        
        //configure the mocked objects
        when(version1.getId()).thenReturn(new Integer(1));
        when(version2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listVersions()).thenReturn(versions);
        whenNew(Problem.class).withNoArguments().thenReturn(problem);
        when(result.hasErrors()).thenReturn(true);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.addProblem(problem, result, map, attribtes);
        
        //testing
        Mockito.verify(map).put("versionList", versions);
        Mockito.verify(map).put("message", error);
        assertNotNull(returned);
        assertEquals("addProblem", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testAddProblemHibernateValidationPassesDuplicateCheckFails () throws Exception {
    	
    	//prepare mock objects/data
    	final List<Version> versions = new ArrayList<Version>();
        
        final Version version1 = Mockito.mock(Version.class);
        final Version version2 = Mockito.mock(Version.class);
    
        versions.add(version1);
        versions.add(version2);

        final Problem problem = Mockito.mock(Problem.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attribtes = Mockito.mock(RedirectAttributes.class);
        
        String error = "OOPS! Error occured!";	        		
        String errorDuplicate = "Duplicate record found!";

        //configure the mocked objects
        when(version1.getId()).thenReturn(new Integer(1));
        when(version2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listVersions()).thenReturn(versions);
        whenNew(Problem.class).withNoArguments().thenReturn(problem);
        when(result.hasErrors()).thenReturn(false);
        when(mockBacktraceService.duplicateCheck(problem.getProblem(), Problem.class, "problem")).thenReturn(false);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.addProblem(problem, result, map, attribtes);
        
        //testing
        Mockito.verify(map).put("versionList", versions);
        Mockito.verify(map).put("message", error);
        Mockito.verify(map).put("messageDuplicate", errorDuplicate);
        assertNotNull(returned);
        assertEquals("addProblem", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testAddProblemsHibernateValidationPassesDuplicateCheckPasses() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Problem> problems = new ArrayList<Problem>();

        final Problem problem1 = Mockito.mock(Problem.class);  
        final Problem problem2 = Mockito.mock(Problem.class);
        problems.add(problem1);
        problems.add(problem2);

        final Problem problem = Mockito.mock(Problem.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attributes = Mockito.mock(RedirectAttributes.class);
        
        String success = "SUCCESS! You have added a new problem. Problem count: ";
        
        //configure the mocked objects
        when(result.hasErrors()).thenReturn(false);
        when(mockBacktraceService.listProblems()).thenReturn(problems);
        when(mockBacktraceService.duplicateCheck(problem.getProblem(), Problem.class, "problem")).thenReturn(true);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.addProblem(problem, result, map, attributes);
        
        //testing
        Mockito.verify(attributes).addFlashAttribute("message", success + problems.size());
        assertNotNull(returned);
        assertEquals("redirect:/listProblems.html", returned);
    } 
    
    @SuppressWarnings("unchecked")
	@Test
    public void testProblemEditForm() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Version> versions = new ArrayList<Version>();
    	final List<Problem> problems = new ArrayList<Problem>();

        final Problem problem1 = Mockito.mock(Problem.class);  
        final Problem problem2 = Mockito.mock(Problem.class);
        problems.add(problem1);
        problems.add(problem2);
        
        final Version version1 = Mockito.mock(Version.class);
        final Version version2 = Mockito.mock(Version.class);
        versions.add(version1);
        versions.add(version2);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        //configure the mocked objects
        when(version1.getId()).thenReturn(new Integer(1));
        when(version2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listVersions()).thenReturn(versions);
        when(problem1.getId()).thenReturn(new Integer(1));
        when(problem2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listProblems()).thenReturn(problems);
        when(mockBacktraceService.getProblem(new Integer(1))).thenReturn(problem1);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.problemEditForm(new Integer(1), map);
        
        //testing
        Mockito.verify(map).put("problem", problem1);
        Mockito.verify(map).put("versionList", versions);
        assertNotNull(returned);
        assertEquals("editProblem", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testEditProblemHibernateValidationFails() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Version> versions = new ArrayList<Version>();
        
        final Version version1 = Mockito.mock(Version.class);
        final Version version2 = Mockito.mock(Version.class);
    
        versions.add(version1);
        versions.add(version2);

        final Problem problem = Mockito.mock(Problem.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attribtes = Mockito.mock(RedirectAttributes.class);
        
        String error = "OOPS! Error occured!";
        
        //configure the mocked objects
        when(version1.getId()).thenReturn(new Integer(1));
        when(version2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listVersions()).thenReturn(versions);
        when(problem.getId()).thenReturn(new Integer(1));
        when(result.hasErrors()).thenReturn(true);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.editProblem(problem, result, problem.getId(), map, attribtes);
        
        //testing
        Mockito.verify(map).put("versionList", versions);
        Mockito.verify(map).put("message", error);
        assertNotNull(returned);
        assertEquals("editProblem", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testEditProblemHibernateValidationPassesNoNeedForDuplicateCheck() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Problem> problems = new ArrayList<Problem>();

        final Problem problem1 = Mockito.mock(Problem.class);  
        final Problem problem2 = Mockito.mock(Problem.class);
        problems.add(problem1);
        problems.add(problem2);

        final Problem problem = Mockito.mock(Problem.class);
        final Problem problemTemp = Mockito.mock(Problem.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attributes = Mockito.mock(RedirectAttributes.class);
        
        String success = "SUCCESS! You have updated the problem. Problem count: ";
        
        //configure the mocked objects
        when(result.hasErrors()).thenReturn(false);
        when(mockBacktraceService.listProblems()).thenReturn(problems);
        when(problem.getProblem()).thenReturn("ABCDEFGH");
        when(problemTemp.getId()).thenReturn(new Integer(1));
        when(problemTemp.getProblem()).thenReturn("ABCDEFGH");
        when(mockBacktraceService.getProblem(new Integer(1))).thenReturn(problemTemp);
        when(problemTemp.getDate()).thenReturn("1987-02-26");
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.editProblem(problem, result, problemTemp.getId(), map, attributes);
        
        //testing
        Mockito.verify(attributes).addFlashAttribute("message", success + problems.size());
        assertNotNull(returned);
        assertEquals("redirect:/listProblems.html", returned);
    } 
    
    @SuppressWarnings("unchecked")
	@Test
    public void testEditProblemHibernateValidationPassesDuplicateCheckFails () throws Exception {
    	
    	//prepare mock objects/data
    	final List<Version> versions = new ArrayList<Version>();
        
        final Version version1 = Mockito.mock(Version.class);
        final Version version2 = Mockito.mock(Version.class);
    
        versions.add(version1);
        versions.add(version2);

        final Problem problem1 = Mockito.mock(Problem.class);
        final Problem problem2 = Mockito.mock(Problem.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attribtes = Mockito.mock(RedirectAttributes.class);
        
        String error = "OOPS! Error occured!";
		String errorDuplicate = "Duplicate record found!";
        
        //configure the mocked objects
        when(version1.getId()).thenReturn(new Integer(1));
        when(version2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listVersions()).thenReturn(versions);
        
        when(result.hasErrors()).thenReturn(false);

        when(problem1.getProblem()).thenReturn("ABCDEFGH");
        when(problem2.getId()).thenReturn(new Integer(1));
        when(mockBacktraceService.getProblem(new Integer(1))).thenReturn(problem2);
        when(problem2.getProblem()).thenReturn("abc");
        when(mockBacktraceService.duplicateCheck(problem2.getProblem(), Problem.class, "problem")).thenReturn(false);
      
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.editProblem(problem1, result, problem2.getId(), map, attribtes);
        
        //testing
        Mockito.verify(map).put("message", error);
        Mockito.verify(map).put("messageDuplicate", errorDuplicate);
        Mockito.verify(map).put("versionList", versions);
        assertNotNull(returned);
        assertEquals("editProblem", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testEditProblemHibernateValidationPassesDuplicateCheckPasses() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Problem> problems = new ArrayList<Problem>();

        final Problem problem1 = Mockito.mock(Problem.class);  
        final Problem problem2 = Mockito.mock(Problem.class);
        problems.add(problem1);
        problems.add(problem2);

        final Problem problem = Mockito.mock(Problem.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attributes = Mockito.mock(RedirectAttributes.class);
        
        String success = "SUCCESS! You have updated the problem. Problem count: ";
        
        //configure the mocked objects
        when(result.hasErrors()).thenReturn(false);
        when(mockBacktraceService.listProblems()).thenReturn(problems);
        when(mockBacktraceService.duplicateCheck(problem.getProblem(), Problem.class, "problem")).thenReturn(true);
        when(problem.getId()).thenReturn(new Integer(1));
        when(problem.getProblem()).thenReturn("ABCDEFGH");
        when(mockBacktraceService.getProblem(new Integer(1))).thenReturn(problem);
        when(problem.getDate()).thenReturn("1987-02-26");
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.editProblem(problem, result, problem.getId(), map, attributes);
        
        //testing
        Mockito.verify(attributes).addFlashAttribute("message", success + problems.size());
        assertNotNull(returned);
        assertEquals("redirect:/listProblems.html", returned);
    } 
    
    @SuppressWarnings("unchecked")
	@Test
    public void testVersionForm() throws Exception {
    	
    	//prepare mock objects/data
        final Version version = Mockito.mock(Version.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        //configure the mocked objects
        whenNew(Version.class).withNoArguments().thenReturn(version);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.versionForm(map);
        
        //testing
        Mockito.verify(map).put("newVersion", version);
        assertNotNull(returned);
        assertEquals("addVersion", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testAddVersionHibernateValidationFails() throws Exception {
    	
    	//prepare mock objects/data
        final Version version = Mockito.mock(Version.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attribtes = Mockito.mock(RedirectAttributes.class);
        
        String error = "OOPS! Error occured!";
        
        //configure the mocked objects
        whenNew(Version.class).withNoArguments().thenReturn(version);
        when(result.hasErrors()).thenReturn(true);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.addVersion(version, result, map, attribtes);
        
        //testing
        Mockito.verify(map).put("message", error);
        assertNotNull(returned);
        assertEquals("addVersion", returned);
    }
        
    @SuppressWarnings("unchecked")
	@Test
    public void testAddVersionHibernateValidationPassesDuplicateCheckFails () throws Exception {
    	
    	//prepare mock objects/data
        final Version version = Mockito.mock(Version.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attribtes = Mockito.mock(RedirectAttributes.class);
        
        String error = "OOPS! Error occured!";
        String errorDuplicate = "Duplicate record found!";
        
        //configure the mocked objects
        whenNew(Version.class).withNoArguments().thenReturn(version);
        when(result.hasErrors()).thenReturn(false);
        when(mockBacktraceService.duplicateCheck(version.getVersion(), Version.class, "version")).thenReturn(false);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.addVersion(version, result, map, attribtes);
        
        //testing
        Mockito.verify(map).put("message", error);
        Mockito.verify(map).put("messageDuplicate", errorDuplicate);
        assertNotNull(returned);
        assertEquals("addVersion", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testAddVersionHibernateValidationPassesDuplicateCheckPasses() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Version> versions = new ArrayList<Version>();
        
        final Version version1 = Mockito.mock(Version.class);
        final Version version2 = Mockito.mock(Version.class);
    
        versions.add(version1);
        versions.add(version2);

        final Version version = Mockito.mock(Version.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attributes = Mockito.mock(RedirectAttributes.class);
        
        String success = "SUCCESS! You have added a new software version. Version count: ";
        
        //configure the mocked objects
        when(result.hasErrors()).thenReturn(false);
        when(mockBacktraceService.listVersions()).thenReturn(versions);
        when(mockBacktraceService.duplicateCheck(version.getVersion(), Version.class, "version")).thenReturn(true);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.addVersion(version, result, map, attributes);
        
        //testing
        Mockito.verify(attributes).addFlashAttribute("message", success + versions.size());
        assertNotNull(returned);
        assertEquals("redirect:/listVersions.html", returned);
    } 
    
    @SuppressWarnings("unchecked")
	@Test
    public void testVersionEditForm() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Version> versions = new ArrayList<Version>();
        final Version version1 = Mockito.mock(Version.class);
        final Version version2 = Mockito.mock(Version.class);
        versions.add(version1);
        versions.add(version2);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        //configure the mocked objects
        when(version1.getId()).thenReturn(new Integer(1));
        when(version2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listVersions()).thenReturn(versions);
        when(mockBacktraceService.getVersion(new Integer(1))).thenReturn(version1);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.versionEditForm(new Integer(1), map);
        
        //testing
        Mockito.verify(map).put("version", version1);
        assertNotNull(returned);
        assertEquals("editVersion", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testEditVersionHibernateValidationFails() throws Exception {
    	
    	//prepare mock objects/data     
        final Version version = Mockito.mock(Version.class);

        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attribtes = Mockito.mock(RedirectAttributes.class);
        
        String error = "OOPS! Error occured!";
        
        //configure the mocked objects
        when(version.getId()).thenReturn(new Integer(1));
        when(result.hasErrors()).thenReturn(true);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.editVersion(version, result, version.getId(), map, attribtes);
        
        //testing
        Mockito.verify(map).put("message", error);
        assertNotNull(returned);
        assertEquals("editVersion", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testEditVersionHibernateValidationPassesNoNeedForDuplicateCheck() throws Exception {
    	
    	//prepare mock objects/data
    	//prepare mock objects/data
    	final List<Version> versions = new ArrayList<Version>();
        
        final Version version1 = Mockito.mock(Version.class);
        final Version version2 = Mockito.mock(Version.class);
        versions.add(version1);
        versions.add(version2);
        
        final Version version = Mockito.mock(Version.class);
        final Version versionTemp = Mockito.mock(Version.class);

        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attributes = Mockito.mock(RedirectAttributes.class);
        
        String success = "SUCCESS! You have updated the software version. Version count: ";
              
        //configure the mocked objects
        when(result.hasErrors()).thenReturn(false);
        when(mockBacktraceService.listVersions()).thenReturn(versions);
        when(version.getVersion()).thenReturn("ABCDEFGH");
        when(versionTemp.getId()).thenReturn(new Integer(1));
        when(versionTemp.getVersion()).thenReturn("ABCDEFGH");
        when(mockBacktraceService.getVersion(new Integer(1))).thenReturn(versionTemp);
        when(versionTemp.getDate()).thenReturn("1987-02-26");
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.editVersion(version, result, versionTemp.getId(), map, attributes);
        
        //testing
        Mockito.verify(attributes).addFlashAttribute("message", success + versions.size());
        assertNotNull(returned);
        assertEquals("redirect:/listVersions.html", returned);
    }     
    
    @SuppressWarnings("unchecked")
	@Test
    public void testEditVersionHibernateValidationPassesDuplicateCheckFails () throws Exception {
    	
    	//prepare mock objects/data
        final Version version1 = Mockito.mock(Version.class);
        final Version version2 = Mockito.mock(Version.class);
        
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attribtes = Mockito.mock(RedirectAttributes.class);
        
        String error = "OOPS! Error occured!";
		String errorDuplicate = "Duplicate record found!";
        
        //configure the mocked objects
        when(result.hasErrors()).thenReturn(false);
        
        when(version1.getVersion()).thenReturn("ABCDEFGH");
        when(version2.getId()).thenReturn(new Integer(1));
        when(mockBacktraceService.getVersion(new Integer(1))).thenReturn(version2);
        when(version2.getVersion()).thenReturn("abc");
        when(mockBacktraceService.duplicateCheck(version2.getVersion(), Version.class, "version")).thenReturn(false);
       
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.editVersion(version1, result, version2.getId(), map, attribtes);
        
        //testing
        Mockito.verify(map).put("message", error);
        Mockito.verify(map).put("messageDuplicate", errorDuplicate);
        assertNotNull(returned);
        assertEquals("editVersion", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testEditVersionHibernateValidationPassesDuplicateCheckPasses() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Version> versions = new ArrayList<Version>();
        
        final Version version1 = Mockito.mock(Version.class);
        final Version version2 = Mockito.mock(Version.class);
        versions.add(version1);
        versions.add(version2);

        Map<String, Object> map = Mockito.mock(Map.class);
        
        BindingResult result = Mockito.mock(BindingResult.class);
        
        RedirectAttributes attributes = Mockito.mock(RedirectAttributes.class);
        
        String success = "SUCCESS! You have updated the software version. Version count: ";
        
        //configure the mocked objects
        when(result.hasErrors()).thenReturn(false);
        when(mockBacktraceService.listVersions()).thenReturn(versions);
        when(mockBacktraceService.duplicateCheck(version1.getVersion(), Version.class, "version")).thenReturn(true);
        when(version1.getId()).thenReturn(new Integer(1));
        when(version1.getVersion()).thenReturn("ABCDEFGH");
        when(mockBacktraceService.getVersion(new Integer(1))).thenReturn(version1);
        when(version1.getDate()).thenReturn("1987-02-26");
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.editVersion(version1, result, version1.getId(), map, attributes);
        
        //testing
        Mockito.verify(attributes).addFlashAttribute("message", success + versions.size());
        assertNotNull(returned);
        assertEquals("redirect:/listVersions.html", returned);
    } 

    @SuppressWarnings("unchecked")
	@Test
    public void testListComments() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Comment> comments = new ArrayList<Comment>();
        
        final Comment comment1 = Mockito.mock(Comment.class);
        final Comment comment2 = Mockito.mock(Comment.class);
    
        comments.add(comment1);
        comments.add(comment2);

        final Comment comment = Mockito.mock(Comment.class);
        
        Map<String, Object> map = Mockito.mock(Map.class);
        
        //configure the mocked objects
        when(comment1.getId()).thenReturn(new Integer(1));
        when(comment2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listComments()).thenReturn(comments);
        whenNew(Comment.class).withNoArguments().thenReturn(comment);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.listComments(map);
        
        //testing
        Mockito.verify(map).put("comment", comment);
        Mockito.verify(map).put("commentList", comments);
        assertNotNull(returned);
        assertEquals("comments", returned);
    }
    
    @SuppressWarnings("unchecked")
   	@Test
       public void testAddCommentFails() throws Exception {
       	
       	//prepare mock objects/data
    	final List<Comment> comments = new ArrayList<Comment>();
        
        final Comment comment1 = Mockito.mock(Comment.class);
        final Comment comment2 = Mockito.mock(Comment.class);
    
        comments.add(comment1);
        comments.add(comment2);

        final Comment comment = Mockito.mock(Comment.class);
           
        Map<String, Object> map = Mockito.mock(Map.class);
           
        BindingResult result = Mockito.mock(BindingResult.class);
                    
        String errorGlobal = "OOPS! Error occured while adding comment!";
           
        //configure the mocked objects
        when(comment1.getId()).thenReturn(new Integer(1));
        when(comment2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listComments()).thenReturn(comments);
        when(result.hasErrors()).thenReturn(true);
        map = Mockito.mock(Map.class);

        //method under test
        final String returned = backtraceController.addComment(comment, result, map);
           
        //testing
        Mockito.verify(map).put("commentList", comments);
        Mockito.verify(map).put("message", errorGlobal);
        assertNotNull(returned);
        assertEquals("comments", returned);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testAddCommentPasses() throws Exception {
    	
    	//prepare mock objects/data
    	final List<Comment> comments = new ArrayList<Comment>();
        
        final Comment comment1 = Mockito.mock(Comment.class);
        final Comment comment2 = Mockito.mock(Comment.class);
    
        comments.add(comment1);
        comments.add(comment2);

        final Comment comment = Mockito.mock(Comment.class);
           
        Map<String, Object> map = Mockito.mock(Map.class);
           
        BindingResult result = Mockito.mock(BindingResult.class);
        
        String success = "Thank You for commenting!";
        
        //configure the mocked objects
        when(result.hasErrors()).thenReturn(false);
        when(comment1.getId()).thenReturn(new Integer(1));
        when(comment2.getId()).thenReturn(new Integer(2));
        when(mockBacktraceService.listComments()).thenReturn(comments);
        whenNew(Comment.class).withNoArguments().thenReturn(comment);
        map = Mockito.mock(Map.class);
        
        //method under test
        final String returned = backtraceController.addComment(comment, result, map);
        
        //testing
        Mockito.verify(map).put("comment", comment);
        Mockito.verify(map).put("commentList", comments);
        Mockito.verify(map).put("message", success);
        assertNotNull(returned);
        assertEquals("comments", returned);
    }
}