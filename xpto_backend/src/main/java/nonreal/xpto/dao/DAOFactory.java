package nonreal.xpto.dao;

import nonreal.xpto.model.ContaBancaria;
import nonreal.xpto.model.Endereco;
import nonreal.xpto.model.Movimentacao;
import nonreal.xpto.model.PessoaFisica;
import nonreal.xpto.model.PessoaJuridica;

/**
 * @author sergio lisan <sergiolisan@gmail.com>
 */
public abstract class DAOFactory {

    /**
     * 
     * @param <T>
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> DAO<T> getDAO(Class<T> clazz) {
        if (clazz.equals(ContaBancaria.class)) {
            return (DAO<T>) new DAOContaBancaria();
        }
        //
        else if (clazz.equals(Endereco.class)) {
            return (DAO<T>) new DAOEndereco();
        }
        //
        else if (clazz.equals(Movimentacao.class)) {
            return (DAO<T>) new DAOMovimentacao();
        }
        //
        else if (clazz.equals(PessoaFisica.class)) {
            return (DAO<T>) new DAOPessoaFisica();
        }
        //
        else if (clazz.equals(PessoaJuridica.class)) {
            return (DAO<T>) new DAOPessoaJuridica();
        }

        return null;
    }

}
