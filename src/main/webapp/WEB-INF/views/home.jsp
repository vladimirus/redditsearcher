<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="resources/css/app.css">
    </head>
	<body>
	    <div class="container">
            <div class="row">
                <h1 class="text-center">reddit searcher</h1>
            </div>
			<div class="row">
			  <div class="col-md-12">
				  <form role="form" method="get">
				    <div class="input-group">
				      <input name="q" class="form-control">
				      <span class="input-group-btn">
				        <button class="btn btn-default" type="submit">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
				      </span>
				    </div>
				  </form>
			  </div>
			</div>
            <c:forEach var="link" items="${links}" varStatus="status">
                <div class="row<c:if test='${status.index == 0}'> spacer</c:if>">
                    <div class="col-md-12">
                    <p><a href="${link.uri}">${link.text}</a></p>
                    </div>
                </div>
            </c:forEach>
	    </div>
        <script src="//code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </body>
</html>
