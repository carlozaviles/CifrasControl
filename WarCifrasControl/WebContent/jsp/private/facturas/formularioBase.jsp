
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code="facturas.modulo"             		var="modulo"/>
<spring:message code="facturas.formularioFacturas"              var="formularioFacturas"/>
<spring:message code="facturas.aplicativo"             		var="aplicativo"/>
<spring:message code="facturas.periodo"              		var="periodo"/>
<spring:message code="facturas.mes"             		var="mes"/>
<spring:message code="facturas.anio"             		var="anio"/>
<spring:message code="facturas.camposObligatorios"             	var="camposObligatorios"/>


<table>
	<tbody>
		<tr>
			<td class="odd" class="odd">${aplicativo}:</td>
			<td class="odd" colspan="4">
				<select name="aplicativo" id="aplicativo" class="Campos_Des" style="font-size: 9px;">
					<option value="" selected="selected">--Seleccione--</option>
					<c:forEach var="producto" items="${productosList}">					
					<option value="${producto.descripcion}">${producto.descripcion}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="odd">${periodo}</td>
			<td class="odd">${mes}</td>
			<td>
				<select name="mes" id="mes" class="Campos_Des">
					<option value="" selected="selected">--Seleccione--</option>
					<c:forEach var="meses" items="${mesesList}">					
					<option value="${meses.key}">${meses.value}</option>
					</c:forEach>
				</select>
			</td>
			<td class="odd">${anio}</td>			
			<td>
				<select name="anio" id="anio" class="Campos_Des">
					<option value="" selected="selected">--Seleccione--</option>
					<c:forEach var="anios" items="${anioList}">					
					<option value="${anios.key}">${anios.value}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</tbody>
</table>
