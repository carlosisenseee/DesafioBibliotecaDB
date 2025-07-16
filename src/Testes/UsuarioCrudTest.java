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
    GetByIdExiste -
    GetByIdNaoExiste -
    excluirExiste -
    excluirNaoexiste
    alterarExiste -
    alterarNaoexiste -
    getbycpfExiste -
    getbycpfNaoExiste -
    getbynomeExiste -
    getbynomeNaoExiste -
    setEmprestimosExiste -
    setEmprestimosNaoExiste -
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

        assertEquals("Powershell", usuarios.get(usuarios.size() - 1).getNome());
    }

    @Test
    public void testBuscarPorIdExistente() {

    }

    @Test
    public void testBuscarPorIdInexistente() {

    }

    @Test
    public void testAtualizarUsuario() {

    }
}
