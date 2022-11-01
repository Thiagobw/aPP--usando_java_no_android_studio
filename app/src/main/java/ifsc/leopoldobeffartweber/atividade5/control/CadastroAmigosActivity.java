package ifsc.leopoldobeffartweber.atividade5.control;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ifsc.leopoldobeffartweber.atividade5.R;
import ifsc.leopoldobeffartweber.atividade5.dao.AmigosDB;
import ifsc.leopoldobeffartweber.atividade5.model.Amigos;

public class CadastroAmigosActivity extends AppCompatActivity {

    Amigos amigo;
    AmigosDB amigosDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_amigos);

        EditText edtNome = findViewById(R.id.edtNome);
        EditText  edtTelefone = findViewById(R.id.edtTelefone);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtAniversario = findViewById(R.id.edtData);
        Button btnAdd = findViewById(R.id.btnSalvar);

        amigosDB = new AmigosDB(CadastroAmigosActivity.this);

        btnAdd.setOnClickListener(view -> {

            if (!edtNome.getText().toString().isEmpty() || !edtEmail.getText().toString().isEmpty() || !edtTelefone.getText().toString().isEmpty() || !edtAniversario.getText().toString().isEmpty()) {

                amigo = new Amigos();
                amigo.setNome(edtNome.getText().toString());
                amigo.setEmail(edtEmail.getText().toString());
                amigo.setTelefone(edtTelefone.getText().toString());
                amigo.setAniversario(edtAniversario.getText().toString());

                if (amigosDB.incluirAmigo(amigo) == 1) {
                    Toast.makeText(getApplicationContext(), "Amigo adicionado com suvesso!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao adicionar amigo!", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Preencha todos os campos para adicionar um amigo!", Toast.LENGTH_LONG).show();

                if (edtNome.getText().toString().isEmpty()) {
                    edtNome.setShadowLayer(5, 2, 2 , R.color.red);
                }
                else if (edtEmail.getText().toString().isEmpty()) {
                    edtEmail.setShadowLayer(5, 2, 2 , R.color.red);
                }
                else if (edtTelefone.getText().toString().isEmpty()) {
                    edtTelefone.setShadowLayer(5, 2, 2 , R.color.red);
                }
                else if (edtAniversario.getText().toString().isEmpty()) {
                    edtAniversario.setShadowLayer(5, 2, 2 , R.color.red);
                }
            }
        }); // final onclick
    }
}