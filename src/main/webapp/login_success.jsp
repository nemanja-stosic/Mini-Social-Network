
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div>
            <h1>Add Friend</h1>
            <s:form action="addFriend">
                <s:textfield key="name" label="Name" />
                <s:submit />
            </s:form>
        </div>

        <div>
            <h2>Your Friends</h2>
            <s:iterator value="#session.user.friends" var="user"><!-- it will asign current friend to var user var- (user is session)-->
                <li><s:property value="#user.name" /></li> <!--creates a string-->
            </s:iterator> 
        </div>
    </body>
</html>
