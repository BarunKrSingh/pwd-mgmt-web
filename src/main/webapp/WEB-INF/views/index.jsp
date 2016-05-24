<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="botDetect" uri="botDetect"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New Password web form</title>
    <link rel="stylesheet" href="resources/css/style.css" type="text/css"/>
  </head>

  <body>
    <h1>Optimización de Aplicaciones Solicitud de nueva contraseña</h1>

    <form:form commandName="index" method="post">
      <fieldset>
        <!-- <legend>CAPTCHA included in Spring MVC form validation</legend> -->
        <div class="input">
          <label for="userId">Usuario:</label>
          <form:input path="userId" cssClass="textbox" /><br>
          <form:errors path="userId" cssClass="incorrect"/>
        </div>              
        <c:if test="${!index.captchaVerified}">
          <label for="captchaCodeTextBox" class="prompt">
              Retype the code from the picture:</label>
          <!-- Adding BotDetect Captcha to the page -->
          <botDetect:captcha id="springFormCaptcha"
              codeLength="3"
              imageWidth="150"
              imageStyles="graffiti, graffiti2"/>
          <div class="validationDiv">
            <input id="captchaCodeTextBox" type="text" 
                name="captchaCodeTextBox" 
                value="${message.captchaCodeTextBox}"/><br>
          </div>
        </c:if>
        <input type="submit" name="Confirmar" value="Confirmar" />&nbsp;
      </fieldset>
    </form:form>
  </body>
</html>