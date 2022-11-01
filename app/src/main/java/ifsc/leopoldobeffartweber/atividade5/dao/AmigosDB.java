package ifsc.leopoldobeffartweber.atividade5.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import ifsc.leopoldobeffartweber.atividade5.model.Amigos;

public class AmigosDB extends SQLiteOpenHelper {

    private static final String NOME_DB = "listaAmigos.db";
    private  static final int VERSAO = 1;
    private  static  final  String TABELA = "Tb_amigos";
    public AmigosDB(@Nullable Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABELA + " ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "nome TEXT, " +
                                            "email TEXT, " + "telefone TEXT, " + "aniversario TEXT " + " ); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public Long incluirAmigo(Amigos a) {
        ContentValues values = new ContentValues();
        values.put("nome", a.getNome());
        values.put("email", a.getEmail());
        values.put("telefone", a.getTelefone());
        values.put("aniversario", a.getAniversario());

        return getWritableDatabase().insert(TABELA, null, values);
    }

    public long alterarDados (Amigos a) {
        ContentValues values = new ContentValues();
        values.put("nome", a.getNome());
        values.put("email", a.getEmail());
        values.put("telefone", a.getTelefone());
        values.put("aniversario", a.getAniversario());
        String[] idAlt = {String.valueOf(a.getId())};

        return getWritableDatabase().update(TABELA, values, "id=?", idAlt);
    }

    public void deletarAmigo (Amigos a) {
        SQLiteDatabase db = getWritableDatabase();

        String[] idDel = {String.valueOf(a.getId())};

        db.delete(TABELA, "id=", idDel);
    }

    public ArrayList<Amigos> listarAmigos () {

        String[] colunas = {"id", "nome", "email", "telefone", "aniversario"};
        @SuppressLint("Recycle") Cursor cursor = getWritableDatabase().query(TABELA, colunas, null, null, null, null, "nome", null);
        ArrayList<Amigos> listaAmigos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Amigos a = new Amigos();
            a.setId(cursor.getLong(0));
            a.setNome(cursor.getString(1));
            a.setEmail(cursor.getString(2));
            a.setTelefone(cursor.getString(3));
            a.setAniversario(cursor.getString(4));
            listaAmigos.add(a);
        }
        return listaAmigos;
    }
}
