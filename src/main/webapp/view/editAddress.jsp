<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit address</title>
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/editAddress.css">
    <script>
        const contextPath = "<%= request.getContextPath() %>";
    </script>
</head>
<body class="secondary-bg">
<%
	int addressId = Integer.parseInt(request.getParameter("addressId"));
	String postal = request.getParameter("postal");
    int floor = Integer.parseInt(request.getParameter("floor"));
    int unit = Integer.parseInt(request.getParameter("unit"));
%>
    <div class="container">
        <div class="form-container primary-bg">
            <form class="h3">
                <p class="h1">Edit Address</p>
                <input hidden name="addressId" id="addressId" value=<%=addressId %>>
                <label for="postal">Postal Code<br></label>
                <input class="form-input" type="number" id="postal" name="postal" value=<%=postal %> required>
                <label for="floor">Floor<br></label>
                <input class="form-input" type="number" id="floor" name="floor" value=<%=floor %> required>
                <label for="unit">Unit<br></label>
                <input class="form-input" type="number" id="unit" name="unit" value=<%=unit %> required>
                <input class="form-button text-danger" id="cancelBtn" type="button" value="Cancel">
                <input class="form-button secondary-bg" id="updateBtn" type="button" value="Update">
            </form>
        </div>
    </div>
    <script src="script/editAddress.js"></script>
</body>
</html>