package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoardGame implements Serializable {
	
	private int id;
    private String nome;
    private String fabricante;
    private String genero;
    private String duracaoPartida;
    private int qtdJogadores;
    private int quantidade;
    private int vezesJogado;
    
	public BoardGame() {
		super();
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

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDuracaoPartida() {
		return duracaoPartida;
	}

	public void setDuracaoPartida(String duracaoPartida) {
		this.duracaoPartida = duracaoPartida;
	}

	public int getQtdJogadores() {
		return qtdJogadores;
	}

	public void setQtdJogadores(int qtdJogadores) {
		this.qtdJogadores = qtdJogadores;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getVezesJogado() {
		return vezesJogado;
	}

	public void setVezesJogado(int vezesJogado) {
		this.vezesJogado = vezesJogado;
	}
  
}
