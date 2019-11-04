<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0,minimal-ui:ios">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<title>simpleshop</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/view/lib/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/view/lib/css/nav.css" />
		<script src="${pageContext.request.contextPath}/view/lib/js/jquery-1.9.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/view/lib/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/view/lib/js/util.js"></script>
		<script src="${pageContext.request.contextPath}/view/lib/js/nav.js"></script>
	</head>
	<style>
		.content{
			margin:0;
		}
		.perviewnav{
			width:120px;
		}
		.perviewnav p{
			padding:0;
			margin-top:10px;
			line-height:35px;
			cursor:pointer;
			-webkit-user-select:none;
		    -moz-user-select:none;
		    -ms-user-select:none;
		    user-select:none;
		}
		.perviewnav p.picked{
			color:#f40;
		}
		.content .perorder{
			display:none;
		}
		.content .perdata{
			border:1px solid #ccc;
			min-height:400px;
			text-align:center;
		}
		.content .perdata .updateform{
			margin-top:20px;
		}
		.content .perorder{
			text-align:center;
		}
		.content .perorder table th{
			text-align:center;
		}
		.content .perorder table td{
			width:60px;
			display: table-cell;
	        vertical-align: middle;
	        text-align: center;
		}
		.content .peraddcomment{
			display:none;
			text-align:center;
		}
		.content .peraddcomment .content{
			width:100%;
			min-height:120px;
		}
	</style>
	<body>
		<!--导航栏-->
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
					<a class="navbar-brand" href="#">Brand</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active">
							<a href="index.do">Home <span class="sr-only">(current)</span></a>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown notLoginView">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">注册/登录<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li>
									<a href="javascript:;" data-toggle="modal" data-target="#regModel">注册</a>
								</li>
								<li>
									<a href="javascript:;" data-toggle="modal" data-target="#loginModel">登录</a>
								</li>
							</ul>
						</li>
						<li class="dropdown LoginView">
							<a href="#"><img class="headpic img-circle" src="#" alt="" /></a>
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
								<span class="nickname"></span>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li>
									<li><a href="zone.do?pno=1&psize=10">个人中心</a></li>
								</li>
								<li>
									<a href="#" onclick="logout();return false;">登出</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	
		<!-- 主题内容 -->
		<div class="row content">
			<div class="perview col-sm-offset-2 col-sm-2">
				<div class="perviewnav">
					<img class="img-responsive perdatapic" src="http://localhost:8080/05-springMVC-SSM-simpleshop${user.piclink}"/>
					<p class="picked databtn">个人资料</p>
					<p class="orderbtn">订单</p>
				</div>
			</div>
			<div class="perdata col-sm-6">
				<form class="form-horizontal updateform" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-sm-2 control-label">昵称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control nickname" name="nickname" placeholder="${user.nickname}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">头像</label>
						<div class="col-sm-10">
							<input type="file" class="form-control file" name="file" onchange="changepic(this)" accept="image/png,image/jpeg,image/jpg">
						</div>
					</div>
					<button type="button" class="btn btn-primary" onclick="updatehUser(${user.uId})">保存</button>
				</form>
			</div>
			<div class="perorder col-sm-7">
				<table class="table table-striped table-bordered table-hover ordertable">
				 	<tr>
						<th>商品名</th>
						<th>价格</th>
						<th>数量</th>
						<th>时间</th>
						<th>评论</th>
					</tr>
					<c:forEach items="${orderlist}" var="order" varStatus="status">
						<tr>
							<td>${order.goodname}</td>
							<td>${order.price}</td>
							<td>${order.number}</td>
							<td>${order.createTime}</td>
							<c:if test="${order.commented == 1}">
								<td>${order.content}</td>
							</c:if>
							<c:if test="${order.commented == 0}">
								<td><input type="button" value="添加评论" onclick="showAddCommentForm(${order.oId},this)"></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
				<!-- 翻页工具 -->
				<div class="paging" style="text-align:center">
					<nav aria-label="Page navigation">
					  <ul class="pagination">
					    <li>
					      <a href="#" aria-label="Previous" onclick="getOrderByUsernameAndPage(1)">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    <li><a href="#" class="page">1</a></li>
					    <li>
					      <a href="#" aria-label="Next" onclick="getOrderByUsernameAndPage(2)">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					  </ul>
					</nav>
				</div>
			</div>
			<div class="peraddcomment col-sm-6">
				<form class="addcommentform">
					<input type="hidden" name="oId" class="oId">
					<textarea name="content" class="content" placeholder=" 给宝贝全面的评价，可以帮助到更多小伙伴哦！"></textarea><br/>
					<button type="button" class="btn btn-danger" onclick="backToOrder()">返回</button>
					<button type="button" class="btn btn-primary" onclick="addCommentByOId()">添加</button>
				</form>	
			</div>
		</div>
		
		<!--注册模态框-->
		<div class="modal fade" id="regModel" tabindex="-1" role="dialog" aria-labelledby="regLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
						<h4 class="modal-title" id="regLabel">注册</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal regForm" method="post" enctype="multipart/form-data">
							<div class="form-group">
								<label class="col-sm-2 control-label">username</label>
								<div class="col-sm-10">
									<input type="text" class="form-control username" name="username" placeholder="username">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control password" name="password" placeholder="password">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">nickname</label>
								<div class="col-sm-10">
									<input type="text" class="form-control nickname" name="nickname" placeholder="nickname">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">pic</label>
								<div class="col-sm-10">
									<input type="file" class="form-control file" name="file" placeholder="file" accept="image/png,image/jpeg,image/jpg">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" onclick="reg()" data-dismiss="modal">注册</button>
					</div>
				</div>
			</div>
		</div>

		<!--登录模态框-->
		<div class="modal fade" id="loginModel" tabindex="-1" role="dialog" aria-labelledby="loginLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
						<h4 class="modal-title" id="loginLabel">登录</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal loginForm" method="post">
							<div class="form-group">
								<label class="col-sm-2 control-label">username</label>
								<div class="col-sm-10">
									<input type="text" class="form-control username" name="username" placeholder="username">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control password" name="password" placeholder="password">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" onclick="login()" data-dismiss="modal">登录</button>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script>
		let newsrc = "";//上传图片的临时路径
		
		function clearInput(which){
			if(which == 'updateform'){
				$('.updateform .nickname').val('');
				$('.updateform .file').val('');
			}else if(which == 'addcommentform'){
				$('.peraddcomment .addcommentform .oId').val('');
				$('.peraddcomment .addcommentform .content').val('');
			}else{
				
			}
		}
		
		/* 修改个人资料 */	
		function updatehUser(uId){
			let formData = new FormData();
			let nickname = document.querySelector('.updateform .nickname').value;
			let file = document.querySelector('.updateform .file');
			if(nickname !== "" && nickname !== null){
				formData.append('nickname',nickname);
			}
			if(file.files[0] != undefined){
				formData.append('file',file.files[0]);
			}
			formData.append('uId',uId);
			af('POST','updateUser.do',formData,function(data){
				if(formData.has('nickname')){
					localStorage.setItem("_nickname",data.nickname);
					document.querySelector('.LoginView .nickname').innerText = localStorage.getItem("_nickname");
					document.querySelector('.updateform .nickname').placeholder = localStorage.getItem("_nickname");
				}
				if(formData.has('file')){
					localStorage.setItem("_piclink",data.piclink);
					document.querySelector('.headpic').src = newsrc;
				}
				clearInput('updateform');
			})
		}	
		
		/* 订单操作 */
		let tempobj;//保存触发位置
		function showAddCommentForm(oId,self){
			tempobj = self;
			$('.perorder').hide();
			$('.peraddcomment').show();
			$('.peraddcomment .addcommentform .oId').val(oId);
		}
		function backToOrder(){
			$('.perorder').show();
			$('.peraddcomment').hide();
			clearInput('addcommentform');
		}
		function addCommentByOId(){
			let oId = $('.peraddcomment .addcommentform .oId').val();
			let content = $('.peraddcomment .addcommentform .content').val();
			ad('post','addCommentByOId.do',{
				oId:oId,
				content:content
			},function(){
				$(tempobj).parent().html('').text(content);
				backToOrder();
			});
		}
		function getOrderByUsernameAndPage(type){
			let username = getCookie('_username');
			let pno = parseInt($('.page').text());
			if(type === 1)pno--;
			else pno++;
			if(pno < 1)pno = 1;
			let psize = 10;
			ad("post", "getOrderByUsernameAndPage.do", {
				"username":username,
				"pno":pno,
				"psize":psize
			}, function(data) {
				$('.paging .page').text(pno);
				worder(data);
			});
		}
		function worder(data){
			$('.ordertable').html();
			let str = "<tr>"+
				"<th>商品名</th>"+
				"<th>价格</th>"+
				"<th>数量</th>"+
				"<th>时间</th>"+
				"<th>评论</th>"+
			"</tr>";
			for(let i=0;i<data.length;i++){
				let order = data[i];
				str += 
					"<tr>"+
						"<td>"+order.goodname+"</td>"+
						"<td>"+order.price+"</td>"+
						"<td>"+order.number+"</td>"+
						"<td>"+order.createTime+"</td>";
				if(order.commented == 1){
					str += "<td>"+order.content+"</td>";
				}else{
					str +=
						"<td><input type='button' value='添加评论' onclick='showAddCommentForm("+order.oId+",this)'></td>"+
						"</tr>";
				}
			}
			$('.ordertable').html(str);
		}
		
		/* 选项卡切换 */
		$('.databtn').click(function(){
			$('.orderbtn').removeClass("picked");
			$('.databtn').addClass("picked");
			$('.perorder').hide();
			$('.perdata').show();		
		});
		$('.orderbtn').click(function(){
			$('.databtn').removeClass("picked");
			$('.orderbtn').addClass("picked");
			$('.perdata').hide();
			$('.perorder').show();
		});
	
		/* 预览图片 */
		function changepic(obj) {//即时预览input上传的图片
			//console.log(obj.files[0]);//这里可以获取上传文件的name
			newsrc = getObjectURL(obj.files[0]);
			document.querySelector('.perdatapic').src = newsrc;
		}
		function getObjectURL(file) {//建立一個可存取到該file的url
			var url = null;
			// 下面函数执行的效果是一样的，只是需要针对不同的浏览器执行不同的 js 函数而已
			if(window.createObjectURL != undefined) { // basic
				url = window.createObjectURL(file);
			} else if(window.URL != undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file);
			} else if(window.webkitURL != undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file);
			}
			return url;
		}
	</script>
</html>