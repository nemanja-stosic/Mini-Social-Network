
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>Register</h1>
        <s:form action="register">
            <s:textfield key="user.name" label="Name" />
            <s:password key="user.password" label="Password" />
            <s:submit />
        </s:form>
    </body>
</html>
