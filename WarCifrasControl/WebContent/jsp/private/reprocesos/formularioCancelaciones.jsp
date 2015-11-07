<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="reprocesos" />
	<jsp:param name="menuSubItem"    value="consultaCancelaciones" />	
</jsp:include>
<script src="${pageContext.servletContext.contextPath}/recursos/js/reprocesos/validaFormCancelaciones.js" type="text/javascript"></script>

<spring:message code="reprocesos.cancelaciones.tituloFormulario" var="tituloFormulario"/>
<spring:message code="reprocesos.cancelaciones.tituloPagina"     var="tituloPagina"/>
<spring:message code="reprocesos.cancelaciones.mes"              var="mes"/>
<spring:message code="reprocesos.cancelaciones.anio"			 var="anio"/>
<spring:message code="reprocesos.cancelaciones.linkAceptar"      var="linkAceptar"/>
<spring:message code="reprocesos.cancelaciones.noCoincidencias"  var="noCoincidencias"/>
<spring:message code="general.selectVacio"                       var="selectVacio"/>
<spring:message code="reprocesos.gralSinDatos"              	var="gralSinDatos"/>
<spring:message code="reprocesos.gralSinDatosRespuesta"           var="gralSinDatosRespuesta"/>
<spring:message code="reprocesos.gralModificarFiltros"            var="gralModificarFiltros"/>

<div class="pageTitleContainer">
   <span class="pageTitle">${tituloPagina}</span>
</div>

<div class="frameFormularioB">
	<div class="contentFormularioB">
		<div class="titleFormularioB">${tituloFormulario}</div>
			<form action="consultaCancelaciones.do" name="formularioCancelaciones" id="formularioCancelaciones">
				
				<input type="hidden" name="sinDatos" id ="sinDatos" value="${sinDatos}">
				<input id="gralSinDatos" type="hidden" value="${gralSinDatos}"/>
				<input id="gralSinDatosRespuesta" type="hidden" value="${gralSinDatosRespuesta}"/>
				<input id="gralModificarFiltros" type="hidden" value="${gralModificarFiltros}"/>
				<table>
					<tr>
						<td class="odd">${mes}:</td>
						<td>
							<select name="mes" id="mes">
								<option value="NONE" selected>${selectVacio}</option>
								<c:forEach var="periodo" items="${listaMeses}">
									<option value="${periodo.key}">${periodo.value}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
			</form>
			<div class="PiePag"> <a href="#" id="aceptarFormulario">${linkAceptar}</a></div>
	</div>
</div>

 <c:if test="${noCoincidenciasCancel}">
 	<script type="text/javascript">
		jAlert($('#gralSinDatos').val(), $('#gralSinDatosRespuesta').val(), 'Alerta', $('#gralModificarFiltros').val());
	</script>
 </c:if>
<jsp:include page="../myFooter.jsp" flush="true"/>