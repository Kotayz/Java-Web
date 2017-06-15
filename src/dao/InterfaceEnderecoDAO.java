package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Endereco;
import modelo.Pessoa;

public interface InterfaceEnderecoDAO {

	public void inserir(Endereco _endereco) throws SQLException;
	public Boolean deletar(int _id) throws SQLException;
	public Boolean atualizar(Endereco _endereco) throws SQLException;
	public Endereco buscarPorID(int _id) throws SQLException;
	public List<Endereco> listarTodos() throws SQLException;
	List<Endereco> buscarEnderecosDaPessoa(Pessoa _pessoa) throws SQLException;
}
