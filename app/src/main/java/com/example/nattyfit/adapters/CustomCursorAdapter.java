package com.example.nattyfit.adapters;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.nattyfit.DataBase.DatabaseHelper;
import com.example.nattyfit.R;

public class CustomCursorAdapter extends CursorAdapter {
    // Fornece de maneira personalizada a vinculação dos dados para a ListView

    public CustomCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflar o layout para cada item da ListView
        return LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Preencher os dados nos elementos do layout

        TextView nomeTextView = view.findViewById(R.id.nomeTextView);
        TextView alturaTextView = view.findViewById(R.id.alturaTextView);
        TextView pescocoTextView = view.findViewById(R.id.pescocoTextView);
        TextView cinturaTextView = view.findViewById(R.id.cinturaTextView);
        TextView quadrilTextView = view.findViewById(R.id.quadrilTextView);
        TextView sexoTextView = view.findViewById(R.id.sexoTextView);
        TextView resultadoTextView = view.findViewById(R.id.resultadoTextView);

        String nome = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOME));
        double altura = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ALTURA));
        double pescoco = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PESCOCO));
        double cintura = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CINTURA));
        double quadril = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_QUADRIL));
        String sexo = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SEXO));
        double resultado = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RESULTADO));

        nomeTextView.setText("Nome: " + nome);
        alturaTextView.setText("Altura: " + String.valueOf(altura));
        pescocoTextView.setText("Pescoco: " + String.valueOf(pescoco));
        cinturaTextView.setText("Cintura: " + String.valueOf(cintura));
        quadrilTextView.setText("Quadril: " + String.valueOf(quadril));
        sexoTextView.setText("Sexo: " + sexo);
        resultadoTextView.setText("Resultado: " + String.format("%.2f%%", resultado));
    }
}
