
package mx.isban.cifrascontrol.controller.principal;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.LookAndFeel;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.agave.commons.utils.LogUtil;
import mx.isban.agave.logging.Level;
import mx.isban.cifrascontrol.beans.administracion.modulo.BeanModulo;
import mx.isban.cifrascontrol.servicio.administracion.modulo.BOModulo;
import mx.isban.cifrascontrol.servicio.administracion.usuario.BOUsuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerPrincipal extends Architech{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Referencia al servicio de Modulos.
	 */
	private BOModulo boModulo;
	/**
	 * Referencia al servicio de Usuarios.
	 */
	private BOUsuario boUsuarios;

	/**
	 * Metodo que sirve para inicializar el LookAndFeel, el Perfilamiento
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("auto.do")
	public ModelAndView auto(HttpServletRequest req, HttpServletResponse res)throws Exception{

		String url = "inicio";
		
		HttpSession lobjSession = req.getSession();
		LookAndFeel lobjLyFBean = (LookAndFeel) lobjSession.getAttribute("LyFBean");

		if (lobjLyFBean == null) {
			lobjLyFBean = new LookAndFeel();
			lobjLyFBean.setLookAndFeel("default");
        }
		
		this.setNameComponent("ControllerPrincipal");
		this.setLoggingBean(LogUtil.getLoggingBean("message", "cmpName", this.getClass()));

		lobjSession.setAttribute("LyFBean", lobjLyFBean);
	
		String txtUser =req.getHeader("iv-user")==null ? "EXPC" :req.getHeader("iv-user").toString();
		String cadenaGrupos = req.getHeader("iv-groups");
		
		this.info("Usuario por header: " + txtUser);
		this.info("Los grupos del usuario son: " + cadenaGrupos);
		
		String tokensGrupos[] = cadenaGrupos.split(",");
		for(int i = 0; i < tokensGrupos.length; i++){
			tokensGrupos[i] = tokensGrupos[i].replaceAll("\"", "");
		}
		
		boUsuarios.validaUsuario(this.getArchitechBean(), txtUser, tokensGrupos);
		txtUser = txtUser.startsWith("Y")?txtUser.substring(1):txtUser;
		List<BeanModulo> modulos = boModulo.obtenerModulosPorUsuarioLogueado(getArchitechBean(), txtUser);
		
		lobjSession.setAttribute("modulosPermitidos", modulos);
		this.setNameComponent("ControllerPrincipal");
		this.setLoggingBean(LogUtil.getLoggingBean("message", "cmpName", this.getClass()));
		
		return new ModelAndView(url);
	}
	
	@RequestMapping("salir.do")
	public ModelAndView salir(final HttpServletRequest request, final HttpServletResponse respones) throws Exception {
		this.debug("saliendo de la aplicacion");
		LookAndFeel lyFBean = (LookAndFeel)request.getSession().getAttribute("LyFBean");
		//ArchitechSessionBean objArchitectBean = (ArchitechSessionBean)request.getSession().getAttribute("ArchitectSession");
		String salirUrl = lyFBean.getLinkSalirSAM();
		request.getSession().invalidate();
		this.debug("Enviando a hacerlogut a SAM[" + salirUrl + "]...");
		return new ModelAndView("redirect:" + salirUrl);
	}
	
	@ExceptionHandler
	public ModelAndView resolveException(final HttpServletRequest request, final HttpServletResponse response,
		final Object handler, final Exception ex) {
	
		LookAndFeel lobjLyFBean = (LookAndFeel) request.getSession().getAttribute("LyFBean");
	
		this.debug("°°Sucedio un error inesperado...");
		this.debug("°°Origen      :" + request.getRequestURL());
		this.debug("°°HandlerError:" + handler.getClass().getName());
		showException((Exception)handler, Level.ERROR);
	
        String lstrPaginaException = "";
        String lstrContextPath = "";
		HashMap<String, String> lhsmParametros = new HashMap<String, String>();
	
		lhsmParametros.put("paginaError", request.getRequestURL().toString());
		if (lobjLyFBean != null) {
			if (handler instanceof BusinessException) {
		        // Pagina de error a mostrar cuando ocirre un error en los BO
				lstrPaginaException = lobjLyFBean.getPaginaExceptionArq();
				lhsmParametros.put("codeError", ((BusinessException) handler).getCode());
			} else {
                // Pagina de error a mostrar cuando ocirre un error en los
                // Controllers
				lstrPaginaException = lobjLyFBean.getPaginaExceptionGral();
            }
        }
		lstrContextPath = request.getContextPath();
		this.debug("ContextPath   :" + lstrContextPath);
		this.debug("Paginadestino:" + lstrPaginaException);
		this.debug("Se dirige a   :" + lstrContextPath + lstrPaginaException);
		this.debug("Redirect      :" + "redirect:/" + lstrPaginaException);
	
		return new ModelAndView("redirect:" + lstrPaginaException, lhsmParametros);
	}

	public BOModulo getBoModulo() {
		return boModulo;
	}

	public void setBoModulo(BOModulo boModulo) {
		this.boModulo = boModulo;
	}

	/**
	 * @return the boUsuario
	 */
	public BOUsuario getBoUsuarios() {
		return boUsuarios;
	}

	/**
	 * @param boUsuario the boUsuario to set
	 */
	public void setBoUsuarios(BOUsuario boUsuario) {
		this.boUsuarios = boUsuario;
	}
	
	
}