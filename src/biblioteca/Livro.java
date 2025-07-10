package biblioteca;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private String isbn;
    private boolean disponivel;

    public Livro(int id, String titulo, String autor, String genero, String isbn) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.isbn = isbn;
        this.disponivel = true;
    }
    
    public Livro(String titulo, String autor, String genero, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.isbn = isbn;
        this.disponivel = true;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getGenero() { return genero; }
    public String getIsbn() { return isbn; }
    public boolean isDisponivel() { return disponivel; }

    public void emprestar() {
        if (disponivel) {
            disponivel = false;
        } else {
            System.out.println("Livro não está disponível para empréstimo.");
        }
    }

    public void devolver() {
        disponivel = true;
    }

    @Override
    public String toString() {
        return "Livro{" + "id=" + id + ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' + ", genero='" + genero + '\'' +
                ", isbn='" + isbn + '\'' + ", disponivel=" + disponivel + '}';
    }
}
