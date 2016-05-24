<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring MVC Form BotDetect CAPTCHA Example</title>
    <link rel="stylesheet" href="stylesheet.css" type="text/css"/>
  </head>
  <body>
    <h1>Spring MVC Form BotDetect CAPTCHA Example</h1>
    <h2>View Messages</h2>
    <form:form commandName="messages" method="post">
      <c:forEach var="message" items="${messages.messagesList}">
        <p class="message">
        <c:out value="${message}"/><br>
        </p>
      </c:forEach>
      <input type="submit" name="clear" value="Clear messages" />
      <input type="submit" name="add" value="Add message" />
    </form:form>
  </body>
</html>