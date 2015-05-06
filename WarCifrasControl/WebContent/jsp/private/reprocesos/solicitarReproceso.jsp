<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"   	 value="reprocesos" />
	<jsp:param name="menuSubItem"    value="solicitudReprocesos" />	
</jsp:include>


<script src="${pageContext.servletContext.contextPath}/recursos/js/reprocesos/validacionesReprocesos.js" type="text/javascript"></script>

<jsp:include page="consultarDatosPersonas.jsp" flush="true"/>


<jsp:include page="../myFooter.jsp" flush="true"/>
