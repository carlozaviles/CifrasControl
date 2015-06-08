
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="facturas" />
	<jsp:param name="menuSubItem"    value="factura" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/facturas/validaFormularioFacturas.js" type="text/javascript"></script>

<spring:message code="facturas.resumenFacturas"         var="resumenFacturas"/>
<spring:message code="facturas.factura"             	var="factura"/>
<spring:message code="facturas.producto"             	var="producto"/>
<spring:message code="facturas.totalFacturas"           var="totalFacturas"/>
<spring:message code="facturas.subtotal"             	var="subtotal"/>
<spring:message code="facturas.tasaIva"             	var="tasaIva"/>
<spring:message code="facturas.total"             	var="total"/>
<spring:message code="facturas.facturasCanceladas"      var="facturasCanceladas"/>

<div class="pageTitleContainer">
   <span class="pageTitle">${resumenFacturas}</span>
</div>

<div class="frameTablaVariasColumnas">
	<div class="titleTablaVariasColumnas">${factura}</div>
		<div class="contentTablaVariasColumnas">
			<table>
				<tr>
					<th width="122" class="text_izquierda">${totalFacturas}</th>
					<th width="223" class="text_centro" scope="col">${subtotal}</th>
					<th width="122" class="text_centro" scope="col">${tasaIva}</th>
					<th width="122" class="text_centro" scope="col">${total}</th>
					<th width="122" class="text_izquierda">${facturasCanceladas}</th>
					<th width="223" class="text_centro" scope="col">${subtotal}</th>
					<th width="122" class="text_centro" scope="col">${tasaIva}</th>
					<th width="122" class="text_centro" scope="col">${total}</th>
				</tr>
			
				<tr>
			
					<Td colspan="8" class="special"></Td>
				</tr>
				<tbody>
					<tr class="odd2">
						<td class="text_izquierda">A1234567</td>
						<td class="text_izquierda">Aparsur Metalurúrgica</td>
						<td class="text_izquierda">Cliente</td>
						<td class="text_derecha">J130013</td>
						<td class="text_izquierda">A1234567</td>
						<td class="text_izquierda">Aparsur Metalurúrgica</td>
						<td class="text_izquierda">Cliente</td>
						<td class="text_derecha">J130013</td>
					</tr>
					<tr class="odd1">
						<td class="text_izquierda">B7654321</td>
						<td class="text_izquierda">Arpaes S.A.</td>
						<td class="text_izquierda">Cliente</td>
						<td class="text_derecha">J130015</td>
						<td class="text_izquierda">A1234567</td>
						<td class="text_izquierda">Aparsur Metalurúrgica</td>
						<td class="text_izquierda">Cliente</td>
						<td class="text_derecha">J130013</td>
					</tr>
				</tbody>
			</table>
			<display:table name="facturaList" sort="list" pagesize="10" id="tableExport"
				requestURI="../facturas/consultaFacturas.do" export="true">
				<display:column property="cantidadFacturas" title="${totalFacturas}" group="1" sortable="false"
					headerClass="text_centro" />
				<display:column property="subTotal" title="${subtotal}" sortable="false"
					headerClass="text_centro" />
				<display:column property="iva" title="${tasaIva}" sortable="false"
					headerClass="text_centro" />
				<display:column property="cantidadFacturas" title="${total}" sortable="false"
					headerClass="text_centro" />
				<display:column property="cantidadFacturas" title="${facturasCanceladas}" sortable="false"
					headerClass="text_centro" />
				<display:column property="subTotal" title="${subtotal}"  sortable="false"
					headerClass="text_centro" />
				<display:column property="cantidadFacturas" title="${tasaIva}" sortable="false"
					headerClass="text_centro" />
				<display:column property="totalImpuestos" title="${total}" sortable="false"
				headerClass="text_centro" />
				<display:setProperty name="export.banner" value="Exportar {0}"/>
				<display:setProperty name="basic.show.header" value="false" />
				<display:setProperty name="export.pdf" value="false" />
				<display:setProperty name="export.excel" value="false" />
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.rtf" value="false" />
				<display:setProperty name="export.csv" value="true" />
				<display:setProperty name="export.csv.filename"
					value="ConsultaFacturas.csv" />
			</display:table>


	</div>
</div>

			
<jsp:include page="../myFooter.jsp" flush="true"/>

