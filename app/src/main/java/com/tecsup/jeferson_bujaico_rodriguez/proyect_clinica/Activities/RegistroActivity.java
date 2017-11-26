package com.tecsup.jeferson_bujaico_rodriguez.proyect_clinica.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tecsup.jeferson_bujaico_rodriguez.proyect_clinica.R;
import com.tecsup.jeferson_bujaico_rodriguez.proyect_clinica.Services.ApiService;
import com.tecsup.jeferson_bujaico_rodriguez.proyect_clinica.Services.ApiServiceGenerator;
import com.tecsup.jeferson_bujaico_rodriguez.proyect_clinica.Services.ResponseMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    private static final String TAG = RegistroActivity.class.getSimpleName();
    private EditText username_input, password_input,type_input, fecha_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        username_input =(EditText)findViewById(R.id.et_usuario_login);
        password_input = (EditText)findViewById(R.id.et_password_login);
        type_input =(EditText)findViewById(R.id.et_type_login);
        fecha_input = (EditText)findViewById(R.id.et_fecha_login);
    }

    public void Registrarse(View view){
        final String nombres = type_input.getText().toString();
        String username = username_input.getText().toString();
        String password = password_input.getText().toString();
        String correo = fecha_input.getText().toString();

        if (username.isEmpty() || password.isEmpty() || correo.isEmpty()){
            Toast.makeText(this, "Usuario, Password y Tipo son campos necesario", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<ResponseMessage> call = null;
        call = service.createUsuario(nombres, username, password, correo);

        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Toast.makeText(RegistroActivity.this, nombres+"Usuario Registrado", Toast.LENGTH_SHORT).show();
                        ResponseMessage responseMessage = response.body();
                        Log.d(TAG, "responseMessage: " + responseMessage);

                        Toast.makeText(RegistroActivity.this, responseMessage.getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(RegistroActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Throwable x) {
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(RegistroActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
}
