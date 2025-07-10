package bibliotecadigital;

import biblioteca.*;

import java.util.Date;
import java.util.List;

public class BibliotecaDigital {

    public static void main(String[] args) {

        SistemaBiblioteca sistema = new SistemaBiblioteca();

        Administrador admin = new Administrador(1, "Ana", "ana@email.com", "99999-9999", "ana");

        Livro livro1 = new Livro(1, "O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", "123456789");
        Livro livro2 = new Livro(2, "1984", "George Orwell", "Distopia", "987654321");

        admin.cadastrarLivro(livro1, sistema);
        admin.cadastrarLivro(livro2, sistema);
    }
}
