	var page;
	var page2;
	var cur;
	var cur2;
	var totalpage;
	var totalpage2;
	var flag;
	var flag2;
	$(function() {
		$('#d1').hide();
		$('#d2').hide();
		$('#myTab li:eq(0) a').tab('show');
		$('#query1').click(function() {
			$("#a").empty();
			$("#b").empty();
			$("#chdiv1").empty();
			$("#chdiv1").append("<canvas id='myChart' width='300' height='300'></canvas>");
			$.ajax({
				url : 'queryscore',
				type : 'post',
				data : {
					'sname' : $('#in1').val(),
					'query' : 1,
					'start' : 1,
					'size' : 3
				},
				dataType : 'json',
				success : function(data) {
					$('#d1').hide();
					flag = data.flag;
					//console.log(data);
					if(flag){
					cur = data.current;
					$("#a").append("<tr><td>用户姓名</td><td>考试名</td><td>分数</td></tr>");
					totalpage = data.total;
					var bu = data.total;
					var stus = data.students;
					var all1 = data.allscore;
					$("#b").append("<button class='bs btn btn-default' id='first' disabled='disabled'>" + "首页" + "</button>");
					$("#b").append("<button class='bs btn btn-default' id='pre' disabled='disabled'>" + "上一页" + "</button>");
					for (var i = 0; i < bu; i++) {
						if (i==0) {
							$("#b").append("<button class='bs btn btn-default' disabled='disabled'>" + (i+1) + "</button>");
						}else{
							$("#b").append("<button class='bs btn btn-default'>" + (i+1) + "</button>");
						}
					}
					$("#b").append("<button class='bs btn btn-default' id='next'>" + "下一页" + "</button>");
					$("#b").append("<button class='bs btn btn-default' id='last'>" + "尾页" + "</button>");
					if(bu==1){
						$("#last").attr("disabled",true);
						$("#next").attr("disabled",true);
					}
					var lab = new Array();
					var sco = new Array();
					$.each(all1, function(i, v) {
						lab[i] = v.ename;
						sco[i] = v.score; 
					})
					$.each(stus, function(i, v) {
						//console.log(v);
						$("#a").append(
								"<tr><td>" + v.uname + "</td><td>" + v.ename
										+ "</td><td>" + v.score + "</td></tr>")
					})
					//alert(111);
					//console.log(lab);
					var chart1 = {
						labels :lab,
						datasets : [
						            {
						            	label: $('#in1').val()+'成绩',
						            	backgroundColor : "rgba(57,233,246,0.5)",
						            	borderColor : "rgba(220,220,220,1)",
						    			data : sco
						            }
						            ]
					}
					var ctx = document.getElementById("myChart").getContext("2d");
				    var myBarChart = new Chart(ctx, {
				                                        type: 'bar',
				                                        data: chart1,
				                                        options : {
				                                        	scales: {
				                                                yAxes: [{
				                                                    ticks: {
				                                                        beginAtZero:true
				                                                    }
				                                                }]
				                                            }
				                                        	}
				                                });
				}else{
					$('#d1').show();
				}
				}
			})
		});
		$('#b').on(
				'click',
				'.bs',
				function() {
					$(".bs").attr("disabled",false);
					$("#first").attr("disabled",false);
					$("#pre").attr("disabled",false);
					$("#last").attr("disabled",false);
					$("#next").attr("disabled",false);
					//console.log(cur);
					if ($(this).text()=="上一页") {
						page = cur-1;
					}else if ($(this).text()=="下一页") {
						page = cur+1;
					}else if ($(this).text()=="首页") {
						page = 1;
					}else if ($(this).text()=="尾页") {
						page = totalpage;
					}else{
						page = $(this).text();
					}
					//console.log("page="+page);
					//console.log($(this).text());
					$.ajax({
						url : 'queryscore',
						type : 'post',
						data : {
							'sname' : $('#in1').val(),
							'query' : 1,
							'start' : page,
							'size' : 3
						},
						dataType : 'json',
						success : function(data) {
							cur = data.current;
							//console.log("aaa="+cur);
							var stu = data.students;
							$("#a").empty();
							$("#a").append("<tr><td>用户姓名</td><td>考试名</td><td>分数</td></tr>");
							$.each(stu, function(i, v) {
								//console.log(v);
								$("#a").append(
										"<tr><td>" + v.uname + "</td><td>" + v.ename
												+ "</td><td>" + v.score
												+ "</td></tr>")
							})
							if (cur==1) {
								$("#first").attr("disabled",true);
								$("#pre").attr("disabled",true);
							}else if(cur == totalpage){
								$("#last").attr("disabled",true);
								$("#next").attr("disabled",true);
							}
						}
					})
					$(this).attr("disabled",true);
				})
		$('#query2').click(function() {
			$("#c").empty();
			$("#d").empty();
			$("#chdiv2").empty();
			$("#chdiv2").append("<canvas id='myChart2' width='300' height='300'></canvas>");
			$.ajax({
				url : 'queryscore',
				type : 'post',
				data : {
					'ename' : $('#in2').val(),
					'query' : 2,
					'start' : 1,
					'size' : 3
				},
				dataType : 'json',
				success : function(data) {
					$('#d2').hide();
					flag2 = data.flag2;
					if(flag2){
					//console.log(data);
					cur2 = data.current;
					$("#c").append("<tr><td>考试名</td><td>用户姓名</td><td>分数</td></tr>");
					totalpage2 = data.total;
					var bu = data.total;
					var stus = data.students;
					var all2 = data.allscore2;
					$("#d").append("<button class='bs2 btn btn-default' id='first2' disabled='disabled'>" + "首页" + "</button>");
					$("#d").append("<button class='bs2 btn btn-default' id='pre2' disabled='disabled'>" + "上一页" + "</button>");
					for (var i = 0; i < bu; i++) {
						if (i==0) {
							$("#d").append("<button class='bs2 btn btn-default' disabled='disabled'>" + (i+1) + "</button>");
						}else{
							$("#d").append("<button class='bs2 btn btn-default'>" + (i+1) + "</button>");
						}
					}
					$("#d").append("<button class='bs2 btn btn-default' id='next2'>" + "下一页" + "</button>");
					$("#d").append("<button class='bs2 btn btn-default' id='last2'>" + "尾页" + "</button>");
					if(bu==1){
						$("#last2").attr("disabled",true);
						$("#next2").attr("disabled",true);
					}
					//console.log(stus);
					$.each(stus, function(i, v) {
						//console.log(v);
						$("#c").append(
								"<tr><td>" + v.ename + "</td><td>" + v.uname
										+ "</td><td>" + v.score + "</td></tr>")
					})
					var s59=0;
					var s79=0;
					var s80=0;
					$.each(all2,function(i,v){
						if (v.score<60) {
							s59++;
						}else if(v.score<80&&v.score>=60){
							s79++;
						}else{
							s80++;
						}
					})
					var chart2 = {
						labels: [
						         '60分以下',
						         '60~79',
						         '80及以上'
						     ],
						datasets: [{
							backgroundColor:["#FF1CFD","#FD1414","#0A41F4"],
					        data: [s59, s79, s80]
					    }],
					}
					var ctx = document.getElementById("myChart2").getContext("2d");
				    var myBarChart = new Chart(ctx, {
				                                        type: 'pie',
				                                        data: chart2,
				                                });
				}else{
					$('#d2').show();
				}
				}
			})
		});
		$('#d').on(
				'click',
				'.bs2',
				function() {
					$(".bs2").attr("disabled",false);
					$("#first2").attr("disabled",false);
					$("#pre2").attr("disabled",false);
					$("#last2").attr("disabled",false);
					$("#next2").attr("disabled",false);
					//console.log(cur);
					if ($(this).text()=="上一页") {
						page2 = cur2-1;
					}else if ($(this).text()=="下一页") {
						page2 = cur2+1;
					}else if ($(this).text()=="首页") {
						page2 = 1;
					}else if ($(this).text()=="尾页") {
						page2 = totalpage2;
					}else{
						page2 = $(this).text();
					}
					//console.log("page="+page);
					//console.log($(this).text());
					$.ajax({
						url : 'queryscore',
						type : 'post',
						data : {
							'ename' : $('#in2').val(),
							'query' : 2,
							'start' : page2,
							'size' : 3
						},
						dataType : 'json',
						success : function(data) {
							cur2 = data.current;
							//console.log("aaa="+cur);
							var stu = data.students;
							$("#c").empty();
							$("#c").append("<tr><td>考试名</td><td>用户姓名</td><td>分数</td></tr>");
							$.each(stu, function(i, v) {
								//console.log(v);
								$("#c").append(
										"<tr><td>" + v.ename + "</td><td>" + v.uname
												+ "</td><td>" + v.score
												+ "</td></tr>")
							})
							if (cur2==1) {
								$("#first2").attr("disabled",true);
								$("#pre2").attr("disabled",true);
							}else if(cur2 == totalpage){
								$("#last2").attr("disabled",true);
								$("#next2").attr("disabled",true);
							}
						}
					})
					$(this).attr("disabled",true);
				})
	})