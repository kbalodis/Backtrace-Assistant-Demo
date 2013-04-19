<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
	    <title>Backtrace Assistant Demo v0.2</title>
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet"  type="text/css" />
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css"/>" rel="stylesheet"  type="text/css" />  
		<script src="<c:url value="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"/>"></script>
		<script>window.jQuery || document.write('<script src="<c:url value="/resources/jQuery/js/jquery-1.9.1.js"/>"><\/script>')</script>
		<link href="<c:url value="/resources/jQuery/css/jquery-ui-1.8.17.custom.css"/>" rel="stylesheet" type="text/css" />
		<script src="<c:url value="/resources/jQuery/js/jquery-ui-1.8.17.custom.min.js"/>"></script>		
		<script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
		<script type="text/javascript">
		    $(function () {
		        $('#problem, #backtrace, #process').bind('change keyup', function () {      
		        	if ($('#problem').val() != '' && $('#backtrace').val() != '' && $('#process').val() != '') {
				      	$(this).closest('form').find(':submit').removeAttr('disabled');
		        	} else {
			      		$(this).closest('form').find(':submit').attr('disabled', 'disabled');      
			      	}
			    });
			});
		</script>
	</head>
	<body>
		<div class="navbar navbar-fixed-top"> 
			<div class="navbar-inner">
				<div class="container">
					<ul class="nav">
						<li class="">
							<a class="brand" href="index.html">Home</a>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">LIST <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="listVersions.html">Software VERSIONS</a></li>
			              		<li><a href="listProblems.html">PROBLEMS</a></li>
			            		<li><a href="listBacktraces.html">BACKTRACES</a></li>
			            	</ul>
			          	</li>
			          	<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">ADD <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="addVersionForm.html">Software VERSION</a></li>
								<li><a href="addProblemForm.html">PROBLEM</a></li>
								<li><a href="addBacktraceForm.html">BACKTRACE</a></li>
			            	</ul>
			          	</li>	
					</ul>
				</div>
			</div>
		</div>
		<c:if test="${!empty message}">
		 		<div class="alert alert-error">
		 			<div align="center">${message}</div>
		 		</div>
		</c:if>
		<form:form class="form-horizontal" method="post" action="addNewBacktrace.html" commandName="newBacktrace">		 
		    <fieldset>
		    	<div align="center">
		    		<legend>Add a Backtrace!</legend> 
		    	</div>
		    	<div class="control-group"> 
					<form:label class="control-label" path="probId">
						<a href="#" rel="tooltip" data-original-title="Please, select the problem name that the corresponding backtrace belongs to!">	
							Problem Name:
						</a>	
					</form:label>
					<div class="controls">
						<form:select id="problem" path="probId">
							<form:option value="" label="--- Select ---" />
								<c:forEach items="${problemList}" var="theProblem">
									<form:option value="${theProblem.id}"><c:out value="${theProblem.problem}, date reported: ${theProblem.dateReported}, software version: ${theProblem.versionId.getVersion()}"/></form:option>
								</c:forEach>
						</form:select>
						<form:errors class="alert alert-info" path="probId" />
			        </div>
			    </div>
		    	<div class="control-group"> 	        
					 <form:label class="control-label" path="backtrace">
					 	<a href="#" rel="tooltip" data-original-title="Please, input the corresponding backtrace!">
					 		Backtrace:
					 	</a>
					 </form:label>
					 <div class="controls">
					 	<form:textarea id="backtrace" class="input-xlarge" path="backtrace" rows="8"/> 
					 	<form:errors class="alert alert-info" path="backtrace" />
					 </div>
				</div> 
				<div class="control-group">   
					<form:label class="control-label" path="name">
						<a href="#" rel="tooltip" data-original-title="Please, add the process name which produced the corresponding coredump!">
							Process Name:
						</a>
					</form:label>
					<div class="controls"> 
						<form:input id="process" type="text" class="input-xlarge" path="name" />
						<form:errors class="alert alert-info" path="name" />
						<c:if test="${!empty messageDuplicate}">
						 	<span class="alert alert-info">${messageDuplicate}</span>
						</c:if> 
					</div>
				</div>
				<div class="form-actions">
					<button type="submit" value="SAVE" class="btn btn-primary" id="submit" disabled="disabled">SAVE</button>
					<button type="reset" value="RESET" class="btn">CLEAR</button>
				</div>
			</fieldset>  
		</form:form>
		<script>
			$('[rel=tooltip]').tooltip(); 
		</script>
	</body>
</html>