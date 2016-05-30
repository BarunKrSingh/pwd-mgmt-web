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
			<div class="success">Nueva contraseña generada</div>
		</h1>
		<br>
		<c:if test="${not empty operationResponse}">
			<div class="resultbox">Su nueva contraseña se ha generado con éxito. En<br>
			breve la recibirá a través del correo electrónico.<br>
			Su nueva contraseña será válida hasta el <b>${operationResponse.currentPasswordExpirationDate}.</b><br>
			Pulse Continuar para volver al servicio e introducir sus<br>
			nuevas credenciales.
			</div>
		</c:if>

	</fieldset>
</body>
</html>