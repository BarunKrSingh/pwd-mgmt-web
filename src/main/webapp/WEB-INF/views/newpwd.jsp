<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="botDetect" uri="botDetect"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Telefonica User Management</title>
<link rel="stylesheet" href="resources/css/style.css" type="text/css" />
</head>

<body>
	<fieldset>
		<h1>
			Optimización de Aplicaciones<br>
			<c:choose>
				<c:when
					test="${operationResponse.code=='1' || operationResponse.code=='2'}">
					<div class="warning">Error en solicitud de nueva contraseña</div>
				</c:when>
				<c:otherwise>			
				Solicitud de nueva contraseña			
				</c:otherwise>
			</c:choose>
		</h1>
		<br>
		<c:if test="${not empty operationResponse}">
			<div class="errormessage">
				<c:choose>			   
					<c:when test="${operationResponse.code=='1'}">					
							Se ha producido un error inesperado.<br> 
							Por favor, reinténtelo más tarde.											
					</c:when>
					<c:otherwise>							
						El identificador de usuario introducido no es válido.<br>
						Por favor reinténtelo de nuevo.			
					</c:otherwise>			
				</c:choose>	
			</div>			
		</c:if>

		<h3>
			Para obtener una nueva contraseña, introduzca su identificador<br>
			de usuario y rellene el texto contenido en la imagen inferior. Se le<br>
			enviará una nueva contraseña por correo electrónico.
		</h3>

		<form:form commandName="newpwd" method="post">
			<div class="input">
				<label for="userId">Usuario:</label>
				<form:input path="userId" cssClass="textbox" autocomplete="true" />
				<br>
				<form:errors path="userId" cssClass="incorrect" />
			</div>
			<c:if test="${!index.captchaVerified}">
				<label for="captchaCodeTextBox" class="prompt"> Vuelva a escribir el código de la imagen</label>
				<!-- Adding BotDetect Captcha to the page -->
				<botDetect:captcha id="springFormCaptcha" codeLength="3"
					imageWidth="150" imageStyles="graffiti" />
				<div class="validationDiv">
					<input id="userCaptchaCode" type="text" name="userCaptchaCode"
						value="${message.userCaptchaCode}" /><br>
					<form:errors path="userCaptchaCode" cssClass="incorrect" />
				</div>
			</c:if>
			<input type="submit" name="Confirmar" value="Confirmar" />&nbsp;      
    </form:form>
	</fieldset>
</body>
</html>