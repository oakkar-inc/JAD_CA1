<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register</title>
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/register.css">
</head>

<body class="primary-bg">
    <p class="h1">Register</p>
    <form action="/JAD_CA1/api/register" method="post" class="h3 form-container">
        <div>
        	<label>Sign Up As</label><br>
         	<label>
    		<input type="radio" name="role" value="2" required>
    		Customer
  			</label>
  			<label>
    		<input type="radio" name="role" value="3" required>
    		Cleaner
  			</label><br>
            <label for="name">Name<br></label>
            <input class="form-input" type="text" id="name" name="name" required>
            <label for="email">Email<br></label>
            <input class="form-input" type="email" id="email" name="email" required>
            <label for="mobile">Mobile Number<br></label>
            <input class="form-input" type="number" id="mobile" name="mobile" required>
            <label for="password">Password<br></label>
            <input class="form-input" type="password" id="password" name="password" required>
        </div>
        <div>
            <label class="h2" for="password">Address<br></label>
            <label for="postal">Postal Code<br></label>
            <input class="form-input" type="number" id="postal" name="postal" required>
            <label for="floor">Floor<br></label>
            <input class="form-input" type="number" id="floor" name="floor" required>
            <label for="unit">Unit<br></label>
            <input class="form-input" type="number" id="unit" name="unit" required>
            <input id="goBackBtn" class="form-button text-danger" name="submit" type="reset" value="Cancel">
            <input class="form-button secondary-bg" name="submit" type="submit" value="Register">
            <%
            	if(request.getParameter("errMsg") != null) {
            		out.print("<p>There was an error during registration</p>");
            	}
            	if(request.getParameter("successMsg") != null) {
            		out.print("<p>You have been successfully registered as a cleaner. Log in to the cleaners portal to start seeing jobs.</p>");
            	}
            %>
        </div>
    </form>
    <script src="script/register.js"></script>
</body>

</html>