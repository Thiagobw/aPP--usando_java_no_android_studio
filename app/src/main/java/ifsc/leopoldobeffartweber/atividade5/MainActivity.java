package ifsc.leopoldobeffartweber.atividade5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ifsc.leopoldobeffartweber.atividade5.control.AlterarDadosDoAmigo;
import ifsc.leopoldobeffartweber.atividade5.control.CadastroAmigosActivity;
import ifsc.leopoldobeffartweber.atividade5.dao.AmigosDB;
import ifsc.leopoldobeffartweber.atividade5.model.Amigos;

public class MainActivity extends AppCompatActivity {
    ListView listagemDeAmigos;
    Amigos amigos;
    AmigosDB amigosDB;
    ArrayList<Amigos> listaAmigos;
    ArrayAdapter<Amigos> amigosArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listagemDeAmigos = findViewById(R.id.listaAmigos);

        FloatingActionButton btnAddAmigo = findViewById(R.id.btnAddAmigo);


        btnAddAmigo.setOnClickListener(this::abrirCadastro);


        /*==============================  alteração  de dados do amigo ==============================*/
        listagemDeAmigos.setOnItemClickListener((adapterView, view, i, l) -> {
            // pegando a posição selecionada
             amigos = listaAmigos.get(i);
            Intent intent = new Intent(MainActivity.this, AlterarDadosDoAmigo.class);
            intent.putExtra("amigo", amigos);
            startActivity(intent);
        });

        listagemDeAmigos.setLongClickable(true);

        /*==============================  exclusão do amigo selecionado ==============================*/
        listagemDeAmigos.setOnItemClickListener((adapterView, view, i, l) -> {
            // pegando a posição selecionada
            amigos = listaAmigos.get(i);

            //mensdagem de confirmação de exclusão
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("tem certeza que você deseja excluir o amigo"+ amigos.getNome()+"?");

            // confirmar exclusão
            builder.setPositiveButton("SIM", (dialogInterface, i1) -> {
                amigosDB.deletarAmigo(amigos);
                atualizarListagem();

                Toast.makeText(getApplicationContext(), "Amigo excluido com sucesso!", Toast.LENGTH_LONG).show();
            });

            //cancelar exclusão
            builder.setNegativeButton("NÃO", (dialogInterface, i12) -> Toast.makeText(getApplicationContext(), "Exclusão cancelada!", Toast.LENGTH_LONG).show());
            builder.setTitle("Excluir");
            builder.setIcon(R.drawable.ic_person_add);
            builder.show();
        });
    }
    private void abrirCadastro(View view) {
        Intent i = new Intent(MainActivity.this, CadastroAmigosActivity.class);
        startActivity(i);
    }

    public void atualizarListagem () {
        amigosDB = new AmigosDB(MainActivity.this);
        listaAmigos = amigosDB.listarAmigos();
        amigosDB.close();

        if (listagemDeAmigos != null) {
            amigosArrayAdapter = new ArrayAdapter<Amigos>(MainActivity.this, android.R.layout.activity_list_item, listaAmigos);

            listagemDeAmigos.setAdapter(amigosArrayAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarListagem();
    }
}