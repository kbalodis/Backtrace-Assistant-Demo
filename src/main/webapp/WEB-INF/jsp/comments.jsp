<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>Backtrace Assistant Demo v0.3</title>
	    <link href="<c:url value="/resources/reset.css"/>" rel="stylesheet" type="text/css" />
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css" />
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css"/>" rel="stylesheet" type="text/css" />
	   	<link href="<c:url value="/resources/bootstrap/datepicker/css/datepicker.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/bootstrap/datepicker/less/datepicker.less"/>" rel="stylesheet" type="text/less" />
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
		<h1>List/Add Comments</h1>
		<c:if test="${!empty message}">
			<div class="alert alert-error">
		 		<div align="center">${message}</div>
			</div>
		</c:if> 
		<c:if test="${!empty success}">
			<div class="alert alert-success">
		 		<div align="center">${success}</div>
			</div>
		</c:if> 
		<c:if test="${!empty commentList}">
			<c:forEach items="${commentList}" var="comment">
				<table id="myTable" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>AUTHOR: ${comment.author} DATE ADDED: ${comment.date}</th>
						</tr>
					</thead>
					<tr>
						<td>${comment.comment}</td>
					</tr>
				</table>   
			</c:forEach>
		</c:if>
		<form:form id="form" class="form-horizontal" method="post" action="addComment.html" commandName="comment">		 
			<fieldset>
				<div align="center">
					<legend>Add a Comment!</legend>
				</div>
				<div class="control-group">
					<form:label class="control-label" path="author">Author: </form:label>
					<div class="controls"> 
						<form:input id="author" type="text" class="input-xlarge" path="author"/>
						<form:errors class="alert alert-info" path="author" />
			    	</div>
			    </div>
		    	<div class="control-group">   
				    <form:label class="control-label" path="comment">Comment: </form:label>
				    <div class="controls">
					 	<form:textarea id="comment" class="input-xlarge" path="comment" rows="8" cols="16"/> 
					 	<form:errors class="alert alert-info" path="comment" />
					 </div>
				</div>
				<div class="form-actions">
					<button type="submit" value="SAVE" class="btn btn-primary" id="submit">POST COMMENT</button>
					<button type="reset" value="RESET" class="btn" id="reset">CLEAR</button>
				</div>
		 	</fieldset>
		</form:form>	     
		<script>
			$('#author').tooltip({'trigger':'hover', 'title': 'Please, add Your name!'}); 
			$('#comment').tooltip({'trigger':'hover', 'title': 'Please, add a comment!'});
			$('#reset').tooltip({'trigger':'hover', 'title': 'To reset all the input fields press CLEAR button!'}); 
		</script>
	</body>
</html>