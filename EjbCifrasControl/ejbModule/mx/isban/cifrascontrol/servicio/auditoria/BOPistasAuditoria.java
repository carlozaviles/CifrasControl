/**
 * 
 */
package mx.isban.cifrascontrol.servicio.auditoria;

import javax.ejb.Local;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.cifrascontrol.bean.auditoria.BeanPistaAuditoria;

/**
 * @author everis
 *
 */
@Local
public interface BOPistasAuditoria {

	/**
	 * Se encarga de complementar y guardar un objeto de tipo Pista de Auditoria.
	 * @param beanAuditoria Contiene las propiedades de las pistas de auditoria.
	 * @param sessionBean Objeto de la arquitectura agave.
	 */
	public void generaPistaAuditoria(BeanPistaAuditoria beanAuditoria, ArchitechSessionBean sessionBean);
}
