<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="reprocesos" />
	<jsp:param name="menuSubItem"    value="consultaReprocesos" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/reprocesos/validacionConsultaReprocesos.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath}/recursos/js/reprocesos/validacionesCommon.js" type="text/javascript"></script>

<spring:message code="reprocesos.tituloConsultaReprocesos" var="tituloConsultaReprocesos"/>
<spring:message code="reprocesos.tituloFormularioReprocesos" var="tituloFormularioConsultaReproceso"/>
<spring:message code="reprocesos.periodo" var="etiquetaPeriodo"/>
<spring:message code="reprocesos.mes" var="etiquetaMes"/>
<spring:message code="reprocesos.anio" var="etiquetaAnio"/>
<spring:message code="reprocesos.consulta" var="linkConsultaReproceso"/>
<spring:message code="general.selectVacio" var="seleccione"/>
<spring:message code="reprocesos.nocuenta" var= "etiquetanoCuenta"/>
<spring:message code="reprocesos.producto" var="etiquetaProducto"/>
<spring:message code="reprocesos.prod" var="etiquetaSelectProducto"/>

<div class="pageTitleContainer">
	<span class="pageTitle">${tituloConsultaReprocesos}</span>
</div>
<br>
<div class="frameFormularioB">
	<div class="contentFormularioB">
		<div class="titleFormularioB">${tituloFormularioConsultaReproceso}</div>
		<form:form action="realizaConsultaReproceso.do" method="POST" modelAttribute="consultaReprocesoForm">
			<table>
				<tr>
					<td class="odd">${etiquetanoCuenta}:</td>
					<td colspan="3"> <form:input path="numeroCuenta" name="numeroCuenta" id="numeroCuenta" /></td>
				</tr>
				<tr>
					<td class="odd">${etiquetaPeriodo}:</td>
					<td>${etiquetaMes}</td>
					<td>
						<form:select path="mes">
							<form:option value="NONE" label="${seleccione}"/>
							<form:options items="${catalogoMes}"/>
						</form:select>
					</td>
					<td>${etiquetaAnio}</td>
					<td>
						<form:select path="anio">
							<form:option value="NONE" label="${seleccione}"/>
							<form:options items="${catalogoAnios}"/>
						</form:select>
					</td>
					<tr>
					<td class="odd">${etiquetaProducto}:</td>
					<td colspan="3">
							<form:select path="productoSeleccionado">
						<form:option value="NONE" selected="selected">--Seleccione--</form:option>
						<c:forEach var="producto" items="${productosList}">					
							<form:option value="${producto.descripcion}">${producto.descripcion}</form:option>
						</c:forEach>
				</form:select>	
				</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
<div class="PiePag">
	<a href="#" id="consultaReproceso">${linkConsultaReproceso}</a>
</div>
<jsp:include page="../myFooter.jsp" flush="true"/>