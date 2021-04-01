package nonreal.xpto.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import nonreal.xpto.model.ContaBancaria;

public class DAOContaBancaria extends DAO<ContaBancaria> {

    public DAOContaBancaria(boolean localhost) {
        this.localhost = localhost;
    }

    private boolean localhost;

    /** */
    private static final String INSERT = "INSERT INTO conta_bancaria "
            + "(proprietario_id, instituicao_financeira, agencia, saldo, saldo_inicial, criada_em, arquivada) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    /** */
    private static final String UPDATE = "UPDATE conta_bancaria SET arquivada = ? WHERE proprietario_id = ?";

    /** */
    private static final String GET = "SELECT * FROM conta_bancaria WHERE proprietario_id = ?";

    /** */
    private static final String LIST = "SELECT * FROM conta_bancaria";

    @Override
    public Integer salvar(ContaBancaria objeto) {
        try (var connection = getConnection(localhost)) {

            if (objeto.getId() == null)
                try (var preparedstatement = connection.prepareStatement(INSERT, new String[] { "id" })) {
                    preparedstatement.setInt(1, objeto.getProprietarioID());
                    preparedstatement.setString(2, objeto.getInstituicaoFinanceira());
                    preparedstatement.setString(3, objeto.getAgencia());
                    preparedstatement.setBigDecimal(4, objeto.getSaldoInicial());
                    preparedstatement.setBigDecimal(5, objeto.getSaldoInicial());
                    preparedstatement.setDate(6, new java.sql.Date(objeto.getCriadaEm().getTime()));
                    preparedstatement.setBoolean(7, false);

                    preparedstatement.executeUpdate();

                    try (var generatedKeys = preparedstatement.getGeneratedKeys();) {
                        if (null != generatedKeys && generatedKeys.next()) {
                            return (int) generatedKeys.getLong(1);
                        }
                    }
                }
        }
        //
        catch (SQLException e) {
            System.out.println("[XPTO-BACKEND] [DAOCONTABANCARIA] [SALVAR] Erro no SQL");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String remover(ContaBancaria objeto) {
        try (var connection = getConnection(localhost)) {

            try (var preparedstatement = connection.prepareStatement(UPDATE)) {
                preparedstatement.setInt(2, objeto.getProprietarioID());
                preparedstatement.setBoolean(1, true);

                preparedstatement.execute();
            }
        }
        //
        catch (SQLException e) {
            System.out.println("[XPTO-BACKEND] [DAOCONTABANCARIA] [REMOVER] Erro no SQL");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ContaBancaria get(Serializable data) {
        try (var connection = getConnection(localhost)) {

            try (var preparedstatement = connection.prepareStatement(GET)) {

                preparedstatement.setInt(1, (Integer) data);

                try (var result = preparedstatement.executeQuery()) {
                    var found = new ContaBancaria();

                    result.next();

                    found.setId((int) result.getLong("id"));
                    found.setProprietarioID((int) result.getLong("proprietario_id"));
                    found.setInstituicaoFinanceira(result.getString("instituicao_financeira"));
                    found.setAgencia(result.getString("agencia"));
                    found.setSaldo(result.getBigDecimal("saldo"));
                    found.setSaldoInicial(result.getBigDecimal("saldo_inicial"));
                    found.setCriadaEm(result.getDate("criada_em"));

                    return found;
                }
            }
        }
        //
        catch (SQLException e) {
            System.out.println("[XPTO-BACKEND] [DAOPESSOAFISICA] [GET] Erro no SQL");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ContaBancaria> list() {

        return null;
    }

}
