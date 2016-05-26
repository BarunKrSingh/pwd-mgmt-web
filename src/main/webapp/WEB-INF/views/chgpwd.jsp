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
			Optimización de Aplicaciones<br>
			<c:choose>
				<c:when
					test="${operationResponse.code=='1' || operationResponse.code=='2' || operationResponse.code=='3' || operationResponse.code=='4' || operationResponse.code=='5'} ">
					<div class="warning">Error en solicitud de nueva contraseña</div>
				</c:when>
				<c:otherwise>			
				Solicitud de nueva contraseña			
				</c:otherwise>
			</c:choose>
		</h1>
		<br>
		<c:if test="${not empty operationResponse}">
			<div class="errormessage">${operationResponse.description}</div>
		</c:if>

		<form:form method="POST" commandName="chgpwd">

			<div class="input">
				<label for="userId">Usuario</label>
				<form:input path="userId" cssClass="textbox" />
				<br>
				<form:errors path="userId" cssClass="incorrect" />
			</div>

			<div class="input">
				<label for="userId">Introduzca la contraseña actual</label>
				<form:password path="currentPassword" cssClass="textbox" />
				<br>
				<form:errors path="currentPassword" cssClass="incorrect" />
			</div>

			<div class="input">
				<label for="userId">Introduzca la nueva contraseña</label>
				<form:password path="newPassword" cssClass="textbox" />
				<br>
				<form:errors path="newPassword" cssClass="incorrect" />
			</div>

			<div class="input">
				<label for="userId">Repita la nueva contraseña</label>
				<form:password path="newPasswordConf" cssClass="textbox" />
				<br>
				<form:errors path="newPasswordConf" cssClass="incorrect" />
			</div>

			<a href="http://localhost:8083/pwdweb/newpwd.htm"><b>Generar nueva contraseña</b></a>&nbsp;&nbsp;

			<input type="submit" name="Confirmar" value="Confirmar" />
			
			</form:form>
		</fieldset>
	</body>
</html>

<%-- <table>
		
			<tr>
				<td>Enter the email Id:</td>
				<td><form:input path="userId" /></td>
				<td><form:errors path="userId" cssStyle="color: #ff0000;"/></td>
			<tr>
			
			<tr>
				<td>Enter your current password:</td>
				<td><form:password path="currentPassword"  /></td>
				<td><form:errors path="currentPassword" cssStyle="color: #ff0000;"/></td>
			</tr>
			
			<tr>
				<td>Enter a password:</td>
				<td><form:password path="newPassword"  /></td>
				<td><form:errors path="newPassword" cssStyle="color: #ff0000;"/></td>
			</tr>
			<tr>
				<td>Confirm your password:</td>
				<td><form:password path="newPasswordConf" /></td>
				<td><form:errors path="newPasswordConf" cssStyle="color: #ff0000;"/></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Submit"></td>
			</tr>
			<tr>
		</table>
</form:form>
</body>
</html> --%>