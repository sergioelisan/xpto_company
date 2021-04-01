package nonreal.xpto.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import nonreal.xpto.util.DBConnection;

/** */
public abstract class DAO<T> {

    /** */
    public abstract Integer salvar(T objeto);

    /** */
    public abstract String remover(T objeto);

    /** */
    public abstract T get(Serializable data);

    /** */
    public abstract List<T> list();

    /** */
    protected Connection getConnection() {
        return DBConnection.getConnection();
    }

}
