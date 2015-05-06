<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="administracion" />
	<jsp:param name="menuSubItem"    value="perfiles" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/administracion/grupo.js" type="text/javascript"></script>

<spring:message code="administracion.modulo"            	var="modulo"/>
<spring:message code="administracion.formularioModifica"	var="formularioModifica"/>
<spring:message code="administracion.modificarPerfil"  		var="modificarPerfil"/>
<spring:message code="administracion.camposObligatorios"     	var="camposObligatorios"/>
<spring:message code="administracion.datosPerfil"     		var="datosPerfil"/>
<spring:message code="administracion.nombrePerfil"     		var="nombrePerfil"/>
<spring:message code="administracion.descripcion"     		var="descripcion"/>
<spring:message code="administracion.pantallas"     		var="pantallas"/>
<spring:message code="administracion.nombrePantalla"   		var="nombrePantalla"/>
<spring:message code="administracion.pantallaAsignada" 		var="pantallaAsignada"/>
<spring:message code="administracion.guardarPerfil" 		var="guardarPerfil"/>
<spring:message code="administracion.cancelar" 			var="cancelar"/>

<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span> - ${formularioModifica}
</div>

<form action="modificarPerfil.do" name="modificarGrupo" id="modificarGrupo" method="post">
<input type="hidden" name="idGrupo" id="idGrupo" value="<c:out value="${grupo.idPerfil}"/>"/>
<div class="frameFormularioB">
	<div class="contentFormularioB">
		<div class="titleFormularioB">${formularioModifica} - <span class="textosin">${modificarPerfil}</span></div>
			<table>
				<tbody>
					<tr>
						<td colspan="4" class="ind">${camposObligatorios}</td>
					</tr>
					<tr>
						<th colspan="4" class="text_izquierda">${datosPerfil}<span
							class="textosin">.......................................................................................................</span></th>
					</tr>
					<tr>
						<td width="154" class="odd">${nombrePerfil}:</td>
						<td colspan="3"><input name="nombreGrupo"
							type="text" class="Campos_Des" id="nombreGrupo" value="<c:out value="${grupo.nombrePerfil}"/>"/></td>
					</tr>
					<tr>
						<td class="odd">${descripcion}:</td>
						<td colspan="3"><textarea rows="4" cols="50" name="descripcion" id="descripcion"><c:out value="${grupo.descripcionPerfil}"/></textarea></td>
					</tr>
				</tbody>
			</table>
			
		</div>
		<div class="framePieContenedor">
			
		</div>
</div>

<div class="frameTablaVariasColumnas">
	<div class="titleTablaVariasColumnas">${pantallas}</div>
		<div class="contentTablaVariasColumnas">
			<table>
				<tr>
					<th width="300" class="text_izquierda">${nombrePantalla}</th>
					<th width="50" class="text_centro" scope="col">${pantallaAsignada}</th>
				</tr>
			
				<tr>
			
					<Td colspan="2" class="special"></Td>
				</tr>
				<tbody>
					<c:forEach var="pantalla" items="${todasPantallas}">
					<tr class="odd2">
						<td class="text_izquierda">${pantalla.nombrePantalla}</td>
						<td class="text_izquierda"><input type="checkbox" name="pantallaActiva" value="${pantalla.idPantalla}"
							<c:if test="${pantalla.pantallaSeleccionada}">checked</c:if>>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
</div>
<div class="PiePag"><button name="btnModificaPerfil" id="btnModificaPerfil" type="submit">${guardarPerfil}</button></form><form action="borrarGrupo.do" name="borrarGrupo" id="borrarGrupo" method="post"><input type="hidden" name="idGrupoBorrar" id="idGrupoBorrar" value="<c:out value="${grupo.idPerfil}"/>"/><button name="btnBorrarGpo" id="btnBorrarGpo" type="submit">Borrar</button><button name="regresar" id="regresar" type="submit">${cancelar}</button></div>
</form>
<jsp:include page="../myFooter.jsp" flush="true"/>
