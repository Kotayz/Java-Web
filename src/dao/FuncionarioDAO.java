package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import modelo.Funcionario;

public class FuncionarioDAO implements InterfaceFuncionarioDAO{
	
	private Connection conexao;

	public FuncionarioDAO(Connection conexao) {		
		this.conexao = conexao;
	}

	@Override
	public void SalvarFuncionario(Funcionario _funcionario) throws SQLException {
        try
        {
        	String SQL = "INSERT INTO FUNCIONARIO (NOME, IDADE, CPF, RG, ENDERECO, NUMENDERECO, CEP, TELEFONE)" +  
        				 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        	
    		PreparedStatement ps = this.conexao.prepareStatement(SQL);

    		ps.setString(1, _funcionario.getNome());  
    		ps.setInt(2, _funcionario.getIdade());  
    		ps.setString(3, _funcionario.getCpf());  
    		ps.setString(4, _funcionario.getRg()); 
    		ps.setString(5, _funcionario.getEndereco()); 
    		ps.setInt(6, _funcionario.getNumendereco()); 
    		ps.setString(7, _funcionario.getCep()); 
    		ps.setString(8, _funcionario.getTelefone());
            
    		ps.execute();
        }            
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
	}

	@Override
	public void DeletarFuncionario(int _IdFuncionario) throws SQLException {
        try
        {
        	String SQL = "DELETE FROM FUNCIONARIO WHERE id = ?";
        	PreparedStatement ps = this.conexao.prepareStatement(SQL);
            ps.setInt(1, _IdFuncionario);
            ps.execute();
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
	}

	@Override
	public void AtualizarFuncionario(Funcionario _funcionario) throws SQLException {
        try
        {
        	String SQL = "UPDATE FUNCIONARIO SET NOME = ?, IDADE = ?, CPF = ?, RG = ?, ENDERECO = ?, NUMENDERECO = ?, CEP = ?, TELEFONE = ?";
            
    		PreparedStatement ps = this.conexao.prepareStatement(SQL);

    		ps.setString(1, _funcionario.getNome());  
    		ps.setInt(2, _funcionario.getIdade());  
    		ps.setString(3, _funcionario.getCpf());  
    		ps.setString(4, _funcionario.getRg()); 
    		ps.setString(5, _funcionario.getEndereco()); 
    		ps.setInt(6, _funcionario.getNumendereco()); 
    		ps.setString(7, _funcionario.getCep()); 
    		ps.setString(8, _funcionario.getTelefone());
            
    		ps.execute();
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
	}

	@Override
	public List<Funcionario> RetornarFuncionarios() throws SQLException {
        List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();

        try
        {
        	String SQL = "SELECT * FROM FUNCIONARIO";
        	
        	PreparedStatement ps = this.conexao.prepareStatement(SQL);
        	
        	ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt(1));
                funcionario.setNome(rs.getString(2));
                funcionario.setIdade(rs.getInt(3));
                funcionario.setCpf(rs.getString(4));
                funcionario.setRg(rs.getString(5));
                funcionario.setEndereco(rs.getString(6));
                funcionario.setNumendereco(rs.getInt(7));
                funcionario.setCep(rs.getString(8));
                funcionario.setTelefone(rs.getString(9));

                listaFuncionarios.add(funcionario);
            }
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
        return listaFuncionarios;
	}	
	
	@Override
	public Funcionario RetornarFuncionarioPorId(int _IdFuncionario) throws SQLException {
		
		Funcionario funcionario = new Funcionario();
		
        try
        {
        	String SQL = "SELECT * FROM FUNCIONARIO WHERE ID = ?";
        	
        	PreparedStatement ps = this.conexao.prepareStatement(SQL);
        	
        	ps.setInt(1, _IdFuncionario);
        	
        	ResultSet rs = ps.executeQuery();

        	if (rs.next())
            {
                funcionario.setId(rs.getInt(1));
                funcionario.setNome(rs.getString(2));
                funcionario.setIdade(rs.getInt(3));
                funcionario.setCpf(rs.getString(4));
                funcionario.setRg(rs.getString(5));
                funcionario.setEndereco(rs.getString(6));
                funcionario.setNumendereco(rs.getInt(7));
                funcionario.setCep(rs.getString(8));
                funcionario.setTelefone(rs.getString(9));
            }
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
        return funcionario;
	}	
}
