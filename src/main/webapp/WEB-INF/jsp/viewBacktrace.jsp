<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
	    <title>Backtrace Assistant Demo v0.3</title>
	    <link href="<c:url value="/resources/reset.css"/>" rel="stylesheet" type="text/css" />
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet"  type="text/css" />
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css"/>" rel="stylesheet"  type="text/css" />  
		<script>window.jQuery || document.write('<script src="<c:url value="/resources/jQuery/js/jquery-1.9.1.js"/>"><\/script>')</script>
		<link href="<c:url value="/resources/jQuery/css/theme.blue.css"/>" rel="stylesheet"/>   
		<script src="<c:url value="/resources/jQuery/js/jquery-1.9.1.js"/>" type="text/javascript"></script>
		<link class="ui-theme" rel="stylesheet" href="<c:url value="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/cupertino/jquery-ui.css"/>">
		<script src="<c:url value="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7/jquery-ui.min.js"/>"></script>
		<script src="<c:url value="http://code.jquery.com/jquery-migrate-1.1.0.js"/>"></script>
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
		<h1>
			Individual Backtrace View
		</h1>
		<c:if test="${backtrace != null}">
			<table id="myTable" class="table table-striped table-bordered table-condensed">	
				<tr>
					<td>Backtrace:</td>
					<td>
				    	<textarea rows="25" style="width: 600px;" readonly="true">${backtrace.backtrace}</textarea>		        
					</td>
				</tr>     
				<tr>        
					<td>Process name:</td>
					<td>${backtrace.name}</td>
				</tr>
				<tr>
					<td>Date added/date modified:</td>
				    <td>${backtrace.date} / ${backtrace.dateModified}</td>
				</tr>
				<tr>
					<td>Corresponding problem:</td>
				    <td>${backtrace.problemId.getProblem()}</td>
				</tr>
				<tr>
					<td>Date the corresponding problem was reported:</td>
				    <td>${backtrace.problemId.getDateReported()}</td>
				</tr>
				<tr>
					<td>Corresponding software version:</td>
				  	<td>${backtrace.problemId.getVersionId().getVersion()}</td>
				</tr>
				<tr>
					<td>Date corresponding software version was released:</td>
				   	<td>${backtrace.problemId.getVersionId().getDateReleased()}</td>
				</tr>
				<tr>
					<td>ACTIONS:</td>
					<td>
						<a class="btn btn-primary" href="<c:url value="/editBacktraceForm/${backtrace.id}.html"/>">EDIT</a>
						<a class="btn btn-primary" href="<c:url value="/addBacktraceForm.html"/>">ADD NEW</a>
						<a class="btn btn-primary" href="<c:url value="/deleteBacktrace/${backtrace.id}.html"/>" onClick="return confirmAction()">DELETE</a>
					</td>
				</tr>
			</table>			
		</c:if>
		<script>
			$('[rel=tooltip]').tooltip();
			function confirmAction(){
			      var confirmed = confirm("Are you sure? This will remove this entry.");
			      return confirmed;
			}
		</script>
	</body>
</html>