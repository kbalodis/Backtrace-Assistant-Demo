<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
	    <title>Backtrace Assistant Demo v0.3</title>
	    <link href="<c:url value="/resources/reset.css"/>" rel="stylesheet" type="text/css" />
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet" type="text/css" />
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css"/>" rel="stylesheet" type="text/css" />
	   	<link href="<c:url value="/resources/bootstrap/datepicker/css/datepicker.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/resources/bootstrap/datepicker/less/datepicker.less"/>" rel="stylesheet" type="text/less" />
		<script src="<c:url value="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"/>"></script>
		<script>window.jQuery || document.write('<script src="<c:url value="/resources/jQuery/js/jquery-1.9.1.js"/>"><\/script>')</script>
		<link href="<c:url value="/resources/jQuery/css/jquery-ui-1.8.17.custom.css"/>" rel="stylesheet" type="text/css" />
		<script src="<c:url value="/resources/jQuery/js/jquery-ui-1.8.17.custom.min.js"/>"></script>		
		<script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
		<script type="text/javascript">
		    $(function () {
		        $('#date, #version, #name').bind('change keyup', function () {      
		        	if ($('#date').val() != '' && $('#version').val() != '' && $('#name').val() != '') {
				      	$(this).closest('form').find(':submit').removeAttr('disabled');
		        	} else {
			      		$(this).closest('form').find(':submit').attr('disabled', 'disabled');      
			      	}
			    });
			});
		</script>
		<style>
			.datepicker{z-index:1151;}
		</style>
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
		<form:form class="form-horizontal" method="post" action="addNewProblem.html" commandName="newProblem">		 
		    <fieldset>
				<div align="center">
					<legend>Add a Problem!</legend>
				</div>
				<div class="control-group">
					<form:label class="control-label" path="versId">
						Software version:
					</form:label>   
					<div class="controls">
						<form:select id="version" path="versId">
							<form:option value="" label="--- Select ---" />
							<c:forEach items="${versionList}" var="theVersion">
						    	<form:option value="${theVersion.id}"><c:out value="${theVersion.version}, release date: ${theVersion.dateReleased}"/></form:option>
					    	</c:forEach>
					    </form:select>
					    <form:errors class="alert alert-info" path="versId" />
					</div>
				</div>
				<div class="control-group">
			        <form:label class="control-label" path="dateReported">
			        	Date Reported:
			        </form:label>
			        <div class="controls">
			        	<div class="input-append date" id="dp" data-date="">
						  	<form:input id="date" class="span2" size="16" type="text" value="" path="dateReported" readonly="true"/>
						 	<span class="add-on"><i class="icon-calendar"></i></span>
						</div>
						<form:errors class="alert alert-info" path="dateReported" />
			        </div>
		     	</div>
				<div class="control-group">
				    <form:label class="control-label" path="problem">
				    	Problem name:
				    </form:label>
				    <div class="controls">
				    	<form:input id="name" type="text" class="input-xlarge" path="problem"/>
				    	<form:errors class="alert alert-info" path="problem" />
				    	<c:if test="${!empty messageDuplicate}">
							<span class="alert alert-info">${messageDuplicate}</span>
						</c:if> 
				    </div>
				</div>   
				<div class="form-actions">
					<button type="submit" value="SAVE" class="btn btn-primary" id="submit" disabled="disabled">SAVE</button>
					<button type="reset" value="RESET" class="btn" id="reset">CLEAR</button>
				</div>
			</fieldset>
		</form:form>	
		<script src="<c:url value="/resources/bootstrap/datepicker/js/bootstrap-datepicker.js"/> " type="text/javascript" charset="UTF-8"></script>
		<script type="text/javascript">
			var nowTemp = new Date();
			var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
			 
			var checkin = $('#dp').datepicker({
				format: 'yyyy-mm-dd',
				onRender: function(date) {
			    	return date.valueOf() > now.valueOf() ? 'disabled' : '';
				}
			});
		</script>
		<script>
			$('#version').tooltip({'trigger':'hover', 'title': 'Please, select the software version that produced the corresponding problem!'});
			$('#dp').tooltip({'trigger':'hover', 'title': 'Please, add the date the problem was reported!'}); 
			$('#name').tooltip({'trigger':'hover', 'title': 'Please, add the name of the problem!'}); 
			$('#reset').tooltip({'trigger':'hover', 'title': 'To reset all the input fields press CLEAR button!'}); 
		</script>
	</body>
</html>