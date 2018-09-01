<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<jsp:include page="common/base.jsp"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录页面</title>
    <link href="./Wopop_files/style_log.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="./Wopop_files/style.css">
    <link rel="stylesheet" type="text/css" href="./Wopop_files/userpanel.css">
    <link rel="stylesheet" type="text/css" href="./Wopop_files/jquery.ui.all.css">
    <script type="text/javascript">
        function resetValue() {
            document.getElementById("userName").value = "";
            document.getElementById("password").value = "";
        }
    </script>
    <script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="js/jquery.XYTipsWindow.min.2.8.js"></script>
</head>

<body class="login" mycollectionplug="bind">
<div class="login_m">
    <div class="login_logo"><img src="./Wopop_files/logo1.png" width="227" height="63"></div>
    <div class="login_boder">

        <div class="login_padding" id="login_model">
            <form action="login" method="post">
                <h2>用户名</h2>
                <label>
                    <input type="text" id="username" name="userName" class="txt_input txt_input2" value="${userName}">
                </label>
                <h2>密码</h2>
                <label>
                    <input type="password" name="password" id="userpwd" class="txt_input" value="${password}">
                </label>

                <p class="forgot"><font color="#4169e1">${error}</font><a id="iforget" href="#">忘记密码?</a></p>

                <div class="rem_sub">
                    <div class="rem_sub_l">
                        <input type="checkbox" name="checkbox" id="save_me">
                        <label for="save_me">记住我</label>
                    </div>
                    <label>
                        <input type="submit" class="sub_button" name="button" value="登录" style="opacity: 0.7;">
                        <input type="button" class="sub_button" id="reg" value="注册" style="opacity: 0.7;"
                               onclick="location='reg.jsp'">
                    </label>
                </div>
            </form>

        </div>


        <!--login_padding  Sign up end-->
    </div><!--login_boder end-->
</div><!--login_m end-->

<!--<br> <br>-->
<!--<p align="center"> More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>-->

</body>
</html>