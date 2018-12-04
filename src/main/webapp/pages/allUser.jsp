<%--
  Created by IntelliJ IDEA.
  User: lypee
  Date: 2018/12/3
  Time: 22:19
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
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <title>用户列表</title>
    <script type="text/javascript">
        function del(id){
            $.get("<%=basePath%>delUser?id=" + id,function(data){
                if("success" == data.result){
                    alert("删除成功");
                    window.location.reload();
                }else{
                    alert("删除失败");
                }
            });
        }
    </script>
    <script type="text/javascript">
        function logout(id){
            $.get("<%=basePath%>logOut?id="+  id,function (data) {
                if("success" == data.result){
                    alert("注销成功")
                    window.location.href("loginFrom");
                }else{
                    alert("注销失败") ;
                }

            })
        }
    </script>
</head>
<body>
<a href="<%=basePath%>toAddUser">新增学员</a>
<a href="<%=basePath%>logOut?id=${user.id}">注销</a>
<br/>

<table border="1">
    <tbody>
    <tr>
        <td>id</td>
        <th>姓名</th>
        <td>email</td>
        <th>年龄</th>
        <td>性别</td>
        <td>上次健身时间</td>
        <td>是否在健身房</td>
        <td>上次离开时间</td>
        <th>操作</th>
    </tr>
    <c:if test="${!empty userList }">
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.age }</td>
                <td>${user.sex}</td>
                <td>${user.lastLoginTime}</td>
                <td>${user.isActive}</td>
                <td>${user.leaveTime}</td>
                <td>
                    <a href="<%=basePath%>toEntryGym?id=${user.id}">进入</a>
                    <a href="<%=basePath%>toEditUser">编辑</a>
                    <a href="<%=basePath%>delUser?id=${user.id}">删除</a>
                    <a href="<%=basePath%>LeaveGym?id=${user.id}">离开</a>
                    <a href="<%=basePath%>DelayTime?id=${user.id}">延期</a>


                </td>
            </tr>

        </c:forEach>
    </c:if>
    </tbody>
</table>
</body>
</html>