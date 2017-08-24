package alexandre.com.br.cadastrodefilmes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import alexandre.com.br.cadastrodefilmes.R;
import alexandre.com.br.cadastrodefilmes.model.Filme;

/**
 * Created by Alexandre on 23/08/2017.
 */

public class ListaAdapter extends ArrayAdapter<Filme> {


    private List<Filme> listaFilmes;
    private Context context;
    private LayoutInflater inflater;


    private TextView titulo;
    private TextView genero;
    private TextView nota;

    public ListaAdapter(Context context, int ResourceId, List<Filme> filmes) {
        super(context, ResourceId, filmes);

        this.context = context;
        this.listaFilmes = filmes;

    }

    @Override
    public int getCount() {
        return listaFilmes.size();
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup parent) {

        if (view == null) {
            this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = this.inflater.inflate(R.layout.item_titulo, parent, false);
        }

        this.titulo = view.findViewById(R.id.titulo);
        this.genero = view.findViewById(R.id.list_genero);
        this.nota = view.findViewById(R.id.list_nota);


        this.titulo.setText(getItem(posicao).getTitulo());
        this.genero.setText(getItem(posicao).getGenero());
        this.nota.setText(getItem(posicao).getNota());

        return view;
    }


}
