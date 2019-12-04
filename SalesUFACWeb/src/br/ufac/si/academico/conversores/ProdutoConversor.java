package br.ufac.si.academico.conversores;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.convert.*;

import br.ufac.si.academico.controladores.ProdutoControlador;
import br.ufac.si.academico.entidades.*;
import br.ufac.si.academico.gerentes.*;

@FacesConverter(value="produtoConversor", forClass=Produto.class)
public class ProdutoConversor implements Converter {

//	ProdutoGerente cg = new ProdutoGerente();
	private ProdutoControlador cc;
	@Override
	public Object getAsObject(FacesContext context, 
			UIComponent component, String value) {
		if(value == null || value.isEmpty())
			return null;
		
		ELContext elContext = context.getELContext();
		ELResolver elResolver = elContext.getELResolver();
		
		cc = (ProdutoControlador)elResolver
				.getValue(elContext, null, 
						"produtoControlador");
		
		return cc.recuperar(Integer.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, 
			UIComponent component, Object value) {
		if(value == null || !(value instanceof Produto))
			return "";	
		return String.valueOf(((Produto)value).getId());
	}

}