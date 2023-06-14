package com.example.sesion11_pc3_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.sesion11_pc3_p2.dao.ReclamoDao;
import com.example.sesion11_pc3_p2.db.DatabaseHelper;
import com.example.sesion11_pc3_p2.enums.ReclamoEnum;
import com.example.sesion11_pc3_p2.model.Reclamo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class DetalleReclamoActivity extends AppCompatActivity {

    private ImageButton regresoListadoDet, borradoReclamo;
    private TextInputLayout detalleCodigoInput, detalleAsuntoInput, detalleDescripcionInput;
    private RadioGroup radioGroupEst;
    private RadioButton radioBtnPend, radiobtnPro, radioBtnCom, radioBtnRec;
    MaterialButton actualizarBtn;
    Reclamo reclamo;
    DatabaseHelper databaseHelper;
    ReclamoDao reclamoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reclamo);

        databaseHelper = DatabaseHelper.getInstance(this);
        reclamoDao = new ReclamoDao(databaseHelper.getWritableDatabase());

        detalleCodigoInput = findViewById(R.id.codigoDetalleInput);
        detalleAsuntoInput = findViewById(R.id.asuntoDetalleInput);
        detalleDescripcionInput = findViewById(R.id.descripcionDetalleInput);
        radioGroupEst = findViewById(R.id.radioGroupEstado);
        radioBtnPend = findViewById(R.id.radioButtonPendiente);
        radiobtnPro = findViewById(R.id.radioButtonProceso);
        radioBtnCom = findViewById(R.id.radioButtonCompleto);
        radioBtnRec = findViewById(R.id.radioButtonRechazado);
        actualizarBtn = findViewById(R.id.actualizarReclamo);
        regresoListadoDet = findViewById(R.id.regresarListadoDet);
        borradoReclamo = findViewById(R.id.borrarReclamo);

        Intent intent = getIntent();
        reclamo = (Reclamo) intent.getSerializableExtra(ReclamoEnum.KEY_NAME.getValue());
        if (reclamo.getCodigo() != null && reclamo.getAsunto() != null && reclamo.getDescripcion() != null) {
            detalleCodigoInput.getEditText().setText(reclamo.getCodigo());
            detalleAsuntoInput.getEditText().setText(reclamo.getAsunto());
            detalleDescripcionInput.getEditText().setText(reclamo.getDescripcion());
            String estado = reclamo.getEstado();
            fijarChecked(estado);
        }

        actualizarBtn.setOnClickListener(v -> {
            String nuevoAsunto = detalleAsuntoInput.getEditText().getText().toString();
            String nuevoDescripcion = detalleDescripcionInput.getEditText().getText().toString();
            String nuevoEstado = capturarEstado();

            Reclamo reclamoUpdate = new Reclamo(reclamo.getId(), reclamo.getCodigo(), nuevoAsunto,
                                                nuevoDescripcion, nuevoEstado, reclamo.getFechaCreacion());
            reclamoDao.update(reclamoUpdate);
        });

        regresoListadoDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarListado();
            }
        });

        borradoReclamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmarDelete();
            }
        });

    }

    private void fijarChecked(String estadoActual) {
        if (radioBtnPend.getText().toString().equals(estadoActual)) {
            radioBtnPend.setChecked(true);
        } else if (radiobtnPro.getText().toString().equals(estadoActual)) {
            radiobtnPro.setChecked(true);
        } else if (radioBtnCom.getText().toString().equals(estadoActual)) {
            radioBtnCom.setChecked(true);
        } else {
            radioBtnRec.setChecked(true);
        }
    }

    private String capturarEstado() {
        String estadoNuevo="";
        switch (radioGroupEst.getCheckedRadioButtonId()) {
            case R.id.radioButtonPendiente:
                estadoNuevo = radioBtnPend.getText().toString();
                break;
            case R.id.radioButtonProceso:
                estadoNuevo = radiobtnPro.getText().toString();
                break;
            case R.id.radioButtonCompleto:
                estadoNuevo = radioBtnCom.getText().toString();
                break;
            case R.id.radioButtonRechazado:
                estadoNuevo = radioBtnRec.getText().toString();
                break;
        }
        return estadoNuevo;
    }

    private void confirmarDelete() {
        new AlertDialog.Builder(this)
                .setTitle(String.format("¿Eliminar %s%s?", reclamo.getCodigo(), reclamo.getAsunto()))
                .setMessage("¿Está seguro de eliminar este registro?")
                .setNegativeButton("No", ((dialog, which) -> {}))
                .setPositiveButton("Sí", (dialog, which) -> {
                    long estado = reclamoDao.deleteById(String.valueOf(reclamo.getId()));
                    if (estado == -1) {
                        return;
                    }
                    finish();
                })
                .show();
    }

    private void regresarListado() {
        Intent intent = new Intent(DetalleReclamoActivity.this, ListaReclamoActivity.class);
        startActivity(intent);
    }
}