package br.com.senacrs.usuario.walkapp;

import static br.com.senacrs.usuario.walkapp.R.id.btnCalcularImc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtPassos, edtPeso, edtAltura;
    Button btnCalcularImc, btnCalcularWalk, btnIrImc, btnIrWalk;
    CheckBox ckbCorrida;
    RadioGroup rgWalk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        CarregarTelaPrincipal();
        ckbCorrida = findViewById(R.id.ckbCorrida);
        rgWalk = findViewById(R.id.rgWalk);
        edtPassos = findViewById(R.id.edtPassos);
        btnCalcularWalk = findViewById(R.id.btnCalcularWalk);
        btnCalcularWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        resultadoWalk = resultadoWalk + (resultadoWalk  * 0.1);
                    }

                } catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();;
                }
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("Walk");
                dialogo.setMessage("Você percorreu: " + String.valueOf(resultadoWalk) + "m");
                dialogo.setNeutralButton("OK", null);
                dialogo.show();
            }
        });
    };

    public void CarregarTelaPrincipal() {
        setContentView(R.layout.activity_main);
        btnIrImc = (Button) findViewById(R.id.btnIrImc);
        btnIrImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregarTela2();
            }
        });
    }

    public void CarregarTela2() {
        setContentView(R.layout.imc);
        btnIrWalk = (Button) findViewById(R.id.btnIrWalk);
        btnIrWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregarTelaPrincipal();
            }
        });

    }

}