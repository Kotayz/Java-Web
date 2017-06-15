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
import dao.FuncionarioDAO;
import modelo.Funcionario;

@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Funcionario> listaFuncionarios;
	private Funcionario funcionario;
	private ListDataModel<Funcionario> funcionarios;
	
 	public FuncionarioBean() {
		listaFuncionarios = new ArrayList<>();
	}
 	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}
	
	public ListDataModel<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(ListDataModel<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public void prepararNovo() {
		try {
			
			this.funcionario = new Funcionario();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrarFuncionario() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			FuncionarioDAO dao = new FuncionarioDAO(conexao);
			dao.SalvarFuncionario(this.funcionario);
			
			this.listaFuncionarios = dao.RetornarFuncionarios();
			
			this.funcionarios = new ListDataModel<Funcionario>(this.listaFuncionarios);
			
			fabrica.fecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prepararEditar() {
		try {
			
			this.funcionario = this.funcionarios.getRowData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarFuncionario() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			FuncionarioDAO dao = new FuncionarioDAO(conexao);
			dao.AtualizarFuncionario(this.funcionario);
			
			this.listaFuncionarios = dao.RetornarFuncionarios();
			
			this.funcionarios = new ListDataModel<Funcionario>(this.listaFuncionarios);
			
			fabrica.fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prepararExcluir() {
		try {
			
			this.funcionario = this.funcionarios.getRowData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluirFuncionario() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			FuncionarioDAO dao = new FuncionarioDAO(conexao);
			dao.DeletarFuncionario(this.funcionario.getId());
			
			this.listaFuncionarios = dao.RetornarFuncionarios();
			
			this.funcionarios = new ListDataModel<Funcionario>(this.listaFuncionarios);
			
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
			
			FuncionarioDAO dao = new FuncionarioDAO(conn);
			this.listaFuncionarios = dao.RetornarFuncionarios();
			
			this.funcionarios = new ListDataModel<Funcionario>(this.listaFuncionarios);
			
			fabrica.fecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
