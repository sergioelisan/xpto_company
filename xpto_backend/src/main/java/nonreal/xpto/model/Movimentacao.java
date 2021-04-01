package nonreal.xpto.model;

import java.math.BigDecimal;
import java.util.Date;

public class Movimentacao {

    public Movimentacao() {

    }

    private Integer id;

    private Integer pessoaOrigemID;

    private Integer pessoaDestinoID;

    private Integer contaOrigemID;

    private Integer contaDestinoID;

    private BigDecimal valor;

    private Date realizadoEm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPessoaOrigemID() {
        return pessoaOrigemID;
    }

    public void setPessoaOrigemID(Integer pessoaOrigemID) {
        this.pessoaOrigemID = pessoaOrigemID;
    }

    public Integer getPessoaDestinoID() {
        return pessoaDestinoID;
    }

    public void setPessoaDestinoID(Integer pessoaDestinoID) {
        this.pessoaDestinoID = pessoaDestinoID;
    }

    public Integer getContaOrigemID() {
        return contaOrigemID;
    }

    public void setContaOrigemID(Integer contaOrigemID) {
        this.contaOrigemID = contaOrigemID;
    }

    public Integer getContaDestinoID() {
        return contaDestinoID;
    }

    public void setContaDestinoID(Integer contaDestinoID) {
        this.contaDestinoID = contaDestinoID;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getRealizadoEm() {
        return realizadoEm;
    }

    public void setRealizadoEm(Date realizadoEm) {
        this.realizadoEm = realizadoEm;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contaDestinoID == null) ? 0 : contaDestinoID.hashCode());
        result = prime * result + ((contaOrigemID == null) ? 0 : contaOrigemID.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((pessoaOrigemID == null) ? 0 : pessoaOrigemID.hashCode());
        result = prime * result + ((realizadoEm == null) ? 0 : realizadoEm.hashCode());
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
        Movimentacao other = (Movimentacao) obj;
        if (contaDestinoID == null) {
            if (other.contaDestinoID != null)
                return false;
        } else if (!contaDestinoID.equals(other.contaDestinoID))
            return false;
        if (contaOrigemID == null) {
            if (other.contaOrigemID != null)
                return false;
        } else if (!contaOrigemID.equals(other.contaOrigemID))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (pessoaOrigemID == null) {
            if (other.pessoaOrigemID != null)
                return false;
        } else if (!pessoaOrigemID.equals(other.pessoaOrigemID))
            return false;
        if (realizadoEm == null) {
            if (other.realizadoEm != null)
                return false;
        } else if (!realizadoEm.equals(other.realizadoEm))
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }

}