package Cruds;
import Models.Usuario;
import db.ConexaoDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioCrud {
    public static List<Usuario> getAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        String sql = "SELECT * FROM tb_usuarios";
        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setEmprestimosAtivos(rs.getInt("emprestimos_ativos"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return usuarios;
        }
    }

    public static Usuario getById(int id) throws SQLException {
        Usuario u = new Usuario();
        String sql = "SELECT * FROM tb_usuarios WHERE id = ?";
        PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        try {
            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setEmprestimosAtivos(rs.getInt("emprestimos_ativos"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            return u;
        }
    }

    public static void excluir(int id) {
        String sql = "DELETE FROM tb_usuarios WHERE id = ?";
        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void alterar(Usuario usuario) {
        String sql = "UPDATE tb_usuarios SET nome = ?, cpf = ? WHERE id = ?";
        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getCpf());
            stm.setInt(3, usuario.getId());
            stm.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Usuario inserir(Usuario usuario) {
        String sql = "INSERT INTO tb_usuarios(nome, cpf) VALUES (?, ?)";
        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1,usuario.getNome());
            stm.setString(2,usuario.getCpf());
            stm.execute();
            System.out.println("Models.Usuario cadastrado com sucesso!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return usuario;
        }
    }

    public static Usuario getByCpf(String cpf) {
        Usuario u = new Usuario();
        String sql = "SELECT * FROM tb_usuarios WHERE cpf = ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1,cpf);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setCpf(rs.getString("cpf"));
                u.setNome(rs.getString("nome"));
                u.setEmprestimosAtivos(rs.getInt("emprestimos_ativos"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            return u;
        }
    }

    public static Usuario getByNome(String nome) {
        Usuario u = new Usuario();
        String sql = "SELECT * FROM tb_usuarios WHERE nome like ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1,nome + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setEmprestimosAtivos(rs.getInt("emprestimos_ativos"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            return u;
        }
    }

    public static void setEmprestimosAtivos(Usuario usuario) {
        String sql = "UPDATE tb_usuario SET emprestimos_ativos = emprestimos_ativos + 1 WHERE id = ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setInt(1,usuario.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}