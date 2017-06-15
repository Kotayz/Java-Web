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
import dao.BoardGameDAO;
import modelo.BoardGame;

@ManagedBean
@ViewScoped
public class BoardGameBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<BoardGame> listaBoardGames;
	private BoardGame boardGame;
	private ListDataModel<BoardGame> boardGames;
	
 	public BoardGameBean() {
		listaBoardGames = new ArrayList<>();
	}
 	
	public BoardGame getBoardGame() {
		return boardGame;
	}

	public void setBoardGame(BoardGame boardGame) {
		this.boardGame = boardGame;
	}

	public List<BoardGame> getListaBoardGames() {
		return listaBoardGames;
	}

	public void setListaBoardGames(List<BoardGame> listaBoardGames) {
		this.listaBoardGames = listaBoardGames;
	}
	
	public ListDataModel<BoardGame> getBoardGames() {
		return boardGames;
	}

	public void setBoardGames(ListDataModel<BoardGame> boardGames) {
		this.boardGames = boardGames;
	}

	public void prepararNovo() {
		try {
			
			this.boardGame = new BoardGame();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrarBoardGame() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			BoardGameDAO dao = new BoardGameDAO(conexao);
			dao.SalvarBoardGame(this.boardGame);
			
			this.listaBoardGames = dao.RetornarBoardGames();
			
			this.boardGames = new ListDataModel<BoardGame>(this.listaBoardGames);
			
			fabrica.fecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prepararEditar() {
		try {
			
			this.boardGame = this.boardGames.getRowData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarBoardGame() {
		try {
			
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			BoardGameDAO dao = new BoardGameDAO(conexao);
			dao.AtualizarBoardGame(this.boardGame);
			
			this.listaBoardGames = dao.RetornarBoardGames();
			
			this.boardGames = new ListDataModel<BoardGame>(this.listaBoardGames);
			
			fabrica.fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prepararExcluir() {
		try {
			
			this.boardGame = this.boardGames.getRowData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluirBoardGame() {
		try {
			FabricaConexao fabrica = new FabricaConexao();
			Connection conexao = fabrica.fazerConexao();
			
			BoardGameDAO dao = new BoardGameDAO(conexao);
			dao.DeletarBoardGame(this.boardGame.getId());
			
			this.listaBoardGames = dao.RetornarBoardGames();
			
			this.boardGames = new ListDataModel<BoardGame>(this.listaBoardGames);
			
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
			
			BoardGameDAO dao = new BoardGameDAO(conn);
			this.listaBoardGames = dao.RetornarBoardGames();
			
			this.boardGames = new ListDataModel<BoardGame>(this.listaBoardGames);
			
			fabrica.fecharConexao();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
