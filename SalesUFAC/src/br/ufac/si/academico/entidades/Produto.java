package br.ufac.si.academico.entidades;

import javax.persistence.*;

@Entity
@Table(name="produtos")
@NamedQueries({
	@NamedQuery(name="Produto.todos", 
		query="SELECT p FROM Produto p"), 
	@NamedQuery(name="Produto.todosPorNome", 
		query="SELECT p FROM Produto p ORDER BY p.nome"),
	@NamedQuery(name="Produto.todosPorNomeContendo", 
		query="SELECT p FROM Produto p WHERE p.nome LIKE :termo ORDER BY p.nome"),
//	@NamedQuery(name="Produto.todosPorVendedor", 
//	query="SELECT p.vendedor FROM Produto p  WHERE :termo = p.vendedor.id "),
	@NamedQuery(name="Produto.ProdutoId",
    query="SELECT p FROM Produto p WHERE p.vendedor = :termo ORDER BY p.id")
})

public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // gerador de ID automatico
	private long Id;
	@Column(nullable=false)
	private long quantidade;
	@Column(nullable=false, length=20)
	private String nome;
	@Column(nullable=false, length=120)
	private String descricao;
	@Column(nullable=false)
	private double avaliacao;
	@Column(nullable=false)
	private double valor;
	@ManyToOne
	@JoinColumn(name="vendedor")
	private Vendedor vendedor;
	private int quantaval; //quantas avalições ja teve
//	private double Avaliacao;
	
	public Produto() {}
	
	public Produto(long quantidade, String nome, String descricao, double valor , Vendedor vendedor) {
		this.quantidade = quantidade;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
//		this.avaliacao = avaliacao;
		this.vendedor = vendedor;
	}
	
	
	public int getQuantaval() {
		return quantaval;
	}
	public void setQuantaval(int quantaval) {
		this.quantaval = quantaval;
	}
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getAvaliacao() {
		return avaliacao/getQuantaval();
	}
	public void setAvaliacao(double avaliacao) {
		
		if(avaliacao>10) {
			avaliacao = 10;
		}
		setQuantaval(getQuantaval()+1);
		this.avaliacao = avaliacao+getAvaliacao();
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	

	
	public void setVendedor(Vendedor vendedor) {
		
		this.vendedor = vendedor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
		
	@Override
	public String toString() {
		return "Produto [Id=" + this.Id + ", quantidade=" + this.quantidade + ", nome=" + this.nome + ", descricao=" + this.descricao
				+ ", avaliacao=" + this.avaliacao + ", valor=" + this.valor + ", vendedor=" + this.vendedor + ", quantaval="
				+ this.quantaval + "]";
	}
	
	
	

}
