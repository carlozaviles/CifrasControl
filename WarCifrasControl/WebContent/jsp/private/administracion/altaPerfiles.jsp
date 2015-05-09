<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="ADMINISTRACION" />
	<jsp:param name="menuSubItem"    value="Grupos" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/administracion/altaGrupo.js" type="text/javascript"></script>

<spring:message code="administracion.modulo"            	var="modulo"/>
<spring:message code="administracion.formulario"     		var="formulario"/>
<spring:message code="administracion.altaGrupo"     		var="altaGrupo"/>
<spring:message code="administracion.camposObligatorios"     	var="camposObligatorios"/>
<spring:message code="administracion.datosGrupo"     		var="datosGrupo"/>
<spring:message code="administracion.nombreGrupo"     		var="nombreGrupo"/>
<spring:message code="administracion.descripcion"     		var="descripcion"/>
<spring:message code="administracion.pantallas"     		var="pantallas"/>
<spring:message code="administracion.nombrePantalla"   		var="nombrePantalla"/>
<spring:message code="administracion.pantallaAsignada" 		var="pantallaAsignada"/>
<spring:message code="administracion.guardarGrupo" 		var="guardarGrupo"/>
<spring:message code="administracion.cancelar" 			var="cancelar"/>

<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span> - ${formulario}
</div>


<form action="altaGrupo.do" id="altaGrupo" name="altaGrupo" method="post">
<div class="frameFormularioB">
	<div class="contentFormularioB">
		<div class="titleFormularioB">${formulario} - <span class="textosin">${altaGrupo}</span></div>
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
							type="text" class="Campos_Des" id="nombreGrupo" maxlength="50"/></td><div style="color:#FF0000;" id="nombreRequerido">Ingrese un nombre v&aacute;lido</div>
					</tr>
					<tr>
						<td class="odd">${descripcion}:</td>
						<td colspan="3"><textarea rows="4" cols="50" name="descripcionGrupo" id="descripcionGrupo"></textarea></td><div style="color:#FF0000;" id="descripcionRequerido">Ingrese una descripcion v&aacute;lida</div>
					</tr>
					<tr><td colspan="3">(300 caracteres m&aacute;ximo)</td></tr>
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
				<tbody><div style="color:#FF0000;" id="pantallaRequerida">Seleccione por lo menos una pantalla</div>
					<c:forEach var="pantalla" items="${todasPantallas}">
					<tr class="odd2">
						<td class="text_izquierda">${pantalla.nombrePantalla}</td>
						<td class="text_izquierda"><input type="checkbox" name="pantallaActiva" value="${pantalla.idPantalla}"/>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
</div>

<div class="PiePag"><button name="btnAltaGrupo" id="btnAltaGrupo" type="button">${guardarGrupo}</button><button name="regresar" id="regresar" type="button">${cancelar}</button></div>
</form>
<jsp:include page="../myFooter.jsp" flush="true"/>
