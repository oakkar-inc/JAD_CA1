<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>  
<%@ include file="navbar.jsp" %>   

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/home.css">
    <link rel="stylesheet" href="style/global.css">
<title>Home</title>
</head>
<body>
	<main>
        <div id="main-div">
            <div id="descriptions">
                <h1 class="des-text-area h1">Make Tour Space Shine With<span> Sparkle Pro </span>Cleaning</h1>
                <p class="des-text-area text-body">Experience the highest standard in cleaning for homes and businesses.
                     Our team is committed to delivering spotless results that bring comfort 
                     and health to every space.</p>
            </div>
            <div id="cards">
                <div class="card" id="card-1">
                    <h3 class="card-text-area h3">Eco-Friendly Products</h3>
                    <p class="card-text-area text-body">Safe, non-toxic cleaning solutions for a healthy environment.</p>
                </div>
                <div class="card" id="card-2">
                    <h3 class="card-text-area h3">Flexible Scheduling</h3>
                    <p class="card-text-area text-body">Book when it’s convenient for you, with options for one-time, weekly, or monthly service.</p>
                </div>
                <div class="card" id="card-3">
                    <h3 class="card-text-area h3">Trusted Professionals</h3>
                    <p class="card-text-area text-body">Fully vetted and trained cleaners who pay attention to the smallest details.</p>
                </div>
                <div class="card" id="card-4">
                    <h3 class="card-text-area h3">Comprehensive Cleaning Packages</h3>
                    <p class="card-text-area text-body">From deep cleaning to routine maintenance, we cover it all.</p>
                </div>
            </div>
        </div>
        <div id="img-div">
            <img src="assets/Cleaner.png" alt="character-1" style="height:60vh">
        </div>
    </main>
</body>
</html>