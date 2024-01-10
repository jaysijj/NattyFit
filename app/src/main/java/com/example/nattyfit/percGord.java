package com.example.nattyfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nattyfit.DataBase.DatabaseHelper;

public class percGord extends AppCompatActivity {

    private TextView textResultadoPG;
    private EditText editNomePG, editAlturaPG, editPescocoPG, editCinturaPG, editQuadrilPG;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_perc_gord);

        // Inicializar o DBHelper
        dbHelper = new DatabaseHelper(this);

        textResultadoPG = findViewById(R.id.textResultadoPG);
        editNomePG = findViewById(R.id.editNomePG);
        editAlturaPG = findViewById(R.id.editAlturaPG);
        editPescocoPG = findViewById(R.id.editPescocoPG);
        editCinturaPG = findViewById(R.id.editCinturaPG);
        editQuadrilPG = findViewById(R.id.editQuadrilPG);
        radioGroup = findViewById(R.id.radioGroup);
    }

    public void calcular_PG(View view) {
        try {
            double altura = Double.parseDouble(editAlturaPG.getText().toString());
            double pescoco = Double.parseDouble(editPescocoPG.getText().toString());
            double cintura = Double.parseDouble(editCinturaPG.getText().toString());
            double quadril = Double.parseDouble(editQuadrilPG.getText().toString());

            String saude;
            int selectId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(selectId);

            // Método Accu-Measure para cálculo de percentual de gordura
            Double resultado = 0.29288 * cintura + 0.0008 * pescoco - 0.0005 * quadril - 0.00002 * altura + 0.15845;

            if (selectId == -1) {
                Toast.makeText(percGord.this, "Selecione o sexo", Toast.LENGTH_SHORT).show();
                return; // Encerra a função se o sexo não estiver selecionado
            }

            if (radioButton.getId() == R.id.radioButtonHomem) {
                if (resultado > 23) {
                    saude = getString(R.string.muitoElevado_PG);
                } else if (resultado >= 21) {
                    saude = getString(R.string.elevado_PG);
                } else if (resultado >= 14) {
                    saude = getString(R.string.normal_PG);
                } else if (resultado >= 11) {
                    saude = getString(R.string.bom_PG);
                } else {
                    saude = getString(R.string.Atleta_PG);
                }
            } else {
                if (resultado > 31) {
                    saude = getString(R.string.muitoElevado_PG);
                } else if (resultado >= 29) {
                    saude = getString(R.string.elevado_PG);
                } else if (resultado >= 20) {
                    saude = getString(R.string.normal_PG);
                } else if (resultado >= 16) {
                    saude = getString(R.string.bom_PG);
                } else {
                    saude = getString(R.string.Atleta_PG);
                }
            }

            Toast.makeText(percGord.this, getString(R.string.percenGord_PG) + " " + saude, Toast.LENGTH_SHORT).show();

            String resultadoFormatado = String.format("%.2f", resultado);
            textResultadoPG.setText(resultadoFormatado + "%");

            salvarNoBancoDeDados(editNomePG.getText().toString(), altura, pescoco, cintura, quadril, radioButton.getText().toString(), Double.parseDouble(resultadoFormatado));
        } catch (NumberFormatException e) {
            // Trata exceção se a conversão de String para double falhar
            Toast.makeText(percGord.this, R.string.inserirValorValido_TMB, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Trata outras exceções inesperadas
            Toast.makeText(percGord.this, R.string.erroInesperado_TMB, Toast.LENGTH_SHORT).show();
            e.printStackTrace(); // Isso imprime a pilha de chamadas para facilitar a depuração
        }
    }


    private void salvarNoBancoDeDados(String nome, double altura, double pescoco, double cintura, double quadril, String sexo, double resultado) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOME, nome);
        values.put(DatabaseHelper.COLUMN_ALTURA, altura);
        values.put(DatabaseHelper.COLUMN_PESCOCO, pescoco);
        values.put(DatabaseHelper.COLUMN_CINTURA, cintura);
        values.put(DatabaseHelper.COLUMN_QUADRIL, quadril);
        values.put(DatabaseHelper.COLUMN_SEXO, sexo);
        values.put(DatabaseHelper.COLUMN_RESULTADO, resultado);

        long newRowId = db.insert(DatabaseHelper.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Toast.makeText(this,getString(R.string.salvoSucesso_PG), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.erroSalvar_PG), Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void verificarDados(View view) {
        Intent in = new Intent (percGord.this, ListView.class);
        startActivity(in);
    }
}
