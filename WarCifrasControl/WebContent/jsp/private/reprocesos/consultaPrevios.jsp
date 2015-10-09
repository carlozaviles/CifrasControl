<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="reprocesos" />
	<jsp:param name="menuSubItem"    value="consultaPrevios" />	
</jsp:include>

<script src="${pageContext.servletContext.contextPath}/recursos/js/reprocesos/consultaPrevios.js" type="text/javascript"></script>

<spring:message code="reprocesos.previos.consulta.tituloPagina" var="tituloPagina"/>
<spring:message code="reprocesos.previos.consulta.tituloTabla"  var="tituloTabla"/>
<spring:message code="reprocesos.previos.consulta.numeroCuenta" var="numeroCuenta"/>
<spring:message code="reprocesos.previos.consulta.producto"     var="producto"/>
<spring:message code="reprocesos.previos.consulta.fecha"        var="fecha"/>
<spring:message code="reprocesos.previos.consulta.descarga"     var="descarga"/> 

<div class="pageTitleContainer">
   <span class="pageTitle">${tituloPagina}</span>
</div>

<div class="frameTablaEstandar">
	<div class="titleTablaEstandar">${tituloTabla}</div>
	<div class="contentTablaEstandar">
		<table id="tablaConsultaPrevios">
			<thead>
				<tr>
					<th class="text_centro">${numeroCuenta}</th>
					<th class="text_centro">${producto}</th>
					<th class="text_centro">${fecha}</th>
					<th class="text_centro">${descarga}</th>
				</tr>
			</thead>
			<tr>
				<td colspan="4" class="special"></td>
			</tr>
			<tbody>
				<c:set var="index" value="0"/>
				<c:forEach var="previo" items="${listaPrevios}">
					<tr>
						<td class="text_centro">${previo.numeroCuenta}</td>
						<td class="text_centro">${previo.producto}</td>
						<td class="text_centro">${previo.cadenaFecha}</td>
						<td class="text_centro">
							<a href="${pageContext.servletContext.contextPath}/reprocesos/descargaPrevio.do?indice=${index}">Descarga</a>
						</td>
					</tr>
					<c:set var="index" value="${index + 1}"/>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="../myFooter.jsp" flush="true"/>