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
		<style type="text/css">
			.good_img {
				width: 98%;
			}
			.shopListView{
				margin:0;
			}
			.shopListView p{
				margin-bottom:10px;
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

		<!--商品列表-->
		<div class="row shopListView">
			<c:forEach items="${goodlist}" var="vargood" varStatus="status">
				<a  href="goodDetail.do?pcik=${vargood.gId}" target="_blank">
					<div class="col-sm-4">
						<div class="col-xs-7">
							<img class="good_img" src="http://localhost:8080${pageContext.request.contextPath}${vargood.piclink}" alt="" />
						</div>
						<div class="col-xs-5">
							<p>${vargood.goodname}</p>
							<p>价格:${vargood.price}</p>
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
		<!-- 翻页工具 -->
		<div class="paging" style="text-align:center">
			<nav aria-label="Page navigation">
			  <ul class="pagination">
			    <li>
			      <a href="indexp.do?pno=${pno-1}&psize=${psize}" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <li><a href="#">${pno}</a></li>
			    <li>
			      <a href="indexp.do?pno=${pno+1}&psize=${psize}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			</nav>
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
	</script>

</html>