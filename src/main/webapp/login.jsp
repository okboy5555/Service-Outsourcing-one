<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>用户登陆</title>
	
	<link rel="stylesheet" type="text/css" href="css/default.css">
	
	<!--必要样式-->
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<!--[if IE]>
		<script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
	
</head>
<body id="container">
	<canvas id="background"></canvas>
	<div class='login'>
	  <div class='login_title'>
	    <span>账号登录</span>
	  </div>
	  <div class='login_fields'>
	    <div class='login_fields__user'>
	      <div class='icon'>
	        <img src='images/user_icon_copy.png'>
	      </div>
	      <input placeholder='用户名' type='text' id="strLogin">
	        <div class='validation'>
	          <img src='images/tick.png'>
	        </div>
	      </input>
	    </div>
	    <div class='login_fields__password'>
	      <div class='icon'>
	        <img src='images/lock_icon_copy.png'>
	      </div>
	      <input placeholder='密码' type='password' id="password">
	      <div class='validation'>
	        <img src='images/tick.png'>
	      </div>
	    </div>
	    <div class='login_fields__submit'>
	      <input id="login_flag" type='submit' value='登录'>
	       <input id="register_flag" type='submit'  value='注册'>
	      <div class='forgot'>
	        <a href='#'>忘记密码?</a>
	      </div>
	    </div>
	    
	  </div>
	  <div class='success'>
	    <h2>认证成功</h2>
	    <p>欢迎回来</p>
	  </div>
		<div class="fail">
			<h2>认证失败</h2>
			<p>请重新登陆</p>
		</div>
	  <div class='disclaimer'>
	    <p>用户登陆</p>
	  </div>
	</div>
	<div class='authent'>
	  <img src='images/puff.svg'>
	  <p>认证中...</p>
	</div>
	
	<script type="text/javascript" src='./js/stopExecutionOnTimeout.js?t=1'></script>
	<script src="http://www.jq22.com/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript" src="./js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="./js/background.js"></script>
	<script type="text/javascript" src="./js/modernizr-custom.js"></script>
	<script type="text/javascript" src="./js/mouseclick.js"></script>
	<script type="text/javascript" src="./js/login.js"></script>
</body>
</html>