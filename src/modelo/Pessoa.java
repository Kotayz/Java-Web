package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String tel;
	private Date dtCadastro;
	private List<Endereco> enderecos;
	
	public Pessoa() {
		this.enderecos = new ArrayList<Endereco>();
	}
	
	public Pessoa(int _id, String _nome, String _tel, Date _dtCadastro, List<Endereco> _enderecos) {
		super();
		this.id = _id;
		this.nome = _nome;
		this.tel = _tel;
		this.dtCadastro = _dtCadastro;
		this.enderecos = _enderecos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public void AdicionarEndereco(Endereco _endereco) {
		this.enderecos.add(_endereco);
	}
	
	public void RemoverEndereco(Endereco _endereco) {
		this.enderecos.remove(_endereco);
	}
}
