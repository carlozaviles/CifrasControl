
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="facturas" />
	<jsp:param name="menuSubItem"    value="notasCredito" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/facturas/validaFormularioFacturas.js" type="text/javascript"></script>

<spring:message code="facturas.moduloNotas"         	var="modulo"/>
<spring:message code="facturas.notas"             	var="notas"/>
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
	<c:if test="${not empty reporteNotasExport}">
		<div class="titleTablaVariasColumnas">${notas}</div>
	</c:if>
	<c:if test="${empty reporteNotasExport}">
		<div class="titleTablaVariasColumnas">${notas}</div>
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
					<c:forEach var="filaNota" items="${reporteNotas}">
						<tr>
							<td class="text_centro">${filaNota.facturasCorrectas}</td>
							<td class="text_centro">${filaNota.subtotalFactCorrectas}</td>
							<td class="text_centro">${filaNota.ivaFactCorrectas}</td>
							<td class="text_centro">${filaNota.totalFactCorrectas}</td>
							<td class="text_centro">${filaNota.facturasCanceladas}</td>
							<td class="text_centro">${filaNota.subtotalFactCanceladas}</td>
							<td class="text_centro">${filaNota.ivaFactCanceladas}</td>
							<td class="text_centro">${filaNota.totalFactCanceladas}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<c:if test="${not empty reporteNotasExtra}">
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
					<c:forEach var="filaNotaExtra" items="${reporteNotasExtra}">
						<tr>
							<td class="text_centro">${filaNotaExtra.facturasCorrectas}</td>
							<td class="text_centro">${filaNotaExtra.subtotalFactCorrectas}</td>
							<td class="text_centro">${filaNotaExtra.ivaFactCorrectas}</td>
							<td class="text_centro">${filaNotaExtra.totalFactCorrectas}</td>
							<td class="text_centro">${filaNotaExtra.facturasCanceladas}</td>
							<td class="text_centro">${filaNotaExtra.subtotalFactCanceladas}</td>
							<td class="text_centro">${filaNotaExtra.ivaFactCanceladas}</td>
							<td class="text_centro">${filaNotaExtra.totalFactCanceladas}</td>
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
	<a href="#" id="exportarReporteNotas">${exportarReporte}</a>
	<a href="../facturas/notasCreditoInit.do">${regresar}</a> 
</div>
			
<jsp:include page="../myFooter.jsp" flush="true"/>

