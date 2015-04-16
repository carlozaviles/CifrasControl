<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../myHeader.jsp" flush="true"/>
<jsp:include page="../myMenu.jsp" flush="true">
	<jsp:param name="menuItem"    value="cargasManuales" />
	<jsp:param name="menuSubItem"    value="monitorCargas" />	
</jsp:include>

<jsp:include page="../myFooter.jsp" flush="true"/>