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
    
    <title>My JSP 'student.jsp' starting page</title>
    
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
    <label class="layui-form-label">手机号码</label>
    <div class="layui-input-block">
      <input type="text" id="phone" name="phone" lay-verify="required" autocomplete="off" placeholder="请输入客户手机号" class="layui-input" style="width:70%;">
    </div>
  </div>
  
  <div class="layui-form-item"  style="width:70%;">
    <label class="layui-form-label">服务</label>
    <div class="layui-input-block">
      <select name="money" id="money" lay-filter="aihao">
      	<option value="129">129元10M/月</option>
		<option value="169">169元20M/月</option>
		<option value="199">199元30M/月</option>
      </select>
    </div>
  </div>
  
 <div class="layui-form-item">
    <label class="layui-form-label">时长</label>
    <div class="layui-input-inline">
      	<select name="num" id="num" lay-filter="test">
	      	<option value="1">1个月</option>
			<option value="3">3个月</option>
			<option value="6">6个月</option>
			<option value="12">12个月</option>
		</select>
    </div>
    <div class="layui-input-inline">
      <select name="prefId" id="area">
		<c:if test="${not empty pList}"> 
			<c:forEach items="${pList}" var="d" varStatus="vs">
				<option value="${d.id}">${d.title}</option>
			</c:forEach>
		</c:if>
      </select>
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="demo1">提交订单</button>
    </div>
  </div>
  </form>
  <div id="stuDesc" style="margin-left:110px;"></div>
</div>
 
<!-- <div class="layui-btn-group demoTable">
  <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
  <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
  <button class="layui-btn" data-type="isAll">验证是否全选</button>
</div> -->
<div class="layui-btn-group demoTable" style="margin-left: 1200px;">
	<button class="layui-btn" data-type="add">添加</button>
</div>
<div class="student" style="margin-left: 30px;">
<table class="layui-table" lay-data="{width: 1300, height:500, url:'<%=basePath%>/order/findAll', page:true, id:'idTest'}" lay-filter="demo">
  <thead>
	  <tr>
	  	<th lay-data="{field:'id', width:40, sort: true, fixed: true}" rowspan="2">ID</th>
	  	<th lay-data="{align:'center'}" colspan="3">优惠信息</th>
	  	<th lay-data="{align:'center'}" colspan="3">客户信息</th>
	  	<th lay-data="{field:'startDate', width:100}" rowspan="2">开始时间</th>
	  	<th lay-data="{field:'endDate', width:100}" rowspan="2">结束时间</th>
	  	<th lay-data="{field:'createDate', width:170}" rowspan="2">创建时间</th>
	  	<th lay-data="{field:'money', width:90}" rowspan="2">实付金额</th>
		<th lay-data="{fixed: 'right', align:'center', toolbar: '#barDemo'}" rowspan="2">操作</th>
	  </tr>
      
      <tr>
      <th lay-data="{field:'title', width:170}">描述</th>
      <th lay-data="{field:'type', width:70}">类型</th>
      <th lay-data="{field:'pref', width:110}">折扣/抵扣值</th>
      <th lay-data="{field:'name', width:90}">姓名</th>
      <th lay-data="{field:'sex', width:60}">性别</th>
      <th lay-data="{field:'phone', width:150}">联系方式</th>
    </tr>
  </thead>
</table>
 </div>
<script type="text/html" id="barDemo">
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
      layer.msg('ID：'+ data.id + ' 的查看操作');
    } else if(obj.event === 'del'){
      layer.confirm('真的删除行么', function(index){
      $.ajax({
		url: "<%=basePath%>/order/del",
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

 form.on('select(test)',function(data){
     var num=parseInt(data.value);
     var money=$("#money").val();
     prefOption(num,money);
});

form.on('select(aihao)',function(data){
     var money=parseInt(data.value);
     var num=$("#num").val();
     prefOption(num,money);
     
});

function prefOption(num,money){
	 $.ajax({
         url: "<%=basePath%>order/getPreferential",
         data: {"num": num,
         		"money":money,
         },//发送的参数
         success:function(data){
             var proHtml = '';
             for(var o in data){
               proHtml += '<option value="' + data[o].id +  '">' + data[o].title + '</option>';
             }
             $('#area').html(proHtml);
             form.render();  
         },
         error:function(){
             //失败执行的方法
             alert("error");
         }
     });
}

  //监听提交
form.on('submit(demo1)', function(data){
	var num = data.field.num;
	var money = data.field.money;
	var prefId = data.field.prefId;
	var phone = data.field.phone;
    $.ajax({
		url: "<%=basePath%>order/add",
        data: {
        	"num":num,
            "money" : money,
            "prefId" : prefId,
            "phone" : phone,
        },
        success: function (data) {
			if(data.result==true){
			   layer.msg("添加成功");

               setTimeout(function () {
                   window.location.href = "<%=basePath%>order/page";
               }, 1000);
				
			}else{
				layer.msg(data.reason);
			}
        }

     });
     return false;
  });

  var $ = layui.$, active = {
  	//添加
    add: function(){ 
		layer.open({
	          type: 1,
	          closeBtn: 0,
	          area: ['700px', '400px'],
	          shift: 2,
	          shadeClose: false,
	          content: $("#edit"),
	          btn: ['查看分配信息','关闭'],
	          yes: function(index, layero){
	            var phone = $("#phone").val();
	          	if(phone==null || phone==""){
	          		layer.msg("请先输入手机号");
	          	}else{
	          		//显示分配信息
	          		$.ajax({
						url: "<%=basePath%>customer/getByPhone",
				        data: {
				        	"phone":phone,
				        },
				        success: function (aa) {
				             var proHtml = '';
				             if(aa!="" && aa!=null){
				                proHtml = '姓名：'+aa.name+'<br>性别：'+aa.sexStr+'<br>联系方式：'+aa.phone;
				             	$('#stuDesc').html(proHtml);
				             	form.render(); 
				             }else{
				             	layer.msg("该客户不存在");
				             }
				        }
			     	});
	          	}
	          }
	          ,btn2: function(index, layero){
	          window.location.href = "<%=basePath%>order/page";
	          }
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
