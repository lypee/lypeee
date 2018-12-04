<%--
  Created by IntelliJ IDEA.
  User: lypee
  Date: 2018/12/4
  Time: 7:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>编辑学员</title>
    <script type="text/javascript">
        function updateUser(){
            var form = document.forms[0];
            form.action = "<%=basePath%>updateUser";
            form.method="post";
            form.submit();
        }
    </script>
</head>
<body>
<h1>编辑(更改)用户 可以为空
    可以修改的有姓名 邮箱 密码 年龄 性别</h1>
<form action="editUser" name="userForm">

    姓名：<input type="text" name="name" value="${user.name}"/>
    邮箱: <input type="text" name="email" value="${user.email}"/>
    个人密码: <input type="text" name="password" value="${user.password}">
    年龄：<input type="int" name="age" value="${user.age}"/>
    性别: <input type="int" name="sex" value="${user.sex}">

    <input type="button" value="编辑" onclick="updateUser()"/>
</form>
</body>
</html>
