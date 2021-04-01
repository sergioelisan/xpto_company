package nonreal.xpto.test;

import java.util.Calendar;

import junit.framework.TestCase;
import nonreal.xpto.logic.RelatorioReceitaEmpresaPeriodo;
import nonreal.xpto.logic.RelatorioSaldoClientePeriodo;
import nonreal.xpto.logic.RelatorioSaldoTodosClientes;

public class RelatoriosTest extends TestCase {

    /**
     * 
     */
    public void testRelatorioSaldoClientePeriodo() {
        var calendar = Calendar.getInstance();

        calendar.set(2021, Calendar.JANUARY, 1);
        var inicio = new java.sql.Date(calendar.getTime().getTime());

        calendar.set(2021, Calendar.NOVEMBER, 7);
        var fim = new java.sql.Date(calendar.getTime().getTime());

        var pessoaID = 51;

        var relatorio = new RelatorioSaldoClientePeriodo();
        var resultado = relatorio.printRelatorio(pessoaID, inicio, fim, true);

        assertNotNull(resultado);
        System.out.println("\n");
    }

    /**
     * 
     */
    public void testRelatorioTodosOsClientes() {
        var calendar = Calendar.getInstance();

        calendar.set(2021, Calendar.JANUARY, 1);
        var inicio = new java.sql.Date(calendar.getTime().getTime());

        calendar.set(2021, Calendar.NOVEMBER, 7);
        var fim = new java.sql.Date(calendar.getTime().getTime());

        var relatorio = new RelatorioSaldoTodosClientes();
        var resultado = relatorio.printRelatorio(inicio, fim, true);

        assertNotNull(resultado);
        System.out.println("\n");
    }

    /**
     * 
     */
    public void testRelatorioReceitaEmpresa() {
        var calendar = Calendar.getInstance();

        calendar.set(2021, Calendar.JANUARY, 1);
        var inicio = new java.sql.Date(calendar.getTime().getTime());

        calendar.set(2021, Calendar.NOVEMBER, 7);
        var fim = new java.sql.Date(calendar.getTime().getTime());

        var relatorio = new RelatorioReceitaEmpresaPeriodo();
        var resultado = relatorio.printRelatorio(inicio, fim, true);

        assertNotNull(resultado);
        System.out.println("\n");
    }

}
