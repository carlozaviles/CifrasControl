
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="facturas" />
	<jsp:param name="menuSubItem"    value="divisas" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/facturas/validaFormularioFacturas.js" type="text/javascript"></script>

<spring:message code="facturas.moduloDivisas"         	var="modulo"/>
<spring:message code="facturas.divisa"             	var="divisa"/>
<spring:message code="facturas.moneda"             	var="moneda"/>
<spring:message code="facturas.producto"             	var="productoLabel"/>
<spring:message code="facturas.totalFacturas"           var="totalFacturas"/>
<spring:message code="facturas.subtotal"             	var="subtotal"/>
<spring:message code="facturas.tasaIva"             	var="tasaIva"/>
<spring:message code="facturas.total"             	var="total"/>
<spring:message code="facturas.facturasCanceladas"      var="facturasCanceladas"/>
<spring:message code="facturas.periodo"              	var="periodoLabel"/>
<spring:message code="facturas.regresar"              	var="regresar"/>
<spring:message code="facturas.resumen"              	var="resumen"/>
<spring:message code="facturas.facturasFactoraje"      	var="facturasFactoraje"/>
<spring:message code="facturas.facturasConfirming"     	var="facturasConfirming"/>
<spring:message code="facturas.exportar"     		var="exportar"/>
<spring:message code="facturas.exportarReporte"         var="exportarReporte"/>


<div class="pageTitleContainer">
   <span class="pageTitle">${resumen}</span> - ${modulo}
</div>

<input type="hidden" name="aplicativo" id ="aplicativo" value="${aplicativo}">   
<input type="hidden" name="periodo" id ="periodo" value="${periodo}">   
<input type="hidden" name="mes" id ="mes" value="${mes}"> 


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
	<c:if test="${not empty factoraje}">
		<div class="titleTablaVariasColumnas">${facturasConfirming}</div>
	</c:if>
	<c:if test="${empty factoraje}">
		<div class="titleTablaVariasColumnas">${divisa}</div>
	</c:if>
		<div class="contentTablaVariasColumnas">
			<table>
				<tr>
					<th width="122" class="text_centro">${totalFacturas}</th>
					<th width="122" class="text_centro">${moneda}</th>
					<th width="223" class="text_centro" scope="col">${subtotal}</th>
					<th width="122" class="text_centro" scope="col">${tasaIva}</th>
					<th width="122" class="text_centro" scope="col">${total}</th>
					<th width="122" class="text_centro">${facturasCanceladas}</th>
					<th width="223" class="text_centro" scope="col">${subtotal}</th>
					<th width="122" class="text_centro" scope="col">${tasaIva}</th>
					<th width="122" class="text_centro" scope="col">${total}</th>
				</tr>
			
				<tr>
			
					<Td colspan="8" class="special"></Td>
				</tr>
				<tbody>
					<c:forEach var="filaDivisa" items="${reporteDivisas}">
						<tr>
							<td class="text_centro">${filaDivisa.facturasCorrectas}</td>
							<td class="text_centro">${filaDivisa.moneda}</td>
							<td class="text_centro">${filaDivisa.subtotalFactCorrectas}</td>
							<td class="text_centro">${filaDivisa.ivaFactCorrectas}</td>
							<td class="text_centro">${filaDivisa.totalFactCorrectas}</td>
							<td class="text_centro">${filaDivisa.facturasCanceladas}</td>
							<td class="text_centro">${filaDivisa.subtotalFactCanceladas}</td>
							<td class="text_centro">${filaDivisa.ivaFactCanceladas}</td>
							<td class="text_centro">${filaDivisa.totalFactCanceladas}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<c:if test="${not empty factoraje}">
<div class="frameTablaVariasColumnas">
	<div class="titleTablaVariasColumnas">${facturasFactoraje}</div>
		<div class="contentTablaVariasColumnas">
			<table>
				<tr>
					<th width="122" class="text_centro">${totalFacturas}</th>
					<th width="223" class="text_centro" scope="col">${subtotal}</th>
					<th width="122" class="text_centro" scope="col">${tasaIva}</th>
					<th width="122" class="text_centro" scope="col">${total}</th>
					<th width="122" class="text_centro">${facturasCanceladas}</th>
					<th width="223" class="text_centro" scope="col">${subtotal}</th>
					<th width="122" class="text_centro" scope="col">${tasaIva}</th>
					<th width="122" class="text_centro" scope="col">${total}</th>
				</tr>
			
				<tr>
			
					<Td colspan="8" class="special"></Td>
				</tr>
				<tbody>
					<c:forEach var="filaReporteExtra" items="${reporteDivisasExtra}">
						<tr>
							<td class="text_centro">${filaReporteExtra.facturasCorrectas}</td>
							<td class="text_centro">${filaReporteExtra.subtotalFactCorrectas}</td>
							<td class="text_centro">${filaReporteExtra.ivaFactCorrectas}</td>
							<td class="text_centro">${filaReporteExtra.totalFactCorrectas}</td>
							<td class="text_centro">${filaReporteExtra.facturasCanceladas}</td>
							<td class="text_centro">${filaReporteExtra.subtotalFactCanceladas}</td>
							<td class="text_centro">${filaReporteExtra.ivaFactCanceladas}</td>
							<td class="text_centro">${filaReporteExtra.totalFactCanceladas}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

	</div>
</div>

			</c:if>

	</div>
</div>



<div class="PiePag">
	<a href="#" id="exportarReporteDivisas">${exportarReporte}</a>
	<a href="../facturas/divisasInit.do">${regresar}</a> 
</div>
			
<jsp:include page="../myFooter.jsp" flush="true"/>

