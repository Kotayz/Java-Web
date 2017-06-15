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
import dao.PessoaDAO;
import modelo.Pessoa;

@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Pessoa> listaPessoas;
	private Pessoa pessoa;
	private ListDataModel<Pessoa> pessoas;
	
 	public PessoaBean() {
		listaPessoas = new ArrayList<>();
	}
 	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
	
	public ListDataModel<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(ListDataModel<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public void prepararNovo() {
		try {
			
			this.pessoa = new Pessoa();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrarPessoa() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			this.pessoa.setDtCadastro(new Date());//Gera DATA Automaticamente e depois será Convertida
			
			PessoaDAO dao = new PessoaDAO(conexao);
			dao.inserir(this.pessoa);
			
			this.listaPessoas = dao.listarTodos();
			
			this.pessoas = new ListDataModel<Pessoa>(this.listaPessoas);
			
			fabrica.fecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prepararEditar() {
		try {
			
			this.pessoa = this.pessoas.getRowData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarPessoa() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			PessoaDAO dao = new PessoaDAO(conexao);
			dao.atualizar(this.pessoa);
			
			this.listaPessoas = dao.listarTodos();
			
			this.pessoas = new ListDataModel<Pessoa>(this.listaPessoas);
			
			fabrica.fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prepararExcluir() {
		try {
			
			this.pessoa = this.pessoas.getRowData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluirPessoa() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			PessoaDAO dao = new PessoaDAO(conexao);
			dao.deletar(this.pessoa);
			
			this.listaPessoas = dao.listarTodos();
			
			this.pessoas = new ListDataModel<Pessoa>(this.listaPessoas);
			
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
			
			PessoaDAO dao = new PessoaDAO(conn);
			this.listaPessoas = dao.listarTodos();
			
			this.pessoas = new ListDataModel<Pessoa>(this.listaPessoas);
			
			fabrica.fecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
