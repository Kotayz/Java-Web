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
import modelo.Funcionario;

public class ClienteDAO implements InterfaceClienteDAO  {
	
	private Connection conexao;

	public ClienteDAO(Connection conexao) {		
		this.conexao = conexao;
	}

	@Override
	public void SalvarCliente(Cliente _cliente) throws SQLException {
        try
        {
        	String SQL = "INSERT INTO CLIENTE (NOME, CPF, TELEFONE)" +  
        				 "VALUES (?, ?, ?)";
        	
    		PreparedStatement ps = this.conexao.prepareStatement(SQL);

    		ps.setString(1, _cliente.getNome());    		
    		ps.setString(2, _cliente.getCpf()); 
    		ps.setString(3, _cliente.getTelefone());
            
    		ps.execute();
        }            
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
	}

	@Override
	public void DeletarCliente(int _IdCliente) throws SQLException {
        try
        {
        	String SQL = "DELETE FROM CLIENTE WHERE id = ?";
        	PreparedStatement ps = this.conexao.prepareStatement(SQL);
            ps.setInt(1, _IdCliente);
            ps.execute();
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
	}

	@Override
	public void AtualizarCliente(Cliente _cliente) throws SQLException {
        try
        {
        	String SQL = "UPDATE CLIENTE SET NOME = ?, CPF = ?, TELEFONE = ?";
            
    		PreparedStatement ps = this.conexao.prepareStatement(SQL);

    		ps.setString(1, _cliente.getNome());  
    		ps.setString(2, _cliente.getCpf());
    		ps.setString(3, _cliente.getTelefone());
            
    		ps.execute();
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
	}

	@Override
	public List<Cliente> RetornarClientes() throws SQLException {
		
        List<Cliente> listaClientes = new ArrayList<Cliente>();

        try
        {
        	String SQL = "SELECT * FROM CLIENTE";
        	
        	PreparedStatement ps = this.conexao.prepareStatement(SQL);
        	
        	ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setCpf(rs.getString(3));
                cliente.setTelefone(rs.getString(4));

                listaClientes.add(cliente);
            }
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
        return listaClientes;
	}
	
	@Override
	public Cliente RetornarClientePorId(int _IdCliente) throws SQLException {
        
        Cliente cliente = new Cliente();
        
        try
        {
        	String SQL = "SELECT * FROM CLIENTE WHERE ID = ?";
        	
        	PreparedStatement ps = this.conexao.prepareStatement(SQL);
        	
        	ps.setInt(1, _IdCliente);
        	
        	ResultSet rs = ps.executeQuery();

        	if (rs.next())
            {
                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setCpf(rs.getString(3));
                cliente.setTelefone(rs.getString(4));
            }
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
        return cliente;
	}
}
