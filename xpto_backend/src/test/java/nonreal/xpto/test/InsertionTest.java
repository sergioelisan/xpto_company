package nonreal.xpto.test;

import java.math.BigDecimal;
import java.util.Calendar;

import junit.framework.TestCase;
import nonreal.xpto.dao.DAOFactory;
import nonreal.xpto.model.ContaBancaria;
import nonreal.xpto.model.Endereco;
import nonreal.xpto.model.PessoaFisica;
import nonreal.xpto.model.PessoaJuridica;

public class InsertionTest extends TestCase {

    /** */
    public void testInserirPessoa() {
        var daoendereco = DAOFactory.getDAO(Endereco.class);
        var daopessoafisica = DAOFactory.getDAO(PessoaFisica.class);
        var daopessoajuridica = DAOFactory.getDAO(PessoaJuridica.class);

        var endereco1 = new Endereco();
        endereco1.setLogradouro("Rua da Guia");
        endereco1.setComplemento(" ");
        endereco1.setCep("50030-210");
        endereco1.setNumero(123);
        endereco1.setDistrito("Bairro do Recife");
        endereco1.setCidade("Recife");
        endereco1.setEstado("Pernambuco");

        var enderecoID1 = daoendereco.salvar(endereco1);
        assertNotNull(enderecoID1);

        var endereco2 = new Endereco();
        endereco2.setLogradouro("Rua do Bom Jesus");
        endereco2.setComplemento(" ");
        endereco2.setCep("50030-110");
        endereco2.setNumero(456);
        endereco2.setDistrito("Bairro do Recife");
        endereco2.setCidade("Recife");
        endereco2.setEstado("Pernambuco");

        var enderecoID2 = daoendereco.salvar(endereco2);
        assertNotNull(enderecoID2);

        endereco2.setId(enderecoID2);

        var calendar = Calendar.getInstance();

        var pessoafisica = new PessoaFisica();
        pessoafisica.setNome("Roberto Anderson");
        pessoafisica.setCpf("211.957.313-10");
        pessoafisica.setEnderecoID(enderecoID1);

        calendar.set(2020, Calendar.DECEMBER, 12);
        pessoafisica.setCadastradaEm(calendar.getTime());

        var pessoafisicaID = daopessoafisica.salvar(pessoafisica);
        assertNotNull(pessoafisicaID);

        var pessoajuridica = new PessoaJuridica();
        pessoajuridica.setNome("Adamastor Melo");
        pessoajuridica.setCnpj("14.576.326/0001-76");
        pessoajuridica.setEnderecoID(enderecoID2);

        calendar.set(2020, Calendar.DECEMBER, 21);
        pessoajuridica.setCadastradaEm(calendar.getTime());

        var pessoajuridicaID = daopessoajuridica.salvar(pessoajuridica);
        assertNotNull(pessoajuridicaID);
    }

    /** */
    public void testInserirContaBancaria() {
        var daocontabancaria = DAOFactory.getDAO(ContaBancaria.class);
        var daopessoafisica = DAOFactory.getDAO(PessoaFisica.class);
        var daopessoajuridica = DAOFactory.getDAO(PessoaJuridica.class);

        var pessoa1 = daopessoafisica.get("211.957.313-10");
        assertNotNull(pessoa1);
        assertEquals("Roberto Anderson", pessoa1.getNome());

        var pessoa2 = daopessoajuridica.get("14.576.326/0001-76");
        assertNotNull(pessoa2);
        assertEquals("Adamastor Melo", pessoa2.getNome());

        var conta1 = new ContaBancaria();
        conta1.setInstituicaoFinanceira("Banco do Brasil");
        conta1.setProprietarioID(pessoa1.getId());
        // conta1.setNumero("12378123-00");
        conta1.setAgencia("1232-2");
        conta1.setSaldoInicial(new BigDecimal(1000.0));
        conta1.setCriadaEm(pessoa1.getCadastradaEm());

        var conta1ID = daocontabancaria.salvar(conta1);
        assertNotNull(conta1ID);

        var conta2 = new ContaBancaria();
        conta2.setInstituicaoFinanceira("NuBank");
        conta2.setProprietarioID(pessoa2.getId());
        // conta2.setNumero("9982-012");
        conta2.setAgencia("12312-2");
        conta2.setSaldoInicial(new BigDecimal(1000.0));
        conta2.setCriadaEm(pessoa2.getCadastradaEm());

        var conta2ID = daocontabancaria.salvar(conta2);
        assertNotNull(conta2ID);
    }

}
