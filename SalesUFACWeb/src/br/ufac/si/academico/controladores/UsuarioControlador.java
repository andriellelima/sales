package br.ufac.si.academico.controladores;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.*;


import br.ufac.si.academico.entidades.*;
import br.ufac.si.academico.gerentes.*;

@ManagedBean(name="usuarioControlador")
@SessionScoped
public class UsuarioControlador implements Serializable{

	private UsuarioGerente ug;
	private ClienteGerente cg;
	private VendedorGerente vg;
	private LoginControlador lc;
	private Usuario usuario;
	private String chave = "";
	private static final Logger LOG = Logger.getLogger(Usuario.class.getName());

	
	public UsuarioControlador() {
		cg = new ClienteGerente();
		vg = new VendedorGerente();
		ug = new UsuarioGerente();
		usuario = new Usuario();

	}

	public String incluir() {
		this.usuario = new Usuario();
		System.out.println("USUARIO " + usuario);
		return "usuarioInclusao";
	}
	
	public String editar(Usuario usuario) {
		System.out.println("EDITANDO PERFIL");
		this.usuario = usuario;
		return "usuarioEdicao";
	}

	public String excluir(Usuario usuario) {
		this.usuario = usuario;
		return "usuarioExclusao";
	}
	
	public String adicionar() throws NoSuchAlgorithmException {
		if (usuario.getFuncao().equals("Cliente")) {
			
			Cliente cliente = new Cliente(usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getDataNascimento(), usuario.getSenha());
			ug.adicionar(cliente);
		}
		else
		{
			Vendedor vendedor = new Vendedor(usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getDataNascimento(), usuario.getSenha());
			ug.adicionar(vendedor);
		}
		return "login.xhtml?faces-redirect=true";
		//return "usuarioGerenciamento?faces-redirect=true";
	}
	
	public String atualizar() {
		System.out.println("USER = " + usuario);
		ug.atualizar(usuario);
		return "usuarioGerenciamento";
	}
	
	public String remover() {
		ug.remover(usuario);
//		lc.sair();
		return "login";
	}
	
	public String reativar() {
		ug.reativar(usuario);
		return "login";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuariosPorNome() {
		return ug.recuperarTodosPorNome();
	}

	public List<Usuario> getUsuariosPorNomeContendo() {
		return ug.recuperarTodosPorNomeContendo(chave);
	}
	public List<Usuario> getuser() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		return ug.user(usuario.getId());
	}
	
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	
	@Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.getUsuario().getEmail());
        return hash;
    }
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.getUsuario().getEmail(), other.login)) {
            return false;
        }
        return true;
    }
	
	@Override
    public String toString() {
        return this.getUsuario().getNome();
    }
	
	
	



}