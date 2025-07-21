package Cruds;
import db.ConexaoDB;
import Models.Funcionario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioCrud {
    public static List<Funcionario> getAll() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM tb_funcionarios";
        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setCargo(rs.getString("cargo"));
                funcionarios.add(f);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return funcionarios;
        }
    }

    public static Funcionario getById(int id) throws SQLException {
        Funcionario f = new Funcionario();
        String sql = "SELECT * FROM tb_funcionarios WHERE id = ?";
        PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        try {
            if (rs.next()) {
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setCargo(rs.getString("cargo"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return f;
        }
    }

    public static int excluir(int id) {
        String sql = "DELETE FROM tb_funcionarios WHERE id = ?";
        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setInt(1, id);
            return stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static int alterar(Funcionario funcionario) {
        String sql = "UPDATE tb_funcionarios SET nome = ?, cpf = ?, cargo = ? WHERE id = ?";
        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1, funcionario.getNome());
            stm.setString(2, funcionario.getCpf());
            stm.setString(3, funcionario.getCargo());
            stm.setInt(4, funcionario.getId());
            return stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static Funcionario inserir(Funcionario funcionario) {
        String sql = "INSERT INTO tb_funcionarios(nome, cpf, cargo, usuario, senha) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1, funcionario.getNome());
            stm.setString(2, funcionario.getCpf());
            stm.setString(3, funcionario.getCargo());
            stm.setString(4, funcionario.getUsuario());
            stm.setString(5, funcionario.getSenha());
            stm.execute();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Funcionario com cpf ja cadastrado\n");
            }
            System.out.println(e.getMessage());
        } finally {
            return funcionario;
        }
    }

    public static Funcionario getByNome(String nome) {
        Funcionario f = new Funcionario();
        String sql = "SELECT * FROM tb_funcionarios WHERE nome = ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1, nome);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setCpf(rs.getString("cargo"));
                f.setUsuario(rs.getString("usuario"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return f;
        }
    }

    public static Funcionario getByCpf(String cpf) {
        Funcionario f = new Funcionario();
        String sql = "SELECT * FROM tb_funcionarios WHERE cpf = ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1, cpf);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setCargo(rs.getString("cargo"));
                f.setUsuario(rs.getString("usuario"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return f;
        }
    }

    public static Funcionario login(Funcionario f) {
        Funcionario funcionario = new Funcionario();
        String sql = "SELECT * FROM tb_funcionarios WHERE usuario = ? AND senha = ?";

        try {
            PreparedStatement stm = ConexaoDB.getConexao().prepareStatement(sql);
            stm.setString(1, f.getUsuario());
            stm.setString(2, f.getSenha());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setUsuario(rs.getString("usuario"));
                return funcionario;
            } else {
                System.out.println("Usuario ou senha incorretos");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}