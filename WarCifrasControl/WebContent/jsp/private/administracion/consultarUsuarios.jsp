<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="administracion" />
	<jsp:param name="menuSubItem"    value="usuarios" />	
</jsp:include>


<spring:message code="administracion.modulo"            var="modulo"/>
<spring:message code="administracion.usuarios"   	var="usuarios"/>
<spring:message code="administracion.perfil"         	var="perfil"/>
<spring:message code="administracion.claveUsuario"    	var="claveUsuario"/>
<spring:message code="administracion.nombre"     	var="nombre"/>
<spring:message code="administracion.correo"     	var="correo"/>
<spring:message code="administracion.perfilActivo"     	var="perfilActivo"/>
<spring:message code="administracion.seleccioneUsuario"	var="seleccioneUsuario"/>
<spring:message code="administracion.detalle"		var="detalle"/>
<spring:message code="administracion.nuevoUsuario"	var="nuevoUsuario"/>

<form action="modificarUsuarioInit.do" name="modificar" id="modificar" method="post">
<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span> - ${usuarios}
</div>

<div class="frameTablaEstandar">
	<div class="titleTablaEstandar">
		${usuarios} <span class="textosin">- ${seleccioneUsuario}</span>
	</div>
<div class="contentTablaEstandar">
		<table>
			<thead>
				<tr>
					<th colspan="2" class="text_izquierda">${claveUsuario}</th>
					<th width="147" class="text_centro">${nombre}</th>
					<th width="147" class="text_centro">${correo}</th>
					<th width="147" class="text_centro">${perfil}</th>
					<th width="50" 	class="text_derecha">${perfilActivo}</th>
				</tr>
			</thead>
			<tr>
				<td colspan="5" class="special"></td>
			</tr>
			<tbody>
				<tr class="odd">
					<td width="20"><input name="radio" type="radio" class="Campos"
						id="radio15" value="radio" /></td>
					<td width="128" class="text_izquierda">30-06-2000</td>
					<td class="text_centro">30/06/2000</td>
					<td class="text_derecha">+2.000,00</td>
					<td class="text_derecha">+2.000,00</td>
					<td class="text_derecha"><input type="checkbox" name="usuarioActivo1" value="activo"></td>
				</tr>
				<tr class="odd1">
					<td><input name="radio" type="radio" class="Campos" id="radio16"
						value="radio" /></td>
					<td class="text_izquierda">30-06-2000</td>
					<td class="text_centro">30/06/2000</td>
					<td class="text_derecha">+2.000,00</td>
					<td class="text_derecha">+2.000,00</td>
					<td class="text_derecha"><input type="checkbox" name="usuarioActivo2" value="activo"></td>
				</tr>
				<tr class="odd2">
					<td><input name="radio" type="radio" class="Campos" id="radio17"
						value="radio" /></td>
					<td class="text_izquierda">30-06-2000</td>
					<td class="text_centro">30/06/2000</td>
					<td class="text_derecha">+2.000,00</td>
					<td class="text_derecha">+2.000,00</td>
					<td class="text_derecha"><input type="checkbox" name="usuarioActivo3" value="activo"></td>
				</tr>
				<tr class="odd1">
					<td><input name="radio" type="radio" class="Campos" id="radio18"
						value="radio" /></td>
					<td class="text_izquierda">30-06-2000</td>
					<td class="text_centro">30/06/2000</td>
					<td class="text_derecha">+2.000,00</td>
					<td class="text_derecha">+2.000,00</td>
					<td class="text_derecha"><input type="checkbox" name="usuarioActivo4" value="activo"></td>
				</tr>
				<tr class="odd2">
					<td><input name="radio" type="radio" class="Campos" id="radio19"
						value="radio" /></td>
					<td class="text_izquierda">30-06-2000</td>
					<td class="text_centro">30/06/2000</td>
					<td class="text_derecha">+2.000,00</td>
					<td class="text_derecha">+2.000,00</td>
					<td class="text_derecha"><input type="checkbox" name="usuarioActivo5" value="activo"></td>
				</tr>
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
<form action="altaUsuarioInit.do" method="post" name="altaPerfil" id="altaPerfil">
<div class="PiePag"><button name="btnAltaUsuario" id="btnAltaUsuario" onclick="">${nuevoUsuario}</button></div>
</form>
<jsp:include page="../myFooter.jsp" flush="true"/>
