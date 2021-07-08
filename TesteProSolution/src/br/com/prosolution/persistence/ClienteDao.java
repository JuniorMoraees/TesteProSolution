package br.com.prosolution.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.prosolution.entity.Cliente;

public class ClienteDao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PUjpa");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	EntityManager manager;
	TypedQuery<Cliente> query;
	
	public void create(Cliente c) throws Exception {
		manager = getEntityManager();
		manager.clear();
		manager.getTransaction().begin();
		manager.persist(c);
		manager.getTransaction().commit();
	}
	
	public List<Cliente> read() {
		manager = getEntityManager();
		List<Cliente> lista = manager.createQuery("select obj from Cliente as obj", Cliente.class).getResultList();
		return lista;
	}
	
	public void update(Cliente c) throws Exception {
		manager = getEntityManager();
		manager.clear();
		manager.getTransaction().begin();
		manager.merge(c);
		manager.getTransaction().commit();
	}
	
	public void delete(Cliente c) throws Exception {
		manager = getEntityManager();
		manager.clear();
		manager.getTransaction().begin();
		Cliente resp = manager.find(Cliente.class, c.getId());
		manager.remove(resp);
		manager.getTransaction().commit();
	}
	
	public Cliente findById(Long id) {
		manager = getEntityManager();
		Cliente cliente = manager.find(Cliente.class, id);
		return cliente;
	}

}
