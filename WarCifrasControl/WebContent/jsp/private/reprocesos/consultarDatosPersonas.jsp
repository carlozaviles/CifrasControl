
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="${pageContext.servletContext.contextPath}/recursos/js/reprocesos/validacionesDatosPersonas.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath}/recursos/js/reprocesos/validacionesCommon.js" type="text/javascript"></script>

<spring:message code="reprocesos.modulo"             		var="modulo"/>
<spring:message code="reprocesos.tituloTablaDatosPersonas"      var="tituloTablaDatosPersonas"/>
<spring:message code="reprocesos.etiquetaNumeroCuenta"         	var="etiquetaNumeroCuenta"/>
<spring:message code="reprocesos.etiquetaInstruccionesDatos"    var="etiquetaInstruccionesDatos"/>
<spring:message code="reprocesos.linkAceptar"     		var="linkAceptar"/>
<spring:message code="reprocesos.linkCancelar"     		var="linkCancelar"/>


<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span> - ${tituloTablaDatosPersonas}
</div>

<form action="datosFiscales.do" name="consultaDatosPersonales" method="post" > 
<div class="frameFormularioB">
	<div class="contentFormularioB">
		<div class="titleFormularioB"><span>${tituloTablaDatosPersonas}</span></div>
			<table>
				<tbody>
					<tr>
						<td colspan="4" class="ind">${etiquetaInstruccionesDatos}</td>
					</tr>
					<tr>
						<td class="odd">${etiquetaNumeroCuenta}</td>
						<td colspan="3"><input name="numero-cuenta" type="text"
							 id="numero-cuenta" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

<div class="PiePag"> <a href="#" id="aceptarFormularioDatos">${linkAceptar}</a></div>
</form>

