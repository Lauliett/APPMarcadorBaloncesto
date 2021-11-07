package com.lauliett.appmarcadorbaloncesto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.lauliett.appmarcadorbaloncesto.model.Partido;

public class ScoreActivity extends AppCompatActivity {

    TextView txtNombreEquipoLocal;
    TextView txtNombreEquipoVisitante;
    TextView txtEquipoLocalPuntos;
    TextView txtEquipoVisitantePuntos;
    TextView txtGanador;

    Bundle datosPartido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        initElements();
        recogerDatos();
        getSupportActionBar().hide();
    }

    private void initElements(){
        txtNombreEquipoLocal = findViewById(R.id.txtASnombreLocal);
        txtNombreEquipoVisitante = findViewById(R.id.txtASNombreVisitante);
        txtEquipoLocalPuntos = findViewById(R.id.txtAsPuntosLocal);
        txtEquipoVisitantePuntos = findViewById(R.id.txtAsPuntosVisitante);
        txtGanador = findViewById(R.id.txtGanador);
    }

    private void recogerDatos(){
        datosPartido = getIntent().getExtras();
        Partido partido = datosPartido.getParcelable("partido");

        txtNombreEquipoLocal.setText(partido.getNombreEquipoLocal());
        txtNombreEquipoVisitante.setText(partido.getNombreEquipoVisitante());

        txtEquipoLocalPuntos.setText(String.valueOf(partido.getPuntuacionEquipoLocal()));
        txtEquipoVisitantePuntos.setText(String.valueOf(partido.getPuntuacionEquipoVisitante()));

        txtGanador.setText(partido.devolverNombreGanador());
    }
}