package br.ufac.si.academico.controladores;

import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import br.ufac.si.academico.entidades.*;
import br.ufac.si.academico.gerentes.*;

@ManagedBean(name="vendaControlador")
@SessionScoped
public class VendaControlador {

	private VendaGerente vg;

	private Venda venda;
	private String chave = "";

	
	public VendaControlador() {
		vg = new VendaGerente();

	}

	public String incluir() {
		this.venda = new Venda();
		return "vendaInclusao";
	}
	
	public String editar(Venda venda) {
		this.venda = venda;
		return "vendaEdicao";
	}

	public String excluir(Venda venda) {
		this.venda = venda;
		return "vendaExclusao";
	}
	
	public String adicionar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuarioLogado;
		Vendedor ven = (Vendedor) context.getExternalContext().getSessionMap().get("usuarioLogado");
		venda.setVendedor(ven);
		String A = vg.adicionar(venda);
		if(A.equalsIgnoreCase("certo")) {
			return "vendaGerenciamento";			
		}else {
			return "Erro";
		}
	}
	
	public String atualizar() {
		vg.atualizar(venda);
		return "vendaGerenciamento";
	}
	
	public String remover() {
		vg.remover(venda);
		return "vendaGerenciamento";
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
//
	public List<Venda> getVendas() {
		FacesContext context = FacesContext.getCurrentInstance();
		Vendedor vendedor = (Vendedor) context.getExternalContext().getSessionMap().get("usuarioLogado");
		return vg.recuperarTodosid(vendedor);
	}
	
	public List<Venda> getVendascli() {
		FacesContext context = FacesContext.getCurrentInstance();
		Cliente vendedor = (Cliente) context.getExternalContext().getSessionMap().get("usuarioLogado");
		return vg.recuperarTodosidcli(vendedor);
	}
	

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	
	public List<Venda> gettodos(){
		return vg.recuperarTodos();
	}


}
