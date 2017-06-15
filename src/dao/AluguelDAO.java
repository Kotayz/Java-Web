package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import modelo.Aluguel;
import modelo.BoardGame;
import modelo.Cliente;
import modelo.Endereco;
import modelo.Funcionario;

public class AluguelDAO implements InterfaceAluguelDAO {

	private Connection conexao;

	public AluguelDAO(Connection conexao) {		
		this.conexao = conexao;
	}

	@Override
	public void SalvarAluguel(Aluguel _aluguel) throws SQLException {
        try
        {
        	String SQL = "INSERT INTO ALUGUEL (IDFUNC, IDBOARDGAME, IDCLIENTE, TEMPOALUGUEL, STATUS) VALUES (?, ?, ?, ?, ?);";
        	
        	PreparedStatement ps = this.conexao.prepareStatement(SQL);
        	
    		ps.setInt(1, _aluguel.getIdFuncionario());  
    		ps.setInt(2, _aluguel.getIdBoardGame());
    		ps.setInt(3, _aluguel.getIdCliente());
    		ps.setString(4, _aluguel.getTempoaluguel());  
    		ps.setBoolean(5, _aluguel.getStatus());
    		
    		ps.execute();
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
		
	}

	@Override
	public void DeletarAluguel(int _IdAluguel) throws SQLException {
        try
        {
        	String SQL = "DELETE FROM ALUGUEL WHERE id = ?";
        	PreparedStatement ps = this.conexao.prepareStatement(SQL);
            ps.setInt(1, _IdAluguel);
            ps.execute();
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
	}

	@Override
	public void AtualizarAluguel(Aluguel _aluguel) throws SQLException {
        try
        {
        	String SQL = "UPDATE ALUGUEL SET IDFUNCIONARIO = ?, IDBOARDGAME = ?, IDCLIENTE = ?, TEMPOALUGUEL = ?, STATUS = ?";
        	
    		PreparedStatement ps = this.conexao.prepareStatement(SQL);
    		
    		ps.setInt(1, _aluguel.getIdFuncionario());
    		ps.setInt(2, _aluguel.getIdBoardGame());
    		ps.setInt(3, _aluguel.getIdCliente());
    		ps.setString(4, _aluguel.getTempoaluguel());  
    		ps.setBoolean(5, _aluguel.getStatus());
    		
    		ps.execute();
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
	}

	@Override
	public List<Aluguel> RetornarAluguel() throws SQLException {
        List<Aluguel> listaAluguel = new ArrayList<Aluguel>();

        try
        {
        	String SQL = "SELECT * FROM ALUGUEL";
        	
        	PreparedStatement ps = this.conexao.prepareStatement(SQL);
        	
        	ResultSet rs = ps.executeQuery();

            while (rs.next())
            {                
            	Aluguel aluguel = new Aluguel();

            	aluguel.setId(rs.getInt(1));
            	aluguel.setIdFuncionario(rs.getInt(2));
            	aluguel.setIdBoardGame(rs.getInt(3));
            	aluguel.setIdCliente(rs.getInt(4));
            	aluguel.setTempoaluguel(rs.getString(5));
            	aluguel.setStatus(rs.getBoolean(6));
    			
    			BoardGameDAO daoBoardGame = new BoardGameDAO(this.conexao);
    			BoardGame boardgame = daoBoardGame.RetornarBoardGamePorId(aluguel.getIdBoardGame());
    			
    			ClienteDAO daoCliente = new ClienteDAO(this.conexao);
    			Cliente cliente = daoCliente.RetornarClientePorId(aluguel.getIdCliente());
    			
    			FuncionarioDAO daoFuncionario = new FuncionarioDAO(this.conexao);
    			Funcionario funcionario = daoFuncionario.RetornarFuncionarioPorId(aluguel.getIdFuncionario());
    			
    			aluguel.setBoardgame(boardgame);
    			aluguel.setCliente(cliente);
    			aluguel.setFuncionario(funcionario);
    			
                listaAluguel.add(aluguel);
            }
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
        return listaAluguel;
	}
}
