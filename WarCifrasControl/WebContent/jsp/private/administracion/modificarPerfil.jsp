<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="ADMINISTRACION" />
	<jsp:param name="menuSubItem"    value="Perfiles" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/administracion/modificarGrupo.js" type="text/javascript"></script>

<spring:message code="administracion.modulo"            	var="modulo"/>
<spring:message code="administracion.formularioModifica"	var="formularioModifica"/>
<spring:message code="administracion.modificarGrupo"  		var="modificarGrupo"/>
<spring:message code="administracion.camposObligatorios"     	var="camposObligatorios"/>
<spring:message code="administracion.datosGrupo"     		var="datosGrupo"/>
<spring:message code="administracion.nombreGrupo"     		var="nombreGrupo"/>
<spring:message code="administracion.descripcion"     		var="descripcion"/>
<spring:message code="administracion.pantallas"     		var="pantallas"/>
<spring:message code="administracion.nombreGrupo"   		var="nombrePantalla"/>
<spring:message code="administracion.pantallaAsignada" 		var="pantallaAsignada"/>
<spring:message code="administracion.guardarGrupo" 		var="guardarGrupo"/>
<spring:message code="administracion.cancelar" 			var="cancelar"/>
<spring:message code="administracion.borrar" 			var="borrar"/>


<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span> - ${formularioModifica}
</div>

<form name="modificarGrupo" id="modificarGrupo" method="post">
<input type="hidden" name="idGrupo" id="idGrupo" value="<c:out value="${grupo.idGrupo}"/>"/>
<div class="frameFormularioB" id="divFormulario">
	<div class="contentFormularioB">
		<div class="titleFormularioB">${formularioModifica} - <span class="textosin">${modificarGrupo}</span></div>
			<table>
				<tbody>
					<tr>
						<td colspan="4" class="ind">${camposObligatorios}</td>
					</tr>
					<tr>
						<th colspan="4" class="text_izquierda">${datosGrupo}<span
							class="textosin">.......................................................................................................</span></th>
					</tr>
					<tr>
						<td width="154" class="odd">${nombreGrupo}:</td>
						<td colspan="3"><input name="nombreGrupo"
							type="text" class="Campos_Des" id="nombreGrupo" maxlength="50" value="<c:out value="${grupo.nombreGrupo}"/>"/></td><div style="color:#FF0000;" id="nombreRequerido">Ingrese un nombre v&aacute;lido </div>
					</tr>
					<tr>
						<td class="odd">${descripcion}:</td>
						<td colspan="3"><textarea rows="6" cols="50" name="descripcionGrupo" id="descripcionGrupo"><c:out value="${grupo.descripcionGrupo}"/></textarea>(300 caracteres m&aacute;ximo)</td><div style="color:#FF0000;" id="descripcionRequerido">Ingrese una descripcion v&aacute;lida</div>
					</tr>
				</tbody>
			</table>
			
		</div>
		<div class="framePieContenedor">
			
		</div>
</div>

<div class="frameTablaVariasColumnas" id="divPantallas">
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
				<tbody><div style="color:#FF0000;" id="pantallaRequerida">Seleccione por lo menos una pantalla</div>
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
<div class="PiePag"><button name="btnModificaGpo" id="btnModificaGpo" type="button">${guardarGrupo}</button><button name="btnBorrarGpo" id="btnBorrarGpo" type="button">${borrar}</button><button name="regresar" id="regresar" type="button">${cancelar}</button></div>
</form>
<jsp:include page="../myFooter.jsp" flush="true"/>
