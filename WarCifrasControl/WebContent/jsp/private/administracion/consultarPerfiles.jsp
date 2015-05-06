
<%@ taglib uri="http://www.springframework.org/tags"    prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt"%>



<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="administracion" />
	<jsp:param name="menuSubItem"    value="perfiles" />	
</jsp:include>


<spring:message code="administracion.modulo"            var="modulo"/>
<spring:message code="administracion.tituloPerfiles"    var="tituloPerfiles"/>
<spring:message code="administracion.perfil"         	var="perfil"/>
<spring:message code="administracion.descripcion"    	var="descripcion"/>
<spring:message code="administracion.nuevoPerfil"     	var="nuevoPerfil"/>
<spring:message code="administracion.seleccione"     	var="seleccione"/>
<spring:message code="administracion.detalle"     	var="detalle"/>

<fmt:parseNumber value="${numeroElementos}" var="numeroElementosLista"/>
<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span> - ${tituloPerfiles}
</div>
Total de registros: ${numeroElementosLista}
<form action="modificarPerfilInit.do" name="modificar" id="modificar" method="post">

	<div class="frameTablaVariasColumnas">
	<div class="titleTablaVariasColumnas">${perfil} <span class="textosin">- ${seleccione}</span></div>
		<div class="contentTablaVariasColumnas">
			<table>
				<tr>
					<th width="122" colspan="2" class="text_izquierda">${perfil}</th>
					<th width="223" class="text_centro" scope="col">${descripcion}</th>
				</tr>
			
				<tr>
			
					<Td colspan="3" class="special"></Td>
				</tr>
				<tbody>
					<c:forEach var="regs" items="${registros}">
					<tr class="odd2">
						<td width="20"><input name="radio" type="radio" class="Campos"
						id="radio" value=${regs.idPerfil}></input></td>
						<td width="100" class="text_izquierda">${regs.nombrePerfil}</td>
						<td width="140" class="text_izquierda">${regs.descripcionPerfil}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<div class="framePieContenedor">
		<div class="contentPieContenedor">
			<table>
				<tr>
					<td class="izq"><a href="javascript:document.modificar.submit()">${detalle}</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="paginador">
		<a href="a">&lt;&lt;10 anteriores</a> | <a href="#b">10 siguientes&gt;&gt;</a></div>
</div>
</form>
<form action="altaPerfilInit.do" method="post" name="altaPerfil" id="altaPerfil">
<div class="PiePag"><button name="btnAltaPerfil" id="btnAltaPerfil" onclick="">${nuevoPerfil}</button></div>
</form>
<jsp:include page="../myFooter.jsp" flush="true"/>
