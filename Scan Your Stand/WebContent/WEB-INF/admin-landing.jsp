<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<!--  See if a string can't be used for title -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logg inn admin</title>
	<link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/images/favicon.png">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Squada+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-landing.css">
</head>

<body>
	<div class="admin-landing-top transparent-bg container-special">
        <h1 class="admin-landing-title">Kontrollpanel</h1>

        <div class="admin-landing-bot transparent-bg">
            <form class="admin-landing-form" action="manageExhibition" method="POST">
                <label>Utstilling</label>
                <select name="exponr" class="exponr">
                    <c:forEach items="${exhibitions}" var="p">
						<option value="${p.id}" selected="${p.id == exhibition.id ? 'seleted' : ''}">${p.name}</option>
					</c:forEach>
                </select>

                <input type="submit" name="operation" class="start-vote button-default" value="Start avstemning">
                <input type="submit" name="operation" class="stop-vote button-default" value="Stopp avstemning">

                <hr>

                <input type="submit" name="operation" class="administrate-expo button-default" value="Administrer utstilling">
            </form> <!--.admin-landing-form-->
        </div> <!--.admin-landing-bot-->

        <a class="admin-logout button-default" href="login-admin">
            Logg ut
        </a>
    </div> <!--.admin-landing-top-->
</body>
</html>