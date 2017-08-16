<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <link href="../style/style.css" rel='stylesheet' type='text/css'/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript"></script>
</head>
<body>
<!-----start-main---->
<div class="main">
    <div class="login-form">
        <h1>Member Login</h1>
        <div class="head">
            <img src="../images/user.png" alt=""/>
        </div>
        <s:form namespace="/pss" action="login">
            <s:textfield type="text" class="text" name="username"/>
            <s:textfield type="password" name="password"/>
            <div class="submit">
                <input type="submit" value="LOGIN">
            </div>
            <p><s:actionerror/></p>
        </s:form>
    </div>
</div>

<div style="display:none">
    <script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script>
</div>
</body>
</html>