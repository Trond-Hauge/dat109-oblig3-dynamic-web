<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<!--  See if a string can't be used for title -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultater</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Squada+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="..css/normalize.css">
    <link rel="stylesheet" href="..css/main.css">
    <link rel="stylesheet" href="..css/view-results.css">
</head>

<body>
    <div class="view-results container-special">
        <div class="view-results-box transparent-bg">
            <div class="view-results-left">
                <table class="view-results-table">
                    <tr class="view-results-table-top">
                        <th class="view-results-col1">Resultat</th>
                        <th class="view-results-col2">Totale Stemmer</th>
                    </tr>

                    <c:forEach items="${resultMap}" var="p">
                        <tr>
                            <td>${p.key}</td>
                            <td>${p.value}</td>
                        </tr>
                    </c:forEach>
                </table> <!--.view-results-table-->

                <form class="view-results-form" action="vote" method="GET">
                    <input type="submit" class="vote-again-cta button-default" value="Gi ny stemme">
                </form>
            </div> <!--.view-results-left-->
        </div> <!--.view-results-box-->

        <a class="vote-success-tilbake" href="#">&larr; Tilbake</a>
    </div> <!--.view-results-->
</body>
</html>