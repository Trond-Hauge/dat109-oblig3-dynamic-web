<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<!--  See if a string can't be used for title -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prosjekt</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Squada+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="..css/normalize.css">
    <link rel="stylesheet" href="..css/main.css">
    <link rel="stylesheet" href="..css/project.css">
</head>

<body>
    <div class="project container clearfix">
        <div class="project-left transparent-bg">
            <img class="project-avatar" src="https://via.placeholder.com/400x400.png/" alt="">
            <p class="project-author"><c:out value="${project.author}"></c:out></p>
            <p class="project-name">Prosjekt: <c:out value="${project.name}"></c:out></p>
        </div> <!--.project-left-->

        <div class="project-right transparent-bg">
            <div class="project-right-top">
                <img class="project-image" src="https://via.placeholder.com/600x300.png/" alt="">
                <p><c:out value="${project.description}"></c:out></p>
            </div>

            <div class="project-vote">
                <form class="project-form clearfix" action="" method="POST">
                    <div class="project-form-left">
                        <label>
                            <input type="radio" name="points" value="0">
                            0 poeng
                        </label>
                        <label>
                            <input type="radio" name="points" value="1">
                            1 poeng
                        </label>
                        <label>
                            <input type="radio" name="points" value="2">
                            2 poeng
                        </label>
                        <label>
                            <input type="radio" name="points" value="3">
                            3 poeng
                        </label>
                        <label>
                            <input type="radio" name="points" value="4">
                            4 poeng
                        </label>
                        <label>
                            <input type="radio" name="points" value="5">
                            5 poeng
                        </label>
                    </div>

                    <div class="project-form-right">
                        <label for="project-phone">Telefonnummer</label>
                        <input
                            type="text"
                            class="project-phonenumber"
                            name="number"
                            placeholder="Telefonnummer"
                            value="<c:out value='${user.phonenumber}'></c:out>"
                        >
                        <input type="submit" class="project-cta button-default" value="Stem">
                    </div>
                </form> <!--.project-form-->
            </div> <!--.project-vote-->
        </div> <!--.project-right-->
    </div> <!--.project-->
</body>
</html>