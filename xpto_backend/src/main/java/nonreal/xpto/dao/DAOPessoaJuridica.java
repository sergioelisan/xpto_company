package nonreal.xpto.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import nonreal.xpto.model.PessoaJuridica;

public class DAOPessoaJuridica extends DAO<PessoaJuridica> {

    /** */
    private static final String INSERT = "INSERT INTO pessoa "
            + "(nome, endereco_id, cnp, pessoa_juridica, cadastrada_em, arquivada) VALUES (?, ?, ?, ?, ?, ?)";

    /** */
    private static final String UPDATE = "UPDATE pessoa SET endereco_id = ? WHERE id = ?";

    /** */
    private static final String DELETE = "UPDATE pessoa SET arquivada = ? WHERE id = ?";

    /** */
    private static final String GET = "SELECT * FROM pessoa WHERE cnp = ?";

    /** */
    private static final String LIST = "SELECT * FROM pessoa";

    @Override
    public Integer salvar(PessoaJuridica objeto) {
        try (var connection = getConnection()) {
            try (var preparedstatement = connection.prepareStatement(INSERT, new String[] { "id" })) {

                preparedstatement.setString(1, objeto.getNome());
                preparedstatement.setInt(2, objeto.getEnderecoID());
                preparedstatement.setString(3, objeto.getCnpj());
                preparedstatement.setBoolean(4, true);
                preparedstatement.setDate(5, new java.sql.Date(objeto.getCadastradaEm().getTime()));
                preparedstatement.setBoolean(6, false);

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
            System.out.println("[XPTO-BACKEND] [DAOPESSOAJURIDICA] [SALVAR] Erro no SQL");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String remover(PessoaJuridica objeto) {
        try (var connection = getConnection()) {

            try (var preparedstatement = connection.prepareStatement(DELETE)) {
                preparedstatement.setInt(2, objeto.getId());
                preparedstatement.setBoolean(1, true);

                preparedstatement.execute();
            }
        }
        //
        catch (SQLException e) {
            System.out.println("[XPTO-BACKEND] [DAOPESSOAJURIDICA] [REMOVER] Erro no SQL");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public PessoaJuridica get(Serializable data) {
        try (var connection = getConnection()) {

            try (var preparedstatement = connection.prepareStatement(GET)) {
                preparedstatement.setString(1, (String) data);

                try (var result = preparedstatement.executeQuery()) {
                    var found = new PessoaJuridica();

                    result.next();

                    found.setId((int) result.getLong(1));
                    found.setNome(result.getString(2));
                    found.setEnderecoID(result.getInt(3));
                    found.setCnpj(result.getString(4));
                    found.setCadastradaEm(result.getDate(6));

                    return found;
                }
            }
        }
        //
        catch (SQLException e) {
            System.out.println("[XPTO-BACKEND] [DAOPESSOAJURIDICA] [GET] Erro no SQL");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<PessoaJuridica> list() {

        return null;
    }

}
