package br.ufac.si.academico.gerentes;

import java.util.*;

import javax.persistence.*;

import br.ufac.si.academico.entidades.Cliente;
import br.ufac.si.academico.entidades.Venda;
import br.ufac.si.academico.entidades.Vendedor;

public class VendaGerente {

	private EntityManagerFactory emf;
	private EntityManager em;

	public VendaGerente() {
		emf = Persistence.createEntityManagerFactory("SalesUFAC");
		em = emf.createEntityManager();
	}

	public String adicionar(Venda venda) {

		try{
			if((venda.getProduto().getQuantidade()-venda.getQuantidade()) >= 0) {
				em.getTransaction().begin();
				venda.getCliente().setFidelidade((int)venda.getCliente().getFidelidade()+venda.getQuantidade());
				venda.getProduto().setQuantidade((int)(venda.getProduto().getQuantidade()-venda.getQuantidade()));
				em.merge(venda.getCliente());
				em.merge(venda.getProduto());
				em.persist(venda);
				em.getTransaction().commit();
				return "Certo";
			}else {
				return "Falha";
			}
			
		}catch(Exception e) {
			venda.getCliente().setFidelidade((int)venda.getCliente().getFidelidade()-venda.getQuantidade());
			venda.getProduto().setQuantidade((int)(venda.getProduto().getQuantidade()+venda.getQuantidade()));
			em.merge(venda.getCliente());
			em.merge(venda.getProduto());
		}
		return null;
	}


	public Venda recuperar(int id) {
		return em.find(Venda.class, id);
	}

	public void atualizar(Venda venda) {
		em.getTransaction().begin();
		em.merge(venda);
		em.getTransaction().commit();
	}

	public void remover(Venda venda) {
		em.getTransaction().begin();
		em.remove(venda);
		em.getTransaction().commit();
	}


	public void encerrar() {
		em.close();
		emf.close();
	}


	@SuppressWarnings("unchecked")
	public List<Venda> recuperarTodos(){
		return em.createNamedQuery("Venda.todos")
				.getResultList();	
	}
//	@SuppressWarnings("unchecked")
//	public List<Venda> recuperarTodosPorNome(){
//		return em.createNamedQuery("Venda.todosPorNome")
//				.getResultList();
//	}
	@SuppressWarnings("unchecked")
	public List<Venda> recuperarTodosid(Vendedor ven){
		return em
				.createNamedQuery("Venda.todosid")
				.setParameter("termo", ven)
				.getResultList();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Venda> recuperarTodosidcli(Cliente ven){
		return em
				.createNamedQuery("Venda.todosidcli")
				.setParameter("termo", ven)
				.getResultList();
	}	

}
