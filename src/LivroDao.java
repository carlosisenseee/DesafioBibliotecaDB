import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDao {
    public static List<Livro> getAll() {
        List<Livro> livros = new ArrayList<Livro>();
        String sql = "SELECT * FROM tb_livros";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Livro l = new Livro();
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAnoPublicacao(rs.getInt("anoPublicacao"));
                l.setIsbn(rs.getString("isbn"));
                livros.add(l);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return livros;
        }
    }

    public static Livro getById(int id) {
        Livro l = new Livro();
        String sql = "SELECT * FROM tb_livros WHERE id = ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
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

    public static void excluir(int id) {
        String sql = "DELETE FROM tb_livros WHERE id = ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void alterar(Livro livro) {
        String sql = "UPTADE tb_livros SET titulo = ?, autor = ?, anoPublicacao = ?, isbn = ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1, livro.getTitulo());
            stm.setString(2, livro.getAutor());
            stm.setInt(3, livro.getAnoPublicacao());
            stm.setString(4, livro.getIsbn());
            stm.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void inserir(Livro livro) {
        String sql = "INSERT INTO tb_livros(titulo, autor, anoPublicacao, isbn) VALUES (?, ?, ?, ?)";

        
    }
}
