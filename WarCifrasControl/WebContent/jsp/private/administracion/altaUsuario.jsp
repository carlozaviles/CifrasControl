<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="ADMINISTRACION" />
	<jsp:param name="menuSubItem"    value="Usuarios" />	
</jsp:include>

<spring:message code="administracion.modulo"            	var="modulo"/>
<spring:message code="administracion.formularioAltaUsuario"	var="formularioAltaUsuario"/>
<spring:message code="administracion.altaUsuario"     		var="altaUsuario"/>
<spring:message code="administracion.camposObligatorios"     	var="camposObligatorios"/>
<spring:message code="administracion.datosUsuario"     		var="datosUsuario"/>
<spring:message code="administracion.nombreUsuario"     	var="nombreUsuario"/>
<spring:message code="administracion.paternoUsuario"     	var="paternoUsuario"/>
<spring:message code="administracion.maternoUsuario"     	var="maternoUsuario"/>
<spring:message code="administracion.correo"			var="correo"/>
<spring:message code="administracion.tituloGrupo"     		var="tituloGrupo"/>
<spring:message code="administracion.grupo"		     	var="grupo"/>
<spring:message code="administracion.asignarGrupo"	     	var="asignarGrupo"/>
<spring:message code="administracion.usuarioActivo"		var="usuarioActivo"/>
<spring:message code="administracion.cancelar" 			var="cancelar"/>
<spring:message code="administracion.guardarUsuario"		var="guardarUsuario"/>
<spring:message code="administracion.identificadorUsuario"	var="identificadorUsuario"/>

<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span> - ${formularioAltaUsuario}
</div>

<form action="altaUsuario.do" method="post">
<div class="frameFormularioB">
	<div class="contentFormularioB">
		<div class="titleFormularioB">${formularioAltaUsuario} - <span class="textosin">${altaUsuario}</span></div>
			<table>
				<tbody>
					<tr>
						<td colspan="4" class="ind">${camposObligatorios}</td>
					</tr>
					<tr>
						<th colspan="4" class="text_izquierda">${datosUsuario}<span
							class="textosin">.......................................................................................................</span></th>
					</tr>
					<tr>
						<td width="154" class="odd">${identificadorUsuario}:</td>
						<td colspan="3"><input name="idUsuario"
							type="text" class="Campos_Des" id="idUsuario" /></td>
					</tr>
					<tr>
						<td width="154" class="odd">${usuarioActivo}:</td>
						<td colspan="3"><input type="checkbox" name="usuarioActivo" value="activo"></td>
					</tr>

				</tbody>
			</table>
			
		</div>
		<div class="framePieContenedor">
			
		</div>
</div>

<div class="frameTablaVariasColumnas">
	<div class="titleTablaVariasColumnas">${tituloGrupo}</div>
		<div class="contentTablaVariasColumnas">
			<table>
				<tr>
					<th width="300" class="text_izquierda">${grupo}</th>
					<th width="50" class="text_centro" scope="col">${asignarGrupo}</th>
				</tr>
			
				<tr>
			
					<Td colspan="2" class="special"></Td>
				</tr>
				<tbody>
					<c:forEach var="grupos" items="${todosGrupos}">
					<tr class="odd2">
						<td class="text_izquierda">${grupos.nombreGrupo}</td>
						<td class="text_izquierda"><input type="checkbox" name="idGrupo" value="${grupos.idGrupo}">
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
</div>

<div class="PiePag"><button name="btnAltaUsuario" id="btnAltaUsuario" onclick="#">${guardarUsuario}</button></div>
</form>
<jsp:include page="../myFooter.jsp" flush="true"/>
