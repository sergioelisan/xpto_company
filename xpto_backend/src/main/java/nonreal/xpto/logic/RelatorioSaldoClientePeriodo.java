package nonreal.xpto.logic;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import nonreal.xpto.util.DBConnection;

public class RelatorioSaldoClientePeriodo {

    public String printRelatorio(Integer pessoaID, Date inicio, Date fim, boolean localhost) {
        try {
            var call = "{? = call relatorio_cliente_periodo (?, ?, ?)}";
            var cstmt = DBConnection.getConnection(localhost).prepareCall(call);

            cstmt.registerOutParameter(1, Types.VARCHAR);

            cstmt.setInt(2, pessoaID);
            cstmt.setDate(3, new java.sql.Date(inicio.getTime()));
            cstmt.setDate(4, new java.sql.Date(fim.getTime()));

            cstmt.execute();

            System.out.println(cstmt.getString(1));

            return cstmt.getString(1);
        }
        //
        catch (SQLException e) {
            System.out.println("Problemas ao requerir o relatorio de saldo");
        }

        return "";
    }

}
