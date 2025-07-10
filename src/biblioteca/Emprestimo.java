package biblioteca;

import java.util.Calendar;
import java.util.Date;

public class Emprestimo {
    private int id;
    private Livro livro;
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private boolean devolvido;

    public Emprestimo(int id, Livro livro, Usuario usuario, Date dataEmprestimo) {
        this.id = id;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.devolvido = false;
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataEmprestimo);
        cal.add(Calendar.WEEK_OF_YEAR, 3);
        this.dataDevolucao = cal.getTime();
    }

    public int getId() { return id; }
    public Livro getLivro() { return livro; }
    public Usuario getUsuario() { return usuario; }
    public Date getDataEmprestimo() { return dataEmprestimo; }
    public Date getDataDevolucao() { return dataDevolucao; }
    public boolean isDevolvido() { return devolvido; }

    public void marcarComoDevolvido() {
        devolvido = true;
        dataDevolucao = new Date();
        livro.devolver();
    }

    @Override
    public String toString() {
        return "Emprestimo{" + "id=" + id + ", livro=" + livro.getTitulo() +
                ", usuario=" + usuario.getNome() +
                ", dataEmprestimo=" + dataEmprestimo +
                ", devolvido=" + devolvido + '}';
    }
}
