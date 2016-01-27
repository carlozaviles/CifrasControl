<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="reprocesos" />
	<jsp:param name="menuSubItem"    value="consultaCancelaciones" />	
</jsp:include>
<script src="${pageContext.servletContext.contextPath}/recursos/js/reprocesos/consultaCancelaciones.js" type="text/javascript"></script>

<spring:message code="reprocesos.cancelaciones.reporte.tituloPagina" var="tituloPagina"/>
<spring:message code="reprocesos.cancelaciones.reporte.tituloTablaEstatus" var="tituloTabla"/>
<spring:message code="reprocesos.cancelaciones.reporte.fechaEncabezado" var="fechaEncabezado"/>
<spring:message code="reprocesos.cancelaciones.reporte.interfazCancelacion" var="interfazCancelacion"/>
<spring:message code="reprocesos.cancelaciones.reporte.numeroCuenta" var="numeroCuenta"/>
<spring:message code="reprocesos.cancelaciones.reporte.aplicativo" var="aplicativo"/>
<spring:message code="reprocesos.cancelaciones.reporte.periodo" var="periodo"/>
<spring:message code="reprocesos.cancelaciones.reporte.fechaCancelacion" var="fechaCancelacion"/>
<spring:message code="reprocesos.cancelaciones.reporte.folioSat" var="folioSat"/>
<spring:message code="reprocesos.cancelaciones.reporte.comicionesIntereses" var="comicionesIntereses"/>
<spring:message code="reprocesos.cancelaciones.reporte.iva" var="iva"/>
<spring:message code="reprocesos.cancelaciones.reporte.retenciones" var="retenciones"/>

<div class="pageTitleContainer">
   <span class="pageTitle">${tituloPagina}</span>
</div> 
<input type="hidden" name="mes" id ="mes" value="${mes}">  
<div class="frameTablaEstandar">
	<div class="titleTablaEstandar">${tituloTablaEstatus}</div>
	<div class="contentTablaEstandar">
		<table id="tablaCancelaciones">
			<thead>
				<tr>
					<th>${fechaEncabezado}</th>
					<th>${interfazCancelacion}</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${fecha}</td>
					<td class="text_centro"><a href="#" id="exportarCancelaciones"><IMG WIDTH="20" HEIGHT="20"  border="0" title="Descargar" SRC="${pageContext.servletContext.contextPath}/recursos/imagenes/descargar_imagen.jpg"></a></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>


<jsp:include page="../myFooter.jsp" flush="true"/>