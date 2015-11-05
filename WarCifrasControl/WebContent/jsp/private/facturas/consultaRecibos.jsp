
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="facturas" />
	<jsp:param name="menuSubItem"    value="recibosDeducibles" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/facturas/validaFormularioFacturas.js" type="text/javascript"></script>

<spring:message code="facturas.moduloRecibos"         	var="modulo"/>
<spring:message code="facturas.recibos"             	var="recibos"/>
<spring:message code="facturas.producto"             	var="productoLabel"/>
<spring:message code="facturas.periodo"              	var="periodoLabel"/>
<spring:message code="facturas.regresar"              	var="regresar"/>
<spring:message code="facturas.resumen"              	var="resumen"/>
<spring:message code="facturas.exportar"     		var="exportar"/>
<spring:message code="facturas.generados"     		var="generados"/>
<spring:message code="facturas.cancelados"     		var="cancelados"/>
<spring:message code="facturas.total"     		var="total"/>
<spring:message code="facturas.exportarReporte"         var="exportarReporte"/>


<div class="pageTitleContainer">
   <span class="pageTitle">${resumen}</span> - ${modulo}
</div>

<div class="contextContainer">
	<div>
		<table>
			<tr>
				<th>${productoLabel}</th>
				<td>${aplicativo}</td>
			</tr>
			<tr>
				<th>${periodoLabel}</th>
				<td>${periodo}</td>
			</tr>
		</table>
	</div>
</div>

   
<div class="frameTablaVariasColumnas">
	<div class="titleTablaVariasColumnas">${recibos}</div>
		<div class="contentTablaVariasColumnas">
			<table>
				<tr>
					<th width="130" class="text_centro">${generados}</th>
					<th width="130" class="text_centro" scope="col">${total}</th>
					<th width="130" class="text_centro" scope="col">${cancelados}</th>
					<th width="130" class="text_centro" scope="col">${total}</th>					
				</tr>
				<tr>
					<Td colspan="4" class="special"></Td>
				</tr>
				<tbody>
					<c:forEach var="filaRecibo" items="${reporteRecibos}">
						<tr>
							<td class="text_centro">${filaRecibo.facturasCorrectas}</td>
							<td class="text_centro">${filaRecibo.totalFactCorrectas}</td>
							<td class="text_centro">${filaRecibo.facturasCanceladas}</td>
							<td class="text_centro">${filaRecibo.totalFactCanceladas}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
</div>

<display:table name="reporteRecibos" sort="list" pagesize="10" id="tableExport"
		requestURI="../facturas/consultaRecibos.do" export="true">
	<display:column property="facturasCorrectas" title="${generados}" group="1" sortable="false"
		headerClass="text_centro" />
	<display:column property="totalFactCorrectas" title="${total}" sortable="false"
		headerClass="text_centro" />
	<display:column property="facturasCanceladas" title="${cancelados}" sortable="false"
		headerClass="text_centro" />
	<display:column property="totalFactCanceladas" title="${total}" sortable="false"
		headerClass="text_centro" />	
	<display:setProperty name="export.excel.filename" value="ConsultaRecibos.xls" />
</display:table>

<div class="PiePag">
	<a href="#" id="exportarReporte">${exportarReporte}</a>
	<a href="../facturas/recibosInit.do">${regresar}</a> 
</div>
			
<jsp:include page="../myFooter.jsp" flush="true"/>
