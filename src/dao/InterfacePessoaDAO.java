package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Pessoa;

public interface InterfacePessoaDAO {

	public void inserir(Pessoa _pessoa) throws SQLException;
	public Boolean deletar(Pessoa _pessoa) throws SQLException;
	public Boolean atualizar(Pessoa _pessoa) throws SQLException;
	public Pessoa buscarPorID(int _id) throws SQLException;
	public List<Pessoa> listarTodos() throws SQLException;
}
