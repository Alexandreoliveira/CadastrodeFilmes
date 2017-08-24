package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alexandre.com.br.cadastrodefilmes.R;

/**
 * Created by Alexandre on 23/08/2017.
 */

public class ListaAdapter extends ArrayAdapter<String> {


    private List<String> listaFilmes;
    private Context context;
    private LayoutInflater inflater;


    private TextView titulo;

    public ListaAdapter(Context context, int ResourceId, List<String> titulos) {
        super(context, ResourceId, titulos);

        this.context = context;
        this.listaFilmes = titulos;

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

        this.titulo = (TextView) view.findViewById(R.id.titulo);
        this.titulo.setText(getItem(posicao).toString());

        return view;
    }


}
