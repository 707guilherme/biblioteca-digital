package biblioteca;

public class Administrador extends Usuario {

    public Administrador(int id, String nome, String email, String telefone, String senha) {
        super(id, nome, email, telefone, senha, PerfilUsuario.ADMINISTRADOR);
    }
    
    public Administrador(String nome, String email, String telefone, String senha) {
        super(nome, email, telefone, senha, PerfilUsuario.ADMINISTRADOR);
    }

    public void cadastrarLivro(Livro livro, SistemaBiblioteca sistema) {
        sistema.adicionarLivro(livro);
        System.out.println("Livro cadastrado: " + livro.getTitulo());
    }

    public void cadastrarUsuario(Usuario usuario, SistemaBiblioteca sistema) {
        sistema.adicionarUsuario(usuario);
        System.out.println("Usuário cadastrado: " + usuario.getNome());
    }

    public void registrarEmprestimo(Emprestimo emprestimo, SistemaBiblioteca sistema) {
        sistema.registrarEmprestimo(emprestimo);
        System.out.println("Empréstimo registrado para o livro: " + emprestimo.getLivro().getTitulo());
    }

    public void registrarDevolucao(Emprestimo emprestimo, SistemaBiblioteca sistema) {
        sistema.registrarDevolucao(emprestimo);
        System.out.println("Devolução registrada para o livro: " + emprestimo.getLivro().getTitulo());
    }
}
