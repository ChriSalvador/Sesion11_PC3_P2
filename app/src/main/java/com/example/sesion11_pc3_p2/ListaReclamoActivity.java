package com.example.sesion11_pc3_p2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.sesion11_pc3_p2.adapter.ReclamoAdapter;
import com.example.sesion11_pc3_p2.dao.ReclamoDao;
import com.example.sesion11_pc3_p2.db.DatabaseHelper;
import com.example.sesion11_pc3_p2.model.Reclamo;

import java.util.List;

public class ListaReclamoActivity extends AppCompatActivity {

    private ImageButton btnCerrarSesion, btnagregarReclamo;
    private SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    ReclamoDao reclamoDao;
    ReclamoAdapter reclamoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reclamo);

        btnCerrarSesion = findViewById(R.id.cerrarSesion);

        sharedPreferences = getSharedPreferences(Constante.NAME_SHARED_PREFERENCE, MODE_PRIVATE);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().remove(Constante.NAME_KEY_LOGGED_IN).apply();
                MainActivity.start(ListaReclamoActivity.this);
                finish();
            }
        });

        btnagregarReclamo = findViewById(R.id.agregarReclamo);

        btnagregarReclamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaReclamoActivity.this, AgregarReclamoActivity.class);
                startActivity(intent);
            }
        });

        databaseHelper = DatabaseHelper.getInstance(this);
        reclamoDao = new ReclamoDao(databaseHelper.getWritableDatabase());

        recyclerView= findViewById(R.id.recyclerView);

        cargarReclamos();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    private void cargarReclamos() {
        List<Reclamo> reclamos = reclamoDao.getAll();
        reclamoAdapter = new ReclamoAdapter(ListaReclamoActivity.this, this, reclamos);
        recyclerView.setAdapter(reclamoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, ListaReclamoActivity.class);
        context.startActivity(intent);
    }

}