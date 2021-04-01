package nonreal.xpto.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import nonreal.xpto.model.Movimentacao;

public class DAOMovimentacao extends DAO<Movimentacao> {

    /** */
    private static final String INSERT = "INSERT INTO movimentacao "
            + "(pessoa_origem_id, pessoa_destino_id, conta_origem_id, conta_destino_id, valor, realizado_em) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    /** */
    private static final String GET = "SELECT * FROM movimentacao WHERE id = ?";

    /** */
    private static final String LIST = "SELECT * FROM movimentacao";

    @Override
    public Integer salvar(Movimentacao objeto) {
        try (var connection = getConnection()) {

            try (var preparedstatement = connection.prepareStatement(INSERT, new String[] { "id" })) {

                preparedstatement.setInt(1, objeto.getPessoaOrigemID());
                preparedstatement.setInt(2, objeto.getPessoaDestinoID());
                preparedstatement.setInt(3, objeto.getContaOrigemID());
                preparedstatement.setInt(4, objeto.getContaDestinoID());
                preparedstatement.setBigDecimal(5, objeto.getValor());
                preparedstatement.setDate(6, new java.sql.Date(objeto.getRealizadoEm().getTime()));

                preparedstatement.executeUpdate();

                try (var generatedKeys = preparedstatement.getGeneratedKeys()) {
                    if (null != generatedKeys && generatedKeys.next()) {
                        return (int) generatedKeys.getLong(1);
                    }
                }
            }
        }
        //
        catch (SQLException e) {
            System.out.println("[XPTO-BACKEND] [DAOMOVIMENTACAO] [SALVAR] Erro no SQL");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String remover(Movimentacao objeto) {
        throw new UnsupportedOperationException("Não é possível remover uma movimentação");
    }

    @Override
    public Movimentacao get(Serializable data) {

        return null;
    }

    @Override
    public List<Movimentacao> list() {

        return null;
    }

}
