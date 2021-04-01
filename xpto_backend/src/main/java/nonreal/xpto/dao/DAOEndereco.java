package nonreal.xpto.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import nonreal.xpto.model.Endereco;

public class DAOEndereco extends DAO<Endereco> {

    /** */
    private static final String INSERT = "INSERT INTO endereco "
            + "(logradouro, complemento, numero, cep, distrito, cidade, estado) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    /** */
    private static final String DELETE = "DELETE endereco WHERE id = ?";

    /** */
    private static final String GET = "SELECT * FROM endereco WHERE cep = ?";

    /** */
    private static final String LIST = "SELECT * FROM endereco";

    @Override
    public Integer salvar(Endereco objeto) {
        try (var connection = getConnection()) {
            try (var preparedstatement = connection.prepareStatement(INSERT, new String[] { "id" })) {
                preparedstatement.setString(1, objeto.getLogradouro());
                preparedstatement.setString(2, objeto.getComplemento());
                preparedstatement.setInt(3, objeto.getNumero());
                preparedstatement.setString(4, objeto.getCep());
                preparedstatement.setString(5, objeto.getDistrito());
                preparedstatement.setString(6, objeto.getCidade());
                preparedstatement.setString(7, objeto.getEstado());

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
            System.out.println("[XPTO-BACKEND] [DAOENDERECO] [SALVAR] Erro no SQL");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String remover(Endereco objeto) {
        try (var connection = getConnection()) {

            try (var preparedstatement = connection.prepareStatement(DELETE)) {
                preparedstatement.setInt(1, objeto.getId());
                preparedstatement.execute();
            }
        }
        //
        catch (SQLException e) {
            System.out.println("[XPTO-BACKEND] [DAOENDERECO] [REMOVER] Erro no SQL");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Endereco get(Serializable data) {
        try (var connection = getConnection()) {

            try (var preparedstatement = connection.prepareStatement(GET)) {

                preparedstatement.setString(1, (String) data);
                preparedstatement.executeQuery();

                try (var result = preparedstatement.getGeneratedKeys()) {
                    var found = new Endereco();

                    while (!result.next()) {
                        found.setId(result.getInt("id"));
                        found.setLogradouro(result.getString("logradouro"));
                        found.setComplemento(result.getString("complmento"));
                        found.setNumero(result.getInt("numero"));
                        found.setCep(result.getString("cep"));
                        found.setDistrito(result.getString("distrito"));
                        found.setCidade(result.getString("cidade"));
                        found.setEstado(result.getString("estado"));

                        return found;
                    }
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
    public List<Endereco> list() {

        return null;
    }

}
