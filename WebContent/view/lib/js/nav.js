let localPath = "http://localhost:8080/05-springMVC-SSM-simpleshop";
function login() {
	let username = document.querySelector('.loginForm .username').value;
	let password = document.querySelector('.loginForm .password').value;
	let user = {
		"username":username,
		"password":password
	};
	aj("post", "loginUser.do", user, function(data) {
		setCookie("_username", data.username, 7);
		localStorage.setItem("_nickname",data.nickname);
		localStorage.setItem("_piclink",data.piclink);
		document.querySelector('.LoginView .headpic').src = localPath+data.piclink;
		document.querySelector('.LoginView .nickname').innerText = data.nickname;
		document.querySelector('.notLoginView').style.display = "none";
		document.querySelector('.LoginView').style.display = "block";
	}, function() {
		alert("账号或密码错误");
	});
}

function reg(){
	let formData = new FormData(document.querySelector('.regForm'));
	af('POST','regUser.do',formData,function(){
		alert("注册成功")
	})
}

function logout() {
	aj('POST','logoutUser.do',null,function(){
		localStorage.removeItem('_piclink');
		localStorage.removeItem('_nickname');
		//document.querySelector('.notLoginView').style.display = "block";
		//document.querySelector('.LoginView').style.display = "none";
		window.location.href = "index.do";
	})
}
window.onload = function() {
	if(getCookie("_username") != null || getCookie("_nickname") != undefined) {
		document.querySelector('.nickname').innerText = localStorage.getItem("_nickname");
		document.querySelector('.headpic').src = localPath+localStorage.getItem("_piclink");
		document.querySelector('.notLoginView').style.display = "none";
		document.querySelector('.LoginView').style.display = "block";
	}else{
		document.querySelector('.LoginView').style.display = "none";
		document.querySelector('.notLoginView').style.display = "block";
	}
}
