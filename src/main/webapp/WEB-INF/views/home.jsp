<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="utf-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
	</head>
	<body>
	    <div class="container">
			<div>
			  <h1>Reddit searcher</h1>
			</div>
			<div class="row">
			  <div class="col-md-12">
				  <form role="form" method="get">
				    <div class="input-group">
				      <input name="q" class="form-control">
				      <span class="input-group-btn">
				        <button class="btn btn-default" type="submit">Search</button>
				      </span>
				    </div>
				  </form>
			  </div>
			</div>

			<c:forEach var="link" items="${links}">
				<div>
					<p><a href="${link.uri}">${link.text}</a></p>
				</div>
			</c:forEach>
	    </div>
	    <!-- Javascript placed at the end of the document so the pages load faster -->
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	</body>
</html>
