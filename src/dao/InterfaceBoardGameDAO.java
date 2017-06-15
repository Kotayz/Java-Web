package dao;

import java.sql.SQLException;
import java.util.List;
import modelo.BoardGame;

public interface InterfaceBoardGameDAO {	
	public void SalvarBoardGame(BoardGame _boardgame) throws SQLException;
	public void DeletarBoardGame(int _IdBoardGame) throws SQLException;
	public void AtualizarBoardGame(BoardGame _boardgame) throws SQLException;
	public List<BoardGame> RetornarBoardGames() throws SQLException;
	public BoardGame RetornarBoardGamePorId(int _IdBoardGame) throws SQLException;
}
