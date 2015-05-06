<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="administracion" />
	<jsp:param name="menuSubItem"    value="perfiles" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/administracion/pantalla.js" type="text/javascript"></script>

<spring:message code="administracion.modulo"            	var="modulo"/>

<spring:message code="administracion.pantallas"			var="pantallas"/>
<spring:message code="administracion.modificarPantalla"     	var="modificarPantalla"/>
<spring:message code="administracion.datosPantalla"    		var="datosPantalla"/>
<spring:message code="administracion.nombrePantalla"     	var="nombrePantalla"/>

<spring:message code="administracion.camposObligatorios"     	var="camposObligatorios"/>
<spring:message code="administracion.descripcionPantalla"     	var="descripcionPantalla"/>

<spring:message code="administracion.guardarPantalla" 		var="guardarPantalla"/>
<spring:message code="administracion.cancelar"	 		var="cancelar"/>


<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span> - ${pantallas}
</div>


<form action="modificarPantalla.do" id="modificarPantalla" name="modificarPantalla" method="post">
<input type="hidden" name="idPantalla" id="idPantalla" value="<c:out value="${pantalla.idPantalla}"/>"/>
<div class="frameFormularioB">
	<div class="contentFormularioB">
		<div class="titleFormularioB">${pantallas} - <span class="textosin">${modificarPantalla}</span></div>
			<table>
				<tbody>
					<tr>
						<td colspan="4" class="ind">${camposObligatorios}</td>
					</tr>
					<tr>
						<th colspan="4" class="text_izquierda">${modificarPantalla}<span
							class="textosin">.......................................................................................................</span></th>
					</tr>
					<tr>
						<td width="154" class="odd">${nombrePantalla}:</td>
						<td colspan="3"><input name="nombrePantalla"
							type="text" class="Campos_Des" id="nombrePantalla"value="<c:out value="${pantalla.nombrePantalla}"/>"/></td>
					</tr>
					<tr>
						<td class="odd">${descripcionPantalla}:</td>
						<td colspan="3"><textarea rows="4" cols="50" id="descripcionPantalla" name="descripcionPantalla"><c:out value="${pantalla.descripcionPantalla}"/></textarea></td>
					</tr>
				</tbody>
			</table>
			
		</div>
		<div class="framePieContenedor">
			
		</div>
</div>


<div class="PiePag"><button name="btnGuardaPantalla" id="btnGuardaPantalla" type="submit">${guardarPantalla}</button></form><form action="borrarPantalla.do" id="borrarPantalla" name="borrarPantalla" method="post"> <input type="hidden" name="idPantallas" id="idPantallas" value="<c:out value="${pantalla.idPantalla}"/>"/><button name="btnGuardaPerfil" id="btnGuardaPerfil" onclick="#">${cancelar}</button></div>
</form>
<jsp:include page="../myFooter.jsp" flush="true"/>
