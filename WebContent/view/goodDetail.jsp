<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>simpleshop</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/lib/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/lib/css/nav.css" />
<script src="${pageContext.request.contextPath}/view/lib/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/view/lib/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/view/lib/js/util.js"></script>
<script src="${pageContext.request.contextPath}/view/lib/js/nav.js"></script>
<style>
	ul{
		list-style:none;
	}
	p{
		margin:0;
		padding:0;
	}
	.goodView p{
		margin-bottom:20px;
	}
	.mybtn{
		font-size:18px;
		cursor:pointer;
		padding:5px;
		display:inline-block;
		margin-bottom:10px;
	}
	.number{
		text-align:center;
	}
	
	.nocomment{
		text-align:center;
		margin-top:40px;
	}
	.commentlist{
		margin-top:10px;
	}
	.commentlist li{
		border:1px solid #ccc;
		border-bottom:0px;
		padding:5px;
	}
	.commentlist li:last-child{
		border-bottom:1px solid #ccc;
	}
	.commentlist .left{
		text-align:center;
	}
	.commentlist .headpic{
		display:inline-block;
		width: 48px;
	}
	.commentlist .cname{
		font-size:12px;
	}
	.commentlist .ctime{
		color:#B0B0B0;
		font-size:12px;
		margin-top:10px;
	}
	
</style>
</head>

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
							<li><a href="zone.do?pno=1&psize=10">个人中心</a></li>
							<li><a href="#"onclick="logout();return false;">登出</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	
	<!-- 商品评论展示 -->
	<div class="container">
		<div class="row goodView">
			<div class="col-sm-6 col-md-4 col-md-offset-2">
				<img class="img-responsive" src="http://localhost:8080${pageContext.request.contextPath}${good.piclink}" alt="" />
			</div>
			<div class="col-sm-6 col-md-4">
				<p>${good.goodname}</p>
				<p>price:${good.price}</p>
				<p>库存：<span class="repertory">${good.repertory}</span></p>
				<span class="mybtn" onclick="changeNum(1,event)">&nbsp;-&nbsp;</span>
				<input type="text" value="1" class="number" readonly />
				<span class="mybtn" onclick="changeNum(2,event)">&nbsp;+&nbsp;</span><br/>
				<button type="button" class="btn btn-primary" onclick="buyGoodById(${good.gId},event)">立即购买</button>
			</div>
		</div>
		<c:if test="${empty commentlist}"> 
			<p class="nocomment">暂无评论</p>
		</c:if>
		<c:if test="${!empty commentlist}"> 
			<ul class="commentlist">
				<c:forEach items="${commentlist}" var="comment" varStatus="status">
					<li>
						<div class="row">
							<div class="col-xs-2 left">
								<img class="img-responsive headpic" src="http://localhost:8080/05-springMVC-SSM-simpleshop${comment.piclink}"/>
								<br/>
								<p class="cname">${comment.nickname}</p>
							</div>
							<div class="col-xs-10 right">
								<p>
									<p class="ccontent">${comment.content}</p>
									<p class="ctime">${comment.time}</p>
								</p>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
			<!-- 翻页工具 -->
			<div class="paging" style="text-align:center">
				<nav aria-label="Page navigation">
				  <ul class="pagination">
				    <li>
				      <a href="#" aria-label="Previous" onclick="getCommentByGIdAndPage(${good.gId},1)">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				    <li><a href="#" class="page">1</a></li>
				    <li>
				      <a href="#" aria-label="Next" onclick="getCommentByGIdAndPage(${good.gId},2)">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				  </ul>
				</nav>
			</div>
		</c:if>
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
	function changeNum(type,e){
		let obj = e.srcElement || e.target;
		let num = parseInt($(obj).parent().find('.number').val());
		if(type == 1)--num; 
		else ++num;
		if(num < 1)num = 1;
		$(obj).parent().find('.number').val(num)
	}
	function buyGoodById(gId,e){
		let obj = e.srcElement || e.target;
		let username = getCookie("_username");
		let number = parseInt($(obj).parent().find('.number').val());
		if(username == null || username == undefined) {
			alert("请先登录");
			return false;
		}
		ad("post", "buyGoodById.do", {
			"gId":gId,
			"username":username,
			"number":number
		}, function(data) {
			if(data.rs === 1){
				let repertory = parseInt($(obj).parent().find('.repertory').text());
				$(obj).parent().find('.repertory').text(repertory - number)
				alert("购买成功");
			}else{
				alert("库存不足");
			}
		}, function(e) {
			alert("购买失败");
		});
	}
	function getCommentByGIdAndPage(gId,type){
		let pno = parseInt($('.page').text());
		if(type === 1)pno--;
		else pno++;
		if(pno < 1)pno = 1;
		let psize = 10;
		ad("post", "getCommentByGIdAndPage.do", {
			"gId":gId,
			"pno":pno,
			"psize":psize
		}, function(data) {
			$('.page').text(pno);
			wcomment(data);	
		});
	}
	function wcomment(data){
		$('.commentlist').html("");
		let str = "";
		for(let i=0;i<data.length;i++){
			let comment = data[i];
			str += 
			"<li>"+
				"<div class='row'>"+
					"<div class='col-xs-2 left'>"+
						"<img class='img-responsive headpic' src='http://localhost:8080/05-springMVC-SSM-simpleshop"+comment.piclink+"'/>"+
						"<br/>"+
						"<p class='cname'>"+comment.nickname+"</p>"+
					"</div>"+
					"<div class='col-xs-10 right'>"+
						"<p>"+
							"<p class='ccontent'>"+comment.content+"</p>"+
							"<p class='ctime'>"+comment.time+"</p>"+
						"</p>"+
					"</div>"+
				"</div>"+
			"</li>";
		}
		$('.commentlist').html(str);
	}
</script>
</html>