package nonreal.xpto.model;

import java.math.BigDecimal;
import java.util.Date;

public class ContaBancaria {

    public ContaBancaria() {

    }

    private Integer id;

    private Integer proprietarioID;

    private String instituicaoFinanceira;

    private String numero;

    private String agencia;

    private BigDecimal saldoInicial;

    private BigDecimal saldo;

    private Date criadaEm;

    private boolean arquivada;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProprietarioID() {
        return proprietarioID;
    }

    public void setProprietarioID(Integer proprietarioID) {
        this.proprietarioID = proprietarioID;
    }

    public String getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }

    public void setInstituicaoFinanceira(String instituicaoFinanceira) {
        this.instituicaoFinanceira = instituicaoFinanceira;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Date getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(Date criadaEm) {
        this.criadaEm = criadaEm;
    }

    public boolean isArquivada() {
        return arquivada;
    }

    public void setArquivada(boolean arquivada) {
        this.arquivada = arquivada;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
        result = prime * result + (arquivada ? 1231 : 1237);
        result = prime * result + ((criadaEm == null) ? 0 : criadaEm.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((instituicaoFinanceira == null) ? 0 : instituicaoFinanceira.hashCode());
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        result = prime * result + ((proprietarioID == null) ? 0 : proprietarioID.hashCode());
        result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
        result = prime * result + ((saldoInicial == null) ? 0 : saldoInicial.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContaBancaria other = (ContaBancaria) obj;
        if (agencia == null) {
            if (other.agencia != null)
                return false;
        } else if (!agencia.equals(other.agencia))
            return false;
        if (arquivada != other.arquivada)
            return false;
        if (criadaEm == null) {
            if (other.criadaEm != null)
                return false;
        } else if (!criadaEm.equals(other.criadaEm))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (instituicaoFinanceira == null) {
            if (other.instituicaoFinanceira != null)
                return false;
        } else if (!instituicaoFinanceira.equals(other.instituicaoFinanceira))
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        if (proprietarioID == null) {
            if (other.proprietarioID != null)
                return false;
        } else if (!proprietarioID.equals(other.proprietarioID))
            return false;
        if (saldo == null) {
            if (other.saldo != null)
                return false;
        } else if (!saldo.equals(other.saldo))
            return false;
        if (saldoInicial == null) {
            if (other.saldoInicial != null)
                return false;
        } else if (!saldoInicial.equals(other.saldoInicial))
            return false;
        return true;
    }

}