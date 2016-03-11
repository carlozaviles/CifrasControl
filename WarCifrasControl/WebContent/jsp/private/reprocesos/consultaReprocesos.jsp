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
<spring:message code="general.selectVacio" var="selectVacio"/>
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
		<form action="realizaConsultaReproceso.do" method="POST" name="consultaReprocesoForm" id="consultaReprocesoForm" >
			<table>
				<tr>
					<td class="odd">${etiquetanoCuenta}:</td>
					<td class="odd"></td>
					<td > <input name="numeroCuenta" id="numeroCuenta" style="width:180px;" /></td>
				</tr>
			
					<tr>
					<td class="odd">${etiquetaProducto}:</td>
					<td class="odd"></td>
					<td colspan="3">
							<select name="productoSeleccionado" id="productoSeleccionado" style="width:180px;">
						<option value="NONE" selected="selected">${selectVacio}</option>
						<c:forEach var="producto" items="${productosList}">					
							<option value="${producto.descripcion}">${producto.descripcion}</option>
						</c:forEach>
				</select>	
				</td>
				
				</tr>
				    <tr>
                    <td class="odd">${etiquetaPeriodo}:</td>
                    <td class="odd">${etiquetaMes}</td>
                    <td>
                        <select name="mes" id="mes" style="width:180px;">
                      <option value="NONE" selected="selected">${selectVacio}</option>
                                <c:forEach var="meses" items="${catalogoMes}">
                                <option value="${meses.key}">${meses.value}</option>
                            </c:forEach>
                         <select>
                    </td>
                    <td class="odd">${etiquetaAnio}</td>
                    <td>
                        <select name="anio" id="anio" style="width:180px;">
                     <option value="NONE" selected="selected">${selectVacio}</option>
                            <c:forEach var="anios" items="${catalogoAnios}">                 
                            <option value="${anios.key}">${anios.value}</option>
                            </c:forEach>
                        <select>
                    </td>
                    </tr>
			</table>
		</form>
	</div>
</div>
<div class="PiePag">
	<a href="#" id="consultaReproceso">${linkConsultaReproceso}</a>
</div>
<jsp:include page="../myFooter.jsp" flush="true"/>