<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action ="LoginServlet" method="post" >
		<label for="input-1">Email</label>
		<input id="input-1" placeholder="Text" type="text" name="Email"/>
		<label for="input-2">Password</label>
		<input id="input-2" placeholder="Text" type="text" name="Password"/>
		<input class="btn btn-primary" type="submit" value="LoginServlet" />
	</form>
</body>
</html>