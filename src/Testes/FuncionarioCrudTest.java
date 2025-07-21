package Testes;

import static org.junit.jupiter.api.Assertions.*;

import Cruds.*;
import Models.*;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FuncionarioCrudTest {
    private FuncionarioCrud service;

    @BeforeEach
    public void setup() {
        service = new FuncionarioCrud();
    }

    /*
    inserir - ok
    getAll - ok
    getByNomeExistente - ok
    getByNomeInexistente - ok
    getByCpfExistente - ok
    getByCpfInexistente - ok
    getByIdExistente - ok
    getByIdInexistente - ok
    loginExistente -
    loginInexistente -
    alterarExistente -
    alterarInexistente -
    excluirExistente -
    excluirInexistente -
     */

    @Test
    @Order(1)
    public void testInserirFuncionario() throws SQLException {
        Funcionario f = new Funcionario();
        f.setNome("Powershell");
        f.setCpf("CPF");
        f.setCargo("CEO");
        f.setUsuario("Usuario");
        f.setSenha("Senha");
        service.inserir(f);

        Funcionario resultado = service.getByCpf(f.getCpf());

        assertNotNull(resultado, "Funcionario não foi encontrado no banco");
        assertEquals("Powershell", resultado.getNome());
    }

    @Test
    @Order(2)
    public void testListarTodos() throws SQLException {
        List<Funcionario> funcionarios = service.getAll();
        assertEquals("Powershell", funcionarios.get(funcionarios.size() - 1).getNome());
    }

    @Test
    @Order(3)
    public void testBuscarPorNomeExistente() {
        Funcionario f = service.getByNome("Powershell");

        assertEquals("Powershell", f.getNome());
    }

    @Test
    @Order(4)
    public void testBuscarPorNomeInexistente() {
        Funcionario f = service.getByCpf("AAAAAAAAAAAAAAAA");

        assertNull(f.getNome());
    }

    @Test
    @Order(5)
    public void testBuscarPorCpfExistente() {
        Funcionario f = service.getByCpf("CPF");

        assertEquals("CPF", f.getCpf());
    }

    @Test
    @Order(6)
    public void testBuscarPorCpfInexistente() {
        Funcionario f = service.getByCpf("N EXISTE");
        assertNull(f.getCpf());
    }

    @Test
    @Order(7)
    public void testBuscarPorIdExistente() throws SQLException {
        int id = service.getByCpf("CPF").getId();

        assertEquals("Powershell", service.getById(id).getNome());
    }

    @Test
    @Order(8)
    public void testBuscarPorIdInexistente() throws SQLException {
        Funcionario f = service.getById(299);

        assertNull(f.getNome());
    }

    @Test
    @Order(9)
    public void testLoginExistente() {
        Funcionario f = service.getByCpf("CPF");
        f.setSenha("Senha");

        assertEquals("Powershell", service.login(f).getNome());
    }

    @Test
    @Order(10)
    public void testLoginInexistente() {
        Funcionario f = service.getByCpf("SEM CPF");

        assertNull(service.login(f));
    }

    @Test
    @Order(11)
    public void testAlterarExistente() {
        Funcionario f = new Funcionario();
        f.setNome("Nome Original");
        f.setUsuario("Usuario Original");
        f.setSenha("Senha Original");
        f.setCpf("Cpf Og");

        service.inserir(f);

        Funcionario f1 = service.getByNome(f.getNome());
        f1.setNome("Nome Alterado");
        f1.setCpf("Cpf Alt");

        int linhas = service.alterar(f1);

        assertEquals(1, linhas);
    }

    @Test
    @Order(12)
    public void testAlterarInexistente() {
        Funcionario f = new Funcionario();
        f.setNome("ADAKDSVDHAD");
        f.setCpf("CEPEEFE");

        int linhas = service.alterar(f);
        assertEquals(0, linhas);
    }

//    @Test
//    @Order(13)
//    public void testExcluirExistente() {
//        int id = service.getByCpf("CPF").getId();
//        int id2 = service.getByCpf("Cpf Alterado").getId();
//
//        int linhas = service.excluir(id) + service.excluir(id2);
//
//        assertEquals(2, linhas);
//    }
//
//    @Test
//    @Order(14)
//    public void testExcluirInexistente() {
//        assertEquals(0, service.excluir(299));
//    }
}
