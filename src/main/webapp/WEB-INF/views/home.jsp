<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="botDetect" uri="botDetect"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Telefonica Password Management</title>
<link rel="stylesheet" href="resources/css/style.css" type="text/css" />
</head>

<body>	
	<fieldset>	
		<h1 align="center">
			Optimización de Aplicaciones			
		</h1>
		<br>
		<br>

		<a href="${pageContext.request.contextPath}/newpwd.htm">Generar nueva contraseña</a><br>
		<br>
		<a href="${pageContext.request.contextPath}/chgpwd.htm">Modificación de contraseña</a>	
	</fieldset>	
</body>
</html>