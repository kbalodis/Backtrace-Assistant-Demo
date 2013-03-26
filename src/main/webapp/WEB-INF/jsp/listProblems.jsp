<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
	    <title>Backtrace Assistant Demo</title>
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css" />
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css"/>" rel="stylesheet" type="text/css" />
	   	<link href="<c:url value="/resources/bootstrap/datepicker/css/datepicker.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/bootstrap/datepicker/less/datepicker.less"/>" rel="stylesheet" type="text/less" />
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
		<h1>List of Problems</h1>
		<c:if test="${!empty message}">
		 	<div class="alert alert-success">
		 		<div align="center">${message}</div>
		 	</div>
		</c:if>
		<c:if test="${!empty problemList}">
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
					    <th>Problem</th>
					    <th>Date Reported</th>
					    <th>Software Version</th>
					</tr>
				</thead>
				<c:forEach items="${problemList}" var="problem">
				    <tr>
				        <td>${problem.problem}</td>
				        <td>${problem.dateReported}</td>
				        <td>${problem.versionId.getVersion()}, released on: ${problem.versionId.getDateReleased()}</td>
				    </tr>
				</c:forEach>
			</table>
		</c:if>
	</body>
</html>