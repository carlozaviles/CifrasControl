<%@ taglib uri="http://www.springframework.org/tags"    prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="ADMINISTRACION" />
	<jsp:param name="menuSubItem"    value="Usuarios" />	
</jsp:include>


<spring:message code="administracion.modulo"            var="modulo"/>
<spring:message code="administracion.usuarios"   	var="usuarios"/>
<spring:message code="administracion.grupo"         	var="grupo"/>
<spring:message code="administracion.claveUsuario"    	var="claveUsuario"/>
<spring:message code="administracion.nombre"     	var="nombre"/>
<spring:message code="administracion.correo"     	var="correo"/>
<spring:message code="administracion.grupoActivo"     	var="grupoActivo"/>
<spring:message code="administracion.seleccioneUsuario"	var="seleccioneUsuario"/>
<spring:message code="administracion.detalle"		var="detalle"/>
<spring:message code="administracion.nuevoUsuario"	var="nuevoUsuario"/>
<spring:message code="administracion.pantallas"		var="pantallas"/>
<form action="modificarUsuarioInit.do" name="modificar" id="modificar" method="post">
<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span> - ${usuarios}
</div>

<div class="frameTablaEstandar">
	<div class="titleTablaEstandar">
		${usuarios} <span class="textosin">- ${seleccioneUsuario}</span>
	</div>
<div class="contentTablaEstandar">
		<table>
			<thead>
				<tr>
					<th colspan="2" class="text_izquierda">${claveUsuario}</th>
					<th width="150" class="text_centro">${grupo}</th>
					<th width="291" class="text_centro">${pantallas}</th>
					<th width="50" 	class="text_derecha">${grupoActivo}</th>
				</tr>
			</thead>
			<tr>
				<td colspan="5" class="special"></td>
			</tr>
			<tbody>
				<c:forEach var="regs" items="${registros}">
				<tr class="odd">
					<td width="20"><input name="idUsuario" type="radio" class="Campos"
						id="idUsuario" value=${regs.idUsuario}></input></td>
					<td width="128" class="text_izquierda">${regs.idUsuario}</td>
					<td width="150" class="text_izquierda">${regs.gruposToString}</td>
					<td width="291" class="text_izquierda">${regs.pantallasToString}</td>
					<td width="50" class="text_centro"><input type="checkbox" name="usuarioActivo" value="${regs.idUsuario}"
							<c:if test="${regs.estatus}">checked</c:if> disabled></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="framePieContenedor">
		<div class="contentPieContenedor">
			<table>
				<tr>
					<td class="izq"><a href="javascript:document.modificar.submit()">${detalle}</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</form>
<form action="altaUsuarioInit.do" method="post" name="altaUsuario" id="altaUsuario">
<div class="PiePag"><button name="btnAltaUsuario" id="btnAltaUsuario" onclick="">${nuevoUsuario}</button></div>
</form>
<jsp:include page="../myFooter.jsp" flush="true"/>
