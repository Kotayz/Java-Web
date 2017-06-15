package dao;

import java.sql.SQLException;
import java.util.List;
import modelo.Aluguel;

public interface InterfaceAluguelDAO {
    public void SalvarAluguel(Aluguel _aluguel) throws SQLException;
    public void DeletarAluguel(int _IdAluguel) throws SQLException;
    public void AtualizarAluguel(Aluguel _aluguel) throws SQLException;
    public List<Aluguel> RetornarAluguel() throws SQLException;    
}
