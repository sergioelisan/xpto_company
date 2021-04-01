package nonreal.xpto.test;

import java.math.BigDecimal;
import java.util.Calendar;

import junit.framework.TestCase;
import nonreal.xpto.dao.DAOFactory;
import nonreal.xpto.model.ContaBancaria;
import nonreal.xpto.model.Movimentacao;
import nonreal.xpto.model.PessoaFisica;
import nonreal.xpto.model.PessoaJuridica;

public class MovimentacoesTest extends TestCase {

    public void testMovimentacoes() {
        var daopessoafisica = DAOFactory.getDAO(PessoaFisica.class, true);
        var daocontabancaria = DAOFactory.getDAO(ContaBancaria.class, true);
        var daopessoajuridica = DAOFactory.getDAO(PessoaJuridica.class, true);
        var daomovimentacoes = DAOFactory.getDAO(Movimentacao.class, true);

        var pessoa1 = daopessoafisica.get("211.957.313-10");
        assertNotNull(pessoa1);
        assertEquals("Roberto Anderson", pessoa1.getNome());

        var conta1 = daocontabancaria.get(pessoa1.getId());
        assertNotNull(conta1);
        assertEquals("Banco do Brasil", conta1.getInstituicaoFinanceira());

        var pessoa2 = daopessoajuridica.get("14.576.326/0001-76");
        assertNotNull(pessoa2);
        assertEquals("Adamastor Melo", pessoa2.getNome());

        var conta2 = daocontabancaria.get(pessoa2.getId());
        assertNotNull(conta2);
        assertEquals("NuBank", conta2.getInstituicaoFinanceira());

        var calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.JANUARY, 1);

        for (var i = 0; i < 50; i++) {
            var mov = new Movimentacao();
            mov.setPessoaOrigemID(conta1.getProprietarioID());
            mov.setContaOrigemID(conta1.getId());

            mov.setPessoaDestinoID(conta2.getProprietarioID());
            mov.setContaDestinoID(conta2.getId());

            mov.setValor(new BigDecimal(10.0));

            calendar.add(Calendar.DAY_OF_MONTH, 5);
            mov.setRealizadoEm(calendar.getTime());

            var movID = daomovimentacoes.salvar(mov);
            assertNotNull(movID);
        }

        calendar.set(2021, Calendar.JANUARY, 1);
        for (var i = 0; i < 50; i++) {
            var mov = new Movimentacao();
            mov.setPessoaOrigemID(conta2.getProprietarioID());
            mov.setContaOrigemID(conta2.getId());

            mov.setPessoaDestinoID(conta1.getProprietarioID());
            mov.setContaDestinoID(conta1.getId());

            mov.setValor(new BigDecimal(7.0));

            calendar.add(Calendar.DAY_OF_MONTH, 3);
            mov.setRealizadoEm(calendar.getTime());

            var movID = daomovimentacoes.salvar(mov);
            assertNotNull(movID);
        }

    }
}
