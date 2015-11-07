
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="facturas" />
	<jsp:param name="menuSubItem"    value="notasCredito" />	
</jsp:include>
<script src="${pageContext.servletContext.contextPath}/recursos/js/facturas/validaFormularioFacturas.js" type="text/javascript"></script>

<spring:message code="facturas.moduloNotas"            		var="modulo"/>
<spring:message code="facturas.formularioNotas"              	var="formularioNotas"/>
<spring:message code="facturas.linkAceptar"             	var="linkAceptar"/>
<spring:message code="facturas.camposObligatorios"             	var="camposObligatorios"/>
<spring:message code="facturas.gralSinDatos"              	var="gralSinDatos"/>
<spring:message code="facturas.gralSinDatosRespuesta"           var="gralSinDatosRespuesta"/>
<spring:message code="facturas.gralModificarFiltros"            var="gralModificarFiltros"/>

<div class="pageTitleContainer">
   <span class="pageTitle">${modulo}</span>
</div>

<form action="consultaNotasCredito.do" name="formularioFacturas" id="formularioFacturas" method="post" > 
	
	<input type="hidden" name="sinDatos" id ="sinDatos" value="${sinDatos}">
	<input id="gralSinDatos" type="hidden" value="${gralSinDatos}"/>
	<input id="gralSinDatosRespuesta" type="hidden" value="${gralSinDatosRespuesta}"/>
	<input id="gralModificarFiltros" type="hidden" value="${gralModificarFiltros}"/>

<div class="frameFormularioB">
	<div class="contentFormularioB">
		<div class="titleFormularioB">${modulo} - <span class="textosin">${formularioNotas}</span></div>
		<jsp:include page="formularioBase.jsp" flush="true"/>
	</div>
</div>

<div class="PiePag"> <a href="#" id="aceptarFormulario">${linkAceptar}</a></div>
</form>
<jsp:include page="../myFooter.jsp" flush="true"/>
