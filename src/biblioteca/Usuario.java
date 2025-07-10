package biblioteca;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private PerfilUsuario perfil;

    public Usuario(int id, String nome, String email, String telefone, String senha, PerfilUsuario perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.perfil = perfil;
    }
    
    public Usuario(String nome, String email, String telefone, String senha, PerfilUsuario perfil) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.perfil = perfil;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public PerfilUsuario getPerfil() { return perfil; }
    public String getSenha() { return senha; }

    public void consultarLivros() {
        System.out.println(nome + " está consultando livros.");
    }

    public void solicitarEmprestimo(Livro livro) {
        if (livro.isDisponivel()) {
            System.out.println(nome + " solicitou empréstimo do livro: " + livro.getTitulo());
        } else {
            System.out.println("Livro não disponível para empréstimo.");
        }
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome='" + nome + '\'' +
                ", email='" + email + '\'' + ", telefone='" + telefone + '\'' +
                ", perfil=" + perfil + '}';
    }
}
