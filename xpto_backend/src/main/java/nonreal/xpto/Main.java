package nonreal.xpto;

import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.util.Calendar;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import nonreal.xpto.dao.DAOFactory;
import nonreal.xpto.model.ContaBancaria;
import nonreal.xpto.model.Endereco;
import nonreal.xpto.model.Movimentacao;
import nonreal.xpto.model.PessoaFisica;
import nonreal.xpto.model.PessoaJuridica;

public class Main {

    public static void main(String[] args) {
        new Main().init();
    }

    /** */
    public void init() {
        try {
            populate();

            var add = new InetSocketAddress("0.0.0.0", 8080);

            var server = new Server(add);
            server.setHandler(build());
            server.start();

            System.out.println("ONLINE");

        } catch (Exception e) {
            System.out.println("ERRO AO SUBIR SERVIDOR");
            e.printStackTrace();
        }
    }

    /** */
    public static ServletContextHandler build() {
        var servletCtx = new ServletContextHandler();
        servletCtx.setContextPath("/");

        // (2)
        var servletHolder = new ServletHolder(ServletContainer.class);
        servletHolder.setInitParameter("javax.ws.rs.Application", "nonreal.xpto.rest.XPTORESTfulAPI");
        servletCtx.addServlet(servletHolder, "/xpto/*");

        return servletCtx;
    }

    /**
     * 
     */
    public void populate() {
        var empty = DAOFactory.getDAO(PessoaFisica.class).list().isEmpty();

        if (empty) {
            var daoendereco = DAOFactory.getDAO(Endereco.class);
            var daopessoafisica = DAOFactory.getDAO(PessoaFisica.class);
            var daopessoajuridica = DAOFactory.getDAO(PessoaJuridica.class);
            var daocontabancaria = DAOFactory.getDAO(ContaBancaria.class);
            var daomovimentacoes = DAOFactory.getDAO(Movimentacao.class);

            var endereco1 = new Endereco();
            endereco1.setLogradouro("Rua da Guia");
            endereco1.setComplemento(" ");
            endereco1.setCep("50030-210");
            endereco1.setNumero(123);
            endereco1.setDistrito("Bairro do Recife");
            endereco1.setCidade("Recife");
            endereco1.setEstado("Pernambuco");

            var enderecoID1 = daoendereco.salvar(endereco1);

            var endereco2 = new Endereco();
            endereco2.setLogradouro("Rua do Bom Jesus");
            endereco2.setComplemento(" ");
            endereco2.setCep("50030-110");
            endereco2.setNumero(456);
            endereco2.setDistrito("Bairro do Recife");
            endereco2.setCidade("Recife");
            endereco2.setEstado("Pernambuco");

            var enderecoID2 = daoendereco.salvar(endereco2);

            endereco2.setId(enderecoID2);

            var calendar = Calendar.getInstance();

            var pessoafisica = new PessoaFisica();
            pessoafisica.setNome("Roberto Anderson");
            pessoafisica.setCpf("211.957.313-10");
            pessoafisica.setEnderecoID(enderecoID1);

            calendar.set(2020, Calendar.DECEMBER, 12);
            pessoafisica.setCadastradaEm(calendar.getTime());

            daopessoafisica.salvar(pessoafisica);

            var pessoajuridica = new PessoaJuridica();
            pessoajuridica.setNome("Adamastor Melo");
            pessoajuridica.setCnpj("14.576.326/0001-76");
            pessoajuridica.setEnderecoID(enderecoID2);

            calendar.set(2020, Calendar.DECEMBER, 21);
            pessoajuridica.setCadastradaEm(calendar.getTime());

            daopessoajuridica.salvar(pessoajuridica);

            var pessoa1 = daopessoafisica.get("211.957.313-10");
            var conta1 = daocontabancaria.get(pessoa1.getId());
            var pessoa2 = daopessoajuridica.get("14.576.326/0001-76");
            var conta2 = daocontabancaria.get(pessoa2.getId());

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

                daomovimentacoes.salvar(mov);
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

                daomovimentacoes.salvar(mov);
            }
        }
    }
}
