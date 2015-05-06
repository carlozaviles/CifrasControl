<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="administracion" />
	<jsp:param name="menuSubItem"    value="perfiles" />	
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
<spring:message code="administracion.tituloPerfiles"     	var="tituloPerfiles"/>
<spring:message code="administracion.perfil"		     	var="perfil"/>
<spring:message code="administracion.asignarPerfil"	     	var="asignarPerfil"/>
<spring:message code="administracion.usuarioActivo"		var="usuarioActivo"/>
<spring:message code="administracion.guardarUsuario"		var="guardarUsuario"/>

<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span> - ${formularioAltaUsuario}
</div>

<form action="" method="post">
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
						<td width="154" class="odd">${nombreUsuario}:</td>
						<td colspan="3"><input name="nombreUsuario"
							type="text" class="Campos_Des" id="nombreUsuario" /></td>
					</tr>
					<tr>
						<td width="154" class="odd">${paternoUsuario}:</td>
						<td colspan="3"><input name="paternoUsuario"
							type="text" class="Campos_Des" id="paternoUsuario" /></td>
					</tr>
					<tr>
						<td width="154" class="odd">${maternoUsuario}:</td>
						<td colspan="3"><input name="maternoUsuario"
							type="text" class="Campos_Des" id="maternoUsuario" /></td>
					</tr>
					<tr>
						<td width="154" class="odd">${correo}:</td>
						<td colspan="3"><input name="correoUsuario"
							type="text" class="Campos_Des" id="correoUsuario" /></td>
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
	<div class="titleTablaVariasColumnas">${tituloPerfiles}</div>
		<div class="contentTablaVariasColumnas">
			<table>
				<tr>
					<th width="300" class="text_izquierda">${perfil}</th>
					<th width="50" class="text_centro" scope="col">${asignarPerfil}</th>
				</tr>
			
				<tr>
			
					<Td colspan="2" class="special"></Td>
				</tr>
				<tbody>
					<tr class="odd2">
						<td class="text_izquierda">A1234567</td>
						<td class="text_izquierda"><input name="radio" type="radio" name="usuarioActivo" value="activo"></td>
					</tr>
					<tr class="odd1">
						<td class="text_izquierda">B7654321</td>
						<td class="text_izquierda"><input name="radio" type="radio" name="usuarioActivo1" value="activo"></td>
					</tr>
					<tr class="odd2">
						<td class="text_izquierda">A0987654</td>
						<td class="text_izquierda"><input name="radio" type="radio" name="usuarioActivo2" value="activo"></td>
					</tr>
					<tr class="odd1">
						<td class="text_izquierda">B6789045</td>
						<td class="text_izquierda"><input name="radio" type="radio" name="usuarioActivo3" value="activo"></td>
					</tr>
					<tr class="odd2">
						<td class="text_izquierda">A4326541</td>
						<td class="text_izquierda"><input name="radio" type="radio" name="usuarioActivo4" value="activo"></td>
					</tr>
				</tbody>
			</table>
	</div>
</div>

<div class="PiePag"><button name="btnAltaUsuario" id="btnAltaUsuario" onclick="#">${guardarUsuario}</button></div>
</form>
<jsp:include page="../myFooter.jsp" flush="true"/>
