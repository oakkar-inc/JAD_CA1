<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link rel="stylesheet" href="style/register.css">
    <link rel="stylesheet" href="style/global.css">
</head>

<body class="primary-bg">
    <p class="h1">Log In</p>
    <form action="/JAD_CA1/api/login" method="post" class="h3 form-container">
        <div>
            <label for="email">Email<br></label>
            <input class="form-input" type="email" id="email" name="email" required>
          
            <label for="password">Password<br></label>
            <input class="form-input" type="password" id="password" name="password" required>
            <input class="form-button text-blue" name="submit" type="reset" value="Continue As Guest">
            <input class="form-button secondary-bg" name="submit" type="submit" value="Log in">
            <%
            	String errMsg = request.getParameter("errMsg");
            	if(errMsg != null) {
            		if(errMsg.equals("wrongpassword")) {
            			out.print("<p>Incorrect password</p>");
            		} else {
            			out.print("<p>There was an error during log in</p>");
            		}
            	}
            %>
        </div>
        <div id="login-img">
            <img src="assets/Cleaner2.png" alt="character-2" width="100%">
        </div>
    </form>
</body>

</html>