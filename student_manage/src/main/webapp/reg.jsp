<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<jsp:include page="common/base.jsp"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>注册页面</title>
    <link href="./Wopop_files/style_log.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="./Wopop_files/style.css">
    <link rel="stylesheet" type="text/css" href="./Wopop_files/userpanel.css">
    <link rel="stylesheet" type="text/css" href="./Wopop_files/jquery.ui.all.css">
    <script type="text/javascript">
        function resetValue(){
            document.getElementById("userName").value="";
            document.getElementById("password").value="";
            document.getElementById("rePassword").value="";
        }
    </script>
    <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="js/jquery.XYTipsWindow.min.2.8.js"></script>
</head>

<body class="login" mycollectionplug="bind">
<div class="login_m">
    <div class="login_logo"><img src="./Wopop_files/reg1.png" width="298" height="63"></div>
    <div class="login_boder" style="height: auto">
        <div class="login_padding"  id="login_model" style="height: auto">
            <form action="register" method="post" >
                <h2>用户名</h2>
                <label>
                    <input type="text" id="username" name="username" class="txt_input txt_input2"  value="${userName}">
                </label>
                <h2>密码</h2>
                <label>
                    <input type="password" name="password" id="userpwd" class="txt_input" value="${password}">
                </label>
                <h2>确认密码</h2>
                <label>
                    <input type="password" name="rePassword" id="reuserpwd" class="txt_input" value="${rePpassword}">
                </label>

                <p class="forgot"></p>

                <div class="rem_sub">
                    <label>
                        <input type="submit" class="sub_button" name="button"  value="注册" style="opacity: 0.7;">
                        <input type="button" class="sub_button" name="button"  value="返回登录" style="opacity: 0.7;" onclick="location='index.jsp'">
                    </label>
                </div>
            </form>
        </div>
        <div>
        <!--提示错误-->
        <p><font color="#4169e1">${error}</font></p>
    </div>
        <!--login_padding  Sign up end-->
    </div><!--login_boder end-->
</div><!--login_m end-->


</body></html>