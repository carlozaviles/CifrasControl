
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code="facturas.modulo"             		var="modulo"/>
<spring:message code="facturas.formularioFacturas"              var="formularioFacturas"/>
<spring:message code="facturas.aplicativo"             		var="aplicativo"/>
<spring:message code="facturas.periodo"              		var="periodo"/>
<spring:message code="general.selectVacio"                       var="selectVacio"/>
<spring:message code="facturas.mes"             		var="mes"/>
<spring:message code="facturas.anio"             		var="anio"/>
<spring:message code="facturas.camposObligatorios"             	var="camposObligatorios"/>


<table>
	<tbody>
		<tr>
			<td class="odd" >${aplicativo}:</td>
			<td class="odd"></td>
			<td >
				<select name="aplicativo" id="aplicativo"   style="width:180px;">
					<option value="NONE" selected>${selectVacio}</option>
					<c:forEach var="producto" items="${productosList}">
						<option value="${producto.descripcion}">${producto.descripcion}</option>
					</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<td class="odd">${periodo}:</td>
			<td class="odd">${mes}</td>
			<td>
				<select name="mes" id="mes" style="width:180px;" >
				<option value="NONE" selected>${selectVacio}</option>
					<c:forEach var="meses" items="${mesesList}">					
					<option value="${meses.key}">${meses.value}</option>
					</c:forEach>
				</select>
			</td>
			<td class="odd">${anio}</td>			
			<td>
				<select name="anio" id="anio" style="width:180px;" >
					<option value="NONE" selected>${selectVacio}</option>
					<c:forEach var="anios" items="${anioList}">					
					<option value="${anios.key}">${anios.value}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</tbody>
</table>
