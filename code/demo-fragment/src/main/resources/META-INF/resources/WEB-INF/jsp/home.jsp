<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>demo-web</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
</head>
<body>
	<div style="margin:20px auto;border:1px solid gray;padding:10px;width:500px;font-size:14px;text-align:center">
			<p>这是一个实例demo</p>
			<img src="${pageContext.request.contextPath }/assets/captcha/png" />
			<p><a href="${pageContext.request.contextPath }/user/login">登陆</a></p>
	</div>
</body>
</html>