<%--
  Created by IntelliJ IDEA.
  User: lypee
  Date: 2018/12/3
  Time: 21:54
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
    <title>添加用户</title>
    <script type="text/javascript">
        function addUser(){
            var form = document.forms[0];
            form.action = "<%=basePath%>addUser";
            form.method="post";
            form.submit();
        }
    </script>
</head>
<body>
<h1><%=path%>添加学员<%=basePath%></h1>
<form action="" name="userForm">
    id: <input type="int" name="id"/>
    姓名：<input type="text" name="name"/><br/>
    email: <input type="text" name="email"/>
    年龄：<input type="int" name="age"><br/>
    性别: <input type="text" name="sex"/>
    remark: <input type="int" name="remark"/>
    password: <input type="text" name="password"/>
    <input type="button" value="添加" onclick="addUser()">
</form>
</body>
</html>