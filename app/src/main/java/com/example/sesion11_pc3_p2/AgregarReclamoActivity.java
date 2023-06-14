package com.example.sesion11_pc3_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sesion11_pc3_p2.dao.ReclamoDao;
import com.example.sesion11_pc3_p2.db.DatabaseHelper;
import com.example.sesion11_pc3_p2.model.Reclamo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AgregarReclamoActivity extends AppCompatActivity {

    private ImageButton regresoListado;
    Reclamo reclamo;
    ReclamoDao reclamoDao;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_reclamo);

        regresoListado = findViewById(R.id.regresarListado);
        regresoListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarListado();
            }
        });

        databaseHelper = DatabaseHelper.getInstance(this);
        reclamoDao = new ReclamoDao(databaseHelper.getWritableDatabase());

        TextInputLayout codigoTextInput = findViewById(R.id.codigoEditText);
        TextInputLayout asuntoTextInput = findViewById(R.id.asuntoEditText);
        TextInputLayout descripcionTextInput = findViewById(R.id.descripcionEditText);
        MaterialButton materialButton = findViewById(R.id.agregarReclamo);

        materialButton.setOnClickListener(v -> {

            EditText codigoEditText = codigoTextInput.getEditText();
            EditText asuntoEditText = asuntoTextInput.getEditText();
            EditText descripcionEditText = descripcionTextInput.getEditText();

            assert codigoEditText != null && asuntoEditText != null && descripcionEditText != null;

            String codigo = codigoEditText.getText().toString();
            String asunto = asuntoEditText.getText().toString();
            String descripcion = descripcionEditText.getText().toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = new Date();
            String fecha = dateFormat.format(date);

            if(!codigo.isEmpty() && !asunto.isEmpty() && !descripcion.isEmpty()) {
                reclamo = new Reclamo(codigo, asunto, descripcion, "Pendiente", fecha);
                reclamoDao.insert(reclamo);
            }

            regresarListado();
        });

    }

    private void regresarListado() {
        Intent intent = new Intent(AgregarReclamoActivity.this, ListaReclamoActivity.class);
        startActivity(intent);
    }
}