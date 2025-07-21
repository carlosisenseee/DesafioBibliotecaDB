package Cruds;
import db.ConexaoDB;
import Models.Livro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroCrud {
    public static List<Livro> getAll() throws SQLException{
        List<Livro> livros = new ArrayList<Livro>();
        String sql = "SELECT * FROM tb_livros";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Livro l = new Livro();
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAnoPublicacao(rs.getInt("anoPublicacao"));
                l.setIsbn(rs.getString("isbn"));
                l.setDisponivel(rs.getBoolean("disponivel"));
                livros.add(l);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return livros;
        }
    }

    public static Livro getById(int id) throws SQLException {
        Livro l = new Livro();
        String sql = "SELECT * FROM tb_livros WHERE id = ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAnoPublicacao(rs.getInt("anoPublicacao"));
                l.setIsbn(rs.getString("isbn"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return l;
        }
    }

    public static int excluir(int id) {
        String sql = "DELETE FROM tb_livros WHERE id = ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setInt(1, id);
            return stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static int alterar(Livro livro) {
        String sql = "UPDATE tb_livros SET titulo = ?, autor = ?, anoPublicacao = ?, isbn = ? WHERE id = ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1, livro.getTitulo());
            stm.setString(2, livro.getAutor());
            stm.setInt(3, livro.getAnoPublicacao());
            stm.setString(4, livro.getIsbn());
            stm.setInt(5, livro.getId());
            return stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static void inserir(Livro livro) {
        String sql = "INSERT INTO tb_livros(titulo, autor, anoPublicacao, isbn) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1,livro.getTitulo());
            stm.setString(2,livro.getAutor());
            stm.setInt(3,livro.getAnoPublicacao());
            stm.setString(4,livro.getIsbn());
            stm.execute();
            System.out.println("Livro cadastrado com sucesso!\n");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Já existem um livro com esse ISBN\n");
            } else {
                System.out.println(e.getMessage() + "\n");
            }
        }

    }

    public static Livro getByIsbn(String isbn) {
        Livro l = new Livro();
        String sql = "SELECT * FROM tb_livros WHERE isbn = ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1,isbn);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAnoPublicacao(rs.getInt("anoPublicacao"));
                l.setIsbn(rs.getString("isbn"));
                l.setDisponivel(rs.getBoolean("disponivel"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            return l;
        }
    }

    public static Livro getByTitulo(String titulo) {
        Livro l = new Livro();
        String sql = "SELECT * FROM tb_livros WHERE titulo like ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1,titulo + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAnoPublicacao(rs.getInt("anoPublicacao"));
                l.setIsbn(rs.getString("isbn"));
                l.setDisponivel(rs.getBoolean("disponivel"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            return l;
        }
    }
    }
