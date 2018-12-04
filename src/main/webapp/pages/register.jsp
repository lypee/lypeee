<%--
  Created by IntelliJ IDEA.
  User: lypee
  Date: 2018/12/3
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注册页面</title>
</head>
<body>
<h3>注册页面</h3>
<br>
<form action="register" method="post">
    <table>
        <tr>
            <td><label>输入你想成为的id:</label></td>
            <td><input type="int" id="id" name="id" placeholder="注意不要重复噢"/> </td>
        </tr>
        <tr>
            <td><label>登录名：</label></td>
            <td><input type="text" id="name" name="name" placeholder="填写用户名"></td>
        </tr>
        <tr>
            <td><label>邮箱：</label></td>
            <td><input type="text" id="email" name="email" placeholder="填写邮箱"></td>
        </tr>
        <tr>
            <td><label>密码：</label></td>
            <td><input type="password" id="password" name="password" placeholder="填写密码"></td>
        </tr>
        <tr>
            <td><label>年龄：</label></td>
            <td><input type="int" id="age" name="age" placeholder="填写年龄"></td>
        </tr>
        <tr>
            <td><label>性别: </label></td>
            <td><input type="int" id="sex" name="sex" placeholder="填写性别"></td>
        </tr>
        <tr>
            <td><input id="submit" type="submit" value="注册"></td>
        </tr>
    </table>
</form>
</body>
</html>
