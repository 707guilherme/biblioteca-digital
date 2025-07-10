package dao;

import biblioteca.Administrador;
import biblioteca.Emprestimo;
import biblioteca.Livro;
import biblioteca.PerfilUsuario;
import biblioteca.Usuario;
import db.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    public void registrar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo, data_devolucao, devolvido) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, emprestimo.getLivro().getId());
            pstmt.setInt(2, emprestimo.getUsuario().getId());
            pstmt.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            pstmt.setDate(4, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
            pstmt.setBoolean(5, emprestimo.isDevolvido());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao registrar empréstimo: " + e.getMessage());
        }
    }

    public void atualizar(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimos SET data_devolucao = ?, devolvido = ? WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
            pstmt.setBoolean(2, emprestimo.isDevolvido());
            pstmt.setInt(3, emprestimo.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar empréstimo: " + e.getMessage());
        }
    }

    public List<Emprestimo> listarTodos() {
        List<Emprestimo> emprestimos = new ArrayList<>();

        String sql = "SELECT e.id as emprestimo_id, e.data_emprestimo, e.data_devolucao, e.devolvido, "
                   + "l.id as livro_id, l.titulo, l.autor, l.genero, l.isbn, l.disponivel, "
                   + "u.id as usuario_id, u.nome, u.email, u.telefone, u.perfil, u.senha "
                   + "FROM emprestimos e "
                   + "JOIN livros l ON e.livro_id = l.id "
                   + "JOIN usuarios u ON e.usuario_id = u.id "
                   + "ORDER BY e.data_emprestimo DESC";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("livro_id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("genero"),
                        rs.getString("isbn")
                );
                if (!rs.getBoolean("disponivel")) {
                    livro.emprestar();
                }

                Usuario usuario;
                String perfil = rs.getString("perfil");
                if (PerfilUsuario.ADMINISTRADOR.name().equals(perfil)) {
                    usuario = new Administrador(
                            rs.getInt("usuario_id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("telefone"),
                            rs.getString("senha")
                    );
                } else {
                    usuario = new Usuario(
                            rs.getInt("usuario_id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("telefone"),
                            rs.getString("senha"),
                            PerfilUsuario.USUARIO_COMUM
                    );
                }

                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("emprestimo_id"),
                        livro,
                        usuario,
                        rs.getDate("data_emprestimo")
                );
                if(rs.getBoolean("devolvido")) {
                    emprestimo.marcarComoDevolvido();
                }

                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return emprestimos;
    }
}