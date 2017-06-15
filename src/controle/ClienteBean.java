package controle;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.FabricaConexao;
import dao.ClienteDAO;
import modelo.Cliente;

@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Cliente> listaClientes;
	private Cliente cliente;
	private ListDataModel<Cliente> clientes;
	
 	public ClienteBean() {
 		listaClientes = new ArrayList<>();
	}
 	
	public Cliente getCliente() {
		return cliente;
	}

	public void setPessoa(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaFuncionarios(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	public ListDataModel<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ListDataModel<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void prepararNovo() {
		try {
			
			this.cliente = new Cliente();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrarCliente() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ClienteDAO dao = new ClienteDAO(conexao);
			dao.SalvarCliente(this.cliente);
			
			this.listaClientes = dao.RetornarClientes();
			
			this.clientes = new ListDataModel<Cliente>(this.listaClientes);
			
			fabrica.fecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prepararEditar() {
		try {
			
			this.cliente = this.clientes.getRowData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarCliente() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ClienteDAO dao = new ClienteDAO(conexao);
			dao.AtualizarCliente(this.cliente);
			
			this.listaClientes = dao.RetornarClientes();
			
			this.clientes = new ListDataModel<Cliente>(this.listaClientes);
			
			fabrica.fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prepararExcluir() {
		try {
			
			this.cliente = this.clientes.getRowData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluirCliente() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			ClienteDAO dao = new ClienteDAO(conexao);
			dao.DeletarCliente(this.cliente.getId());
			
			this.listaClientes = dao.RetornarClientes();
			
			this.clientes = new ListDataModel<Cliente>(this.listaClientes);
			
			fabrica.fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	private void inicializar() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conn = fabrica.fazerConexao();
			
			ClienteDAO dao = new ClienteDAO(conn);
			this.listaClientes = dao.RetornarClientes();
			
			this.clientes = new ListDataModel<Cliente>(this.listaClientes);
			
			fabrica.fecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
