
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Social Network</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>Log In</h1>
        <s:form action="login">
            <s:textfield key="user.name" label="Name" />
            <s:password key="user.password" label="Password" />
            <s:submit />
        </s:form>
        <br/>
        <form action="/SocialNetwork/register.jsp">
            <input type="submit" value="Register"/>
        </form>
    </body>
</html>
