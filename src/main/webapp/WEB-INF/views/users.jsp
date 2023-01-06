<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/ressources/css/style.css"type="text/css"/>
</head>
<body>
<%-- 	<p>${users}</p> --%>

<div class="container">
	
		<table class="table">
		 <h3>Liste des utilisateurs</h3>
			<thead>
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Firstname</th>
				<th scope="col">Lastname</th>
				<th scope="col">Salaire</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ users }" var="user">
			
			<tr>
				<th scope="row"><c:out value="${ user.id }"/></th>
				
				<td class= test><c:out value="${ user.firstName }"/></td>
				<td><c:out value="${ user.lastName }"/></td>
				<td><c:out value="${ user.salary }"/></td>
				
			</tr>
			</c:forEach>
			</tbody>
		
	</table>
</div>
	
</body>
</html>