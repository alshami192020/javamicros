<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Friends</title>
	</head>
	<body>
	<h3>Your Facebook Friends</h3>
	<ul>
 	<c:forEach items="${friends}" var="friend">
		<li><img src="http://graph.facebook.com/<c:out value="${friend.id}"/>/picture" align="middle"/><c:out value="${friend.name}"/></li>
	</c:forEach>
	</ul>	
	</body>
</html>