package br.ufac.si.academico.entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;
import javax.print.attribute.DateTimeSyntax;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="anuncios")

@NamedQueries({
	@NamedQuery(name="Anuncio.todos", 
		query="SELECT a FROM Anuncio a"), 
	@NamedQuery(name="Anuncio.todosPorNome", 
		query="SELECT a FROM Anuncio a ORDER BY a.nome"),
	@NamedQuery(name="Anuncio.todosPorNomeContendo", 
		query="SELECT a FROM Anuncio a WHERE a.nome LIKE :termo ORDER BY a.nome"),
	@NamedQuery(name="Anuncio.porstatus",
    query="SELECT a FROM Anuncio a, Usuario u  WHERE a.vendedor = u.id AND u.status = true")
})
public class Anuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // gerador de ID automatico
	private int id;
	@ManyToOne
	@JoinColumn(name="vendedor")
	private Vendedor vendedor;
	@ManyToOne
	@JoinColumn(name="anuncio")
	private Produto produtos;
//	@Column(nullable=false)
//	private long quantProdutoDisp;
	@Column(nullable=false)
	private String nome;
	@Column(nullable=false)
	private String local;
	@Column(nullable=false)
	private String dtinicio;
	@Column(nullable=false)
	private String dtfim;
	@Column(nullable=false)
	private String horario;
	private long quantProdutoDisp;
	
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Anuncio() {}
	
	public Anuncio(String nome,Vendedor vendedor,Produto produto , long quantProdutoDisp, String dtinicio, String dtfim, String local, String horario)  {
		this.vendedor = vendedor;
		this.produtos = produto;
		this.quantProdutoDisp = quantProdutoDisp;
		this.dtinicio = dtinicio;
		this.dtfim = dtfim;
		this.local = local;
		this.horario = horario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public Produto getProdutos() {
		return produtos;
	}
	public void setProdutos(Produto produtos) {
		this.produtos = produtos;
	}
	public long getQuantProdutoDisp() {
		return quantProdutoDisp;
	}
	public void setQuantProdutoDisp(long quantProdutoDisp) {
		this.quantProdutoDisp = quantProdutoDisp;
	}
	public String getDtinicio() {
		return dtinicio;
	}
	public void setDtinicio(String dtinicio) {
		this.dtinicio = dtinicio;
	}
	public String getDtfim() {
		return dtfim;
	}
	public void setDtfim(String dtfim) {
		this.dtfim = dtfim;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	

	public String getHorario() {
		return horario;
	}

	public void setHorario(String hora) {
		this.horario = hora;
	}

	@Override
	public String toString() {
		return "Anuncio [id=" + id + ", vendedor=" + vendedor.getNome() + ", produtos=" + produtos + ", quantProdutoDisp="
				+ quantProdutoDisp + ", local=" + local + ", dtinicio=" + dtinicio + ", dtfim=" + dtfim + ", hora="
				+ horario + "]";
	}
	
//	public void setadicionarProduto(Produto produto) {
//        this.produtos.add(produto);
//
//    }
//
//    public void setremoverProduto(Produto produto) {
//        this.produtos.remove(produto);
//    }
	
	
}
