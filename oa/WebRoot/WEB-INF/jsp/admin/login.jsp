<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>办公管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta http-equiv="pragma" content="no-cache"/>
  <meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
  <link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
  <link rel="stylesheet" type="text/css" href="${path}/css/common/login1.css"/>
  <link rel="stylesheet" type="text/css" href="${path}/css/common/login2.css"/>
  <link rel="stylesheet" type="text/css" href="${path}/css/common/login3.css"/>
  <script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
  <script type="text/javascript">
    $(function(){
      //看不清楚按钮绑定点击事件
      $("#unclear").click(function(){
        $("#img").attr("src", "${path}/verifyCode?random=" + Math.random());
      });
      
      //点击验证码图片更换验证码
      $("#img").on("click", function(){
        $("#unclear").trigger("click");
      }).on("mouseover",function(){
        $(this).css("cursor", "pointer");
      });
      
      //按回车提交表单
      $(document).keyup(function(event){
        if(event.keyCode == 13){
          $("#vcode").blur();
          $(login_btn).trigger("click");
        }
      });
      
      //验证userId
      var usermsg = "";
      $("#userId").on("blur",function(){
        if($(this).val()=="") {
          //判断用户名是否为空
          usermsg = "用户名不能为空";
          $(this).attr("value",usermsg);
          $(this).css({"background-color":"#FFDEDE"});
        }else if(!/^\w{5,20}$/.test($(this).val())){
          //判断用户名格式
          usermsg = "请输入5-20个字符";
          $(this).attr("value",usermsg);
          $(this).css({"background-color":"#FFDEDE"});
        }
      }).on("focus",function(){
        if($(this).css("background-color") == "rgb(255, 222, 222)"){
          $(this).attr("value",null);
          $(this).css({"background-color":"WHITE"});
          usermsg="";
        }
      });
      
      //验证password
      var pwmsg = "";
      $("#password").on("blur",function(){
        if($(this).val()=="") {
          //判断密码是否为空
          pwmsg = "密码不能为空";
          $(this).attr("type","text");
          $(this).attr("value",pwmsg);
          $(this).css({"background-color":"#FFDEDE"});
        }else if(!/^[A-Za-z0-9]{6,20}$/.test($(this).val())){
          //判断密码格式
          pwmsg = "请输入6-20个字符";
          $(this).attr("type","text");
          $(this).attr("value",pwmsg);
          $(this).css({"background-color":"#FFDEDE"});
        }
      }).on("focus",function(){
        if($(this).css("background-color") == "rgb(255, 222, 222)"){
          $(this).attr("type","password");
          $(this).attr("value","");
          $(this).css({"background-color":"WHITE"});
          pwmsg="";
        }
      });
      
      //检验验证码
      var vcmsg = "";
      $("#vcode").on("blur",function(){
        if($(this).val()=="") {
          //判断是否为空
          vcmsg = "验证码不能为空";
          $(this).attr("value",vcmsg);
          $(this).css({"background-color":"#FFDEDE"});
        }else if(!/^[A-Za-z0-9]{4}$/.test($(this).val())){
          //判断格式
          vcmsg = "验证码长度为4位";
          $(this).attr("value",vcmsg);
          $(this).css({"background-color":"#FFDEDE"});          
        }
      }).on("focus",function(){
        if($(this).css("background-color") == "rgb(255, 222, 222)"){
          $(this).attr("value","");
          $(this).css({"background-color":"WHITE"});
          vcmsg="";
        }
      });
      
      //表单提交验证
      $("#login_btn").click(function(){
        $("#unclear").trigger("click");
        //验证没有发生错误时发送异步请求
        if(usermsg=="" && pwmsg=="" && vcmsg=="") {
          //序列化表格数据
          var formData = $("#login_form").serialize();
          //进行异步请求
          $.ajax({
            url: "${path}/admin/loginAjax",
            type: "post",
            data: formData,
            dataType: "json",
            success:function(data){
              if(data.status == 0) {
                //成功则跳转到main页面
                window.location.href = "${path}/admin/main";
              }else if(data.status == 1){
                //提示验证码错误
                $("#vcode").attr("value","请重新输入");
                $("#vcode").css({"background-color":"#FFDEDE"});
                //1.5秒后聚焦到验证码输入框
                window.setTimeout(function() {
                  $("#vcode").focus();
                }, 1500);
                
              }else if(data.status == 2){
                //提示用户名或密码错误
                $("#userId").attr("value","请重新输入");
                $("#userId").css({"background-color":"#FFDEDE"});
                $("#password").attr("type","text");
                $("#password").attr("value","请重新输入");
                $("#password").css({"background-color":"#FFDEDE"});
                //清空验证码输入框
                $("#vcode").attr("value","");
              } 
            },
            error:function(){
              alert("登录失败，请联系管理员");
            }
          });
        }
      });
    });
  </script>
</head>
<body class="logged-out   env-production windows  session-authentication page-responsive min-width-0">
  <!-- logo -->
  <div class="header header-logged-out width-full" role="banner">
    <div class="container clearfix width-full">
      <a class="header-logo" href="javascript:void(0)">
        <svg aria-hidden="true" class="octicon octicon-mark-github" height="48" version="1.1" viewBox="0 0 16 16" width="48">
           <path d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0 0 16 8c0-4.42-3.58-8-8-8z"></path>
        </svg>
      </a>
    </div>
  </div>

  <div role="main">
    <div id="js-pjax-container" >
      <div class="auth-form p-3" id="login">

        <!-- 登录表单 -->
        <form id="login_form" accept-charset="UTF-8" action="#" method="post">
          <div style="margin:0;padding:0;display:inline"></div>      
            <div class="auth-form-header">
              <h1>欢迎登录办公管理系统</h1>
          </div>
        
          <!-- 错误信息提示框 -->
          <div id="js-flash-container" style="display:none">
              <div class="flash flash-full flash-error">
                <div class="container">
                  <button class="flash-close js-flash-close" type="button">
                    <svg class="octicon octicon-x" height="16" version="1.1" viewBox="0 0 12 16" width="12">
                      <path d="M7.48 8l3.75 3.75-1.48 1.48L6 9.48l-3.75 3.75-1.48-1.48L4.52 8 .77 4.25l1.48-1.48L6 6.52l3.75-3.75 1.48 1.48z"></path>
                    </svg>
                  </button>
                 	 用户名或密码错误
                </div>
              </div>
          </div>

          <!-- 登录表单输入框 -->
          <div class="auth-form-body mt-4">
            <!-- 用户名 -->
            <label for="userId">请输入用户名</label>
            <input class="form-control input-block" id="userId" name="userId" tabindex="1" type="text" value="" />
            <!-- 密码 -->
            <label for="password">
              	 请输入密码 <a href="javascript:void(0)" class="label-link">忘记密码？</a>
            </label>
            <input class="form-control form-control input-block" id="password" name="password" tabindex="2" type="password" />
          <!-- 验证码 -->
            <label for="vcode">
              	请输入验证码
              <a href="javascript:void(0)" class="label-link" id="unclear">看不清楚</a>
            </label>
            <input class="form-control form-control input-block" id="vcode" name="vcode" tabindex="2" type="text" />
            <input type="checkbox" name="key" value="1" id="key"/>&nbsp;记住我&nbsp;&nbsp;&nbsp;
            <!-- 验证码生成 -->
            <img src="${path}/verifyCode" width="60" height="22" title="验证码" id="img"/>
            <!-- 登录按钮 -->
            <input class="btn btn-primary btn-block" id="login_btn" name="commit" tabindex="3" type="button" value="登&nbsp;&nbsp;&nbsp;&nbsp;录" />
          </div>
        </form>

        <p class="create-account-callout mt-3">
         	 没有账号？
          <a href="/join?source=login" data-ga-click="Sign in, switch to sign up">创建新用户</a>
        </p>
      </div>

    </div>
    <div class="modal-backdrop js-touch-events"></div>
  </div>

  <div class="site-footer" role="contentinfo">
    <ul class="site-footer-links">
      <li><font>办公管理系统</font></li>
      <li><font>Powered By 温家熙</font></li>
      <li><font>version 1.0</font></li>
    </ul>
  </div>
</html>