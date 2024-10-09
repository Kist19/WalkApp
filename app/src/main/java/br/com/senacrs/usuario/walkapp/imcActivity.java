package br.com.senacrs.usuario.walkapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class imcActivity extends AppCompatActivity {
    EditText edtPeso, edtAltura;
    Button btnCalcularImc, btnIrWalk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imc); // Certifique-se de que este layout existe

        btnCalcularImc = findViewById(R.id.btnCalcularImc);
        edtAltura = findViewById(R.id.edtAltura);
        edtPeso = findViewById(R.id.edtPeso);
        btnIrWalk = findViewById(R.id.btnIrWalk);

        btnCalcularImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularImc();
            }
        });

        btnIrWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Volta para a MainActivity
            }
        });
    }

    private void calcularImc() {
        double peso, altura;
        String classificacao;
        try {
            peso = Double.parseDouble(edtPeso.getText().toString());
            altura = Double.parseDouble(edtAltura.getText().toString());
            double resultadoImc = peso / (altura * altura);

            if (resultadoImc < 18.5) {
                classificacao = "Abaixo do peso";
            } else if (resultadoImc >= 18.5 && resultadoImc < 24.9) {
                classificacao = "Peso normal";
            } else if (resultadoImc >= 25 && resultadoImc < 29.9) {
                classificacao = "Sobrepeso";
            } else if (resultadoImc >= 30 && resultadoImc < 34.9) {
                classificacao = "Obesidade grau 1";
            } else if (resultadoImc >= 35 && resultadoImc < 39.9) {
                classificacao = "Obesidade grau 2";
            } else {
                classificacao = "Obesidade grau 3";
            }

            AlertDialog.Builder dialogo = new AlertDialog.Builder(imcActivity.this);
            dialogo.setTitle("IMC");
            dialogo.setMessage("IMC: " + String.format("%.2f", resultadoImc) + " situação: " + classificacao);
            dialogo.setNeutralButton("OK", null);
            dialogo.show();

        } catch (NumberFormatException e) {
            Toast.makeText(imcActivity.this, "Por favor, insira valores válidos.", Toast.LENGTH_SHORT).show();
        }
    }
}
