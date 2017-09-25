<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-1.9.1.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css" />
<!-- 引入主题样式 -->
<link href="${pageContext.request.contextPath}/statics/themes/bootstrap/easyui.css" rel="stylesheet">
<!-- 引入图标的样式 -->
<link href="${pageContext.request.contextPath}/statics/themes/icon.css" rel="stylesheet">
<!-- 引入easyui.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
$(function(){
	
	//对表格数据的查询
	$('#usertb').datagrid({
		url:"${pageContext.request.contextPath}/selectUser",
	    title:"用户管理",
	    queryParams : {
	    	userkey : $("#userkeybox").val()
		},
		pageNumber:1,			/* 初始化页码 */
		pageSize:5,				/* 初始化页面大小 */
		pageList:[1,2,3,4,5,10],/* 初始化页面大小选择列表 */
	    columns:[[
	        {field:'u_id',title:'用户ID',width:100,align:'center',hidden:true},    
	        {field:'u_name',title:'用户名',width:100,align:'center'},    
	        {field:'u_telephone',title:'联系电话',width:100,align:'center'},
	        {field:'u_email',title:'邮箱',width:100,align:'center'},    
	        {field:'u_registertime',title:'注册时间',width:100,align:'center'}
	    ]],
	    fitColumns:true,		/* 自动调整行距 */
	    toolbar:"#tools",		/* 顶部工具栏 */
	    rownumbers:true , 		/* 显示行列号 */
	    ctrlSelect:true,
	    pagination:true			/* 显示分页工具栏,出现page,rows */
	});  
	//根据用户关键字查询用户信息
	$('#searchbtn').linkbutton({    
	    iconCls: 'icon-search' ,
	    url:"${pageContext.request.contextPath}/selectUser",
	    onClick:function(){
	    	//加载根据关键字搜索后的表格
	    	$('#usertb').datagrid("reload",{ 
	    		userkey : $("#userkeybox").val()
	    	})
	    	
	    }
	});  
	
	//删除用户
	//点击删除按钮，弹出删除对话框  
	$("#remove").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			var ids = [];
			var rows = $('#usertb').datagrid('getSelections');
			if (rows.length>0){
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    	    if (r){    
	    				for(var i=0; i<rows.length; i++){
		    				ids.push(rows[i].u_id);
		    			}
		    			$.ajax({
		    				url:"${pageContext.request.contextPath}/removeUser",
		    				type:'post',
		    				 data:{
		    					 selectUid:  ids
		    				} , 
		    				
		    			})
		    			//重载删除后的表格
		    			$('#usertb').datagrid("reload",{ 
		    				userkey : $("#userkeybox").val()
		    	    	}) 
	    			
	    	    	
	    	    }    
	    	}); 
			}
	    }
	}) 
	
	
	//编辑用户信息
	//弹出编辑窗口
	$("#edit").linkbutton({
		iconCls:"icon-edit",
		onClick : function() {
			var row = $('#usertb').datagrid('getSelected');
			if (row) {
				$('#editwin').dialog('open').dialog('center');
				$('#fm').form('load', row);
			}
		}
	})
	//保存修改后的用户信息
	$("#save").linkbutton({
		
		
		onClick : function() {
			$.ajax({
				url:"${pageContext.request.contextPath}/editUser",
				type:'post',
				data : {
					uid : $("input[name='u_id']").val(),
			    	uname : $("input[name='u_name']").val(),
			    	utelephone:$("input[name='u_telephone']").val(),
			    	uemail : $("input[name='u_email']").val(),
			    	uregistertime : $("input[name='u_registertime']").val()
				},
			})
			
			/* $('#fm').form('submit',{
	            url: "${pageContext.request.contextPath}/eidtUser",
	           
	            
	        }); */
		}
	})
		
		

	})
</script>
<body>
<div>
	<!-- 表格内容 -->
	<div>
		<table id="usertb" ></table>
	</div>	
	<div id="tools" style="padding:2px 5px;">
        <a id="edit"  class="easyui-linkbutton" iconCls="icon-edit" plain="true">Edit User</a>
        <a id="remove"  class="easyui-linkbutton" iconCls="icon-remove" plain="true">Remove User</a>
        <div style="float:right">
    		<input id="userkeybox" type="text"/>
    		<a id="searchbtn" href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true">Search</a>
    	</div>
    </div>
    <!-- 编辑弹窗 -->
    <div id="editwin" class="easyui-dialog" style="width:400px" title="编辑用户" closed="true"
             buttons="#dlg-buttons">
        <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <div style="margin-bottom:20px;font-size:14px;border-bottom:1px solid #ccc">用户信息</div>
            <div style="margin-bottom:10px;display:none">
                	用户id：<input name="u_id" class="easyui-textbox" required="true" label="用户id:" style="width:100%;">
            </div>
            <div style="margin-bottom:10px">
                	用户名：<input name="u_name" class="easyui-textbox" required="true" label="用户名:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                	联系电话：<input name="u_telephone" class="easyui-textbox" required="true" label="联系电话:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                	邮箱：<input name="u_email" class="easyui-textbox" required="true" label="邮箱:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                	注册时间：<input name="u_registertime" class="easyui-textbox" required="true" label="注册时间:" style="width:100%">
            </div>
        </form>
			<div id="footers" style="padding: 2px 5px;float:right;">
				<a id="save" class="easyui-linkbutton" iconCls="icon-save" plain="true" style="width:100px">保存</a> 
				<a id="cancel" class="easyui-linkbutton" iconCls="icon-cancel"	plain="true" style="width:100px">取消</a>
			</div>
		</div>  
    
    
	
		 







	</div>

</body>
</html>