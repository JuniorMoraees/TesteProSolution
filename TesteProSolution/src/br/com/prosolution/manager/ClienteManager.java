package br.com.prosolution.manager;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.prosolution.entity.Cliente;
import br.com.prosolution.persistence.ClienteDao;

@ManagedBean(name="mb")
@RequestScoped
public class ClienteManager implements Serializable{
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private List<Cliente> clientes;
	
	public ClienteManager() {
		this.cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		try {
			this.clientes = new ClienteDao().read();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public void gravar() {
		FacesContext fc = FacesContext.getCurrentInstance();
			try {
				new ClienteDao().create(this.cliente);
				this.cliente = new Cliente();
					fc.addMessage(null, new FacesMessage("Cliente Gravado"));
			} catch (Exception ex) {
				fc.addMessage(null, new FacesMessage("error: Verifique os dados digitados"));
			}
	}
	
	public List<Cliente> listar() {
		try {
			this.clientes = new ClienteDao().read();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return clientes;
	}
	
	public void alterar() {
		FacesContext fc = FacesContext.getCurrentInstance();
			try {
				new ClienteDao().update(this.cliente);
				this.cliente = new Cliente();
					fc.addMessage(null, new FacesMessage("Cliente Alterado"));
			}catch (Exception ex) {
				fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));
			}
	}

	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance();
			try {
				new ClienteDao().delete(this.cliente);
				this.cliente = new Cliente();
					fc.addMessage(null, new FacesMessage("Cliente Excluido"));
			}catch (Exception ex) {
				fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));
			}
	}
}

