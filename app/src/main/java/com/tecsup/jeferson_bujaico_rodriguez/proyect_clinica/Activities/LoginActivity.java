package com.tecsup.jeferson_bujaico_rodriguez.proyect_clinica.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText username_input,password_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        username_input = (EditText)findViewById(R.id.et_usuario_login);
        password_input = (EditText)findViewById(R.id.et_password_login);

        final String email = sharedPreferences.getString("email", null);
        if(email != null){
            username_input.setText(email);
            password_input.requestFocus();
        }

        // islogged remember
        if(sharedPreferences.getBoolean("islogged", false)){
            // Go to Dashboard
            goDashboard();
        }

    }

    public void Ingresar(View view){
        final String username = username_input.getText().toString();
        String password = password_input.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Campos obligatorios: username y password", Toast.LENGTH_SHORT).show();
        }

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<ResponseMessage> call = null;
        call = service.loginUsuario(username,password);

        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                try {
                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {
                        ResponseMessage responseMessage = response.body();
                        Log.d(TAG, "responseMessage: " + responseMessage);
                        Toast.makeText(LoginActivity.this, "Bienvenido "+username, Toast.LENGTH_LONG).show();

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        boolean success = editor
                                .putString("email", username)
                                .putBoolean("islogged", true)
                                .commit();

                        goDashboard();

                    } else {

                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error no se enuentra en servicio");

                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }

    public void Registrar(View view){
        Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(intent);
    }


    private void goDashboard() {
        Intent intent = new Intent(this, DrawerActivity.class);
        intent.putExtra("correo",username_input.getText().toString());
        startActivity(intent);
        finish();
    }

}
