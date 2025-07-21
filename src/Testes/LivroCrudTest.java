package Testes;

import static org.junit.jupiter.api.Assertions.*;

import Cruds.*;
import Models.*;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LivroCrudTest {
    private LivroCrud service;

    @BeforeEach
    public void setup() {
        service = new LivroCrud();
    }


    /*
    getAll - ok
    getByIdExiste - ok
    getByIdInexistetne - ok
    inserir - ok
    getByIsbnExistente - ok
    getByIsbnInexistente - ok
    getByTituloExistente - ok
    getByTituloInexistente - ok
    alterarExistente - ok
    alterarInexistente - ok
    excluirExistente - ok
    excluirInexistente - ok
     */

    @Test
    @Order(1)
    public void testCriarLivro() throws SQLException {
        Livro l = new Livro();
        l.setTitulo("Powershell");
        l.setAutor("Windows");
        l.setAnoPublicacao(1980);
        l.setIsbn("12345678910");
        service.inserir(l);

        Livro resultado = service.getByTitulo(l.getTitulo());

        assertNotNull(resultado, "Livro não foi encontrado no banco");
        assertEquals("Powershell", resultado.getTitulo());
    }

    @Test
    @Order(2)
    public void testListarTodos() throws SQLException {
        List<Livro> livros = service.getAll();

        assertEquals("Powershell", livros.get(livros.size() - 1).getTitulo());
    }

    @Test
    @Order(3)
    public void testBuscarPorIdExistente() throws SQLException {
        int id = service.getByIsbn("12345678910").getId();

        assertEquals("Powershell", service.getById(id).getTitulo());
    }

    @Test
    @Order(4)
    public void testBuscarPorIdInexistente() throws SQLException {
        Livro l = service.getById(299);

        assertNull(l.getTitulo());
    }

    @Test
    @Order(5)
    public void testBuscarPorIsbnExistente() {
        Livro l = service.getByIsbn("12345678910");

        assertEquals("12345678910", l.getIsbn());
    }

    @Test
    @Order(6)
    public void testBuscarPorIsbnInexistente() {
        Livro l = service.getByIsbn("AAAAAAAAAAAA");

        assertNull(l.getIsbn());
    }

    @Test
    @Order(7)
    public void testBuscarPorTituloExistente() {
        Livro l = service.getByTitulo("Powershell");

        assertEquals("Powershell", l.getTitulo());
    }

    @Test
    @Order(8)
    public void testBuscarPorTituloInexistente() {
        Livro l = service.getByTitulo("Bla Bla Bla Bla Bla");

        assertNull(l.getTitulo());
    }

    @Test
    @Order(9)
    public void testAlterarExistente() {
        Livro l = new Livro();
        l.setTitulo("Livro Original");
        l.setIsbn("Isbn Original");

        service.inserir(l);

        Livro l1 = service.getByTitulo(l.getTitulo());
        l1.setTitulo("Livro Alterado");
        l1.setIsbn("Isbn Alterado");

        int linhas = service.alterar(l1);

        assertEquals(1, linhas);
    }

    @Test
    @Order(10)
    public void testAlterarInexistente() {
        Livro l = new Livro();
        l.setTitulo("ADAKDSVDHAD");
        l.setIsbn("99999999999");

        int linhas = service.alterar(l);
        assertEquals(0, linhas);
    }

    @Test
    @Order(11)
    public void testExcluirExistente() {
        int id = service.getByIsbn("12345678910").getId();
        int id2 = service.getByIsbn("Isbn Alterado").getId();

        int linhas = service.excluir(id) + service.excluir(id2);

        assertEquals(2, linhas);
    }

    @Test
    @Order(12)
    public void testExcluirInexistente() {
        assertEquals(0, service.excluir(299));
    }
}
