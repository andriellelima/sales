package br.ufac.si.academico.controladores;

import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import br.ufac.si.academico.entidades.*;
import br.ufac.si.academico.gerentes.*;

@ManagedBean(name="produtoControlador")
@SessionScoped
public class ProdutoControlador {

	private ProdutoGerente pg;
	private LoginControlador lc;
	private VendedorGerente vg;
	private Produto produto;
	private String chave = "";

	
	public ProdutoControlador() {
		pg = new ProdutoGerente();
		lc = new LoginControlador();
		vg = new VendedorGerente();
		
	}

	public String incluir() {
		this.produto = new Produto();
		return "produtoInclusao";
	}
	
	public String editar(Produto produto) {
		this.produto = produto;
		return "produtoEdicao";
	}

	public String excluir(Produto produto) {
		this.produto = produto;
		return "produtoExclusao";
	}
	
	public String adicionar() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Vendedor ven = (Vendedor) context.getExternalContext().getSessionMap().get("usuarioLogado");
		produto.setVendedor(ven);
		pg.adicionar(produto);	
		return "produtoGerenciamento";
	}
	public String canc() {
		return "index.xhtml?faces-redirect=true";
	}
	
	public String atualizar() {
		pg.atualizar(produto);
		return "produtoGerenciamento";
	}
	
	public String atualizarcli(Produto produto) {
		pg.atualizar(produto);
		return "anuncioCliente";
	}
	
	public String remover() {
		pg.remover(produto);
		return "produtoGerenciamento";
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Produto recuperar(long codigo) {
		return pg.recuperar(codigo);
	}


	public List<Produto> getProdutosPorNomeContendo() {
		return pg.recuperarTodosPorNomeContendo(chave);
	}
	
	public List<Produto> getTodosPorVendedor() {
		return pg.recuperarTodosPorVendedor();
	}
	
	public List<Produto> getProdutosPorId(){
		FacesContext context = FacesContext.getCurrentInstance();
		Vendedor vendedor = (Vendedor) context.getExternalContext().getSessionMap().get("usuarioLogado");
        return pg.recuperarPorId(vendedor);
    }
	
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	public void setvend() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuarioLogado;
		Vendedor ven = (Vendedor) context.getExternalContext().getSessionMap().get("usuarioLogado");
//		Vendedor vv = (Vendedor) lc.getUsuariologado();
		produto.setVendedor(ven);
	}



}
