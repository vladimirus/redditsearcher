<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
	<form method="get">
		<input name="q" placeholder="">
		<input type="submit" value="search">
	</form>
	<c:forEach var="link" items="${links}">
	   <div>
	       <a href="${link.uri}">${link.text}</a>
	   </div>
	</c:forEach>
</body>
</html>
