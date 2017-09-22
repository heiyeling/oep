<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/js/jquery-1.9.1.js"></script>
<title>在线考试平台(OEP)-注册页面</title>
<script type="text/javascript">
	$(document).ready(function() {
		<!--判断两次密码是否一致-->
		$("#conPsw").focusout(function() {
			$.ajax({
				url : "valipsw",
				type : "post",

				data : {
					password : $("#password").val(),
					conPassword : $("#conPassword").val()
				},
				dataType : "json",
				success : function(data) {
					if (data == 0) {
						$("#psw-nullerror").css("display", "block");
					}
					if (data == 1) {
						$("#conpsw-nullerror").css("display", "block");
					}
					if (data == 2) {
						$("#conpsw-valierror").css("display", "block");
					}
					if (data == 5) {
						$("#psw-nullerror").css("display", "none");
						$("#conpsw-nullerror").css("display", "none");
						$("#conpsw-valierror").css("display", "none");
					}

				},

			});
		});
		<!--判断email地址是否合法-->
		$("#EMail").focusout(function() {
			$.ajax({
				url : "checkemail",
				type : "post",

				data : {
					email : $("#email").val(),
				},
				dataType : "json",
				success : function(data) {
					if (data == 0) {
						$("#email-nullerror").css("display", "block");
					}
					if (data == 1) {
						$("#email-checkerror").css("display", "block");
					}
					if (data == 5) {
						$("#email-nullerror").css("display", "none");
						$("#email-checkerror").css("display", "none");
					}
				},

			});
		});
	});
</script>

</head>
<body class="bg-primary">
	<div class="container">
		<div class="row">
			<div style="margin-top: 50px; margin-left: 42%">
				<h1>欢&nbsp;迎&nbsp;注&nbsp;册</h1>
			</div>
			<div class="col-md-3"></div>
			<div class="col-md-6" style="margin-top: 50px">

				<form action="register" method="post" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-0 control-label">用户名:</label> <input
							type="text" id="userName" placeholder="请输入用户名"
							class="form-control">
					</div>
					<div class="form-group">
						<label class="col-sm-0 control-label">密码:</label> <input
							type="text" id="password" placeholder="请输入密码"
							class="form-control">
						<div id="psw-nullerror" style="color: red; display: none">密码不能为空！</div>
					</div>
					<div class="form-group" id="conPsw">
						<label class="col-sm-0 control-label">确认密码:</label> <input
							type="text" placeholder="请确认密码" class="form-control"
							id="conPassword">
						<div id="conpsw-nullerror" style="color: red; display: none">密码不能为空！</div>
						<div id="conpsw-valierror" style="color: red; display: none">两次密码输入不一致！</div>
					</div>
					<div class="form-group" id="EMail">
						<label class="col-sm-0 control-label">E-mail:</label> <input
							type="text" id="email" placeholder="请输入e-mail"
							class="form-control">
							<div id="email-nullerror" style="color: red; display: none">邮箱地址不能为空！</div>
							<div id="email-checkerror" style="color: red; display: none">邮箱地址不合法！</div>
					</div>
					<div class="form-group">
						<label class="col-sm-0 control-label">手机号：</label> <input
							type="text" id="telephone" placeholder="请输入手机号"
							class="form-control">
					</div>
					<br />
					<div align="center">
						<input type="submit" value="提交注册" class="btn btn-success"
							name="register">
					</div>
				</form>
				<br />
				<div align="center">
					已有账号？<a href="Login.jsp" style="color: #cc99cc">点击直接登录</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>