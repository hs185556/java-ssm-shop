<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0,minimal-ui:ios">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>manage</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view/lib/css/bootstrap.min.css" />
	<script src="${pageContext.request.contextPath}/view/lib/js/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/view/lib/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/view/lib/js/util.js"></script>
</head>
<style type="text/css">
	header{
		width: 100%;
		height: 50px;
		background: #eee;
		text-align:center;
		line-height: 50px;
	}
	#content{
		padding-top:10px;
		width: 480px;
		height: 500px;
		margin:0 auto;
		text-align:center;
	}
</style>
<body>
	<header>
		<div id='ln'>
			<span>后台</span>
		</div>
	</header>
	<div id='content'>
		<form class="form-horizontal regForm" method="post" enctype="multipart/form-data" id="addGoodForm">
			<div class="form-group">
				<label class="col-sm-2 control-label">username</label>
				<div class="col-sm-10">
					<input type="text" class="form-control goodname" name="goodname" placeholder="goodname">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">password</label>
				<div class="col-sm-10">
					<input type="text" class="form-control price" name="price" placeholder="price">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">nickname</label>
				<div class="col-sm-10">
					<input type="text" class="form-control repertory" name="repertory" placeholder="repertory">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">pic</label>
				<div class="col-sm-10">
					<input type="file" class="form-control file" name="file" placeholder="file" accept="image/png,image/jpeg,image/jpg">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12">
					<input type="submit" onclick="addGood();return false;" value="添加商品">
				</div>
			</div>
		</form>
	</div>
</body>
<script>
	let localPath = "http://localhost:8080/05-springMVC-SSM-simpleshop";
	function clearInput(){
		$('.regForm .form-group').eq(0).find("input").val("");
		$('.regForm .form-group').eq(1).find("input").val("");
		$('.regForm .form-group').eq(2).find("input").val("");
		$('.regForm .form-group').eq(3).find("input").val("");
	}
	function addGood(){
		let formData = new FormData($('#addGoodForm')[0]);
		af('POST','addGood.do',formData,function(data){
			if(data.rs == 1){
				alert("添加成功");
			}else{
				alert("商品已存在");
			}
			clearInput();
		},function(){
			alert("添加失败");
			clearInput();
		})
	}
</script>
</html>