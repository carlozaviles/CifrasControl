<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="reprocesos" />
	<jsp:param name="menuSubItem"    value="solicitudReprocesos" />	
</jsp:include>

<spring:message code="reprocesos.encabezadoDatosPersonas"       var="encabezadoDatosPersonas"/>
<spring:message code="reprocesos.tablaDatosPersonas"            var="tablaDatosPersonas"/>
<spring:message code="reprocesos.datosPersonas.nombre"          var="nombre"/>
<spring:message code="reprocesos.datosPersonas.rfc"             var="rfc"/>
<spring:message code="reprocesos.datosPersonas.domicilio"       var="domicilio"/>
<spring:message code="reprocesos.encabezadoSolicitudReprocesos" var="encabezadoSolicitudReprocesos"/>
<spring:message code="reprocesos.tituloFormularioSolicitud" var="tituloFormularioSolicitud"/>
<spring:message code="reprocesos.solicitud.numeroCuenta"   var="etiquetaNumeroCuenta"/>
<spring:message code="reprocesos.solicitud.producto"       var="etiquetaProducto"/>
<spring:message code="reprocesos.solicitud.periodo"        var="etiquetaPeriodo"/>
<spring:message code="reprocesos.solicitud.mes"            var="etiquetaMes"/>
<spring:message code="reprocesos.solicitud.anio"           var="etiquetaAnio"/>
<spring:message code="reprocesos.solicitud.tipoMovimiento" var="etiquetaTipoMovimiento"/>
<spring:message code="general.selectVacio"                 var="seleccione"/>
<spring:message code="reprocesos.linkAceptar"     		   var="linkAceptar"/>
<spring:message code="reprocesos.linkCancelar"     		   var="linkCancelar"/>

<script src="${pageContext.servletContext.contextPath}/recursos/js/reprocesos/validacionesReprocesos.js" type="text/javascript"></script>

<jsp:include page="consultarDatosPersonas.jsp" flush="true"/>
<c:if test="${muestraDatosFiscales}">
<br>
<div class="pageTitleContainer">
	<span class="pageTitle">${encabezadoDatosPersonas}</span>
</div>
<br>
<div class="formularioConfirmacion">
	<div class="titleFormularioConfirmacion">${tablasDatosPersonas}</div>
	<div class="contentFormularioConfirmacion">
		<table>
			<tr>
				<td colspan="2" align="center">
					<img border="0" src="${pageContext.servletContext.contextPath}/lf/${LyFBean.lookAndFeel}/img/cifrascontrol/santander-kredit.png" width="140" height="100" align="middle" alt="logo_santander">
				</td>
			</tr>
			<tr>
				<td class="odd">${nombre}:</td>
				<td class="text_izquierda"><label>${solicitudReprocesoForm.nombre}&nbsp;${solicitudReprocesoForm.paterno}&nbsp;${solicitudReprocesoForm.materno}</label></td>
			</tr>
			<tr>
				<td class="odd">${rfc}:</td>
				<td class="text_izquierda"><label>${solicitudReprocesoForm.rfc}</label></td>
			</tr>
			<tr>
				<td class="odd">${domicilio}:</td>
				<td class="text_izquierda">
					<label>
						${solicitudReprocesoForm.calle}&nbsp;#&nbsp;${solicitudReprocesoForm.numeroExterior},
						&nbsp;${solicitudReprocesoForm.colonia},&nbsp;${solicitudReprocesoForm.municipio},&nbsp;${solicitudReprocesoForm.estado},
						&nbsp;CP&nbsp;${solicitudReprocesoForm.codigoPostal}
					</label>
				</td>
			</tr>
		</table>
	</div>
</div>
<br>
<div class="pageTitleContainer">
	<span class="pageTitle">${encabezadoSolicitudReprocesos}</span>
</div>
<br>
<div class="frameFormularioB">
	<div class="contentFormularioB">
		<div class="titleFormularioB">${tituloFormularioSolicitud}</div>
		<form:form action="solicitudReproceso.do" method="POST" modelAttribute="solicitudReprocesoForm">
			<table>
				<tr>
					<td class="odd">${etiquetaNumeroCuenta}:</td>
					<td colspan="4"><input type="text" value="${numeroCuenta}" disabled></td>
				</tr>
				<tr>
					<td class="odd">${etiquetaProducto}:</td>
					<td colspan="4">
						<form:select path="producto">
							<form:option value="NONE" label="${seleccione}"/>
							<form:options items="${catalogoProductos}"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="odd">${etiquetaPeriodo}</td>
					<td>${etiquetaMes}</td>
					<td>
						<form:select path="mes">
							<form:option value="NONE" label="${seleccione}"/>
							<form:options items="${catalogoMeses}"/>
						</form:select>
					</td>
					<td>${etiquetaAnio}</td>
					<td>
						<form:select path="anio">
							<form:option value="NONE" label="${seleccione}"/>
							<form:options items="${catalogoAnios}"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="odd">${etiquetaTipoMovimiento}</td>
					<td colspan="4">
						<form:select path="movimiento">
							<form:option value="NONE" label="${seleccione}"/>
							<form:options items="${catalogoTiposMovimiento}"/>
						</form:select>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
<div class="PiePag">
	<a href="#" id="solicitudReproceso">${linkAceptar}</a> <a href="${pageContext.servletContext.contextPath}/reprocesos/consultaPersonas.do" id="cancelarSolicitudReproceso">${linkCancelar}</a>
</div>
</c:if>
<c:if test="${noHayDatos}">
	<spring:message code="reprocesos.consultaSinResultados" var="mensajeNoResultados"/>
	<script>
		jInfo('', '${mensajeNoResultados}', '', '');
	</script>
</c:if>
<c:if test="${solicitudReprocesoRealizada}">
	<spring:message code="reprocesos.confirmacionSolicitud" var="mensajeConfirmacion"/>
	<script>
		jInfo('', '${mensajeConfirmacion}', '', '');
	</script>
</c:if>
<jsp:include page="../myFooter.jsp" flush="true"/>
