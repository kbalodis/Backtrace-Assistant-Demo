<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
	    <title>Backtrace Assistant Demo</title>
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet"  type="text/css" />
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css"/>" rel="stylesheet"  type="text/css" />  
	</head>
	<body>
		<div class="navbar navbar-fixed-top"> 
			<div class="navbar-inner">
				<div class="container">
					<ul class="nav">
						<li class="active">
							<a class="brand" href="index.html">Home</a>
						</li>
						<li><a href="listBacktraces.html">List BACKTRACES</a></li>
						<li><a href="addBacktraceForm.html">Add BACKTRACE</a></li>
						<li><a href="listProblems.html">List PROBLEMS</a></li>
						<li><a href="addProblemForm.html">Add PROBLEM</a></li>
						<li><a href="listVersions.html">List VERSIONS</a></li>
						<li><a href="addVersionForm.html">Add VERSION</a></li>
					</ul>
				</div>
			</div>
		</div>
		<h1>Backtraces</h1>
		<c:if test="${!empty message}">
		 	<div class="alert alert-success">
		 		<div align="center">${message}</div>
		 	</div>
		</c:if>
		<c:if test="${!empty backtraceList}">
			<table class="table table-striped table-bordered table-condensed">
				<thead>	
					<tr>
					    <th>Backtrace</th>
					    <th>Date added</th>
					    <th>Corresponding problem</th>
					    <th>Date corresponding problem was reported</th>
					    <th>Corresponding software version</th>
					    <th>Date of software version release</th>
					</tr>
				</thead>	
				<c:forEach items="${backtraceList}" var="backtrace">
				    <tr>
				        <td>
				        	<b>Name: ${backtrace.name}</b>
				        	<textarea rows="8">${backtrace.backtrace}</textarea>
				        </td>
				        <td>${backtrace.date}</td>
				        <td>${backtrace.problemId.getProblem()}</td>
				        <td>${backtrace.problemId.getDateReported()}</td>
				        <td>${backtrace.problemId.getVersionId().getVersion()}</td>
				        <td>${backtrace.problemId.getVersionId().getDateReleased()}</td>
				        <td><a href="delete/${backtrace.id}.html">delete</a></td>
				    </tr>
				</c:forEach>
			</table>
		</c:if> 
	</body>
</html>