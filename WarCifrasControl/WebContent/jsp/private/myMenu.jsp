	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<%@ page import="mx.isban.agave.configuracion.Configuracion"%>

	<script src="${pageContext.servletContext.contextPath}/lf/${LyFBean.lookAndFeel}/js/global.js"           type="text/javascript"></script>
	<script src="${pageContext.servletContext.contextPath}/lf/${LyFBean.lookAndFeel}/js/menu/dynamicMenu.js" type="text/javascript"></script>
	<link href="${pageContext.servletContext.contextPath}/lf/${LyFBean.lookAndFeel}/css/menu/estilos.css"            rel="stylesheet" type="text/css">
	<link href="${pageContext.servletContext.contextPath}/lf/${LyFBean.lookAndFeel}/css/menu/elementos_interfaz.css" rel="stylesheet" type="text/css">

	<spring:message code="menu.menuItemOne"    						var="principal"/>
	
	<spring:message code="horarios.menuHorarios"  					var="menuHorarios"/>
	<spring:message code="horarios.subMenuAdminHorarios" 			var="subMenuAdminHorarios"/>
	<spring:message code="horarios.subMenuConsulHorarios"			var="subMenuConsulHorarios"/>
	
	<spring:message code="sesiones.menuSesiones"  					var="menuSesiones"/>
	<spring:message code="sesiones.subMenuAdminSesiones" 			var="subMenuAdminSesiones"/>

	<spring:message code="menu.menuConfig"    						var="menuConfig"/>
	<spring:message code="menu.submenuConfig_ConsTemplates"  		var="submenuConfig_ConsTemplates"/>
	<spring:message code="menu.submenuConfig_ConsAplicacion"		var="submenuConfig_ConsAplicacion"/>
	<spring:message code="menu.submenuConfig_ConsHost"  			var="submenuConfig_ConsHost"/>
	<spring:message code="menu.submenuConfig_AsigHost"  			var="submenuConfig_AsigHost"/>
	<spring:message code="menu.submenuConfig_AsigTemplates"  		var="submenuConfig_AsigTemplates"/>
	<spring:message code="menu.submenuConfig_ModifParams"  			var="submenuConfig_ModifParams"/>
	<spring:message code="menu.submenuConfig_ConsAmbitos"  			var="submenuConfig_ConsAmbitos"/>
	
	<spring:message code="menu.menuLogging"    						var="menuLogging"/>
	<spring:message code="menu.subMenuLogging_nivelTrace"  			var="subMenuLogging_nivelTrace"/>
	<spring:message code="menu.subMenuLogging_activacionFiltro"		var="subMenuLogging_activacionFiltro"/>
	<spring:message code="menu.subMenuLogging_consultaLogs"			var="subMenuLogging_consultaLogs"/>
	
	<spring:message code="menu.subMenuModAyudas"  		   			var="subMenuModAyudas"/>
	<spring:message code="menu.subMenuModAyudasAppSer"     			var="subMenuModAyudasAppSer"/>
	<spring:message code="menu.subMenuModAyudasImgDoc"   			var="subMenuModAyudasImgDoc"/>
	<spring:message code="menu.subMenuModAyudasAyuTem"   			var="subMenuModAyudasAyuTem"/>
	
	<spring:message code="menu.menuItemAdmonCmpSMSEmail" 			var="cmpSMSEmail"/>
	<spring:message code="menu.ItemConsultaAplicacion" 	 			var="consultaAplicacion"/>
	
	<spring:message code="menu.menuPrincipal" 						var="menuPrincipal"/>
	<spring:message code="menu.menuPrincipalReprocesos" 					var="menuPrincipalReprocesos"/>
	<spring:message code="menu.submenuReprocesosSolicitud" 					var="submenuReprocesosSolicitud"/>
	<spring:message code="menu.submenuReprocesosConsulta" 					var="submenuReprocesosConsulta"/>

	<spring:message code="menu.menuPrincipalCifras" 					var="menuPrincipalCifras"/>
	<spring:message code="menu.submenuCifrasConsultaCifras" 				var="submenuCifrasConsultaCifras"/>

	<spring:message code="menu.menuPrincipalFacturas" 					var="menuPrincipalFacturas"/>
	<spring:message code="menu.submenuFacturas" 						var="submenuFacturas"/>
	<spring:message code="menu.submenuNotasCredito" 					var="submenuNotasCredito"/>
	<spring:message code="menu.submenuDivisas" 						var="submenuDivisas"/>
	<spring:message code="menu.submenuRecibosDeducibles" 					var="submenuRecibosDeducibles"/>
	
	
	<spring:message code="menu.menuPrincipalAdministracion" 				var="menuPrincipalAdministracion"/>
	<spring:message code="menu.submenuGrupo" 						var="submenuGrupo"/>
	<spring:message code="menu.submenuUsuarios" 						var="submenuUsuarios"/>
	<spring:message code="menu.submenuPantallas" 						var="submenuPantallas"/>
	<spring:message code="menu.submenuModulo" 						var="submenuModulo"/>




	<!-- <body onload="initialize('${param.menuItem}', '${param.menuSubitem}'); addMenuItem('eight','Mi opcion dinamica','','', 'true', 'true'); disabledMenuItem('three'); disabledMenuItem('fiveDotTwo');"> -->
<body onload="initialize('${param.menuItem}', '${param.menuSubitem}'); enabledMenuItems('${LyFBean.idsMenuPerfil}', '${LyFBean.tipoMenu}', '${LyFBean.tipoIdsMenu}'); estableceAyuda('${param.helpPage}')">
	<div id="top04">
		<c:if test="${LyFBean.menuHabilitado}">
			<div class="frameMenuContainer">
				<ul id="mainMenu">
					<!-- Menu Principal Cifras Control -->
					<li id="principal" class="startMenuGroup"> <a href="javascript:selectMenuItem('principal')"><span>${menuPrincipal}</span></a></li>

					<c:forEach var="modulo" items="${sessionScope.modulosPermitidos}">
						<c:if test = "${modulo.nombreModulo == 'REPROCESOS'}">
					<li id="reprocesos" class="withSubMenus startMenuGroup"><a href="javascript:selectMenuItem('reprocesos')"><span>${menuPrincipalReprocesos}</span></a>
						<ul>
							<c:forEach var="pantalla" items="${modulo.pantallas}">
								<c:if test = "${pantalla.nombrePantalla == 'SOLICITAR REPROCESO'}">
							<li id="solicitudReprocesos">      <a href="../reprocesos/consultaPersonas.do">    &gt;<span class="subMenuText">${submenuReprocesosSolicitud}</span></a></li>
								</c:if>
								<c:if test = "${pantalla.nombrePantalla == 'CONSULTA DE REPROCESOS'}">							
							<li id="consultaReprocesos">      	<a href="../reprocesos/consultaReprocesos.do">    &gt;<span class="subMenuText">${submenuReprocesosConsulta}</span></a></li>
								</c:if>							
							</c:forEach>						
						</ul>
					</li>	
						</c:if>

					<c:if test = "${modulo.nombreModulo == 'CIFRAS CONTROL'}">
					<li id="cifras" class="withSubMenus startMenuGroup"><a href="javascript:selectMenuItem('cifras')"><span>${menuPrincipalCifras}</span></a>
						<ul>
							<c:forEach var="pantalla" items="${modulo.pantallas}">
								<c:if test = "${pantalla.nombrePantalla == 'CONSULTAR CIFRAS DE CONTROL'}">
							<li id="consultaCifras">      		<a href="../cifrascontrol/cifrasInit.do">    &gt;<span class="subMenuText">${submenuCifrasConsultaCifras}</span></a></li>
								</c:if>
							</c:forEach>						
						</ul>
					</li>	
					</c:if>

					<c:if test = "${modulo.nombreModulo == 'FACTURAS'}">
					<li id="facturas" class="withSubMenus startMenuGroup"><a href="javascript:selectMenuItem('facturas')"><span>${menuPrincipalFacturas}</span></a>
						<ul>
							<c:forEach var="pantalla" items="${modulo.pantallas}">
								<c:if test = "${pantalla.nombrePantalla == 'FACTURAS'}">
							<li id="factura">      		<a href="../facturas/facturasInit.do">    &gt;<span class="subMenuText">${submenuFacturas}</span></a></li>
								</c:if>
								<c:if test = "${pantalla.nombrePantalla == 'NOTAS DE CREDITO'}">							
							<li id="notasCredito">      	<a href="../facturas/notasCreditoInit.do">    &gt;<span class="subMenuText">${submenuNotasCredito}</span></a></li>
								</c:if>	
								<c:if test = "${pantalla.nombrePantalla == 'DIVISAS'}">							
							<li id="divisas">      	<a href="../facturas/divisasInit.do">    &gt;<span class="subMenuText">${submenuDivisas}</span></a></li>
								</c:if>	
								<c:if test = "${pantalla.nombrePantalla == 'RECIBOS DEDUCIBLES'}">							
							<li id="recibosDeducibles">      	<a href="../facturas/recibosInit.do">    &gt;<span class="subMenuText">${submenuRecibosDeducibles}</span></a></li>
								</c:if>							
							</c:forEach>						
						</ul>
					</li>	
					</c:if>
					
					<c:if test = "${modulo.nombreModulo == 'ADMINISTRACION'}">
					<li id="administracion" class="withSubMenus startMenuGroup"><a href="javascript:selectMenuItem('administracion')"><span>${menuPrincipalAdministracion}</span></a>
						<ul>
							<c:forEach var="pantalla" items="${modulo.pantallas}">
								<c:if test = "${pantalla.nombrePantalla == 'USUARIOS'}">
							<li id="usuarios">      	<a href="../administracion/consultarUsuarios.do">    &gt;<span class="subMenuText">${submenuUsuarios}</span></a></li>
								</c:if>	
								<c:if test = "${pantalla.nombrePantalla == 'GRUPOS'}">
							<li id="grupos">      		<a href="../administracion/consultarGrupos.do">    &gt;<span class="subMenuText">${submenuGrupo}</span></a></li>
								</c:if>	
								<c:if test = "${pantalla.nombrePantalla == 'PANTALLAS'}">							
							<li id="pantallas">      	<a href="../administracion/consultarPantallas.do">    &gt;<span class="subMenuText">${submenuPantallas}</span></a></li>
								</c:if>	
								<c:if test = "${pantalla.nombrePantalla == 'MODULOS'}">							
							<li id="modulos" name="modulos">      	<a href="../administracion/consultarModulos.do">    &gt;<span class="subMenuText">${submenuModulo}</span></a></li>
								</c:if>							
							</c:forEach>
						</ul>

					</li> 											
					</c:if>
 				</c:forEach>
					<!-- 
					<li id="isbandataaccess" class="withSubMenus startMenuGroup"><a href="javascript:selectMenuItem('isbandataaccess')"><span>${isbandataaccess}</span></a>
						<ul>
							<li id="canalCICS">     <a href="../isbandataaccess/canalCICS.do">&gt;<span class="subMenuText">${canalCICS}</span></a></li>
							<li id="canalDataBase"> <a href="../isbandataaccess/canalDataBase.do">&gt;<span class="subMenuText">${canalDataBase}</span></a></li>
							<li id="canalTuxedo">   <a href="../isbandataaccess/canalTuxedo.do">&gt;<span class="subMenuText">${canalTuxedo}</span></a></li>
							<li id="canalGenericMQ"><a href="../isbandataaccess/canalGenericMQ.do">&gt;<span class="subMenuText">${canalGenericMQ}</span></a></li>
						</ul>
					</li>
					<li id="plantillas"  class="withSubMenus startMenuGroup"><a href="javascript:selectMenuItem('plantillas')"><span>${plantillas}</span></a>
						<ul>
							<li id="listaComponentes"><a href="../templetes/listaComponentes.do">&gt;<span class="subMenuText">${listaComponentes}</span></a></li>
							<li id="plantilla1">      <a href="../templetes/plantilla1.do">      &gt;<span class="subMenuText">${plantilla1}</span></a></li>
							<li id="plantilla2">      <a href="../templetes/plantilla2.do">      &gt;<span class="subMenuText">${plantilla2}</span></a></li>
							<li id="plantilla3">      <a href="../templetes/plantilla3.do">      &gt;<span class="subMenuText">${plantilla3}</span></a></li>
							<li id="plantilla4">      <a href="../templetes/plantilla4.do">      &gt;<span class="subMenuText">${plantilla4}</span></a></li>
						</ul>
					</li>
					 -->
				</ul>
			
				<div id="menuFooter">
					<div></div>
				</div>
	
			</div>
		</c:if>
	</div>
<!-- </div>  -->
<!-- </body> -->
<!-- </html> -->
<div id="content_container">
