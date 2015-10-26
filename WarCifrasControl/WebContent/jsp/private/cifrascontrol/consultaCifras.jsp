
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="cifras" />
	<jsp:param name="menuSubItem"    value="consultaCifras" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/cifrascontrol/validaCifrasControl.js" type="text/javascript"></script>

<spring:message code="cifrascontrol.moduloCifras"         	var="modulo"/>
<spring:message code="cifrascontrol.producto"             	var="productoLabel"/>
<spring:message code="cifrascontrol.periodo"              	var="periodoLabel"/>
<spring:message code="cifrascontrol.regresar"              	var="regresar"/>
<spring:message code="cifrascontrol.resumen"              	var="resumen"/>
<spring:message code="cifrascontrol.exportar"     		var="exportar"/>

<spring:message code="cifrascontrol.aplicativoorigen"           var="aplicativoorigen"/>
<spring:message code="cifrascontrol.moneda"              	var="moneda"/>
<spring:message code="cifrascontrol.estatus"              	var="estatus"/>
<spring:message code="cifrascontrol.cuentas"              	var="cuentas"/>

<spring:message code="cifrascontrol.interfase"             	var="interfase"/>
<spring:message code="cifrascontrol.tipoDocumento"              var="tipoDocumento"/>
<spring:message code="cifrascontrol.comisiones"              	var="comisiones"/>
<spring:message code="cifrascontrol.ivas"              		var="ivas"/>
<spring:message code="cifrascontrol.retenciones"     		var="retenciones"/>

<spring:message code="cifrascontrol.sat"             		var="sat"/>

<spring:message code="cifrascontrol.edc"             		var="edc"/>
<spring:message code="cifrascontrol.conSello"              	var="conSello"/>
<spring:message code="cifrascontrol.sinSello"             	var="sinSello"/>
<spring:message code="cifrascontrol.conIncidente"              	var="conIncidente"/>


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
<form action="consultaDetalleCifras.do" name="formularioFacturas" id="formularioFacturas" method="post" > 
<input type="hidden" name="aplicativo" id ="aplicativo" value="${aplicativo}">   
<input type="hidden" name="periodo" id ="periodo" value="${periodo}">   
</form>

<div class="frameTablaVariasColumnas">
	<div class="titleTablaVariasColumnas">${aplicativoorigen}</div>
		<div class="contentTablaVariasColumnas">
			<table>
				<tr>
					<th width="174" class="text_centro">${moneda}</th>
					<th width="174" class="text_centro" scope="col">${estatus}</th>
					<th width="174" class="text_centro" scope="col">${cuentas}</th>
				</tr>
			
				<tr>
			
					<Td colspan="3" class="special"></Td>
				</tr>
				<tbody>
					<c:forEach var="origen" items="${origenList}">
						<tr>
							<td class="text_centro">${origen.moneda}</td>
							<td class="text_centro">${origen.status}</td>
							<td class="text_centro">${origen.cantidadCuentas}</td>
						</tr>           				
					</c:forEach>
				</tbody>
			</table>
	</div>
<br>
<div class="titleTablaVariasColumnas">${interfase}</div>
		<div class="contentTablaVariasColumnas">
			<table>
				<tr>
					<th width="150" class="text_centro">${tipoDocumento}</th>
					<th width="150" class="text_centro" scope="col">${cuentas}</th>
					<th width="150" class="text_centro" scope="col">${comisiones}</th>
					<th width="150" class="text_centro" scope="col">${ivas}</th>
					<th width="150" class="text_centro" scope="col">${retenciones}</th>
				</tr>
			
				<tr>
			
					<Td colspan="5" class="special"></Td>
				</tr>
				<tbody>
					<c:forEach var="interfaseItem" items="${interfaseList}">
						<tr>
							<td class="text_centro">${interfaseItem.tipoDocumento}</td>
							<td class="text_centro">${interfaseItem.cantidadCuentas}</td>
							<td class="text_centro">${interfaseItem.comisionesIntereses}</td>
							<td class="text_centro">${interfaseItem.ivas}</td>
							<td class="text_centro">${interfaseItem.retenciones}</td>
						</tr>           				
					</c:forEach>
				</tbody>
			</table>
		
	</div>
<br>
<div class="titleTablaVariasColumnas">${sat}</div>
		<div class="contentTablaVariasColumnas">
			<table>
				<tr>
					<th width="150" class="text_centro">${moneda}</th>
					<th width="150" class="text_centro" scope="col">${tipoDocumento}</th>
					<th width="150" class="text_centro" scope="col">${cuentas}</th>
					<th width="150" class="text_centro" scope="col">${comisiones}</th>
					<th width="150" class="text_centro" scope="col">${ivas}</th>
					<th width="150" class="text_centro" scope="col">${retenciones}</th>
				</tr>
			
				<tr>
			
					<Td colspan="7" class="special"></Td>
				</tr>
				<tbody>
					<c:forEach var="satItem" items="${satList}" varStatus="myIndex">
						<tr>
							<td class="text_centro">${satItem.moneda}</td>
							<td class="text_centro">${satItem.tipoDocumento}</td>
							<td class="text_centro">${satItem.cantidadCuentas}</td>
							<td class="text_centro">${satItem.comisionesIntereses}</td>
							<td class="text_centro">${satItem.ivas}</td>
							<td class="text_centro">${satItem.retenciones}</td>
						</tr>           				
					</c:forEach>
				</tbody>
			</table>
	
</div>
<br>
<div class="titleTablaVariasColumnas">${edc}</div>
		<div class="contentTablaVariasColumnas">
			<table>
				<tr>
					<th width="150" class="text_centro">${estatus}</th>
					<th width="150" class="text_centro" scope="col">${conSello}</th>
					<th width="150" class="text_centro" scope="col">${sinSello}</th>
					<th width="150" class="text_centro" scope="col">${conIncidente}</th>
				</tr>
			
				<tr>
			
					<Td colspan="4" class="special"></Td>
				</tr>
				<tbody>
					<c:forEach var="edcItem" items="${edcList}">
						<tr>
							<td class="text_centro">${edcItem.status}</td>
							<td class="text_centro">${edcItem.cuentasConSello}</td>
							<td class="text_centro">${edcItem.cuentasSinSello}</td>
							<td class="text_centro">${edcItem.cuentasConIncidencias}</td>
						</tr>           				
					</c:forEach>
				</tbody>
			</table>
	
</div>
</div>

<display:table name="listaExportar" sort="list" pagesize="10" id="tableExport"
				requestURI="../cifrascontrol/consultaCifras.do" export="true">
				<display:column property="moneda" title="${moneda}" group="1" sortable="false"
					headerClass="text_centro" />
				<display:column property="status" title="${estatus}" sortable="false"
					headerClass="text_centro" />
				<display:column property="cantidadCuentas" title="${cuentas}" sortable="false"
					headerClass="text_centro" />
				
				<display:column property="tipoDocumento" title="${tipoDocumento}" group="1" sortable="false"
					headerClass="text_centro" />
				<display:column property="comisionesIntereses" title="${comisiones}" sortable="false"
					headerClass="text_centro" />
				<display:column property="retenciones" title="${retenciones}" sortable="false"
					headerClass="text_centro" />
				
				<display:column property="cuentasConSello" title="${conSello}" sortable="false"
					headerClass="text_centro" />
				<display:column property="cuentasSinSello" title="${sinSello}" sortable="false"
					headerClass="text_centro" />
				<display:column property="cuentasConIncidencias" title="${conIncidente}" sortable="false"
					headerClass="text_centro" />
								

				<display:setProperty name="export.banner" value="${exportar}"/>
				<display:setProperty name="basic.show.header" value="false" />
				<display:setProperty name="export.pdf" value="false" />
				<display:setProperty name="export.excel" value="true" />
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.rtf" value="false" />
				<display:setProperty name="export.csv" value="false" />
				<display:setProperty name="export.excel.filename" value="ConsultaCifrasEDC.xls" />
			</display:table>


<div class="PiePag">

<a href="../cifrascontrol/cifrasInit.do">${regresar}</a> 


</div>
			
<jsp:include page="../myFooter.jsp" flush="true"/>

