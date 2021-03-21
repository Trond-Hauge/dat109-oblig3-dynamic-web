<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<!--  See if a string can't be used for title -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logg inn admin</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Squada+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="..css/normalize.css">
    <link rel="stylesheet" href="..css/main.css">
	<link rel="stylesheet" href="..css/login.css">
</head>

<body>
	<div class="login-admin container-special clearfix">
        <div class="login-left transparent-bg">
            <h1 class="login-title">Logg inn som admin</h1>

			<c:if test="${login.error} != null}">
                <div class="login-error">
                    Brukernavn eller passord er feil.
                </div>
            </c:if>

            <form class="login-admin-form" action="" method="POST">
                <label>Brukernavn</label>
                <input type="text" class="username" name="username" placeholder="Skriv inn navnet ditt">
                <label>Passord:</label>
                <input type="password" class="password" name="password" placeholder="Skriv inn passordet ditt">
                <input type="submit" class="login-admin-cta button-default" value="Sign in">
            </form>
        </div>

        <div class="login-right">
            <img class="login-logo" src="../images/ScanYourStand.png" alt="">
        </div>
    </div> <!--.login-admin-->
</body>
</html>