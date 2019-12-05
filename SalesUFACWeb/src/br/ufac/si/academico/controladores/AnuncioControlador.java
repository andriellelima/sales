package br.ufac.si.academico.controladores;

import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import br.ufac.si.academico.entidades.*;
import br.ufac.si.academico.gerentes.*;

@ManagedBean(name="anuncioControlador")
@SessionScoped
public class AnuncioControlador {

	private AnuncioGerente ag;

	private Anuncio anuncio;
	private String chave = "";

	
	public AnuncioControlador() {
		ag = new AnuncioGerente();

	}

	public String incluir() {
		this.anuncio = new Anuncio();
		return "anuncioInclusao";
	}
	
	public String editar(Anuncio anuncio) {
		this.anuncio = anuncio;
		return "anuncioEdicao";
	}

	public String excluir(Anuncio anuncio) {
		this.anuncio = anuncio;
		return "anuncioExclusao";
	}
	
	
	public String adicionar() {
        FacesContext context = FacesContext.getCurrentInstance();
        Vendedor ven = (Vendedor) context.getExternalContext().getSessionMap().get("usuarioLogado");
        anuncio.setVendedor(ven);
        ag.adicionar(anuncio);
        return "anuncioGerenciamento";
    }
	
	public String atualizar() {
		ag.atualizar(anuncio);
		return "anuncioGerenciamento";
	}
	
	public String remover() {
		ag.remover(anuncio);
		return "anuncioGerenciamento";
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public List<Anuncio> getAnuncios() {
		return ag.recuperarTodosPorNomeContendo(chave);
	}

	public List<Anuncio> getProdutosPorStatus(){
        return ag.recuperarPorStatus();
    }
	
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}


}
