<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>Backtrace Assistant Demo</title>
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css" />
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css"/>" rel="stylesheet" type="text/css" />
	   	<link href="<c:url value="/resources/bootstrap/datepicker/css/datepicker.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/bootstrap/datepicker/less/datepicker.less"/>" rel="stylesheet" type="text/less" />
		<script src="<c:url value="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"/>"></script>
		<script>window.jQuery || document.write('<script src="<c:url value="/resources/jQuery/js/jquery-1.9.1.js"/>"><\/script>')</script>
		<script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
	</head>
	<body>
		<div class="navbar navbar-fixed-top"> 
			<div class="navbar-inner">
				<div class="container">
					<ul class="nav">
						<li class="active">
							<a class="brand" href="index.html">Home</a>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">LIST <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="listBacktraces.html">BACKTRACES</a></li>
			              		<li><a href="listProblems.html">PROBLEMS</a></li>
			            		<li><a href="listVersions.html">Software VERSIONS</a></li>
			            	</ul>
			          	</li>
			          	<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">ADD <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="addBacktraceForm.html">BACKTRACE</a></li>
								<li><a href="addProblemForm.html">PROBLEM</a></li>
								<li><a href="addVersionForm.html">Software VERSION</a></li>
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
		<form:form class="form-horizontal" method="post" action="addNewVersion.html" commandName="newVersion">		 
			<fieldset>
				<div align="center">
					<legend>Add a Software Version!</legend>
				</div>
				<div class="control-group">   
				    <form:label class="control-label" path="version">Version:</form:label>
				    <div class="controls"> 
				    	<form:input type="text" class="input-xlarge" path="version" />
	 					<form:errors class="alert alert-info" path="version" />
	 					<c:if test="${!empty messageDuplicate}">
						 	<span class="alert alert-info">${messageDuplicate}</span>
						</c:if> 
				    	<p class="help-block">Add the name of the software version!</p>
				    </div>
				</div>
				<div class="control-group"> 
					<form:label class="control-label" path="dateReleased">Release Date:</form:label>
				    <div class="controls"> 
						<div class="input-append date" id="dp" data-date="">
						  	<form:input class="span2" size="16" type="text" value="" readonly="true" path="dateReleased"/>
						 	<span class="add-on"><i class="icon-calendar"></i></span>
						</div>
						<form:errors class="alert alert-info" path="dateReleased" />
		    		</div>
		    	</div>
		    	<div class="form-actions">
					<button type="submit" value="SAVE" class="btn btn-primary">SAVE</button>
					<button type="reset" value="RESET" class="btn">RESET</button>
				</div>
		 	</fieldset>
		</form:form>	     
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