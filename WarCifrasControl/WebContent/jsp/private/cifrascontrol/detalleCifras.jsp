
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="cifras" />
	<jsp:param name="menuSubItem"    value="consultaCifras" />	
</jsp:include>

<spring:message code="cifrascontrol.moduloDetalle"         	var="modulo"/>
<spring:message code="cifrascontrol.producto"             	var="productoLabel"/>
<spring:message code="cifrascontrol.periodo"              	var="periodoLabel"/>
<spring:message code="cifrascontrol.regresar"              	var="regresar"/>
<spring:message code="cifrascontrol.detalleInicidencias"        var="detalleInicidencias"/>
<spring:message code="cifrascontrol.exportar"     		var="exportar"/>
<spring:message code="cifrascontrol.moneda"              	var="moneda"/>
<spring:message code="cifrascontrol.tipoDocumento"              var="tipoDocumento"/>
<spring:message code="cifrascontrol.numeroCuenta"              	var="numeroCuenta"/>
<spring:message code="cifrascontrol.observaciones"              var="observaciones"/>
<spring:message code="cifrascontrol.algunosItems"               var="algunosItems"/>
<spring:message code="cifrascontrol.registro"               	var="registros"/>
<spring:message code="cifrascontrol.registros"               	var="registro"/>
<spring:message code="cifrascontrol.unRegistro"               	var="unRegistro"/>
<spring:message code="cifrascontrol.todosRegistros"             var="todosRegistros"/>

<div class="pageTitleContainer">
   <span class="pageTitle">${detalleInicidencias}</span> - ${modulo}
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
	<div class="titleTablaVariasColumnas">${detalleInicidencias}</div>
		<div class="contentTablaVariasColumnas">
			<display:table name="listaDetalle" sort="list" pagesize="10" id="tableExport"
				requestURI="../cifrascontrol/consultaDetalleCifras.do" export="true">
				<display:column property="moneda" title="${moneda}" group="1" sortable="false"
					headerClass="text_centro" />
				<display:column property="tipoDocumento" title="${tipoDocumento}" sortable="false"
					headerClass="text_centro" />
				<display:column property="numeroCuenta" title="${numeroCuenta}" group="1" sortable="false"
					headerClass="text_centro" />
				<display:column property="descripcionError" title="${observaciones}" sortable="false"
					headerClass="text_centro" />
				<display:setProperty name="paging.banner.some_items_found" value="${algunosItems}"/>
				<display:setProperty name="export.banner" value="${exportar}"/>
				<display:setProperty name="paging.banner.item_name" value="${registro}"/>
				<display:setProperty name="paging.banner.items_name" value="${registros}"/>

				<display:setProperty name="paging.banner.one_item_found" value="${unRegistro}"/>
				<display:setProperty name="paging.banner.all_items_found" value="${todosRegistros}"/>

				<display:setProperty name="basic.show.header" value="true" />
				<display:setProperty name="export.pdf" value="false" />
				<display:setProperty name="export.excel" value="false" />
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.rtf" value="false" />
				<display:setProperty name="export.csv" value="true" />
				<display:setProperty name="export.csv.filename"
					value="ConsultaDetalleCifras.csv" />
			</display:table>
	</div>
</div>


<div class="PiePag">

<a href="javascript:history.back(1)">${regresar}</a> 

</div>
			
<jsp:include page="../myFooter.jsp" flush="true"/>

