<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="cifras" />
	<jsp:param name="menuSubItem"    value="consultaCifras" />	
</jsp:include>
<script src="${pageContext.servletContext.contextPath}/recursos/js/cifrascontrol/validaFormIncidencias.js" type="text/javascript"></script>

<spring:message code="cifrascontrol.modulo.incidencias"     var="modulo"/>
<spring:message code="cifrascontrol.aplicativo"        		var="aplicativo"/>
<spring:message code="cifrascontrol.periodo"           		var="periodo"/>
<spring:message code="cifrascontrol.mes"             		var="mes"/>
<spring:message code="cifrascontrol.anio"             		var="anio"/>
<spring:message code="general.selectVacio"                  var="selectVacio"/>
<spring:message code="cifrascontrol.linkAceptar"            var="linkAceptar"/>
<spring:message code="cifrascontrol.gralSinDatos"              	var="gralSinDatos"/>
<spring:message code="cifrascontrol.gralSinDatosRespuesta"           var="gralSinDatosRespuesta"/>
<spring:message code="cifrascontrol.gralModificarFiltros"            var="gralModificarFiltros"/>                            

<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span>
</div>

<div class="frameFormularioB">
	<div class="contentFormularioB">
		<div class="titleFormularioB">${modulo}</div>
		<form action="consultaInsidencias.do" name="formularioIncidencias" id="formularioIncidencias" method="POST">
			<input type="hidden" name="sinDatos" id ="sinDatos" value="${sinDatos}">
			<input id="gralSinDatos" type="hidden" value="${gralSinDatos}"/>
			<input id="gralSinDatosRespuesta" type="hidden" value="${gralSinDatosRespuesta}"/>
			<input id="gralModificarFiltros" type="hidden" value="${gralModificarFiltros}"/>
			<table>
				<tr>
					<td class="odd">${aplicativo}:</td>
					<td class="odd">
						<select name="aplicativo" id="aplicativo" class="Campos_Des" style="font-size: 9px;">
							<option value="NONE" selected="selected">${selectVacio}</option>
							<c:forEach var="producto" items="${productosList}">					
							<option value="${producto.descripcion}">${producto.descripcion}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="odd">${periodo}</td>
					<td class="odd">${mes}</td>
					<td>
						<select name="mes" id="mes" class="Campos_Des">
							<option value="NONE" selected="selected">${selectVacio}</option>
							<c:forEach var="meses" items="${mesesList}">					
							<option value="${meses.key}">${meses.value}</option>
							</c:forEach>
						</select>
					</td>
					<td class="odd">${anio}</td>			
					<td>
						<select name="anio" id="anio" class="Campos_Des">
							<option value="NONE" selected="selected">${selectVacio}</option>
							<c:forEach var="anios" items="${anioList}">					
							<option value="${anios.key}">${anios.value}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			<div class="PiePag"> <a href="#" id="aceptarFormulario">${linkAceptar}</a></div>
		</form>
	</div>
</div>
<c:if test="${noCoincidencias}">
	<script type="text/javascript">
		jAlert($('#gralSinDatos').val(), $('#gralSinDatosRespuesta').val(), 'Alerta', $('#gralModificarFiltros').val());
	</script>
</c:if>
<jsp:include page="../myFooter.jsp" flush="true"/>