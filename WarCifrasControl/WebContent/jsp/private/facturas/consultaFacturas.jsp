
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
<spring:message code="facturas.exportar"     		var="exportar"/>


<c:set var="facturasCorrectasList" value="${facturasCorrectas}" />
<c:set var="facturasIncorrectasList" value="${facturasIncorrectas}" />



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
	<c:if test="${not empty factoraje}">
		<div class="titleTablaVariasColumnas">${facturasConfirming}</div>
	</c:if>
	<c:if test="${empty factoraje}">
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
					<c:forEach var="i" begin="0" end="2">
						<tr>
							<td class="text_centro">${facturasCorrectasList[i].cantidadFacturas}</td>
							<td class="text_centro">${facturasCorrectasList[i].subTotal}</td>
							<td class="text_centro">${facturasCorrectasList[i].iva}</td>
							<td class="text_centro">${facturasCorrectasList[i].totalImpuestos}</td>
							<td class="text_centro">${facturasIncorrectasList[i].cantidadFacturas}</td>
							<td class="text_centro">${facturasIncorrectasList[i].subTotal}</td>
							<td class="text_centro">${facturasIncorrectasList[i].iva}</td>
							<td class="text_centro">${facturasIncorrectasList[i].totalImpuestos}</td>
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
					<c:forEach var="i" begin="0" end="2">
						<tr>
							<td class="text_centro">${factorajeCorrecto[i].cantidadFacturas}</td>
							<td class="text_centro">${factorajeCorrecto[i].subTotal}</td>
							<td class="text_centro">${factorajeCorrecto[i].iva}</td>
							<td class="text_centro">${factorajeCorrecto[i].totalImpuestos}</td>
							<td class="text_centro">${factorajeIncorrecto[i].cantidadFacturas}</td>
							<td class="text_centro">${factorajeIncorrecto[i].subTotal}</td>
							<td class="text_centro">${factorajeIncorrecto[i].iva}</td>
							<td class="text_centro">${factorajeIncorrecto[i].totalImpuestos}</td>
						</tr>           				
					</c:forEach>
				</tbody>
			</table>

	</div>
</div>

			</c:if>

	</div>
</div>

<display:table name="facturaList" sort="list" pagesize="10" id="tableExport"
				requestURI="../facturas/consultaFacturas.do" export="true">
				<display:column property="cantidadFacturas" title="${totalFacturas}" group="1" sortable="false"
					headerClass="text_centro" />
				<display:column property="subTotal" title="${subtotal}" sortable="false"
					headerClass="text_centro" />
				<display:column property="iva" title="${tasaIva}" sortable="false"
					headerClass="text_centro" />
				<display:column property="totalImpuestos" title="${total}" sortable="false"
					headerClass="text_centro" />
				<display:setProperty name="export.banner" value="${exportar}"/>
				<display:setProperty name="basic.show.header" value="false" />
				<display:setProperty name="export.pdf" value="false" />
				<display:setProperty name="export.excel" value="false" />
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.rtf" value="false" />
				<display:setProperty name="export.csv" value="true" />
				<display:setProperty name="export.csv.filename"
					value="ConsultaFacturas.csv" />
			</display:table>

<div class="PiePag">

<a href="../facturas/facturasInit.do">${regresar}</a> 


</div>
			
<jsp:include page="../myFooter.jsp" flush="true"/>

