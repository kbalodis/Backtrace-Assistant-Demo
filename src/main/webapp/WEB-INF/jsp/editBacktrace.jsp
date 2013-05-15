<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
	    <title>Backtrace Assistant Demo v0.3</title>
	    <link href="<c:url value="/resources/reset.css"/>" rel="stylesheet" type="text/css" />
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet"  type="text/css" />
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css"/>" rel="stylesheet"  type="text/css" />  
		<script src="<c:url value="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"/>"></script>
		<script>window.jQuery || document.write('<script src="<c:url value="/resources/jQuery/js/jquery-1.9.1.js"/>"><\/script>')</script>
		<link href="<c:url value="/resources/jQuery/css/jquery-ui-1.8.17.custom.css"/>" rel="stylesheet" type="text/css" />
		<script src="<c:url value="/resources/jQuery/js/jquery-ui-1.8.17.custom.min.js"/>"></script>		
		<script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
	</head>
	<body>
		<div class="navbar navbar-fixed-top"> 
			<div class="navbar-inner">
				<div class="container">
					<ul class="nav">
						<li class="">
							<a class="brand" href="<c:url value="/index.html"/>">Home</a>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">LIST <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/listVersions.html"/>">Software VERSIONS</a></li>
			              		<li><a href="<c:url value="/listProblems.html"/>">PROBLEMS</a></li>
			            		<li><a href="<c:url value="/listBacktraces.html"/>">BACKTRACES</a></li>
			            	</ul>
			          	</li>
			          	<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">ADD <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/addVersionForm.html"/>">Software VERSION</a></li>
								<li><a href="<c:url value="/addProblemForm.html"/>">PROBLEM</a></li>
								<li><a href="<c:url value="/addBacktraceForm.html"/>">BACKTRACE</a></li>
			            	</ul>
			          	</li>
			          	<li class="">
							<a href="<c:url value="/listComments.html"/>">COMMENTS</a>
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
		<form:form class="form-horizontal" method="post" action="/editBacktrace/${backtrace.id}.html" commandName="backtrace">		 
		    <fieldset>
		    	<div align="center">
		    		<legend>Edit a Backtrace!</legend> 
		    	</div>
		    	<div class="control-group"> 
					<form:label class="control-label" path="probId">
						Problem Name:
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
				 		Backtrace:
					 </form:label>
					 <div class="controls">
					 	<form:textarea id="backtrace1" class="input-xlarge" path="backtrace" rows="8"/> 
					 	<form:errors class="alert alert-info" path="backtrace" />
					 </div>
				</div> 
				<div class="control-group">   
					<form:label class="control-label" path="name">
						Process Name:
					</form:label>
					<div class="controls"> 
						<form:input id="process" type="text" class="input-xlarge" path="name" />
						<form:errors class="alert alert-info" path="name" />
					</div>
				</div>
				<div class="form-actions">
					<button type="submit" value="SAVE" class="btn btn-primary" id="submit">SAVE</button>
				</div>
			</fieldset>  
		</form:form>
		<script>
			$('#problem').tooltip({'trigger':'hover', 'title': 'Please, select the problem name that the corresponding backtrace belongs to!'});
			$('#backtrace1').tooltip({'trigger':'hover', 'title': 'Please, input the corresponding backtrace!'}); 
			$('#process').tooltip({'trigger':'hover', 'title': 'Please, add the process name which produced the corresponding coredump!'}); 
		</script>
	</body>
</html>