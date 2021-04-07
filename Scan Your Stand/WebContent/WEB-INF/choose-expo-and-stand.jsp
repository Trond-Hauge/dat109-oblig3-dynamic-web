<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<!--  See if a string can't be used for title -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Velg expo og stand</title>
    <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/images/favicon.png">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Squada+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/choose-expo-and-stand.css">
</head>

<body>
	<div class="choose container-special">
        <div class="choose-right">
            <img class="login-logo" src="${pageContext.request.contextPath}/images/ScanYourStand.png" alt="">
        </div>

        <div class="choose-left transparent-bg">
            <form class="choose-form" action="vote" method="GET">
                <h1 class="choose-title">Velg expo</h1>

                <label>Utstilling</label>
				<select name="exhibitionid" class="exhibition">
                    <c:forEach items="${exhibitions}" var="p">
						<option value="${p.id}" selected="${p.id == exhibition.id ? 'seleted' : ''}">${p.name}</option>
					</c:forEach>
                </select>

				<c:if test="${projects != null}">
					<label>Standnummer</label>
	                <select name="projectnr" class="standnr">
	                    <c:forEach items="${projects}" var="p">
							<option value="${p.projectNumber}">${p.projectName}</option>
						</c:forEach>
	                </select>
				</c:if>

                <input type="submit" class="vote-cta button-default" value="Velg">
            </form>
        </div> <!-- .choose-left -->
    </div> <!-- .choose-->
</body>
</html>