<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="./menu.jsp" charEncoding="UTF-8"></c:import>
	
	<p>Je suis la page home.jsp</p>
	<!-- scriplet -->
	<%
		String firstName = request.getParameter("firstName");
		out.print("Hello " + firstName);
	%>
	<%
		String sport = (String)request.getAttribute("sport");
		out.print("J'aime le  " + sport);
	%>
	<p>${sport}</p>
	
	<p>${user.firstName}</p>
	<p>${user.getLastName()}</p>
	<p>${user.salary}</p>
	
	<p>${ firstName }</p>
	<p>${ lastName }</p>
	
	<!-- Librairie Core -->
	<!-- 	ici on parcours une collection avec c:forEach déclarée et initialisée dans HomeServlet -->
	<c:forEach items="${ users }" var="user">
		<c:out value="${ user.firstName }"/>
		<c:out value="${ user.lastName }"/>
		<c:out value="${ user.salary }"/>
	</c:forEach>
	
	<br><br>
	
	<!-- 	Affichage conditionnel avec l'instruction if -->
	<c:if test="${firstName == 'John' }" var="result">
		<c:out value="${ result }"/>
	</c:if>
	<br><br>
	


		<!-- Declaration et initialisation d'une variable avec c:set-->
	<c:set var="income" value="${ 4000 * 4 }" />
	
	<p>
	<!-- Ic, on affiche la variable avec c:out-->
		Your income is :
		<c:out value="${income}" />
	</p>
	<p>
	<!-- Affichage conditionnel avec l'instruction c:choose (equivalent Switch Case) -->
		<c:choose>
			<c:when test="${income <= 1000}">  
       Income is not good.<br>
			</c:when>
			<c:when test="${income > 10000}">  
        Income is very good.<br>
			</c:when>
			<c:otherwise>  
       Income is undetermined...  
    		</c:otherwise>
		</c:choose>
	</p>
	
	
	<c:url value="/home" var="monlien" />
	<h2>
		<a href="${ monLien }">lien</a>
	</h2>
	
	<!-- Librairie Function-->
	<!-- Retourne true si "The" est au debut ds "Il était une fois", sino return false -->	
	<c:set var="msg" value="The Example of JSTL fn:startsWith() Function" />
	<p>La phrase commence avec "The" : ${fn:startsWith(msg, 'The')}</p>
	<p>La phrase commence avec "The" : ${fn:startsWith(msg, 'example')}</p>
	
	
	<!-- Indique le nombre d'éléments d'une chaine -->
	<p>${fn:length("bonjour")}</p>
	
	<!--Retourne true si "une" est dans "Il était une fois", sinon return false -->
	<p>${ fn:contains("Il était une fois", "une") }</p>
	
	<!-- Retourne true si "fois" est a la fin  ds "Il était une fois", sino return false -->
	<p>${ fn:endsWith("Il était une fois", "fois") }</p>
	
	<!--Retourne l'index de la sous chaîne dans la chaîne -->
	<p>${ fn:indexOf("Il était une fois", "une") }</p>
	
		<!--Retourne la sous chaîne dans la chaîne selon l'intervalle precise
		en paramètre (fin non inclus) -->
	<p>${ fn:substring("Il était une fois", 9, 12) }</p>
	
	
</body>
</html>