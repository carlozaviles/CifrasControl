<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../private/myHeader.jsp" flush="true"/>
<jsp:include page="../private/myMenu.jsp" flush="true">
	<jsp:param name="menuItem"    value="principal" />
</jsp:include>
		
	<spring:message code="error.labelDescripcion"       var="descripcion" />
	<spring:message code="error.errorGral"              var="errorGral" />
		
	
	<div class="frameFormularioConfirmacion">
	<div class="titleFormularioConfirmacion">${errorGral} </div>
		<div class="contentFormularioConfirmacion">
			<table>
				<tbody>
					<tr>
						<td class="odd">${descripcion} </td>
						<td class="text_izquierda">${errorGral}</td>
					</tr>
				</tbody>
			</table>
	</div>
</div>
<jsp:include page="../private/myFooter.jsp" flush="true"/>


