package dao;

import java.sql.SQLException;

import modelo.Cliente;
import java.util.List;

public interface InterfaceClienteDAO {
	public void SalvarCliente(Cliente _cliente) throws SQLException;
    public void DeletarCliente(int _IdCliente) throws SQLException;
    public void AtualizarCliente(Cliente _cliente) throws SQLException;
    public List<Cliente> RetornarClientes() throws SQLException;
    public Cliente RetornarClientePorId(int _IdCliente) throws SQLException;
}
