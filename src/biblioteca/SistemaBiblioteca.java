// Pacote: biblioteca
// Arquivo: SistemaBiblioteca.java
package biblioteca;

import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.UsuarioDAO;
import java.util.List;

public class SistemaBiblioteca {

    private final LivroDAO livroDAO;
    private final UsuarioDAO usuarioDAO;
    private final EmprestimoDAO emprestimoDAO;

    public SistemaBiblioteca() {
        this.livroDAO = new LivroDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.emprestimoDAO = new EmprestimoDAO();
    }

    public void adicionarLivro(Livro livro) {
        livroDAO.adicionar(livro);
    }

    public List<Livro> getLivros() {
        return livroDAO.listarTodos();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarioDAO.adicionar(usuario);
    }

    public List<Usuario> getUsuarios() {
        return usuarioDAO.listarTodos();
    }

    public Usuario buscarUsuario(String nome) {
        return usuarioDAO.buscarPorNome(nome);
    }

    public void registrarEmprestimo(Emprestimo emprestimo) {
        emprestimo.getLivro().emprestar();
        livroDAO.atualizar(emprestimo.getLivro());
        emprestimoDAO.registrar(emprestimo);
    }

    public void registrarDevolucao(Emprestimo emprestimo) {
        emprestimo.getLivro().devolver();
        emprestimo.marcarComoDevolvido();
        livroDAO.atualizar(emprestimo.getLivro());
        emprestimoDAO.atualizar(emprestimo);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimoDAO.listarTodos();
    }
    
    public Livro buscarLivro(String identificador) {
        List<Livro> livros = livroDAO.listarTodos();
        for (Livro livro : livros) {
            if (livro.getIsbn().equalsIgnoreCase(identificador)) {
                return livro;
            }
        }
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(identificador)) {
                return livro;
            }
        }
        return null;
    }
    
    public Usuario fazerLogin(String nome, String senha) {
        return usuarioDAO.autenticar(nome, senha);
    }
}