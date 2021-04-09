<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<!--  See if a string can't be used for title -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prosjekt</title>
    <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/images/favicon.png">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Squada+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/project.css">
</head>

<body>
    <div class="project container-special">
        <div class="project-left transparent-bg">
            <img class="login-logo" src="https://via.placeholder.com/400x400.png/" alt="">
            <p class="project-name"></p>
            <p class="project-exhibition-name">Utstilling: ${project.exhibition.name}</p>
            <p class="project-project-name">Prosjekt: <c:out value="${project.projectName}"></c:out></p>
        </div> <!--.project-left-->

        <div class="project-right transparent-bg">
            <div class="project-right-top">
                <img class="project-image" src="https://via.placeholder.com/600x300.png/" alt="">
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quod quibusdam dignissimos, quo vero laboriosam aliquid officiis, odio nemo accusamus possimus veniam enim repellendus consectetur consequatur incidunt laborum totam dolores cumque?</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Veniam odio saepe quisquam, hic ratione mollitia, alias laudantium molestias voluptate delectus, temporibus labore. Quisquam soluta officia nisi. Illo itaque soluta necessitatibus!</p>
            </div>

            <div class="project-vote">
                <form class="project-form clearfix" action="" method="POST">
                    <input
                        type="hidden"
                        name="projectid"
                        value="<c:out value='${project.projectNumber}'></c:out>"
                    >

                    <div class="project-form-left">
						<label>
                            <input type="radio" name="points" value="0" ${points == null || points == '0' ? 'checked' : ''}>
                            0 poeng
                        </label>
                        <label>
                            <input type="radio" name="points" value="1" ${points == '1' ? 'checked' : ''}>
                            1 poeng
                        </label>
                        <label>
                            <input type="radio" name="points" value="2" ${points == '2' ? 'checked' : ''}>
                            2 poeng
                        </label>
                        <label>
                            <input type="radio" name="points" value="3" ${points == '3' ? 'checked' : ''}>
                            3 poeng
                        </label>
                        <label>
                            <input type="radio" name="points" value="4" ${points == '4' ? 'checked' : ''}>
                            4 poeng
                        </label>
                        <label>
                            <input type="radio" name="points" value="5" ${points == '5' ? 'checked' : ''}>
                            5 poeng
                        </label>
                    </div> <!--.project-form-left-->

                    <div class="project-form-right">
                        <label>Telefonnummer</label>
                        <input
                            type="text"
                            class="project-phonenumber"
                            name="number"
                            placeholder="Telefonnummer"
                            value="${phoneNumber}"
                            pattern="^[1-9]{1}[0-9]{7}$"
                            required
                        >
                        <input type="submit" class="project-cta button-default" value="Stem">
                        <c:if test="${revote != null}">
                       		<input type="hidden" name="revote" value="revote">
                        	<p>Du har allerede stemt på dette prosjektet, vil du stemme på nytt?</p>
                        </c:if>
                    </div>
                </form> <!--.project-form-->
            </div> <!--.project-vote-->
        </div> <!--.project-right-->
    </div> <!--.project-->
</body>
</html>