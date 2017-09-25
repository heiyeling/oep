<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 引入主题样式 -->
<link href="${pageContext.request.contextPath}/statics/themes/bootstrap/easyui.css" rel="stylesheet">
<!-- 引入图标的样式 -->
<link href="statics/themes/icon.css" rel="stylesheet">
<!-- 先引入jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-1.7.2.js"></script>
<!-- 引入easyui.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
$(function(){
	$("a[name='selectUser']")
})
	

</script>

<body>

<div id="cc" class="easyui-layout" style="height:900px;width：100%">   
    <div data-options="region:'west',title:'West',split:true" style="width:20%;">
    <ul id="tt" class="easyui-tree">   
    <li>   
        <span>考生</span> 
        <ul>
        	<li><a href ="#">注册</a></li>
        	<li><a href ="#">登录</a></li>
        	<li>
        		 <span>个人中心</span> 
        		 <ul>
        		 	<li>
        		 	<span>我的考试</span>
						<ul>
						<li><a href ="#">查看详情</a></li>
						<li><a href ="#">成绩查询</a></li>
						<li><a href ="#">查询考试</a></li>
						</ul>
					</li>
        			<li><a href ="#">个人信息</a></li>
        		 </ul>
        		
        	</li>
        </ul>
    </li>   
    <li>   
        <span>管理员</span> 
        <ul>
        	<li>
        		<span>考试管理</span> 
        		<ul>
        			<li>kao1</li>
        			<li>kao1</li>
        		</ul>
        	</li>
        	<li>
        		<span>题库管理</span> 
        		<ul>
        			<li>kao1</li>
        			<li>kao1</li>
        		</ul>
        	</li>
        	<li>
        		<span>用户管理</span> 
        		<ul>
        			<li><a href ="${pageContext.request.contextPath}/selectuser.jsp">用户查询</a></li>
        			<li><a href ="#">修改信息</a></li>
        			<li><a href ="#">删除用户</a></li>
        		</ul>
        	</li>
        	<li>
        		<span>成绩查询</span> 
        		<ul>
        			<li>kao1</li>
        			<li>kao1</li>
        		</ul>
        	</li>
        	
        
        </ul>  
    </li>   
</ul>  
    
    </div>   
    <div data-options="region:'center',title:'center title'" style="padding:5px;width:80%;background:#eee;"></div>   
</div>  


	

</body>
</html>