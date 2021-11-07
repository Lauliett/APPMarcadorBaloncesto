package com.lauliett.appmarcadorbaloncesto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lauliett.appmarcadorbaloncesto.model.AccionesMarcador;
import com.lauliett.appmarcadorbaloncesto.model.Equipo;
import com.lauliett.appmarcadorbaloncesto.model.Partido;
import com.lauliett.appmarcadorbaloncesto.model.PuntuacionPartidoEquipo;

public class MainActivity extends AppCompatActivity {



    Partido partido;

   private Button btnAdd1Local;
   private Button btnAdd2Local;
   private Button btnMenos1Local;

   private Button btnAdd1Visitante;
   private Button btnAdd2Visitante;
   private Button btnMenos1Visitante;

   private Button btnRestart;
   private Button btnFinish;

   private TextView txtPuntuacionLocal;
   private TextView txtPuntuacionVisitante;
   private TextView txtNombreLocal;
   private TextView txtNombreVisitante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElements();
        iniciarPartido();
        initActions();
        getSupportActionBar().hide();
    }


    private void initElements(){
        btnAdd1Local = findViewById(R.id.btnLocalMas1);
        btnAdd2Local = findViewById(R.id.btnLocalMas2);
        btnMenos1Local = findViewById(R.id.btnLocalMenos1);

        btnAdd1Visitante = findViewById(R.id.btnVisitanteMas1);
        btnAdd2Visitante = findViewById(R.id.btnVisitanteMas2);
        btnMenos1Visitante = findViewById(R.id.btnVisitanteMenos1);

        btnFinish = findViewById(R.id.btnVerResultado);
        btnRestart = findViewById(R.id.btnReiniciar);

        txtPuntuacionLocal = findViewById(R.id.txtPuntosLocal);
        txtPuntuacionVisitante = findViewById(R.id.txtPuntosVisitante);
        txtNombreLocal = findViewById(R.id.txtNombreLocal);
        txtNombreVisitante = findViewById(R.id.txtNombreVisitante);
    }

    private void initActions(){
        btnAdd1Local.setOnClickListener(l -> {
            partido.actualizarPuntuacionEquipoLocal(1, AccionesMarcador.SUMAR);
            actualizarMarcador();
            }
        );
        btnAdd2Local.setOnClickListener(l -> {
            partido.actualizarPuntuacionEquipoLocal(2, AccionesMarcador.SUMAR);
            actualizarMarcador();}
        );
        btnMenos1Local.setOnClickListener(l -> {
            partido.actualizarPuntuacionEquipoLocal(1, AccionesMarcador.RESTAR);
            actualizarMarcador();}
        );

        btnAdd1Visitante.setOnClickListener(l -> {
            partido.actualizarPuntuacionEquipoVisitante(1, AccionesMarcador.SUMAR);
            actualizarMarcador(); }
        );
        btnAdd2Visitante.setOnClickListener(l -> {
            partido.actualizarPuntuacionEquipoVisitante(2, AccionesMarcador.SUMAR);
            actualizarMarcador();}
        );
        btnMenos1Visitante.setOnClickListener(l -> {
            partido.actualizarPuntuacionEquipoVisitante(1, AccionesMarcador.RESTAR);
            actualizarMarcador();}
        );

        btnRestart.setOnClickListener( l -> {
            iniciarPartido();
            actualizarMarcador();
        });

        btnFinish.setOnClickListener( l -> {
            finalizarPartido();
        });
    }

    private void iniciarPartido(){
        Equipo equipoLocal = new Equipo("local");
        Equipo equipoVisitante = new Equipo("visitante");

        PuntuacionPartidoEquipo puntuacionEquipoLocal = new PuntuacionPartidoEquipo(equipoLocal, 0);
        PuntuacionPartidoEquipo puntuacionEquipoVisitante = new PuntuacionPartidoEquipo(equipoVisitante, 0);

        partido = new Partido(1, puntuacionEquipoLocal, puntuacionEquipoVisitante);

        txtNombreLocal.setText(equipoLocal.getNombre());
        txtNombreVisitante.setText(equipoVisitante.getNombre());
    }

    private void actualizarMarcador(){
        txtPuntuacionLocal.setText(String.valueOf(partido.getPuntuacionEquipoLocal()));
        txtPuntuacionVisitante.setText(String.valueOf(partido.getPuntuacionEquipoVisitante()));
    }

    private void finalizarPartido(){
        Intent finalizarPartido = new Intent(this, ScoreActivity.class);
        finalizarPartido.putExtra("partido", partido);
        startActivity(finalizarPartido);
    }
}