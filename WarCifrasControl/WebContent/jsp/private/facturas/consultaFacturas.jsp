
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="facturas" />
	<jsp:param name="menuSubItem"    value="factura" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/facturas/validaFormularioFacturas.js" type="text/javascript"></script>

<spring:message code="facturas.modulo"         		var="modulo"/>
<spring:message code="facturas.factura"             	var="factura"/>
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
<spring:message code="facturas.exportar"     		    var="exportar"/>
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
	<c:if test="${not empty reporteExtra}">
		<div class="titleTablaVariasColumnas">${facturasConfirming}</div>
	</c:if>
	<c:if test="${empty reporteExtra}">
		<div class="titleTablaVariasColumnas">${factura}</div>
	</c:if>
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
					<c:forEach var="filaFactura" items="${reporteFacturas}">
						<tr>
							<td class="text_centro">${filaFactura.facturasCorrectas}</td>
							<td class="text_centro">${filaFactura.subtotalFactCorrectas}</td>
							<td class="text_centro">${filaFactura.ivaFactCorrectas}</td>
							<td class="text_centro">${filaFactura.totalFactCorrectas}</td>
							<td class="text_centro">${filaFactura.facturasCanceladas}</td>
							<td class="text_centro">${filaFactura.subtotalFactCanceladas}</td>
							<td class="text_centro">${filaFactura.ivaFactCanceladas}</td>
							<td class="text_centro">${filaFactura.totalFactCanceladas}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<c:if test="${not empty reporteExtra}">
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
					<c:forEach var="filaReporteExtra" items="${reporteExtra}">
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
	<a href="#" id="exportarReporte">${exportarReporte}</a>
	<a href="../facturas/facturasInit.do">${regresar}</a> 
</div>
			
<jsp:include page="../myFooter.jsp" flush="true"/>

