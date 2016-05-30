<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Telefonica Password Management</title>
<link rel="stylesheet" href="resources/css/style.css" type="text/css" />
</head>

<body>
	<fieldset>
		<h1>
			Optimizaci�n de Aplicaciones<br>
			<c:choose>
				<c:when	test="${operationResponse.code=='1' || operationResponse.code=='2' || operationResponse.code=='3' || operationResponse.code=='4' || operationResponse.code=='5'}">
					<div class="warning">Error en solicitud de nueva contrase�a</div>
				</c:when>
				<c:otherwise>			
				Solicitud de nueva contrase�a		
				</c:otherwise>
			</c:choose>
		</h1>
		<br>
		<c:if test="${not empty operationResponse}">
			<div class="errormessage">
				<c:choose>			   
					<c:when test="${operationResponse.code=='1'}">					
							Se ha producido un error inesperado.<br> 
							Por favor, reint�ntelo m�s tarde.											
					</c:when>
					<c:when test="${operationResponse.code=='2'}">
					 	El identificador de usuario introducido no es v�lido.<br>
						Por favor reint�ntelo de nuevo.
					</c:when>
					<c:when test="${operationResponse.code=='3'}">
						La contrase�a actual no es correcta.<br>
						Por favor int�ntelo de nuevo.<br>
						* Reintentos disponibles antes de bloquear la cuenta: ${operationResponse.remainingPasswordAttempts}
					</c:when>
					<c:when test="${operationResponse.code=='4'}">
						La nueva contrase�a coincide con una de sus<br>
						contrase�as m�s reciente.<br>
						Por favor int�ntelo de nuevo.
					</c:when>					
					<c:otherwise>					
						
					</c:otherwise>			
				</c:choose>	
			</div>			
		</c:if>

		<form:form method="POST" commandName="chgpwd">

			<div class="input">
				<label for="userId">Usuario</label>
				<form:input path="userId" cssClass="textbox" autocomplete="true"/>
				<br>
				<form:errors path="userId" cssClass="incorrect" />
			</div>

			<div class="input">
				<label for="userId">Introduzca la contrase�a actual</label>
				<form:password path="currentPassword" autocomplete="true" cssClass="textbox" />
				<br>
				<form:errors path="currentPassword" cssClass="incorrect" />
			</div>

			<div class="input">
				<label for="userId">Introduzca la nueva contrase�a</label>
				<form:password path="newPassword" cssClass="textbox" autocomplete="true"/>
				<br>
				<form:errors path="newPassword" cssClass="incorrect" />
			</div>

			<div class="input">
				<label for="userId">Repita la nueva contrase�a</label>
				<form:password path="newPasswordConf" cssClass="textbox" autocomplete="true"/>
				<br>
				<form:errors path="newPasswordConf" cssClass="incorrect" />
			</div>

			<a href="${pageContext.request.contextPath}/newpwd.htm"><b>Generar nueva contrase�a</b></a>&nbsp;&nbsp;			
			<input type="submit" name="Confirmar" value="Confirmar" />
			
			</form:form>
		</fieldset>
	</body>
</html>
