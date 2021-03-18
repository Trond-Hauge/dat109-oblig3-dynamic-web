<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<!--  See if a string can't be used for title -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Velg expo og stand</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Squada+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="..css/normalize.css">
    <link rel="stylesheet" href="..css/main.css">
	<link rel="stylesheet" href="..css/choose-expo-and-stand.css">
</head>

<body>
	<div class="choose container-special clearfix">
        <div class="choose-left transparent-bg">
            <form class="choose-form" action="vote" method="GET">
                <h1 class="choose-title">Velg expo</h1>

                <label>Utstilling</label>
                <input type="text" class="exhibition" name="exhibition">

				<label>Standnummer</label>
                <select name="projectnr" class="standnr">
                    <option value="1">Stand 1</option>
                    <option value="2">Stand 2</option>
                    <option value="3">Stand 3</option>
                    <option value="4">Stand 4</option>
                </select>

                <input type="submit" class="vote-cta button-default" value="Stem">
            </form>
        </div> <!-- .choose-left -->

        <div class="choose-right">
            <img class="login-logo" src="../images/ScanYourStand.png" alt="">
        </div>
    </div> <!-- .container-->
</body>
</html>