package nonreal.xpto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** */
public class DBConnection {

    /** */
    private static final String URL = "jdbc:oracle:thin:@172.17.0.1:51521/XE";

    /** */
    private static final String LOCALHOST = "jdbc:oracle:thin:@localhost:51521/XE";

    /** */
    private static final String USER = "SYSTEM";

    /** */
    private static final String PASSWD = "12345678";

    /** */
    public static Connection getConnection(boolean localhost) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(localhost ? LOCALHOST : URL, USER, PASSWD);
        }
        //
        catch (ClassNotFoundException e) {
            System.out.println("[XPTO-BACKEND] Driver de banco de dados nao encontrado");
        }
        //
        catch (SQLException e) {
            System.out.println("[XPTO-BACKEND] Problemas na autenticacao com o banco de dados");
        }

        return null;
    }

}
