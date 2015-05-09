<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="ADMINISTRACION" />
	<jsp:param name="menuSubItem"    value="Pantallas" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/administracion/modificaPantalla.js" type="text/javascript"></script>

<spring:message code="administracion.modulo"            	var="modulo"/>

<spring:message code="administracion.pantallas"			var="pantallas"/>
<spring:message code="administracion.modificarPantalla"     	var="modificarPantalla"/>
<spring:message code="administracion.datosPantalla"    		var="datosPantalla"/>
<spring:message code="administracion.nombrePantalla"     	var="nombrePantalla"/>

<spring:message code="administracion.camposObligatorios"     	var="camposObligatorios"/>
<spring:message code="administracion.descripcionPantalla"     	var="descripcionPantalla"/>

<spring:message code="administracion.guardarPantalla" 		var="guardarPantalla"/>
<spring:message code="administracion.cancelar"	 		var="cancelar"/>
<spring:message code="administracion.borrar" 			var="borrar"/>

<spring:message code="administracion.modulos" 			var="modulos"/>
<spring:message code="administracion.nombreModulo" 		var="nombreDelModulo"/>
<spring:message code="administracion.moduloAsignado" 		var="moduloAsignado"/>
<spring:message code="administracion.seleccioneModulo" 		var="seleccioneModulo"/>
<spring:message code="administracion.urlPantalla" 		var="urlPantalla"/>


<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span> - ${pantallas}
</div>


<form action="" id="modificarPantalla" name="modificarPantalla" method="post">
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
							type="text" class="Campos_Des" maxlength="50" id="nombrePantalla" value="<c:out value="${pantalla.nombrePantalla}"/>"/></td><div style="color:#FF0000;" id="nombreRequerido">Ingrese un nombre v&aacute;lido </div>
					</tr>
					<tr>
						<td class="odd">${descripcionPantalla}:</td>
						<td colspan="3"><textarea rows="4" cols="50" id="descripcionPantalla" name="descripcionPantalla"><c:out value="${pantalla.descripcionPantalla}"/></textarea>(300 caracteres m&aacute;ximo)</td><div style="color:#FF0000;" id="descripcionRequerido">Ingrese una descripcion v&aacute;lida</div>
					</tr>
					<tr>
						<td width="154" class="odd">${urlPantalla}:</td>
						<td colspan="3"><input name="urlPantalla"
							type="text" class="Campos_Des" maxlength="100" id="urlPantalla" value="<c:out value="${pantalla.url}"/>"/></td><div style="color:#FF0000;" id="urlRequerido">Ingrese un url v&aacute;lido </div>
					</tr>
				</tbody>
			</table>
			
		</div>
		<div class="framePieContenedor">
			
		</div>
</div>

<div class="frameTablaVariasColumnas" id="divPantallas">
	<div class="titleTablaVariasColumnas">${modulos}</div>
		<div class="contentTablaVariasColumnas">
			<table>
				<tr>
					<th width="300" class="text_izquierda">${nombreDelModulo}</th>
					<th width="50" class="text_centro" scope="col">${moduloAsignado}</th>
				</tr>
			
				<tr>
			
					<Td colspan="2" class="special"></Td>
				</tr>
				<tbody><div style="color:#FF0000;" id="pantallaRequerida">${seleccioneModulo}</div>
					<c:forEach var="modulo" items="${todosModulos}">
					<tr class="odd2">
						<td class="text_izquierda">${modulo.nombreModulo}</td>
						<td class="text_izquierda"><input type="radio" name="moduloActivo" id="moduloActivo" value="${modulo.idModulo}"
							<c:if test="${modulo.moduloSeleccionado}">checked</c:if>>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
</div>


<div class="PiePag"><button name="btnGuardaPantalla" id="btnGuardaPantalla" type="button">${guardarPantalla}</button><button name="btnBorrarPantalla" id="btnBorrarPantalla" type="button">${borrar}</button><button name="regresar" id="regresar" type="button">${cancelar}</button></div>
</form>
<jsp:include page="../myFooter.jsp" flush="true"/>
