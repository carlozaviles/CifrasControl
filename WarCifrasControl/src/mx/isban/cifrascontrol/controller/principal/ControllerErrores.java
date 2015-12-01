package mx.isban.cifrascontrol.controller.principal;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.isban.agave.commons.architech.Architech;

@Controller
public class ControllerErrores extends Architech{
	
	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 960423900408489008L;

	/**
	 * Manejador de error de la capa de negocio.
	 * @param req Request
	 * @param res Response
	 * @return ModelAndView
	 */
	@RequestMapping("errorAgave.do")
	public ModelAndView errorEBE(final HttpServletRequest req, final HttpServletResponse res) {
		final String nombreParametro = "codeError";
		Map<String, String> modelo = new HashMap<String, String>();
		modelo.put(nombreParametro, req.getParameter(nombreParametro));
		return new ModelAndView("excepcionInesperadaArq", modelo);
	}
	
	/**
	 * Manejador de error general
	 * @param req Request
	 * @param res Response
	 * @return ModelAndView
	 */
	@RequestMapping("errorGrl.do")
	public ModelAndView errorGrl(final HttpServletRequest req, final HttpServletResponse res) {
		return new ModelAndView("excepcionInesperadaGrl");
	}
}