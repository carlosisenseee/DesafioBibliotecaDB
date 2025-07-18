package Testes;

import static org.junit.jupiter.api.Assertions.*;

import Cruds.*;
import Models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioCrudTest {

    private UsuarioCrud service;

    @BeforeEach
    public void setup() {
        service = new UsuarioCrud();
    }
    /*
    GetAll - ok
    inserir - ok
    GetByIdExiste - ok
    GetByIdNaoExiste - ok
    getbycpfExiste - ok
    getbycpfNaoExiste - ok
    getbynomeExiste - ok
    getbynomeNaoExiste - ok
    alterarExiste - ok
    alterarNaoexiste - ok
    excluirExiste -
    excluirNaoexiste -
     */

    @Test
    public void testCriarUsuario() throws SQLException {
        Usuario u = new Usuario();
        u.setNome("Powershell");
        u.setCpf("12345678910");
        service.inserir(u);

        Usuario resultado = service.getByCpf(u.getCpf());

        assertNotNull(resultado, "Usuário não foi encontrado no banco");
        assertEquals("Powershell", resultado.getNome());

        //service.excluir(resultado.getId());
    }

    @Test
    public void testListarTodos() throws SQLException {
        List<Usuario> usuarios = service.getAll();

        assertEquals("Powershell2", usuarios.get(usuarios.size() - 1).getNome());
    }

    @Test
    public void testBuscarPorIdExistente() throws SQLException {
        int id = service.getByCpf("12345678910").getId();

        assertEquals("Powershell", service.getById(id).getNome());
    }

    @Test
    public void testBuscarPorIdInexistente() throws SQLException {
        Usuario u = service.getById(299);

        assertNull(u.getNome());
    }

    @Test
    public void testBuscarPorCpfExistente() {
        Usuario u = service.getByCpf("12345678910");

        assertEquals("12345678910", u.getCpf());
    }

    @Test
    public void testBuscarPorCpfInexistente() {
        Usuario u = service.getByCpf("99999999999");

        assertNull(u.getCpf());
    }

    @Test
    public void testBuscarPorNomeExistente() {
        Usuario u = service.getByNome("Powershell");

        assertEquals("Powershell", u.getNome());
    }

    @Test
    public void testBuscarPorNomeInexistente() {
        Usuario u = service.getByCpf("AAAAAAAAAAAAAAAA");

        assertNull(u.getNome());
    }

    @Test
    public void testAlterarExistente() {
        Usuario u = service.getByCpf("12345678910");
        u.setNome("Powershell2");

        int linhas = service.alterar(u);

        assertEquals(1, linhas);
    }

    @Test
    public void testAlterarInexistente() {
        Usuario u = new Usuario();
        u.setNome("ADAKDSVDHAD");
        u.setCpf("99999999999");

        int linhas = service.alterar(u);
        assertEquals(0, linhas);
    }

    @Test
    public void testExcluirExistente() {
        int id = service.getByCpf("12345678910").getId();

        int linhas = service.excluir(id);

        assertEquals(1, linhas);
    }

    @Test
    public void testExcluirInexistente() {
        assertEquals(0, service.excluir(299));
    }

}
