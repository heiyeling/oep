<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 先引入jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-1.9.1.js"></script>
<!-- 引入bootstrap.css -->
<link type="text/css" href="${pageContext.request.contextPath}/statics/js/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
	#head{
		width: 100%;
        height: 40px;
        background-color: #9d9d9d;
	}
</style>
</head>
<body>
<div id="head" >
    <ul class="nav nav-pills">
        <li role="presentation"><img src="imag/IMG_0978.JPG" class="img-responsive" style="width: 65px;height: 40px;margin: 0px 200px 0px 50px"></li>
        <li role="presentation"><a href="#">首页</a></li>
        <li role="presentation" class="active"><a href="#">我的考试</a></li>
        <li role="presentation"><a href="#" style="margin:0px 400px 0px 0px">个人信息</a></li>
        <li role="presentation"><h4>名字</h4></li>
    </ul>
</div>
<div id="body">
    <div class="container" style="margin-top: 50px;height: 100px;border: 1px solid" >
        <h2>我的考试</h2>
        <ul>
            <li><a role="button" class="btn btn-default">zzzzz</a></li>
        </ul>
    </div>
</div>
</body>
</html>