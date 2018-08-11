<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@include file="common/base.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>学生信息管理系统主界面</title>
    <%
        // 权限验证
        if (session.getAttribute("currentUser") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
    %>
    <link rel="stylesheet" type="text/css" href="./assets/js/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./assets/js/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="./assets/js/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="./assets/js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>


    <script type="text/javascript">
        $(function () {
            // 数据
            var treeData = [{
                text: "根",
                children: [{
                    text: "班级信息管理",
                    attributes: {
                        url: "grade/grade_info_manage.jsp"
                    }
                }, {
                    text: "学生信息管理",
                    attributes: {
                        url: "student/student_info_manage.jsp"
                    }
                }]
            }];

            // 实例化树菜单
            $("#tree").tree({
                data: treeData,
                lines: true,
                onClick: function (node) {
                    if (node.attributes) {
                        openTab(node.text, node.attributes.url);
                    }
                }
            });

            // 新增Tab
            function openTab(text, url) {
                if ($("#tabs").tabs('exists', text)) {
                    $("#tabs").tabs('select', text);
                } else {
                    var content = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src=" + url + "></iframe>";
                    $("#tabs").tabs('add', {
                        title: text,
                        closable: true,
                        content: content
                    });
                }
            }
        });
        function showTime(){
            nowtime=new Date();
            year=nowtime.getFullYear();
            month=nowtime.getMonth()+1;
            date=nowtime.getDate();
            document.getElementById("mytime").innerText=year+"年"+month+"月"+date+" "+nowtime.toLocaleTimeString();
        }
        setInterval("showTime()",1000);
    </script>


</head>
<body class="easyui-layout">

<div region="north" style="height: 80px;background-color: #E0EDFF">
    <div align="left" style="width: 80%;float: left"><img src="assets/images/main.jpg"></div>
    <div style="padding-top: 50px;padding-right: 10px;font-weight: bold;font-size: 18px">
        欢迎：&nbsp;<font color="#4169e1">${currentUser.userName }</font>
        &nbsp;&nbsp;
        <a href="logout" style="padding-right: 0;text-decoration: none;">退出</a>
    </div>

</div>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" style="background:url('./assets/images/hy.jpg') fixed center center no-repeat; width: 100%;background-size: cover;">
            <div align="center" style="padding-top: 100px;"><font color="white" size="10">
                <span id="mytime"></span>
                </font>
            </div>
        </div>
    </div>
</div>
<div region="west" style="width: 150px;background:url('./Wopop_files/login_bgx.gif') fixed center center repeat-x;background-size: cover;" title="导航菜单" split="true" >
    <ul id="tree"></ul>
</div>
<div region="south" style="height: 25px;" align="center">版权所有<a href="https://blog.csdn.net/qq_35402412">Chang</a></div>
</body>
</html>