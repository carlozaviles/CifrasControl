<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp"   flush="true">
	<jsp:param name="menuItem"   	value="cifras" />
	<jsp:param name="menuSubItem"   value="consultaCifras" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/cifrascontrol/consultaInsidencia.js" type="text/javascript"></script>

<spring:message code="cifrascontrol.insidencias.tituloPagina"  var="tituloPagina"/>
<spring:message code="cifrascontrol.insidencias.tituloTabla"   var="tituloTabla"/>
<spring:message code="cifrascontrol.insidencias.producto"      var="producto"/>
<spring:message code="cifrascontrol.insidencias.fecha"         var="fecha"/>
<spring:message code="cifrascontrol.insidencias.fase"          var="fase"/>
<spring:message code="cifrascontrol.incidencias.moneda"        var="moneda"/>
<spring:message code="cifrascontrol.incidencias.tipoDocumento" var="tipoDoc"/>
<spring:message code="cifrascontrol.incidencias.numeroCuenta"  var="numeroCuenta"/>
<spring:message code="cifrascontrol.insidencias.detalle"       var="detalle"/>
<spring:message code="cifrascontrol.incidencias.exportar"      var="exportar"/>
<spring:message code="cifrascontrol.incidencias.regresar"      var="regresar"/>

<div class="pageTitleContainer">
   <span class="pageTitle">${tituloPagina}</span>
</div>
<div class="contextContainer">
	<div>
		<table>
			<tr>
				<th>${producto}:</th>
				<td>${aplicativo}</td>
			</tr>
			<tr>
				<th>${fecha}:</th>
				<td>${periodo}</td>
			</tr>
		</table>
	</div>
</div>
<div class="frameTablaEstandar">
	<div class="titleTablaEstandar">${tituloTabla}</div>
	<div class="contentTablaEstandar">
		<table id="tablaIncidencias">
			<thead>
				<tr>
					<th class="text_centro">${fase}</th>
					<th class="text_centro">${moneda}</th>
					<th class="text_centro">${tipoDoc}</th>
					<th class="text_centro">${numeroCuenta}</th>
					<th class="text_centro">${detalle}</th>
				</tr>
			</thead>
			<tr>
				<td colspan="4" class="special"></td>
			</tr>
			<tbody>
				<c:forEach var="insidencia" items="${listaInsidencias}">
					<tr>
						<td class="text_centro">${insidencia.fase}</td>
						<td class="text_centro">${insidencia.moneda}</td>
						<td class="text_centro">${insidencia.tipoDocumento}</td>
						<td class="text_centro">${insidencia.numeroCuenta}</td>
						<td class="text_centro">${insidencia.descripcionError}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<div class="PiePag">
	<a id="linkExportaExcel" href="#">${exportar}</a>
	<a href="../cifrascontrol/initConsultaIncidencias.do">${regresar}</a> 
</div>
<form id="formExportaExcel" action="${pageContext.request.servletContext.contextPath}/cifrascontrol/excelIncidenciasCifras.xls">
	<input type="hidden" name="aplicativo" value="${aplicativo}" />
	<input type="hidden" name="mes" value="${mes}" />
	<input type="hidden" name="anio" value="${anio}" />
</form>

<jsp:include page="../myFooter.jsp" flush="true"/>