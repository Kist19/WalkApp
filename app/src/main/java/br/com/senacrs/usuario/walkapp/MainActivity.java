package br.com.senacrs.usuario.walkapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edtPassos;
    Button btnCalcularWalk, btnIrImc;
    CheckBox ckbCorrida;
    RadioGroup rgWalk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ckbCorrida = findViewById(R.id.ckbCorrida);
        rgWalk = findViewById(R.id.rgWalk);
        edtPassos = findViewById(R.id.edtPassos);
        btnCalcularWalk = findViewById(R.id.btnCalcularWalk);
        btnIrImc = findViewById(R.id.btnIrImc);

        btnCalcularWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularWalk();
            }
        });

        btnIrImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, imcActivity.class);
                startActivity(intent);
            }
        });
    }

    private void calcularWalk() {
        double passos, resultadoWalk = 0;

        try {
            passos = Double.parseDouble(edtPassos.getText().toString());
            int wk = rgWalk.getCheckedRadioButtonId();
            if (wk == R.id.rbCurto) {
                resultadoWalk = passos * 0.5;
            } else if (wk == R.id.rbMedio) {
                resultadoWalk = passos * 0.7;
            } else {
                resultadoWalk = passos * 1.0;
            }
            if (ckbCorrida.isChecked()) {
                resultadoWalk += resultadoWalk * 0.1;
            }

            AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
            dialogo.setTitle("Walk");
            dialogo.setMessage("Você percorreu: " + String.valueOf(resultadoWalk) + "m");
            dialogo.setNeutralButton("OK", null);
            dialogo.show();

        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Por favor, insira um número válido.", Toast.LENGTH_SHORT).show();
        }
    }
}
