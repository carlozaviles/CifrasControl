
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code="reprocesos.modulo"             		var="modulo"/>
<spring:message code="reprocesos.tituloTablaDatosPersonas"      var="tituloTablaDatosPersonas"/>
<spring:message code="reprocesos.etiquetaNumeroCuenta"         	var="etiquetaNumeroCuenta"/>
<spring:message code="reprocesos.etiquetaInstruccionesDatos"    var="etiquetaInstruccionesDatos"/>
<spring:message code="reprocesos.linkAceptar"     		var="linkAceptar"/>
<spring:message code="reprocesos.linkCancelar"     		var="linkCancelar"/>


<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span> - ${tituloTablaDatosPersonas}
</div>

<form action="" name="consultaDatosPersonales" id="consultaDatosPersonales" method="post" > 
<div class="frameFormularioB">
	<div class="contentFormularioB">
		<div class="titleFormularioB">${tituloTablaDatosPersonas}</span></div>
			<table>
				<tbody>
					<tr>
						<td colspan="4" class="ind">${etiquetaInstruccionesDatos}</td>
					</tr>
					<tr>
						<td class="odd">${etiquetaNumeroCuenta}</td>
						<td colspan="3"><input name="numero-cuenta" type="text"
							class="CamposErroneos" id="numero-cuenta" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>

<div class="PiePag"><a href="#">${linkCancelar}</a> <a href="#" id="aceptarFormularioDatos">${linkAceptar}</a></div>
</form>

