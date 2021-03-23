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
	<link rel="stylesheet" href="..css/admin-landing.css">
</head>

<body>
	<div class="admin-landing-top transparent-bg center-align">
        <img class="admin-pb" src="https://via.placeholder.com/400x400.png/" alt="">
        <p class="admin-name">Per Viskelær</p>
        
        <div class="admin-landing-bot transparent-bg">
            <div class="admin-landing-links">
                <div class="choose-date-admin">
                    <label for="starttado">Starttado:</label>
                    <input th:field="${vote.timeVote}" type="date" id="starttado" name="starttado">
                </div>
                <div class="timer">
                    <span class="hour">00</span>:<span class="minute">00</span>:<span class="second">10</span>
                </div>
                <div class="control">
                    <button onClick="timer.start(1000)">Start</button> 
                    <button onClick="timer.stop()">Stop</button> 
                    <button onClick="timer.reset(60)">Reset</button> 
                    <button onClick="timer.mode(1)">Count up</button> 
                    <button onClick="timer.mode(0)">Count down</button>
                </div>
                <a class="start-vote button-default" href="view-results.html">
                    Start avstemning
                </a>

                <a class="stop-vote button-default" href="#">
                    Stopp avstamning
                </a>
            </div>
        </div>
    </div>
</body>
</html>