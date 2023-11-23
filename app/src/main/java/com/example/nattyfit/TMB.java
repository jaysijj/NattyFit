package com.example.nattyfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TMB extends AppCompatActivity {
    private TextView textResultadoTmb;
    private EditText editPesoTMB, editAlturaTmb, editIdadeTmb;
    private RadioGroup radioGroup;
    private RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmb);

        textResultadoTmb = findViewById(R.id.textResultadoTmb);
        editPesoTMB = findViewById(R.id.editPesoTMB);
        editAlturaTmb = findViewById(R.id.editAlturaTmb);
        editIdadeTmb = findViewById(R.id.editIdadeTmb);
        radioGroup = findViewById(R.id.radioGroup);
    }

    public void calcular_tmb(View view) {
        try {
            double peso = Double.parseDouble(editPesoTMB.getText().toString());
            double altura = Double.parseDouble(editAlturaTmb.getText().toString());
            double idade = Double.parseDouble(editIdadeTmb.getText().toString());
            double resultado;

            int selectId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(selectId);
            if (selectId == -1) {
                Toast.makeText(TMB.this, "Selecione o sexo", Toast.LENGTH_SHORT).show();
                return; // Encerra a função se o sexo não estiver selecionado
            }

            if (radioButton.getId() == R.id.radioButtonHomem) {
                // Equação de Harris-Benedict
                resultado = 88.365 + (13.397 * peso) + (4.799 * altura) - (5.677 * idade);
            } else {
                resultado = 447.593 + (9.247 * peso) + (3.098 * altura) - (4.330 * idade);
            }

            String resultadoFormatado = String.format("%.2f", resultado);
            textResultadoTmb.setText(resultadoFormatado + "");
        } catch (NumberFormatException e) {
            // Trata exceção se a conversão de String para double falhar
            Toast.makeText(TMB.this, R.string.inserirValorValido_TMB, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Trata outras exceções inesperadas
            Toast.makeText(TMB.this, R.string.erroInesperado_TMB, Toast.LENGTH_SHORT).show();
            e.printStackTrace(); // Isso imprime a pilha de chamadas para facilitar a depuração
        }
    }

}