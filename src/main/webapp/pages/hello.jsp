<%--
  Created by IntelliJ IDEA.
  User: lypee
  Date: 2018/12/3
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>首页</title>
</head>
<body>
<h5>
    <a href="<%=basePath%>getAllUser">进入用户管理页</a>
</h5>

</body>
</html>
