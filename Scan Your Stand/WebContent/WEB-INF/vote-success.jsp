<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<!--  See if a string can't be used for title -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vellykket stemme</title>
	<link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/images/favicon.png">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Squada+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vote-success.css">
</head>

<body>
	<div class="vote-success center-align">
        <div class="vote-success-inner transparent-bg">
            <h1 class="vote-success-title">Takk for din stemme!</h1>

            <div class="vote-success-links">
                <a class="view-results button-default" href="result">
                    Vis resultat
                </a>

                <p></p>

                <a class="new-vote button-default" href="vote">
                    Endre min stemme
                </a>
            </div>
        </div>

        <a class="vote-success-tilbake" href="vote">&larr; Tilbake</a>
    </div> <!--.vote-success-->
</body>
</html>