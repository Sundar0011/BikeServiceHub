<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="form-1" action="otpServlet" method="post">
	<label for="input-1">Enter otp:</label>
	<input id="input-1" placeholder="Text" type="text" name="vcode"/>
	<input class="btn btn-primary" type="submit" value="otpServlet" id="button-1" />
	</form>
</body>
</html>