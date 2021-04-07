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
        <div class="admin-landing-bot transparent-bg">
            <div class="admin-landing-links">
                <label>Utstilling</label>
                <select name="exponr" class="exponr">
                    <option value="1">EXPO1</option>
                    <option value="2">EXPO2</option>
                    <option value="3">EXPO3</option>
                    <option value="4">EXPO4</option>
                </select>
                <a class="start-vote button-default" href="#">
                    Start avstemning
                </a>

                <a class="stop-vote button-default" href="#">
                    Stopp avstamning
                </a>

                <a class="administrate-expo button-default" href="admin-expo-config.jsp">
                    Administrer utstilling
                </a>
                
            </div>
        </div>
        <a class="admin-logout button-default" href="login-admin.jsp">
                    Logg ut
                </a>
    </div>
</body>
</html>