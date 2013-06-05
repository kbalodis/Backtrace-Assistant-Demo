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
		<div>
			<h1>Backtrace Assistant Demo v0.3</h1>		
		</div>
		<h2>Brief Explanations (README)</h2>
		<p>This initial page is aimed at giving brief explanations about the application and its usage.</p>
		<h3>Mission Statement:</h3>
		<p>
			This application is mainly aimed at assisting its users in finding backtraces given concrete parameters. 
			Its features include: saving backtraces, problems corresponding backtraces belong to, and software versions 
			which produced the coredump in the data base; viewing list of backtraces already saved with options to sort, 
			filter, and search them.
		</p>
		<h3>Main Functionality:</h3>
		<p>It is possible to add and save data as well as list/filter/search the saved data.</p>
		<p>
			It is required to add a software version before a problem (the problem cannot be entered without the corresponding software version) and
			it is also required to add the problem before adding corresponding backtraces belonging to the problem.
		</p>		
		<p>The following table has links to the particular functionality options.</p>
		<table class="table table-striped table-bordered table-condensed">
			<thead>
		        <tr>
		            <th>Functionality</th>
		            <th>Software VERSIONS</th>
		            <th>PROBLEMS</th>
		            <th>BACKTRACES</th>
		        </tr>
		    </thead>
		    <tbody>
		        <tr>
		            <td>ADD</td>
		            <td><a class="btn btn-primary" href="addVersionForm.html">ADD VERSION</a></td>
		            <td><a class="btn btn-primary" href="addProblemForm.html">ADD PROBLEM</a></td>
		            <td><a class="btn btn-primary" href="addBacktraceForm.html">ADD BACKTRACE</a></td>
		        </tr>
		        <tr>
		            <td>LIST</td>
		            <td><a class="btn btn-primary" href="listVersions.html">LIST VERSIONS</a></td>
		            <td><a class="btn btn-primary" href="listProblems.html">LIST PROBLEMS</a></td>
		            <td><a class="btn btn-primary" href="listBacktraces.html">LIST BACKTRACES</a></td>
		        </tr>
		    </tbody>
		</table>
		<h3>Status of the Data Base:</h3>
		<p>Version Count: ${versionCount}</p>
		<p>Problem Count: ${problemCount}</p>
		<p>Backtrace Count: ${backtraceCount}</p>	
	</body>
</html>