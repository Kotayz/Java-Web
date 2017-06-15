package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Endereco;
import modelo.Pessoa;

public class EnderecoDAO implements InterfaceEnderecoDAO {

	private Connection conexao;
	
	public EnderecoDAO(Connection _conexao) {
		this.conexao = _conexao;
	}
	
	@Override
	public void inserir(Endereco _endereco) throws SQLException {
		
		String comando = "insert into endereco (estado, cidade, bairro, logradouro, cep, idPessoa) " 
		+ "values (?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setString(1, _endereco.getEstado());
		ps.setString(2, _endereco.getCidade());
		ps.setString(3, _endereco.getBairro());
		ps.setString(4, _endereco.getLogrodouro());
		ps.setString(5, _endereco.getCep());
		ps.setInt(6, _endereco.getPessoa().getId());

		ps.execute();

	}

	@Override
	public Boolean deletar(int _id) throws SQLException {
		
		String comando = "delete from endereco where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _id);
		
		return ps.execute();
	}

	@Override
	public Boolean atualizar(Endereco _endereco) throws SQLException {
		
		String comando = "update endereco set "
				+ "estado = ?, "
				+ "cidade = ?, "
				+ "bairro = ?, "
				+ "logradouro = ?, "
				+ "cep = ? "
				+ "where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setString(1, _endereco.getEstado());
		ps.setString(2, _endereco.getCidade());
		ps.setString(3, _endereco.getBairro());
		ps.setString(4, _endereco.getLogrodouro());
		ps.setString(5, _endereco.getCep());
		
		ps.setInt(6, _endereco.getId());

		return ps.execute();
	}

	@Override
	public Endereco buscarPorID(int _id) throws SQLException {
		
		Endereco e = null;
		
		String comando = "select * from endereco where id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _id);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			e = new Endereco();
			e.setId(rs.getInt(1));
			e.setEstado(rs.getString(2));
			e.setCidade(rs.getString(3));
			e.setBairro(rs.getString(4));
			e.setLogrodouro(rs.getString(5));
			e.setCep(rs.getString(6));
			
			//PessoaDAO daoPessoa = new PessoaDAO(this.conexao);//ALTERADO POR PROBLEMA DE LOPP INFINITO
			//Pessoa p = daoPessoa.buscarPorID(rs.getInt(7));//ALTERADO POR PROBLEMA DE LOPP INFINITO
			
			//e.setIdPessoa(rs.getInt(7));//ALTERADO POR PROBLEMA DE LOPP INFINITO
			
			PessoaDAO daoPessoa = new PessoaDAO(this.conexao);
			Pessoa p = daoPessoa.buscarPorID(rs.getInt(7));
			
			e.setPessoa(p);//ACRESCENTADO
		}
		return e;
	}

	@Override
	public List<Endereco> listarTodos() throws SQLException {
		
		List<Endereco> listaEnderecos = new ArrayList<Endereco>();
		
		String comando = "select * from endereco";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {

			Endereco e = new Endereco();
			e.setId(rs.getInt(1));
			e.setEstado(rs.getString(2));
			e.setCidade(rs.getString(3));
			e.setBairro(rs.getString(4));
			e.setLogrodouro(rs.getString(5));
			e.setCep(rs.getString(6));
			
			PessoaDAO daoPessoa = new PessoaDAO(this.conexao);
			Pessoa p = daoPessoa.buscarPorID(rs.getInt(7));
			
			e.setPessoa(p);//ACRESCENTADO
			
			listaEnderecos.add(e);
		}
		
		return listaEnderecos;
	}
	
	@Override
	public List<Endereco> buscarEnderecosDaPessoa(Pessoa _pessoa) throws SQLException {
		
		List<Endereco> listaEnderecos = new ArrayList<Endereco>();
		
		String comando = "select * from endereco where \"idPessoa\" = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _pessoa.getId());
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {

			Endereco e = new Endereco();
			e.setId(rs.getInt(1));
			e.setEstado(rs.getString(2));
			e.setCidade(rs.getString(3));
			e.setBairro(rs.getString(4));
			e.setLogrodouro(rs.getString(5));
			e.setCep(rs.getString(6));
			e.setPessoa(_pessoa);//ACRESCENTADO
			
			listaEnderecos.add(e);
		}
		
		return listaEnderecos;
	}

}
