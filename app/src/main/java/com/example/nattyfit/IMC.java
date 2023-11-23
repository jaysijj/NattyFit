package com.example.nattyfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IMC extends AppCompatActivity {

    private TextView textResultado;
    private EditText editPeso, editAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        textResultado = findViewById(R.id.textResultado);
        editPeso = findViewById(R.id.editPeso);
        editAltura = findViewById(R.id.editAltura);

    }

    public void calcular_imc(View view) {
        EditText editPeso = findViewById(R.id.editPeso);
        EditText editAltura = findViewById(R.id.editAltura);
        TextView textResultado = findViewById(R.id.textResultado);

        if (editPeso != null && editAltura != null && textResultado != null) {
            try {
                double peso = Double.parseDouble(editPeso.getText().toString());
                double altura = Double.parseDouble(editAltura.getText().toString());

                if (altura != 0) {
                    double resultado = peso / (altura * altura) * 10000;
                    String resultadoFormatado = String.format("%.2f", resultado);
                    textResultado.setText(resultadoFormatado);

                    String saude;
                    if (resultado >= 40){
                        saude = getString(R.string.obesidadeIII_IMC);
                    } else if (resultado >= 35){
                        saude = getString(R.string.obesidadeII_IMC);
                    } else if (resultado >= 30){
                        saude = getString(R.string.obesidadeI_IMC);
                    } else if (resultado >= 25){
                        saude = getString(R.string.sobrepeso_IMC);
                    } else if (resultado >= 18.5){
                        saude = getString(R.string.normal_IMC);
                    } else {
                        saude = getString(R.string.abaixoPeso_IMC);
                    }

                    Toast.makeText(this, getString(R.string.resultadoDo_IMC) + " " + saude, Toast.LENGTH_SHORT).show();
                } else {
                    // Altura é zero, tratar o caso
                    Toast.makeText(this, R.string.alturaZero_IMC, Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                // Tratar o caso em que peso ou altura não são números válidos
                Toast.makeText(this, R.string.pesoAlturaInvalido_IMC, Toast.LENGTH_SHORT).show();
            }
        } else {
            // Alguma das vistas é nula, tratar o caso
            Toast.makeText(this, R.string.naoEncontrado_IMC, Toast.LENGTH_SHORT).show();
        }
    }

}