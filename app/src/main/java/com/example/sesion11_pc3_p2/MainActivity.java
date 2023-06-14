package com.example.sesion11_pc3_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout usuarioTextInput, contraTextInput;
    private MaterialButton iniciarSesion, registrarCuenta;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioTextInput = findViewById(R.id.usuarioTextInput);
        contraTextInput = findViewById(R.id.contraTextInput);
        iniciarSesion = findViewById(R.id.loginButton);
        registrarCuenta = findViewById(R.id.crearCuentaButton);

        sharedPreferences = getSharedPreferences(Constante.NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        boolean isLogged = sharedPreferences.getBoolean(Constante.NAME_KEY_LOGGED_IN, false);

        if (isLogged) {
            goToListadoActivity();
        }

        EditText usuarioEditText = usuarioTextInput.getEditText();
        EditText contraEditText = contraTextInput.getEditText();

        assert usuarioEditText != null && contraEditText != null;


        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = usuarioEditText.getText().toString();
                String contra = contraEditText.getText().toString();

                String savedUsuario = sharedPreferences.getString(Constante.USERNAME_KEY, "admin");
                String savedContra = sharedPreferences.getString(Constante.PASSWORD_KEY, "123");

                if (usuario.equals(savedUsuario) && contra.equals(savedContra)) {
                    Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    goToListadoActivity();
                } else {
                    // Credenciales inválidas
                    Toast.makeText(MainActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }

            }
        });

        registrarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = usuarioEditText.getText().toString();
                String contra = contraEditText.getText().toString();

                if (usuario.isEmpty() || contra.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Completa los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constante.USERNAME_KEY, usuario);
                editor.putString(Constante.PASSWORD_KEY, contra);
                editor.apply();
                if (editor.commit()) {
                    Toast.makeText(MainActivity.this, "Cuenta creado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goToListadoActivity() {
        sharedPreferences.edit().putBoolean(Constante.NAME_KEY_LOGGED_IN, true).apply();
        ListaReclamoActivity.start(this);
        finish();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}