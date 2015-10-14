package mx.isban.cifrascontrol.dao.auditoria;

import javax.ejb.Local;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.interfaces.BeanResultBO;
import mx.isban.cifrascontrol.bean.auditoria.BeanPistaAuditoria;

@Local
public interface DAOPistasAuditoria {
	
	/**
	 * Codigo que indica que una operacion fue realizada con exito.
	 */
	public static final String CODIGO_OPERACION_OK = "0";

	/**
	 * Guarda una pista de auditoria en BD.
	 * @param pistaAuditoria Contiene la informacion que sera guardada en BD.
	 * @param sessionBean Objeto de la arquitectura agave.
	 * @return BeanResultBO
	 */
	public BeanResultBO guardaPistaAuditoria(BeanPistaAuditoria pistaAuditoria, ArchitechSessionBean sessionBean);
}
