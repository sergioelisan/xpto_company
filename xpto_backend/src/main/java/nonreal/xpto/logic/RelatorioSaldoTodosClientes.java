package nonreal.xpto.logic;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import nonreal.xpto.util.DBConnection;

public class RelatorioSaldoTodosClientes {

    public String printRelatorio(Date inicio, Date fim) {
        try {
            var call = "{? = call relatorio_saldo_todos_clientes (?, ?)}";
            var cstmt = DBConnection.getConnection().prepareCall(call);

            cstmt.registerOutParameter(1, Types.VARCHAR);

            cstmt.setDate(2, new java.sql.Date(inicio.getTime()));
            cstmt.setDate(3, new java.sql.Date(fim.getTime()));

            cstmt.execute();

            System.out.println(cstmt.getString(1));

            return cstmt.getString(1);
        }
        //
        catch (SQLException e) {
            System.out.println("Problemas ao requerir o relatorio de todos os clientes");
        }

        return null;
    }

}
