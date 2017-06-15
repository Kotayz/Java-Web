package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Endereco;
import modelo.Pessoa;

public class PessoaDAO implements InterfacePessoaDAO {

	private Connection conexao;
	
	public PessoaDAO(Connection _conexao) {
		this.conexao = _conexao;
	}
	
	@Override
	public void inserir(Pessoa _pessoa) throws SQLException {

		String comando = "insert into pessoa (nome, tel, \"dtCadastro\") " + "values (?, ?, ?)";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		//ps.setInt(1, _pessoa.getId());//Configurado para auto-incremento
		ps.setString(1, _pessoa.getNome());
		ps.setString(2, _pessoa.getTel());
		ps.setDate(3, new Date(_pessoa.getDtCadastro().getTime()));

		ps.execute();
		
		for (Endereco item : _pessoa.getEnderecos()) {
			
			EnderecoDAO daoEndereco = new EnderecoDAO(this.conexao);
			daoEndereco.inserir(item);		
		}

	}

	@Override
	public Boolean deletar(Pessoa _pessoa) throws SQLException {
		
		String comando = "delete from pessoa where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _pessoa.getId());
		
		EnderecoDAO daoEndereco = new EnderecoDAO(this.conexao);
		List<Endereco> listaEnderecos = daoEndereco.buscarEnderecosDaPessoa(_pessoa);
		
		for (int i = 0; i < listaEnderecos.size(); i++) {
			
			Endereco end = listaEnderecos.get(i);
			daoEndereco.deletar(end.getId());		
		}
		
//		for (Endereco endereco : listaEnderecos) {
//			daoEndereco.deletar(endereco.getId());
//		}
		
		return ps.execute();
	}

	@Override
	public Boolean atualizar(Pessoa _pessoa) throws SQLException {
		
		String comando = "update pessoa set nome = ?, tel = ? where id = ?";

		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setString(1, _pessoa.getNome());
		ps.setString(2, _pessoa.getTel());
		ps.setInt(3, _pessoa.getId());
		
		for (Endereco item : _pessoa.getEnderecos()) {
			
			EnderecoDAO daoEndereco = new EnderecoDAO(this.conexao);
			daoEndereco.atualizar(item);		
		}	

		return ps.execute();
	}

	@Override
	public Pessoa buscarPorID(int _id) throws SQLException {

		Pessoa p = null;
		
		String comando = "select * from pessoa where id = ?";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ps.setInt(1, _id);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			p = new Pessoa();
			p.setId(rs.getInt(1));
			p.setNome(rs.getString(2));
			p.setTel(rs.getString(3));
			p.setDtCadastro(rs.getDate(4));
			
			EnderecoDAO daoEndereco = new EnderecoDAO(this.conexao);
			List<Endereco> enderecos = daoEndereco.buscarEnderecosDaPessoa(p);
			
			p.setEnderecos(enderecos);
		}
		
		return p;
	}

	@Override
	public List<Pessoa> listarTodos() throws SQLException {
		
		List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		
		String comando = "select * from pessoa";
		
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Pessoa p = new Pessoa();
			p.setId(rs.getInt(1));
			p.setNome(rs.getString(2));
			p.setTel(rs.getString(3));
			p.setDtCadastro(rs.getDate(4));
			
			//int id = rs.getInt(1);
			//String nome = rs.getString(2);
			//String tel = rs.getString(3);
			//Date data = rs.getDate(4);
			
			EnderecoDAO daoEndereco = new EnderecoDAO(this.conexao);
			List<Endereco> enderecos = daoEndereco.buscarEnderecosDaPessoa(p);
			
			p.setEnderecos(enderecos);
			
			//p.setEnderecos(enderecos);
			
			listaPessoas.add(p);//VOLTAR AQUI
		}
		
		return listaPessoas;
	}

}
