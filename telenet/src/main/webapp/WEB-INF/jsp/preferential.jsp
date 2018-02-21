<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=basePath%>/static/css/layui.css">
  </head>
<body>  
<div style="margin-bottom: 5px;">       
   
<ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px" data-ad-client="ca-pub-6111334333458862" data-ad-slot="3820120620"></ins>
 
</div>

<div id="edit" style="display:none; margin-top:20px">
<form class="layui-form" action="">  

   <div class="layui-form-item">
    <label class="layui-form-label">优惠名称</label>
    <div class="layui-input-block">
      <input type="text" id="title" name="title" lay-verify="required" autocomplete="off" placeholder="请输入优惠项标题" class="layui-input" style="width:70%;">
    </div>
  </div>
  
  <div class="layui-form-item"  style="width:70%;">
    <label class="layui-form-label">优惠类型</label>
    <div class="layui-input-block">
      <select name="type" lay-filter="aihao">
      	<option value="1">抵扣优惠</option>
      	<option value="2">折扣优惠</option>
      </select>
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">条件金额</label>
      <div class="layui-input-inline">
        <input type="text" id="conditions" name="conditions" lay-verify="required|number" autocomplete="off" class="layui-input" placeholder="请输入优惠的条件金额">
      </div>
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">抵扣/折扣</label>
      <div class="layui-input-inline">
        <input type="text" id="pref" name="pref" lay-verify="required|number" autocomplete="off" class="layui-input" placeholder="折扣为：1-10">
      </div>
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">确认</button>
    </div>
  </div>
  </form>
</div>
 
<!-- <div class="layui-btn-group demoTable">
  <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
  <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
  <button class="layui-btn" data-type="isAll">验证是否全选</button>
</div> -->
<div class="layui-btn-group demoTable" style="margin-left: 1200px;">
	<button class="layui-btn" data-type="addStu">添加</button>
</div>
<div class="student" style="margin-left: 30px;">
<table class="layui-table" lay-data="{width: 1300, height:500, url:'<%=basePath%>/preferential/findAll', page:true, id:'idTest'}" lay-filter="demo">
  <thead>
    <tr>
     <!--  <th lay-data="{type:'checkbox', fixed: 'left'}"></th> -->
      <th lay-data="{field:'id', width:60, sort: true, fixed: true}">ID</th>
      <th lay-data="{field:'title', width:300}">名称</th>
      <th lay-data="{field:'typeStr', width:150}">类型</th>
      <th lay-data="{field:'conditions', width:150}">条件金额</th>
      <th lay-data="{field:'pref', width:150}">折扣/抵扣金额</th>
      <th lay-data="{fixed: 'right', align:'center', toolbar: '#barDemo'}">操作</th>
    </tr>
  </thead>
</table>
 </div>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
               
          
<script src="<%=basePath%>/static/layui.js"></script>
<script src="<%=basePath%>/static/js/jquery.js"></script>
<script>
layui.use(['table', 'form'],function(){
  var table = layui.table
  ,form = layui.form;
  var id;
  //监听表格复选框选择
  table.on('checkbox(demo)', function(obj){
    console.log(obj)
  });
  //监听工具条
  table.on('tool(demo)', function(obj){
    var data = obj.data;
    if(obj.event === 'detail'){
    } else if(obj.event === 'del'){
    //删除操作
      layer.confirm('真的删除行么', function(index){
      $.ajax({
		url: "<%=basePath%>/preferential/del",
                    data: {
                        "id" : JSON.stringify(data.id)
                    },
                    success: function (data) {
                    	//var dataobj = typeof data === "object" ? data : eval("(" + data + ")");
						if(data.result==true){
							obj.del();
        					layer.close(index);
        					layer.msg("删除成功");
						}else{
							layer.msg(data.reason);
						}
                    }
            });
      });
      
    } else if(obj.event === 'edit'){
      //layer.alert('编辑行：<br>'+ JSON.stringify(data));
	      layer.open({
	          type: 1,
	          closeBtn: 0,
	          area: ['600px', '400px'],
	          shift: 2,
	          shadeClose: false,
	          content: $("#edit"),
	          btn: ['关闭'],
	          yes: function(index, layero){
	           /* id=null;
	           layer.close(index); */
	           window.location.reload();
	          }
	      });
	      document.getElementById("title").value=data.title;
	      $("#type option[value='"+ data.type +"']").prop('selected', true);
	      document.getElementById("conditions").value=data.conditions;
	      document.getElementById("pref").value=data.pref;
	      form.render();  
	      id = data.id;
    }
  });
  
  //自定义验证规则
form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    }
    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
    ,content: function(value){
      layedit.sync(editIndex);
    }
});

  //监听提交
form.on('submit(demo1)', function(data){
	var title = data.field.title;
	var type = data.field.type;
	var conditions = data.field.conditions;
	var pref = data.field.pref;
	if(!(conditions>0) || !(pref>0)){
		layer.msg("金额需大于0");
		alert(1);
		return false;
	}
	if(id==null){
		$.ajax({
			url: "<%=basePath%>preferential/add",
	        data: {
	        	"title":title,
	            "type" : type,
	            "conditions" : conditions,
	            "pref" : pref,
	        },
	        success: function (data) {
				if(data.result==true){
				   layer.msg("添加成功");
	
	               setTimeout(function () {
	                   window.location.href = "<%=basePath%>preferential/page";
	               }, 1000);
					
				}else{
					layer.msg(data.reason);
				}
	        }
	
	     });
	}else{
		$.ajax({
			url: "<%=basePath%>preferential/add",
	        data: {
	        	"id":id,
	            "title":title,
	            "type" : type,
	            "conditions" : conditions,
	            "pref" : pref,
	        },
	        success: function (data) {
				if(data.result==true){
				   layer.msg("修改成功");
	
	               setTimeout(function () {
	                   window.location.href = "<%=basePath%>preferential/page";
	               }, 1000);
					
				}else{
					layer.msg(data.reason);
				}
	        }
	
	     });
	}
     return false;
  });

  var $ = layui.$, active = {
  	//添加
    addStu: function(){ 
		layer.open({
	          type: 1,
	          closeBtn: 1,
	          area: ['500px', '400px'],
	          shift: 2,
	          shadeClose: true,
	          content: $("#edit"),
	      });
    }
    //以下未使用
    ,getCheckData: function(){ //获取选中数据
      var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
      layer.alert(JSON.stringify(data));
    }
    ,getCheckLength: function(){ //获取选中数目
      var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
      layer.msg('选中了：'+ data.length + ' 个');
    }
    ,isAll: function(){ //验证是否全选
      var checkStatus = table.checkStatus('idTest');
      layer.msg(checkStatus.isAll ? '全选': '未全选')
    }
  };
  
  $('.demoTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
});
</script>

</body>
</html>
