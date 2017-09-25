<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入主题样式 -->
<link href="${pageContext.request.contextPath }/statics/js/jquery-easyui-1.5.3/themes/bootstrap/easyui.css" rel="stylesheet">
<!-- 引入图标的样式 -->
<link href="${pageContext.request.contextPath }/statics/js/jquery-easyui-1.5.3/themes/icon.css" rel="stylesheet">
<!-- 先引入jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery-1.9.1.js"></script>
<!-- 引入easyui.js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<title>设置考题信息</title>
</head>
<script type="text/javascript">
	$(function(){
		//获取考试基本信息
		$.ajax({
			url : "${pageContext.request.contextPath}/ems/getExamBaseInfo",
			type : "post",
			success : function(json){
				var data = eval("("+json+")");
				var infoSpan = $("span[name='examInfo']");
				infoSpan.eq(0).html(data.examName);
				infoSpan.eq(1).html(data.examTime);
				infoSpan.eq(2).html(data.examTotal);
				infoSpan.eq(3).html(data.examState);
			}
		});
		
		//设置考题的table
		$("#setQuestionType").datagrid({
		    url:'${pageContext.request.contextPath}/ems/questionType.json',
		    width : 305,
		    columns:[[    
		        {field:'ck',checkbox:true,align:'center'},    
		        {field:'questionTypeName',title:'题型',width:100,align:'center'},    
		        {field:'questionNumber',title:'题数',width:50,align:'center',editor:{
	                type:'numberspinner',
	                options:{
	                	min : 0,
	                	max : 100,
	                	editable: true
	                }  	
		        }},    
		        {field:'questionScore',title:'每题分数',width:75,align:'center',editor:{
	                type:'numberspinner',
	                options:{
	                	min : 0,
	                	max : 100,
	                	editable: true
	                }
		        }},    
		        {field:'questionTotal',title:'总分数',width:50,align:'center'}    
		    ]],
		    border : true,
		    //编辑单元格
		    onClickCell : function(index, field, value){
				if (endEditing()){
					var tb = $('#setQuestionType');
					tb.datagrid('selectRow', index)
							.datagrid('editCell', {index:index,field:field});
					var data = tb.datagrid("getData");
					if(previousIndex != undefined){
						var total = data.rows[previousIndex].questionNumber * data.rows[previousIndex].questionScore;
						tb.datagrid("updateRow",{
							index : previousIndex,
							row : {
								questionTotal : total,
							}
						});
					}
					editIndex = index;
				}
		    },
		    onCheckAll : function(rows){//使所有tabs可用
		    	var tabs = $("#tt");
		    	tabs.tabs("select",0);
	    		tabs.tabs("enableTab",1);
	    		tabs.tabs("enableTab",2);
	    		tabs.tabs("enableTab",3);
		    },
		    onCheck : function(index, row){//选择部分可用
		    	var tabs = $("#tt");
		    	if(index == 0){
		    		tabs.tabs("enableTab",1);
		    		tabs.tabs("select",1);
		    	}
		    	if(index == 1){
		    		tabs.tabs("enableTab",2);
		    		tabs.tabs("select",2);
		    	}
		    	if(index == 2){
		    		tabs.tabs("enableTab",3);
		    		tabs.tabs("select",3);
		    	}
		    },
		    onUncheck : function(index, row){//取消设置某些考题
		    	var tabs = $("#tt");
	    		tabs.tabs("select",0);
		    	if(index == 0){
		    		tabs.tabs("disableTab",1);
		    	}
		    	if(index == 1){
		    		tabs.tabs("disableTab",2);
		    	}
		    	if(index == 2){
		    		tabs.tabs("disableTab",3);
		    	}
		    },
		    onUncheckAll : function(rows){//取消设置全部考题
		    	var tabs = $("#tt");
	    		tabs.tabs("select",0);
	    		tabs.tabs("disableTab",1);
	    		tabs.tabs("disableTab",2);
	    		tabs.tabs("disableTab",3);
		    }
		    
		});
		//editCell方法
		$.extend($.fn.datagrid.methods, {
			editCell: function(jq,param){
				return jq.each(function(){
					var opts = $(this).datagrid('options');
					var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
					for(var i=0; i<fields.length; i++){
						var col = $(this).datagrid('getColumnOption', fields[i]);
						col.editor1 = col.editor;
						if (fields[i] != param.field){
							col.editor = null;
						}
					}
					$(this).datagrid('beginEdit', param.index);
					for(var i=0; i<fields.length; i++){
						var col = $(this).datagrid('getColumnOption', fields[i]);
						col.editor = col.editor1;
					}
				});
			}
		});
		//是否结束编辑
		var editIndex = undefined;
		var previousIndex = undefined;
		function endEditing(){
			var tb = $('#setQuestionType');
			if (editIndex == undefined){return true}
			if (tb.datagrid('validateRow', editIndex)){
				tb.datagrid('endEdit', editIndex);
				//记录上一个编辑的索引
				previousIndex = editIndex;
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		};
		
		$("#tt").tabs({
			fit : true
		});
		//禁用选项卡
		$("#tt").tabs("disableTab",1);
		$("#tt").tabs("disableTab",2);
		$("#tt").tabs("disableTab",3);
		
		//选择题设置部分
		
		
		var indicator = $('<div class="indicator">>></div>').appendTo('#singleChoicePanel');
		
		$('.drag-item').draggable({
			revert:true,
			deltaX:0,
			deltaY:0
		}).droppable({
			onDragOver:function(e,source){
				indicator.css({
					display:'block',
					left:$(this).offset().left-10,
					top:$(this).offset().top+$(this).outerHeight()-5
				});
			},
			onDragLeave:function(e,source){
				indicator.hide();
			},
			onDrop:function(e,source){
				$(source).insertAfter(this);
				indicator.hide();
			}
		});
		
	});
</script>
<body>
  <div class="easyui-layout" style="width:100%;height:700px;">
        <div data-options="region:'north'" title="考试信息" style="height:110px">
        	<h2 style="float: right;">
        		<label>考试名:</label><span name='examInfo'></span>
        		<label>考试时间:</label><span name='examInfo'></span>
	        	<br/>
        		<label>考试总分:</label><span name='examInfo'></span>
        		<label>考试状态:</label><span name='examInfo'></span>
        	</h2>
        </div>
        <div  data-options="region:'east',split:false" title="选择考题类型" style="width: 307px;">
        	<!-- 设置考题 -->
			<table id="setQuestionType" >
			</table>
        </div>
        <div data-options="region:'center',title:'选题区'">
        	<div id="tt" class="easyui-tabs" style="width:700px;height:250px">
        		<div title="Welcome">
	        	<h1>请先选择考题</h1>
        		</div>
	       		<!-- 单选题设置面板 -->
				<div id="singleChoicePanel" title="单选题" style="padding:10px">
					<!-- 已选面板 -->
					<div>
						<ul style="margin:0;padding:0;margin-left:10px;">
							<li class="drag-item">Drag 1</li>
							<li class="drag-item">Drag 2</li>
							<li class="drag-item">Drag 3</li>
							<li class="drag-item">Drag 4</li>
							<li class="drag-item">Drag 5</li>
							<li class="drag-item">Drag 6</li>
						</ul>
					</div>
				</div>
				<!-- 多选题设置面板 -->
				<div id="multipleChoicePanel" title="多选题" style="padding:10px">
				</div>
				<!-- 主观题设置面板 -->
				<div id="subjectiveItemPanel" title="主观题" style="padding:10px">
				</div>
			</div>
        </div>
    </div>
</body>
</html>