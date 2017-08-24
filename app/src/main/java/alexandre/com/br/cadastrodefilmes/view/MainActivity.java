package alexandre.com.br.cadastrodefilmes.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import alexandre.com.br.cadastrodefilmes.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Context context;
    private EditText titulo;
    private MaterialBetterSpinner genero;
    private MaterialBetterSpinner nota;
    private Button salvar;
    private Button listar;
    private Button filtrar;

    public static List<String> titulos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.titulo = (EditText) findViewById(R.id.nome_filme);
        this.listar = (Button) findViewById(R.id.listar);
        this.salvar = (Button) findViewById(R.id.salvar);
        this.filtrar = (Button) findViewById(R.id.filtrar);

        this.genero = (MaterialBetterSpinner) findViewById(R.id.genero);
        this.nota = (MaterialBetterSpinner) findViewById(R.id.nota);

        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        this.mToolbar.setTitle(R.string.cadastrofilmes);
        setSupportActionBar(this.mToolbar);


        String[] list1 = getResources().getStringArray(R.array.generos_filmes);
        String[] list2 = getResources().getStringArray(R.array.nota_filmes);


        this.genero.setAdapter(new ArrayAdapter<>(this, R.layout.layout_spinner, list1));
        this.nota.setAdapter(new ArrayAdapter<>(this, R.layout.layout_spinner, list2));


        this.listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLista();
            }
        });

        this.filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLista();
            }
        });

        this.salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!titulo.getText().toString().equals("")) {

                    titulos.add(titulo.getText().toString());
                    genero.setText("");
                    genero.clearListSelection();
                    nota.setText("");
                    nota.clearListSelection();
                    titulo.setText("");
                }
            }
        });
    }

    private void goLista() {
        Intent intent = new Intent(this, Lista.class);
        startActivity(intent);
    }

    private void goLista(String filtro) {
        Intent intent = new Intent(this, Lista.class);
        intent.putExtra("filtro", filtro);
        startActivity(intent);
    }

}
