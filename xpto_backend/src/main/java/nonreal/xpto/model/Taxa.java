package nonreal.xpto.model;

import java.math.BigDecimal;
import java.util.Date;

public class Taxa {

    public Taxa() {
    }

    private Integer id;

    private Integer cobradoID;

    private Integer movimentacaoID;

    private BigDecimal valor;

    private Date cobradaEm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCobradoID() {
        return cobradoID;
    }

    public void setCobradoID(Integer cobradoID) {
        this.cobradoID = cobradoID;
    }

    public Integer getMovimentacaoID() {
        return movimentacaoID;
    }

    public void setMovimentacaoID(Integer movimentacaoID) {
        this.movimentacaoID = movimentacaoID;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getCobradaEm() {
        return cobradaEm;
    }

    public void setCobradaEm(Date cobradaEm) {
        this.cobradaEm = cobradaEm;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cobradaEm == null) ? 0 : cobradaEm.hashCode());
        result = prime * result + ((cobradoID == null) ? 0 : cobradoID.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((movimentacaoID == null) ? 0 : movimentacaoID.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
        Taxa other = (Taxa) obj;
        if (cobradaEm == null) {
            if (other.cobradaEm != null)
                return false;
        } else if (!cobradaEm.equals(other.cobradaEm))
            return false;
        if (cobradoID == null) {
            if (other.cobradoID != null)
                return false;
        } else if (!cobradoID.equals(other.cobradoID))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (movimentacaoID == null) {
            if (other.movimentacaoID != null)
                return false;
        } else if (!movimentacaoID.equals(other.movimentacaoID))
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }

}
