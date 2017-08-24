package alexandre.com.br.cadastrodefilmes.model;

import java.io.Serializable;

/**
 * Created by projetos1 on 24/08/2017.
 */

public class Filme implements Serializable {

    private String titulo;
    private String genero;
    private String nota;


    public Filme() {
    }

    public Filme(String titulo, String genero, String nota) {
        this.titulo = titulo;
        this.genero = genero;
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
