<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
	    <title>Backtrace Assistant Demo</title>
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet"  type="text/css" />
	    <link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css"/>" rel="stylesheet"  type="text/css" />  
		<script>window.jQuery || document.write('<script src="<c:url value="/resources/jQuery/js/jquery-1.9.1.js"/>"><\/script>')</script>
		<script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
		<link href="<c:url value="/resources/jQuery/css/theme.blue.css"/>" rel="stylesheet"/>   
		<script src="<c:url value="/resources/jQuery/js/jquery-1.9.1.js"/>" type="text/javascript"></script>
		<link class="ui-theme" rel="stylesheet" href="<c:url value="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/cupertino/jquery-ui.css"/>">
		<script src="<c:url value="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7/jquery-ui.min.js"/>"></script>
		<script src="<c:url value="/resources/jQuery/js/jquery.tablesorter.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/resources/jQuery/js/jquery.tablesorter.pager.js"/>" type="text/javascript"></script>
		<script src="<c:url value="http://code.jquery.com/jquery-migrate-1.1.0.js"/>"></script>
		<script src="<c:url value="/resources/jQuery/js/jquery.tablesorter.widgets.js"/>"></script>
		<script type="text/javascript">
			$(function() {
	
				// call the tablesorter plugin
				$("#myTable").tablesorter({
					theme: 'blue',
	
					// hidden filter input/selects will resize the columns, so try to minimize the change
					widthFixed : true,
	
					// initialize zebra striping and filter widgets
					widgets: ["zebra", "filter"],
	
					// headers: { 5: { sorter: false, filter: false } },
	
					widgetOptions : {
	
						// If there are child rows in the table (rows with class name from "cssChildRow" option)
						// and this option is true and a match is found anywhere in the child row, then it will make that row
						// visible; default is false
						filter_childRows : false,
	
						// if true, a filter will be added to the top of each table column;
						// disabled by using -> headers: { 1: { filter: false } } OR add class="filter-false"
						// if you set this to false, make sure you perform a search using the second method below
						filter_columnFilters : true,
	
						// css class applied to the table row containing the filters & the inputs within that row
						filter_cssFilter : 'tablesorter-filter',
	
						// add custom filter functions using this option
						// see the filter widget custom demo for more specifics on how to use this option
						filter_functions : null,
	
						// if true, filters are collapsed initially, but can be revealed by hovering over the grey bar immediately
						// below the header row. Additionally, tabbing through the document will open the filter row when an input gets focus
						filter_hideFilters : false,
	
						// Set this option to false to make the searches case sensitive
						filter_ignoreCase : true,
	
						// jQuery selector string of an element used to reset the filters
						filter_reset : 'button.reset',
	
						// Delay in milliseconds before the filter widget starts searching; This option prevents searching for
						// every character while typing and should make searching large tables faster.
						filter_searchDelay : 300,
	
						// Set this option to true to use the filter to find text from the start of the column
						// So typing in "a" will find "albert" but not "frank", both have a's; default is false
						filter_startsWith : false,
	
						// Filter using parsed content for ALL columns
						// be careful on using this on date columns as the date is parsed and stored as time in seconds
						filter_useParsedData : false
	
					}
				})
				.tablesorterPager({container: $("#pager")}); 
			});
		</script>
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
		<h1>List of Backtraces</h1>
		<c:if test="${!empty message}">
		 	<div class="alert alert-success">
		 		<div align="center">${message}</div>
		 	</div>
		</c:if>
		<c:if test="${!empty backtraceList}">
			<button class="reset">Reset</button>
			<table id="myTable" class="tablesorter">
				<thead>	
					<tr>
					    <th data-placeholder="Search...">Backtrace</th>
					    <th data-placeholder="Search...">Name</th>
					    <th data-placeholder="Search...">Date added</th>
					    <th data-placeholder="Search...">Corresponding problem</th>
					    <th data-placeholder="Search...">Date corresponding problem was reported</th>
					    <th data-placeholder="Search...">Corresponding software version</th>
					    <th data-placeholder="Search...">Date of software version release</th>
					</tr>
				</thead>	
				<c:forEach items="${backtraceList}" var="backtrace">
				    <tr>
				        <td>
				        	<textarea rows="5" readonly="true">${backtrace.backtrace}</textarea>
				        </td>
				        <td>
				        	${backtrace.name}
				        	<p><a href="delete/${backtrace.id}.html">delete</a></p>
				        </td>
				        <td>${backtrace.date}</td>
				        <td>${backtrace.problemId.getProblem()}</td>
				        <td>${backtrace.problemId.getDateReported()}</td>
				        <td>${backtrace.problemId.getVersionId().getVersion()}</td>
				        <td>${backtrace.problemId.getVersionId().getDateReleased()}</td>
				    </tr>
				</c:forEach>
			</table>
			<div id="pager" class="pager">
				<form>
					<img src="<c:url value="/resources/jQuery/img/first.png"/>" class="first"/>
					<img src="<c:url value="/resources/jQuery/img/prev.png"/>" class="prev"/>
					<input type="text" class="pagedisplay"/>
					<img src="<c:url value="/resources/jQuery/img/next.png"/>" class="next"/>
					<img src="<c:url value="/resources/jQuery/img/last.png"/>" class="last"/>
					<select class="pagesize">
						<option selected="selected"  value="10">10</option>
						<option value="20">20</option>
						<option value="30">30</option>
						<option value="40">40</option>
					</select>
				</form>
			</div>			
		</c:if>
	</body>
</html>