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
import dao.AluguelDAO;
import modelo.Aluguel;

@ManagedBean
@ViewScoped
public class AluguelBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Aluguel> listaAlugueis;
	private Aluguel aluguel;
	private ListDataModel<Aluguel> alugueis;
	
 	public AluguelBean() {
		listaAlugueis = new ArrayList<>();
	}
 	
	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public List<Aluguel> getListaBoardGames() {
		return listaAlugueis;
	}

	public void setListaBoardGames(List<Aluguel> listaAlugueis) {
		this.listaAlugueis = listaAlugueis;
	}
	
	public ListDataModel<Aluguel> getBoardGames() {
		return alugueis;
	}

	public void setBoardGames(ListDataModel<Aluguel> boardGames) {
		this.alugueis = boardGames;
	}

	public void prepararNovo() {
		try {
			
			this.aluguel = new Aluguel();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrarAluguel() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			AluguelDAO dao = new AluguelDAO(conexao);
			dao.SalvarAluguel(this.aluguel);
			
			this.listaAlugueis = dao.RetornarAluguel();
			
			this.alugueis = new ListDataModel<Aluguel>(this.listaAlugueis);
			
			fabrica.fecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prepararEditar() {
		try {
			
			this.aluguel = this.alugueis.getRowData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarAluguel() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			AluguelDAO dao = new AluguelDAO(conexao);
			dao.AtualizarAluguel(this.aluguel);
			
			this.listaAlugueis = dao.RetornarAluguel();
			
			this.alugueis = new ListDataModel<Aluguel>(this.listaAlugueis);
			
			fabrica.fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prepararExcluir() {
		try {
			
			this.aluguel = this.alugueis.getRowData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluirAluguel() {
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			AluguelDAO dao = new AluguelDAO(conexao);
			dao.DeletarAluguel(this.aluguel.getId());
			
			this.listaAlugueis = dao.RetornarAluguel();
			
			this.alugueis = new ListDataModel<Aluguel>(this.listaAlugueis);
			
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
			
			AluguelDAO dao = new AluguelDAO(conn);
			this.listaAlugueis = dao.RetornarAluguel();
			
			this.alugueis = new ListDataModel<Aluguel>(this.listaAlugueis);
			
			fabrica.fecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
