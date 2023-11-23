package com.example.nattyfit;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

import com.example.nattyfit.DataBase.DatabaseHelper;

public class ListView extends AppCompatActivity {
    // Exibir os dados do Banco de Dados no ListView

    private android.widget.ListView listView;


    private DatabaseHelper dbHelper;
//    private CustomCursorAdapter cursorAdapter; // Crie esta classe personalizada
    private SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        dbHelper = new DatabaseHelper(this);
        atualizarListView();    // Chama o método para atualizar os dados na ListView
    }

    private void atualizarListView() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);

        listView = findViewById(R.id.activity_lista_dados);


        String[] fromColumns = {DatabaseHelper.COLUMN_NOME,DatabaseHelper.COLUMN_ALTURA, DatabaseHelper.COLUMN_PESCOCO, DatabaseHelper.COLUMN_CINTURA, DatabaseHelper.COLUMN_QUADRIL, DatabaseHelper.COLUMN_SEXO, DatabaseHelper.COLUMN_RESULTADO};
        int[] toViews = {R.id.nomeTextView, R.id.alturaTextView, R.id.pescocoTextView, R.id.cinturaTextView, R.id.quadrilTextView, R.id.sexoTextView, R.id.resultadoTextView};

        cursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item_layout, cursor, fromColumns, toViews, 0);
        listView.setAdapter(cursorAdapter);

        db.close();
    }

}