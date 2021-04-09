<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<!--  See if a string can't be used for title -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultater</title>
	<link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/images/favicon.png">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Squada+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/view-results.css">
</head>

<body>
    <div class="view-results container-special">
		<c:if test="${exhibitions != null}">
			<form class="transparent-bg" action="result" method="POST">
				<h1 class="choose-title">Velg expo</h1>

                <label>Utstilling</label>
				<select name="id" class="exhibition">
                    <c:forEach items="${exhibitions}" var="p">
						<option value="${p.id}">${p.name}</option>
					</c:forEach>
                </select>
                <input type="submit" class="vote-cta button-default" value="Velg">
			</form>
		</c:if>

		<c:if test="${resultMap != null}">
			<c:set var="count" value="0" scope="page" />

			<div class="view-results-box transparent-bg">
		    	<div class="view-results-left">
	                <table class="view-results-table">
						<thead class="view-results-table-head">
							<tr class="view-results-table-top">
								<th class="view-results-col1">Resultat</th>
								<th class="view-results-col2">Poeng</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${resultMap}" var="p">
								<c:set var="count" value="${count + 1}" scope="page" />

								<tr>
									<td data-label="resultat">
										<span class="table-numbering">
											<c:out value="${count}" />
										</span>
										<span class="view-results-td-label">${p.key}</span>
									</td>
									<td data-label="stemmer">${p.value}</td>
								</tr>
							</c:forEach>
						</tbody>
	                </table> <!--.view-results-table-->

	                <form class="view-results-form" action="vote" method="GET">
	                    <input type="submit" class="vote-again-cta button-default" value="Gi ny stemme">
	                </form>
	            </div> <!--.view-results-left-->
	        </div> <!--.view-results-box-->
		</c:if>

        <a class="vote-success-tilbake" href="confirmation">&larr; Tilbake</a>
    </div> <!--.view-results-->
</body>
</html>