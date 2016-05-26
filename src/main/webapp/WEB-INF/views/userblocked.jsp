<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="botDetect" uri="botDetect"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Password Generated</title>
<link rel="stylesheet" href="resources/css/style.css" type="text/css" />
</head>

<body>
	<fieldset>
		<h1>
			Optimización de Aplicaciones <br>
			<div class="warning">Acceso bloqueado</div>
		</h1>
		<br>
		<c:if test="${not empty operationResponse}">
			<div class="errormessage">
				Ha superado el número de reintentos permitido, razón<br> por la
				cual su cuenta de usuario ha sido bloqueada.<br> Para
				desbloquear su cuenta debe generar una nueva<br> contraseña a
				través de la opción <b>Resetear contraseña</b>.
			</div>
		</c:if>
		
		<a href="http://localhost:8083/pwdweb/newpwd.htm"><b>Resetear contraseña</b></a>

	</fieldset>
</body>
</html>