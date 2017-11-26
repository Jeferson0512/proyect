package com.tecsup.jeferson_bujaico_rodriguez.proyect_clinica.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tecsup.jeferson_bujaico_rodriguez.proyect_clinica.R;

public class MainActivity extends AppCompatActivity {

    private Button btn_ingresar, btn_registrar;
    private TextView tv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_ingresar = (Button)findViewById(R.id.fb_Ingresar);
        btn_registrar = (Button)findViewById(R.id.fb_Registrar);
        tv_main = (TextView)findViewById(R.id.tv_texto_registro_ingreso);

        //Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/NABILA.TFT");
        //tv_main.setTypeface(typeface);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

    }
}
