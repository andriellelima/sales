package br.ufac.si.academico.gerentes;

import java.util.*;

import javax.persistence.*;

import br.ufac.si.academico.entidades.Anuncio;
import br.ufac.si.academico.entidades.Cliente;
import br.ufac.si.academico.entidades.Produto;
import br.ufac.si.academico.entidades.Vendedor;

public class AnuncioGerente {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	public AnuncioGerente() {
		emf = Persistence.createEntityManagerFactory("SalesUFAC");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Anuncio anuncio) {
		
		em.getTransaction().begin();
		em.persist(anuncio);
		em.getTransaction().commit();
	}
	
	public Anuncio recuperar(int id) {
		return em.find(Anuncio.class, id);
	}
	
	public void atualizar(Anuncio anuncio) {
		em.getTransaction().begin();
		em.merge(anuncio);
		em.getTransaction().commit();
	}
	
	public void remover(Anuncio anuncio) {
		em.getTransaction().begin();
		em.remove(anuncio);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Anuncio> recuperarTodos(){
		return em.createNamedQuery("Anuncio.todos")
				.getResultList();	
	}
	@SuppressWarnings("unchecked")
	public List<Anuncio> recuperarTodosPorNome(){
		return em.createNamedQuery("Anuncio.todosPorNome")
				.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Anuncio> recuperarTodosPorNomeContendo(String termo){
		return em
				.createNamedQuery("Anuncio.todosPorNomeContendo")
				.setParameter("termo", "%"+termo+"%")
				.getResultList();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Anuncio> recuperarPorStatus(){
        return em.createNamedQuery("Anuncio.porstatus").getResultList();
    }
	
	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
