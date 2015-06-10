<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="reprocesos" />
	<jsp:param name="menuSubItem"    value="consultaReprocesos" />	
</jsp:include>

<spring:message code="reprocesos.consulta.tituloPaginaReporte" var="tituloReporteReprocesos"/>
<spring:message code="reprocesos.consulta.tituloTablaEstatus" var="tituloTablaEstatus"/>
<spring:message code="reprocesos.consulta.descripcionSOL" var="descripcionSOL"/>
<spring:message code="reprocesos.consulta.descripcionENCI" var="descripcionENCI"/>
<spring:message code="reprocesos.consulta.descripcionENCFD" var="descripcionENCFD"/>
<spring:message code="reprocesos.consulta.descripcionACTCI" var="descripcionACTCI"/>
<spring:message code="reprocesos.consulta.descripcionCONFCI" var="descripcionCONFCI"/>

<spring:message code="reprocesos.consulta.usuario" var="etiquetaUsuario"/>
<spring:message code="reprocesos.consulta.fecha" var="etiquetaFecha"/>
<spring:message code="reprocesos.consulta.periodo" var="etiquetaPeriodo"/>
<spring:message code="reprocesos.consulta.numeroCuenta" var="etiquetaNumeroCuenta"/>
<spring:message code="reprocesos.consulta.producto" var="etiquetaProducto"/>
<spring:message code="reprocesos.consulta.movimiento" var="etiquetaMovimiento"/>
<spring:message code="reprocesos.consulta.nombre" var="etiquetaNombreCliente"/>
<spring:message code="reprocesos.consulta.rfc" var="etiquetaRfc"/>
<spring:message code="reprocesos.consulta.selloInicial" var="etiquetaSelloInicial"/>
<spring:message code="reprocesos.consulta.selloCancelacion" var="etiquetaSelloCancelacion"/>
<spring:message code="reprocesos.consulta.selloFiscal" var="etiquetaSelloFiscal"/>
<spring:message code="reprocesos.consulta.estatus" var="etiquetaEstatus"/>
<spring:message code="reprocesos.consulta.linkRegresar" var="linkRegresar"/>
<spring:message code="reprocesos.consulta.linkExportar" var="linkExportar"/>

<div class="pageTitleContainer">
	<span class="pageTitle">${tituloReporteReprocesos}</span>
</div>
<br>
<div class="frameTablaEstandar">
	<div class="titleTablaEstandar">${tituloTablaEstatus}</div>
	<div class="contentTablaEstandar">
		<table>
			<tr>
				<td class="special" colspan="5"></td>
			</tr>
			<tr class="odd1">
				<td class="text_centro"><strong>SOL.</strong>${descripcionSOL}</td>
				<td class="text_centro"><strong>ENCI.</strong>${descripcionENCI}</td>
				<td class="text_centro"><strong>ENCFD</strong>${descripcionENCFD}</td>
				<td class="text_centro"><strong>ACTCI.</strong>${descripcionACTCI}</td>
				<td class="text_centro"><strong>CONFCI.</strong>${descripcionCONFCI}</td>
			</tr>
		</table>
	</div>
</div>
<c:if test="${not empty listaReprocesos}">
	<div class="frameTablaEstandar">
		<div class="contentTablaEstandar">
			<table>
				<thead>
					<tr>
						<th class="text_centro">${etiquetaUsuario}</th>
						<th class="text_centro">${etiquetaFecha}</th>
						<th class="text_centro">${etiquetaPeriodo}</th>
						<th class="text_centro">${etiquetaNumeroCuenta}</th>
						<th class="text_centro">${etiquetaProducto}</th>
						<th class="text_centro">${etiquetaMovimiento}</th>
						<th class="text_centro">${etiquetaNombreCliente}</th>
						<th class="text_centro">${etiquetaRfc}</th>
						<th class="text_centro">${etiquetaSelloInicial}</th>
						<th class="text_centro">${etiquetaSelloCancelacion}</th>
						<th class="text_centro">${etiquetaSelloFiscal}</th>
						<th class="text_centro">${etiquetaEstatus}</th>
					</tr>
				</thead>
				<tr>
					<td class="special" colspan="12"></td>
				</tr>
				<tbody>
					<c:forEach items="${listaReprocesos}" var="reproceso">
						<tr>
							<td class="text_centro">${reproceso.usuarioOperativo}</td>
							<td class="text_centro">${reproceso.fechaSolicitud}</td>
							<td class="text_centro">${reproceso.periodo}</td>
							<td class="text_centro">${reproceso.numeroCuenta}</td>
							<td class="text_centro">${reproceso.producto}</td>
							<td class="text_centro">${reproceso.movimiento}</td>
							<td class="text_centro">${reproceso.nombre}</td>
							<td class="text_centro">${reproceso.rfc}</td>
							<td class="text_centro">${reproceso.folioInicial}</td>
							<td class="text_centro">${reproceso.folioCancelacion}</td>
							<td class="text_centro">${reproceso.folioNuevo}</td>
							<td class="text_centro">${reproceso.estatus}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="PiePag">
		<a href="${pageContext.servletContext.contextPath}/reprocesos/consultaReprocesos.do">${linkRegresar}</a>
		<a href="#" id="exportarInformeReprocesos">${linkExportar}</a>
	</div>
</c:if>
<c:if test="${noHayDatos}">
	<spring:message code="reprocesos.mensaje.NoDatosConsultaReprocesos" var="mensajeNoDatos"/>
	<script>
		jInfo('', '${mensajeNoDatos}', '', '');
	</script>
</c:if>
<jsp:include page="../myFooter.jsp" flush="true"/>