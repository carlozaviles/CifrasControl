/**
 * 
 */
package mx.isban.cifrascontrol.controller.principal;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.isban.agave.commons.architech.Architech;

/**
 * @author Administrador
 *
 */
@Controller
public class ControllerPublico extends Architech {
	
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2920201202143662767L;

	/**
     * Sesionexistente.
     * @paramreqthereq
     * @param res the res
     * @return the model and view
     * @throws Exception the exception
     */
	@RequestMapping("sesionExistente.go")
	public ModelAndView sesionExistente(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		this.debug("Enviar a sesionExistente...");
		String lstrUsuario = req.getParameter("user") == null ? "" :req.getParameter("user");
		this.info("Usuario :" + lstrUsuario);
	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user", lstrUsuario);
		return new ModelAndView("sesionExistente", map);
    }

    /**
     * Login.
     * @paramreq the req
     * @param res the res
     * @return the model and view
     * @throws Exception the exception*/
	@RequestMapping("login.go")
	public ModelAndView login(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		this.debug("Enviar a Login...");
        String lstrUsuario = req.getParameter("user") == null ? "" :req.getParameter("user");
        String lstrClave = req.getParameter("password") == null ? "" :req.getParameter("password");
        String lstrLenguaje = req.getParameter("siteLanguage") == null ? "" :req.getParameter("siteLanguage");

		this.info("Usuario :" + lstrUsuario);
		this.debug("Clave   :" + lstrClave);
		this.info("Lenguaje:" + lstrLenguaje);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user", lstrUsuario);
		map.put("password", lstrClave);
		map.put("siteLanguage", lstrLenguaje);
		return new ModelAndView("login", map);
    }

    /**
     * Resolve exception.
     * @param request the request
     * @param response the response
     * @param handler the handler
     * @param ex the ex
     * @return the model and view
     */
    @ExceptionHandler
	public ModelAndView resolveException(final HttpServletRequest request, final HttpServletResponse response,
		final Object handler, final Exception ex) {
		this.debug("Sucedio un error inesperado...");
		this.debug("Origen      :" + request.getRequestURL());
		this.debug("HandlerError:" + handler.getClass().getName());
		showException(ex);
        String lstrPaginaException = "errorPublico";
		this.debug("Paginadestino:" + lstrPaginaException);
		return new ModelAndView(lstrPaginaException);
    }

}
