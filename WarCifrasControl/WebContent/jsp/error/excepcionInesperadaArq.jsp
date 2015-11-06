<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../private/myHeader.jsp" flush="true"/>
<jsp:include page="../private/myMenu.jsp" flush="true">
	<jsp:param name="menuItem"    value="principal" />
</jsp:include>

	<spring:message code="error.${codeError}.descripcionError"  var="descripcionError"  />
	<spring:message code="error.${codeError}.tipoError"         var="tipoError"   />
	<spring:message code="error.${codeError}.tituloError"       var="tituloError" />
	
	<spring:message code="error.labelCodigo"            var="codigo" />
	<spring:message code="error.labelDescripcion"       var="descripcion" />
	<spring:message code="error.labelTipo"              var="tipo" />
		
	
	<div class="frameFormularioConfirmacion">
	<div class="titleFormularioConfirmacion">${tituloError} </div>
		<div class="contentFormularioConfirmacion">
			<table>
				<tbody>
				    <tr>
						<td width="206" class="odd">${codigo} </td>
						<td width="353" class="text_izquierda"><label>${codeError}</label></td>
					</tr>
					<tr>
						<td width="206" class="odd">${tipo} </td>
						<td width="353" class="text_izquierda"><label>${tipoError}</label></td>
					</tr>
					<tr>
						<td class="odd">${descripcion} </td>
						<td class="text_izquierda">${descripcionError}</td>
					</tr>
				</tbody>
			</table>
	</div>
</div>

		
		
<jsp:include page="../private/myFooter.jsp" flush="true"/>


