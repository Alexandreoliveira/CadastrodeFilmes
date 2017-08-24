package alexandre.com.br.cadastrodefilmes.view;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import alexandre.com.br.cadastrodefilmes.R;
import alexandre.com.br.cadastrodefilmes.model.Filme;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Context context;
    private EditText titulo;
    private MaterialBetterSpinner genero;
    private MaterialBetterSpinner nota;
    private Button salvar;
    private Button listar;
    private Button filtrar;

    public static List<Filme> titulos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;


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


        titulos = lefilme();


        this.listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLista();
            }
        });

        this.filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLista(genero.getText().toString());
            }
        });

        this.salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (titulo.getText().toString().equals("")) {
                    Toast.makeText(context, "Insira o título do filme", Toast.LENGTH_SHORT).show();
                    titulo.requestFocus();
                    return;
                }
                if (genero.getText().toString().equals("")) {
                    Toast.makeText(context, "Insira o gênero do filme", Toast.LENGTH_SHORT).show();
                    genero.requestFocus();
                    return;
                }
                if (nota.getText().toString().equals("")) {
                    Toast.makeText(context, "Insira a nota do filme", Toast.LENGTH_SHORT).show();
                    nota.requestFocus();
                    return;
                }

                try {
                    Filme f = new Filme(titulo.getText().toString(), genero.getText().toString(), nota.getText().toString());
                    titulos.add(f);
                    salvaFilme(f);
                    genero.setText("");
                    genero.clearListSelection();
                    nota.setText("");
                    nota.clearListSelection();
                    titulo.setText("");
                    titulo.requestFocus();
                    Toast.makeText(context, "Filme salvo com sucesso!", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(context, "Erro ao salvar filme", Toast.LENGTH_SHORT).show();
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


    private void salvaFilme(Filme filme) {


        File logFile = new File(context.getFilesDir(), "filmes.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                Toast.makeText(context, "Erro ao salvar filme", Toast.LENGTH_SHORT).show();
            }
        }
        try {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(filme.getTitulo() + ";" + filme.getGenero() + ";" + filme.getNota());
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            Toast.makeText(context, "Erro ao salvar filme", Toast.LENGTH_SHORT).show();
        }
    }

    private List<Filme> lefilme() {

        BufferedReader br = null;
        File arq;
        String lstrlinha;
        List<Filme> filmes = new ArrayList<>();

        try {
            arq = new File(context.getFilesDir() + "/filmes.txt");
            br = new BufferedReader(new FileReader(arq));

            while ((lstrlinha = br.readLine()) != null) {

                String[] filme = lstrlinha.split(";");
                filmes.add(new Filme(filme[0], filme[1], filme[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filmes;
    }


}


