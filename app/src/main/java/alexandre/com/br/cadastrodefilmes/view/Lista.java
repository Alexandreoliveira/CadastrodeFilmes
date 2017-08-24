package alexandre.com.br.cadastrodefilmes.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.BaseAdapter;
import android.widget.ListView;

import adapter.ListaAdapter;
import alexandre.com.br.cadastrodefilmes.R;

public class Lista extends AppCompatActivity {

    private Toolbar mToolbar;
    private Context context;
    private ListView lista;

    private BaseAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);


        this.lista = (ListView) findViewById(R.id.lista);


        this.mToolbar = (Toolbar) findViewById(R.id.toolbar_lista);
        this.mToolbar.setTitle(R.string.listafilmes);
        setSupportActionBar(this.mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_voltar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        this.mAdapter = new ListaAdapter(this, R.layout.item_titulo, MainActivity.titulos);
        this.lista.setAdapter(mAdapter);

    }
}
