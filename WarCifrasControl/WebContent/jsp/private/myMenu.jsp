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
	
	<spring:message code="monitoreo.menu.menuMonitoreo"  				var="menuMonitoreo"/>
	<spring:message code="monitoreo.menu.subMenuConsultaFiltros" 		var="subMenuConsultaFiltros"/>
	<spring:message code="monitoreo.menu.subMenuConsultaNotificaciones" var="subMenuConsultaNotificacion"/>
	<spring:message code="monitoreo.menu.subMenuConsultaSlaNCumplidos" 	var="subMenuConsultaSlaNCumplidos"/>
	<spring:message code="monitoreo.menu.subMenuTopTPromedios" 			var="subMenuTopTPromedios"/>
	<spring:message code="monitoreo.menu.subMenuConsultaSLAS" 			var="subMenuConsultaSLAS"/>
	<spring:message code="monitoreo.menu.subMenuTableroControl" 		var="subMenuTableroControl"/>
	<spring:message code="monitoreo.menu.subMenuDisponibilidad" 		var="subMenuDisponibilidad"/>
	<spring:message code="monitoreo.menu.subMenuConsultaAlarma" 		var="subMenuConsultaAlarma"/>
	<spring:message code="monitoreo.menu.subMenuConsTrackingOpers" 		var="subMenuConsTrackingOpers"/>
	<spring:message code="monitoreo.menu.submenuEstadisticasFlujo" 		var="submenuEstadisticasFlujo"/>
	<spring:message code="monitoreo.menu.submenuTiempoPromFlujo" 		var="submenuTiempoPromFlujo"/>
	<spring:message code="monitoreo.menu.submenuGestionFlujos" 			var="submenuGestionFlujos"/>
	<spring:message code="monitoreo.menu.submenuConsEjecProceso" 		var="submenuConsEjecProceso"/>
	<spring:message code="monitoreo.menu.submenuConsolidadoOper" 		var="submenuConsolidadoOper"/>
	<spring:message code="monitoreo.menu.submenuTopEstadisticas" 		var="submenuTopEstadisticas"/>
	<spring:message code="monitoreo.menu.submenuGestionAlias" 			var="submenuGestionAlias"/>
	<spring:message code="monitoreo.menu.submenuConsRendimiento" 		var="submenuConsRendimiento"/>
	<spring:message code="monitoreo.menu.submenuConfigEntorno" 			var="submenuConfigEntorno"/>
	<spring:message code="monitoreo.menu.submenuEntorno" 				var="submenuEntorno"/>

	<spring:message code="menu.menuPrincipalCargas" 					var="menuPrincipalCargas"/>
	<spring:message code="menu.submenuCargaInputs" 						var="submenuCargaInputs"/>
	<spring:message code="menu.submenuMonitorCargas" 					var="submenuMonitorCargas"/>
	<spring:message code="menu.menuPrincipalLogs" 						var="menuPrincipalLogs"/>
	<spring:message code="menu.submenuLogs" 							var="submenuLogs"/>
	<spring:message code="menu.submenuValidaciones" 					var="submenuValidaciones"/>
	<spring:message code="menu.menuPrincipalParametros" 				var="menuPrincipalParametros"/>
	<spring:message code="menu.submenuConsultaTablas" 					var="submenuConsultaTablas"/>
	<spring:message code="menu.submenuAltaTablas" 						var="submenuAltaTablas"/>
	<spring:message code="menu.menuPrincipalInterfacesFinales" 			var="menuPrincipalInterfacesFinales"/>
	<spring:message code="menu.submenuEdInterfacesFinales" 				var="submenuEdInterfacesFinales"/>
	<spring:message code="menu.submenuLanzadorMotor" 					var="submenuLanzadorMotor"/>
	<spring:message code="menu.menuPrincipalAprovisionamiento" 			var="menuPrincipalAprovisionamiento"/>
	<spring:message code="menu.submenuLanzadorAprovisionamiento" 		var="submenuLanzadorAprovisionamiento"/>
	<spring:message code="menu.menuPrincipalReproceso" 					var="menuPrincipalReproceso"/>
	<spring:message code="menu.submenuLanzadorReproceso" 				var="submenuLanzadorReproceso"/>
	<spring:message code="menu.menuPrincipalConsultas" 					var="menuPrincipalConsultas"/>
	<spring:message code="menu.submenuConsultaPorContrato" 				var="submenuConsultaPorContrato"/>
	<spring:message code="menu.submenuValidacionesMinimas" 				var="submenuValidacionesMinimas"/>
	
	<!-- <body onload="initialize('${param.menuItem}', '${param.menuSubitem}'); addMenuItem('eight','Mi opcion dinamica','','', 'true', 'true'); disabledMenuItem('three'); disabledMenuItem('fiveDotTwo');"> -->
<body onload="initialize('${param.menuItem}', '${param.menuSubitem}'); enabledMenuItems('${LyFBean.idsMenuPerfil}', '${LyFBean.tipoMenu}', '${LyFBean.tipoIdsMenu}'); estableceAyuda('${param.helpPage}')">
	<div id="top04">
		<c:if test="${LyFBean.menuHabilitado}">
			<div class="frameMenuContainer">
				<ul id="mainMenu">
					<!-- Menu Principal Cifras Control -->
					<li id="cargasManuales" class="withSubMenus startMenuGroup"><a href="javascript:selectMenuItem('cargasManuales')"><span>${menuPrincipalCargas}</span></a>
						<ul>
							<li id="cargaInsumos">      		<a href="">    &gt;<span class="subMenuText">${submenuCargaInputs}</span></a></li>
							<li id="monitorCargas">      		<a href="">    &gt;<span class="subMenuText">${submenuMonitorCargas}</span></a></li>
						</ul>
					</li> 
					<li id="descargaLogs" class="withSubMenus startMenuGroup"><a href="javascript:selectMenuItem('descargaLogs')"><span>${menuPrincipalLogs}</span></a>
						<ul>
							<li id="logs">      		<a href="">    &gt;<span class="subMenuText">${submenuLogs}</span></a></li>
							<li id="validaciones">      <a href="">    &gt;<span class="subMenuText">${submenuValidaciones}</span></a></li>
						</ul>
					</li>
					<li id="tablasParametros" class="withSubMenus startMenuGroup"><a href="javascript:selectMenuItem('tablasParametros')"><span>${menuPrincipalParametros}</span></a>
						<ul>
							<li id="consultaTablas">    <a href="">    &gt;<span class="subMenuText">${submenuConsultaTablas}</span></a></li>
							<li id="altaTablas">        <a href="">    &gt;<span class="subMenuText">${submenuAltaTablas}</span></a></li>
						</ul>
					</li>
					<li id="interfacesFinales" class="withSubMenus startMenuGroup"><a href="javascript:selectMenuItem('interfacesFinales')"><span>${menuPrincipalInterfacesFinales}</span></a>
						<ul>
							<li id="edicionInterfacesFinales">    <a href="">    &gt;<span class="subMenuText">${submenuEdInterfacesFinales}</span></a></li>
							<li id="lanzadorMotor">               <a href="">    &gt;<span class="subMenuText">${submenuLanzadorMotor}</span></a></li>
						</ul>
					</li>
					<li id="aprovisionamientoHistorico" class="withSubMenus startMenuGroup"><a href="javascript:selectMenuItem('aprovisionamientoHistorico')"><span>${menuPrincipalAprovisionamiento}</span></a>
						<ul>
							<li id="lanzadorAprovisionamiento">    <a href="">    &gt;<span class="subMenuText">${submenuLanzadorAprovisionamiento}</span></a></li>
						</ul>
					</li>
					<li id="reprocesoInputs" class="withSubMenus startMenuGroup"><a href="javascript:selectMenuItem('reprocesoInputs')"><span>${menuPrincipalReproceso}</span></a>
						<ul>
							<li id="lanzadorReproceso">    <a href="">    &gt;<span class="subMenuText">${submenuLanzadorReproceso}</span></a></li>
						</ul>
					</li>
					<li id="consultas" class="withSubMenus startMenuGroup"><a href="javascript:selectMenuItem('consultas')"><span>${menuPrincipalConsultas}</span></a>
						<ul>
							<li id="consultaPorContrato">    <a href="">    &gt;<span class="subMenuText">${submenuConsultaPorContrato}</span></a></li>
							<li id="validacionesMinimas">    <a href="">    &gt;<span class="subMenuText">${submenuValidacionesMinimas}</span></a></li>
						</ul>
					</li>
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
