package Cruds;

import Models.Emprestimo;
import db.ConexaoDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoCrud {
    public static List<Emprestimo> getAll() throws SQLException {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM tb_emprestimos";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setId(rs.getInt("id"));
                e.setUsuario_id(rs.getInt("usuario_id"));
                e.setLivro_id(rs.getInt("livro_id"));
                e.setFuncionario_id(rs.getInt("funcionario_id"));
                emprestimos.add(e);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return emprestimos;
        }
    }

    public static Emprestimo inserir (String cpf, String isbn, String cpfU) {
        String sql = "INSERT INTO tb_emprestimos(usuario_id, livro_id, funcionario_id) VALUES (?,?, ?)";
        Emprestimo e = new Emprestimo();

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setInt(1, UsuarioCrud.getByCpf(cpf).getId());
            stm.setInt(2, LivroCrud.getByIsbn(isbn).getId());
            stm.setInt(3, FuncionarioCrud.getByCpf(cpfU).getId());
            if(!LivroCrud.getByIsbn(isbn).isDisponivel()) {
                System.out.println("Livro ja emprestado");
            } else {
                stm.execute();
            }
        } catch (SQLException er) {
            System.out.println(er.getMessage());
        }
        finally {
            return e;
        }
    }

    public static void excluir (String cpf, String isbn) {
        String sql = "DELETE FROM tb_emprestimos WHERE id = ?";
        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setInt(1, EmprestimoCrud.getByCpfEIsbn(cpf, isbn).getId());
            stm.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static Emprestimo getByCpfEIsbn(String cpf, String isbn) {
        String sql = "SELECT * FROM tb_emprestimos WHERE usuario_id = ? AND livro_id = ?";
        Emprestimo e = new Emprestimo();

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setInt(1, UsuarioCrud.getByCpf(cpf).getId());
            stm.setInt(2, LivroCrud.getByIsbn(isbn).getId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                e.setId(rs.getInt("id"));
                e.setUsuario_id(rs.getInt("usuario_id"));
                e.setLivro_id(rs.getInt("livro_id"));
                e.setFuncionario_id(rs.getInt("funcionario_id"));
                e.setDataEmprestimo(String.valueOf(rs.getDate("data_emprestimo")));
            } else {
                System.out.println("Não Encontrado! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            return e;
        }
    }

    public static List<Emprestimo> getByCpf(String cpf) {
        String sql = "SELECT * FROM tb_emprestimos WHERE usuario_id = ?";
        List<Emprestimo> emprestimos = new ArrayList<>();

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setInt(1, UsuarioCrud.getByCpf(cpf).getId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setId(rs.getInt("id"));
                e.setUsuario_id(rs.getInt("usuario_id"));
                e.setLivro_id(rs.getInt("livro_id"));
                e.setFuncionario_id(rs.getInt("funcionario_id"));
                e.setDataEmprestimo(String.valueOf(rs.getDate("data_emprestimo")));
                emprestimos.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            return emprestimos;
        }
    }
}
