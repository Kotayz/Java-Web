package dao;

import java.sql.SQLException;
import modelo.Funcionario;
import java.util.List;

public interface InterfaceFuncionarioDAO {
	public void SalvarFuncionario(Funcionario _funcionario) throws SQLException;
    public void DeletarFuncionario(int _IdFuncionario) throws SQLException;
    public void AtualizarFuncionario(Funcionario _funcionario) throws SQLException;
    public List<Funcionario> RetornarFuncionarios() throws SQLException;
    public Funcionario RetornarFuncionarioPorId(int _IdFuncionario) throws SQLException;
}
