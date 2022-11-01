package ifsc.leopoldobeffartweber.atividade5.control;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ifsc.leopoldobeffartweber.atividade5.R;
import ifsc.leopoldobeffartweber.atividade5.dao.AmigosDB;
import ifsc.leopoldobeffartweber.atividade5.model.Amigos;

public class AlterarDadosDoAmigo extends AppCompatActivity {
    Amigos amigos;
    AmigosDB amigosDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_dados_do_amigo);

        EditText edtNome = (EditText) findViewById(R.id.edtNome);
        EditText  edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        EditText edtEmail = (EditText) findViewById(R.id.edtEmail);
        EditText edtAniversario = (EditText) findViewById(R.id.edtData);
        Button btnAdd = (Button) findViewById(R.id.btnSalvar);

        amigos = (Amigos) getIntent().getSerializableExtra("amigo");

        btnAdd.setOnClickListener(view -> {

            if (!edtNome.getText().toString().isEmpty() || !edtEmail.getText().toString().isEmpty() || !edtTelefone.getText().toString().isEmpty() || !edtAniversario.getText().toString().isEmpty()) {

                if (edtNome.getText().toString() != amigos.getNome() || edtEmail.getText().toString() != amigos.getEmail() || edtTelefone.getText().toString() != amigos.getTelefone() || edtAniversario.getText().toString() != amigos.getAniversario()) {

                    amigos = new Amigos();
                    amigos.setNome(edtNome.getText().toString());
                    amigos.setEmail(edtEmail.getText().toString());
                    amigos.setTelefone(edtTelefone.getText().toString());
                    amigos.setAniversario(edtAniversario.getText().toString());

                    if (amigosDB.alterarDados(amigos) == 1) {
                        Toast.makeText(getApplicationContext(), "dados salvos com sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao salvar os dados!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "ERRO! todos os dados são iguais aos já salvos!", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Preencha todos os campos para alterar os dados!", Toast.LENGTH_LONG).show();

                if (edtNome.getText().toString().isEmpty()) {
                    //adicionar borda vermelha
                }
                else if (edtEmail.getText().toString().isEmpty()) {
                    //adicionar borda vermelha
                }
                else if (edtTelefone.getText().toString().isEmpty()) {
                    //adicionar borda vermelha
                }
                else if (edtAniversario.getText().toString().isEmpty()) {
                    //adicionar borda vermelha
                }
            }
        }); // final onclick
    }
}