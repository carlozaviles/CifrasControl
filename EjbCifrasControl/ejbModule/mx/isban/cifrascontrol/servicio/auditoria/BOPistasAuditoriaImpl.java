package mx.isban.cifrascontrol.servicio.auditoria;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.interfaces.BeanResultBO;
import mx.isban.cifrascontrol.bean.auditoria.BeanPistaAuditoria;
import mx.isban.cifrascontrol.dao.auditoria.DAOPistasAuditoria;

/**
 * Session Bean implementation class PistasAuditoria
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BOPistasAuditoriaImpl extends Architech implements BOPistasAuditoria{

    /**
	 * Serial.
	 */
	private static final long serialVersionUID = 1287562724333198335L;
	@EJB
	private DAOPistasAuditoria daoPistas;

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.auditoria.PistasAuditoria#generaPistaAuditoria(mx.isban.cifrascontrol.bean.auditoria.BeanPistaAuditoria, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public void generaPistaAuditoria(BeanPistaAuditoria beanAuditoria, ArchitechSessionBean sessionBean) {
		this.info("Se genera pista de auditoria para el usuario: " + sessionBean.getUsuario());
		final Date fechaActual = new Date();
		final SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss:SSS");
		final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		beanAuditoria.setHora(formatoHora.format(fechaActual));
		beanAuditoria.setFecha(formatoFecha.format(fechaActual));
		beanAuditoria.setIpTerminalCliente(sessionBean.getIPCliente());
		beanAuditoria.setUsuarioApp(sessionBean.getUsuario());
		beanAuditoria.setAplicacion(sessionBean.getNombreAplicacion());
		beanAuditoria.setIdSesion(sessionBean.getIdSesion());
		beanAuditoria.setNombreServidorWeb(sessionBean.getNombreServidor());
		beanAuditoria.setIpServidorWeb(sessionBean.getIPServidor());
		final BeanResultBO resultadoInsercion = daoPistas.guardaPistaAuditoria(beanAuditoria, sessionBean);
		if(resultadoInsercion != null && DAOPistasAuditoria.CODIGO_OPERACION_OK.equals(resultadoInsercion.getCodError())){
			this.info("La pista de auditoria fua guardada de manera correcta.");
		}else{
			this.warn("Ocurrio un error al guardar la pista de auditoria. El codigo de error es: " + resultadoInsercion.getCodError());
		}
	}
}
