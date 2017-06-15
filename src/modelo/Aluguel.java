package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Aluguel implements Serializable {
	
    private int id;
    private int idFuncionario;        
    private int idBoardGame;
    private int idCliente;
    private String tempoaluguel;
    private boolean status;
    private BoardGame boardgame;
    private Cliente cliente;
    private Funcionario funcionario;
	
	public Aluguel(int _id, int _idFuncionario, int _idBoardGame, int _idCliente, String _tempoaluguel, Boolean _status) {
		this.id = _id;
		this.idFuncionario = _idFuncionario;
		this.idBoardGame = _idBoardGame;
		this.idCliente = _idCliente; 
		this.tempoaluguel = _tempoaluguel;
		this.status = _status;
	}
	
	public Aluguel(int _id, int _idFuncionario, int _idBoardGame, int _idCliente, String _tempoaluguel, Boolean _status, BoardGame _boardgame, Cliente _cliente, Funcionario _funcionario) {
		this.id = _id;
		this.idFuncionario = _idFuncionario;
		this.idBoardGame = _idBoardGame;
		this.idCliente = _idCliente; 
		this.tempoaluguel = _tempoaluguel;
		this.status = _status;
		this.boardgame = _boardgame;
		this.cliente = _cliente;
		this.funcionario = _funcionario;
	}

	public Aluguel() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public int getIdBoardGame() {
		return idBoardGame;
	}

	public void setIdBoardGame(int idBoardGame) {
		this.idBoardGame = idBoardGame;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getTempoaluguel() {
		return tempoaluguel;
	}

	public void setTempoaluguel(String tempoAluguel) {
		this.tempoaluguel = tempoAluguel;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public BoardGame getBoardgame() {
		return boardgame;
	}

	public void setBoardgame(BoardGame boardgame) {
		this.boardgame = boardgame;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}
