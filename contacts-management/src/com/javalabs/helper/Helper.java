package com.javalabs.helper;

import javax.servlet.http.HttpServletRequest;

import com.javalabs.services.PersonService;

public class Helper {

	private HttpServletRequest request;
	private ICommand coman = null;

	public Helper(HttpServletRequest request) {
		super();
		this.request = request;
	}
	
	/**
	 * Metodo que se utiliza para obtener una referencia de acuerdo a la accion a ejecutar.
	 * Este metodo puede ser implementado de varias maneras, otras condiciones.
	 * Por ejemplo mirar la url /persona, redirigir todo a PersonService, /contact redirigir a ContactService.
	 * Si no entiendes me lo comentas, el nombre del metodo es irrelevante, puedes cambiarlo a tu gusto
	 * ahora como es montada, es enviar algo estido, 
	 * */
	public ICommand getCommand(){
		String accion = request.getParameter("accion");
		String path =request.getRequestURI();
		if(path.contains("/person") ||path.contains("/person/")){
			return person();
		}else{
			
		}
		
		return null;
	}
	
	
	private ICommand person(){
		coman = new PersonService(request);
		return coman;
	}
}
