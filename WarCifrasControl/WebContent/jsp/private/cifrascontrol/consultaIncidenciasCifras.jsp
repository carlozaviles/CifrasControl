<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="cifras" />
	<jsp:param name="menuSubItem"    value="consultaCifras" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/cifrascontrol/consultaInsidencia.js" type="text/javascript"></script>

<spring:message code="cifrascontrol.insidencias.tituloPagina" var="tituloPagina"/>
<spring:message code="cifrascontrol.insidencias.tituloTabla" var="tituloTabla"/>
<spring:message code="cifrascontrol.insidencias.producto" var="producto"/>
<spring:message code="cifrascontrol.insidencias.fase" var="fase"/>
<spring:message code="cifrascontrol.insidencias.fecha" var="fecha"/>
<spring:message code="cifrascontrol.insidencias.detalle" var="detalle"/>

<div class="pageTitleContainer">
   <span class="pageTitle">${tituloPagina}</span>
</div>

<div class="frameTablaEstandar">
	<div class="titleTablaEstandar">${tituloTabla}</div>
	<div class="contentTablaEstandar">
		<table id="tablaIncidencias">
			<thead>
				<tr>
					<th class="text_centro">${producto}</th>
					<th class="text_centro">${fase}</th>
					<th class="text_centro">${fecha}</th>
					<th class="text_centro">${detalle}</th>
				</tr>
			</thead>
			<tr>
				<td colspan="4" class="special"></td>
			</tr>
			<tbody>
				<c:set var="index" value="0"/>
				<c:forEach var="insidencia" items="${listaInsidencias}">
					<tr>
						<td class="text_centro">${insidencia.producto}</td>
						<td class="text_centro">${insidencia.fase}</td>
						<td class="text_centro">${insidencia.cadenaFecha}</td>
						<td class="text_centro">
							<a href="${pageContext.servletContext.contextPath}/cifrascontrol/descargaInsidencia.do?indice=${index}">Descarga</a>
						</td>
					</tr>
					<c:set var="index" value="${index + 1}"/>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="../myFooter.jsp" flush="true"/>