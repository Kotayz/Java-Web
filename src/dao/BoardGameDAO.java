package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import modelo.BoardGame;
import modelo.Funcionario;

public class BoardGameDAO implements InterfaceBoardGameDAO {

	private Connection conexao;

	public BoardGameDAO(Connection conexao) {		
		this.conexao = conexao;
	}

	@Override
	public void SalvarBoardGame(BoardGame _boardgame) throws SQLException {
        try
        {
        	String SQL = "INSERT INTO boardgames (NOME, FABRICANTE, GENERO, DURACAOPARTIDA, QTDJOGADORES, Quantidade) VALUES (?, ?, ?, ?, ?, ?)";
        	
        	PreparedStatement ps = this.conexao.prepareStatement(SQL);
        	
    		ps.setString(1, _boardgame.getNome());  
    		ps.setString(2, _boardgame.getFabricante());  
    		ps.setString(3, _boardgame.getGenero());  
    		ps.setString(4, _boardgame.getDuracaoPartida()); 
    		ps.setInt(5, _boardgame.getQtdJogadores()); 
    		ps.setInt(6, _boardgame.getQuantidade());
    		
    		ps.execute();
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
	}

	@Override
	public void DeletarBoardGame(int _IdBoardGame) throws SQLException {
        try
        {
        	String SQL = "DELETE FROM boardgames WHERE id = ?";
        	PreparedStatement ps = this.conexao.prepareStatement(SQL);
            ps.setInt(1, _IdBoardGame);
            ps.execute();
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
	}

	@Override
	public void AtualizarBoardGame(BoardGame _boardgame) throws SQLException {
        try
        {
        	String SQL = "UPDATE boardgames SET NOME = ?, FABRICANTE = ?, GENERO = ?, DURACAOPARTIDA = ?, QTDJOGADORES = ?, Quantidade = ?";
        	
    		PreparedStatement ps = this.conexao.prepareStatement(SQL);
    		
    		ps.setString(1, _boardgame.getNome());  
    		ps.setString(2, _boardgame.getFabricante());  
    		ps.setString(3, _boardgame.getGenero());  
    		ps.setString(4, _boardgame.getDuracaoPartida()); 
    		ps.setInt(5, _boardgame.getQtdJogadores()); 
    		ps.setInt(6, _boardgame.getQuantidade());
    		
    		ps.execute();
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
	}

	@Override
	public List<BoardGame> RetornarBoardGames() throws SQLException {
        List<BoardGame> listaBoardGames = new ArrayList<BoardGame>();

        try
        {
        	String SQL = "SELECT * FROM boardgames";
        	
        	PreparedStatement ps = this.conexao.prepareStatement(SQL);
        	
        	ResultSet rs = ps.executeQuery();

            while (rs.next())
            {                
                BoardGame boardgame = new BoardGame();

                boardgame.setId(rs.getInt(1));
                boardgame.setNome(rs.getString(2));
                boardgame.setFabricante(rs.getString(3));
                boardgame.setGenero(rs.getString(4));
                boardgame.setDuracaoPartida(rs.getString(5));
                boardgame.setQtdJogadores(rs.getInt(6));
                boardgame.setQuantidade(rs.getInt(7));

                listaBoardGames.add(boardgame);
            }
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
        return listaBoardGames;
	}
	
	@Override
	public BoardGame RetornarBoardGamePorId(int _IdBoardGame) throws SQLException {
		
		BoardGame boardgame = new BoardGame();
		
        try
        {
        	String SQL = "SELECT * FROM boardgames WHERE id = ?";
        	
        	PreparedStatement ps = this.conexao.prepareStatement(SQL);
        	
        	ps.setInt(1, _IdBoardGame);
        	
        	ResultSet rs = ps.executeQuery();

        	if (rs.next())
            {
                boardgame.setId(rs.getInt(1));
                boardgame.setNome(rs.getString(2));
                boardgame.setFabricante(rs.getString(3));
                boardgame.setGenero(rs.getString(4));
                boardgame.setDuracaoPartida(rs.getString(5));
                boardgame.setQtdJogadores(rs.getInt(6));
                boardgame.setQuantidade(rs.getInt(7));
            }
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
        return boardgame;
	}
}
