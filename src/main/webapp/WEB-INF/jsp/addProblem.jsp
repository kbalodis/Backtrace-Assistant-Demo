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
		<c:if test="${!empty message}">
		 		<div class="alert alert-error">
		 			<div align="center">${message}</div>
		 		</div>
		</c:if>
		<form:form class="form-horizontal" method="post" action="addNewProblem.html" modelAttribute="newProblem">		 
		    <fieldset>
				<div align="center">
					<legend>Add a Problem!</legend>
				</div>
				<div class="control-group">
				    <form:label class="control-label" path="problem">Problem name:</form:label>
				    <div class="controls">
				    	<form:input type="text" class="input-xlarge" path="problem"/>
				    	<form:errors class="alert alert-info" path="problem" />
				    	<p class="help-block">Add the name of the problem!</p>
				    </div>
				</div>   
				<div class="control-group">
					<label class="control-label">Software version:</label>   
					<div class="controls">
						<form:select path="versId">
							<form:option value="" label="--- Select ---" />
							<c:forEach items="${versionList}" var="theVersion">
						    	<form:option value="${theVersion.id}"><c:out value="${theVersion.version}, release date: ${theVersion.dateReleased}"/></form:option>
					    	</c:forEach>
					    </form:select>
					    <form:errors class="alert alert-info" path="versId" />
					</div>
				</div>
				<div class="control-group">
			        <form:label class="control-label" path="dateReported">Date Reported:</form:label>
			        <div class="controls">
			        	<div class="input-append date" id="dp" data-date="">
						  	<form:input class="span2" size="16" type="text" value="" path="dateReported"/>
						 	<span class="add-on"><i class="icon-calendar"></i></span>
						</div>
						<form:errors class="alert alert-info" path="dateReported" />
			        </div>
		     	</div>
		        <div class="form-actions">
					<button type="submit" value="SAVE" class="btn btn-primary">SAVE</button>
					<button type="reset" value="RESET" class="btn">RESET</button>
				</div>
			</fieldset>
		</form:form>	
		<script src="<c:url value="/resources/jQuery/jquery-1.9.1.js" />" type="text/javascript" charset="UTF-8"></script>
		<script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/> " type="text/javascript" charset="UTF-8"></script>
		<script src="<c:url value="/resources/bootstrap/datepicker/js/bootstrap-datepicker.js"/> " type="text/javascript" charset="UTF-8"></script>
		<script type="text/javascript">
			var nowTemp = new Date();
			var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
			 
			var checkin = $('#dp').datepicker({
				format: 'dd-mm-yyyy',
				onRender: function(date) {
			    	return date.valueOf() > now.valueOf() ? 'disabled' : '';
				}
			});
		</script>
	</body>
</html>