package br.ufac.si.academico.conversores;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.convert.*;

import br.ufac.si.academico.controladores.ClienteControlador;
import br.ufac.si.academico.entidades.*;
import br.ufac.si.academico.gerentes.*;

@FacesConverter(value="clienteConversor", forClass=Cliente.class)
public class ClienteConversor implements Converter {

//	ClienteGerente cg = new ClienteGerente();
	private ClienteControlador cc;
	@Override
	public Object getAsObject(FacesContext context, 
			UIComponent component, String value) {
		if(value == null || value.isEmpty())
			return null;
		
		ELContext elContext = context.getELContext();
		ELResolver elResolver = elContext.getELResolver();
		
		cc = (ClienteControlador)elResolver
				.getValue(elContext, null, 
						"clienteControlador");
		
		return cc.recuperar(Integer.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, 
			UIComponent component, Object value) {
		if(value == null || !(value instanceof Cliente))
			return "";	
		return String.valueOf(((Cliente)value).getId());
	}

}