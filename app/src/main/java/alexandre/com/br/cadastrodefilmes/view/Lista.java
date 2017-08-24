package alexandre.com.br.cadastrodefilmes.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import alexandre.com.br.cadastrodefilmes.adapter.ListaAdapter;
import alexandre.com.br.cadastrodefilmes.R;
import alexandre.com.br.cadastrodefilmes.model.Filme;

public class Lista extends AppCompatActivity {

    private Toolbar mToolbar;
    private Context context;
    private ListView lista;

    private List<Filme> filmes = new ArrayList<>();

    private BaseAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Intent intent = getIntent();

        String filtro = intent.getStringExtra("filtro");

        if (filtro != null && !filtro.equals("")) {

            for (Filme f : MainActivity.titulos) {
                if (f.getGenero().equals(filtro)) {
                    filmes.add(f);
                }
            }
        } else {
            filmes = MainActivity.titulos;
        }


        this.lista = (ListView) findViewById(R.id.lista);


        this.mToolbar = (Toolbar) findViewById(R.id.toolbar_lista);
        this.mToolbar.setTitle(R.string.listafilmes);
        setSupportActionBar(this.mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_voltar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        this.mAdapter = new ListaAdapter(this, R.layout.item_titulo, filmes);
        this.lista.setAdapter(mAdapter);

    }
}
