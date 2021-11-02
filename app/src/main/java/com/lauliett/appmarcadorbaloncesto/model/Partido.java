package com.lauliett.appmarcadorbaloncesto.model;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Clase que representa a un partido
  @author Laura Gimeno Villanueva
 */
public class Partido implements Parcelable {

    private int idPartido;
    private PuntuacionPartidoEquipo puntuacionEquipoLocal;
    private PuntuacionPartidoEquipo puntuacionEquipoVisitante;

    public Partido(int idPartido, PuntuacionPartidoEquipo puntuacionEquipoLocal, PuntuacionPartidoEquipo puntuacionEquipoVisitante) {
        this.idPartido = idPartido;
        this.puntuacionEquipoLocal = puntuacionEquipoLocal;
        this.puntuacionEquipoVisitante = puntuacionEquipoVisitante;
    }

    protected Partido(Parcel in) {
        idPartido = in.readInt();
        puntuacionEquipoLocal = new PuntuacionPartidoEquipo(new Equipo(in.readString()), in.readInt());
        puntuacionEquipoVisitante = new PuntuacionPartidoEquipo(new Equipo(in.readString()), in.readInt());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idPartido);
        dest.writeString(puntuacionEquipoLocal.getEquipoName());
        dest.writeInt(puntuacionEquipoLocal.getPuntos());
        dest.writeString(puntuacionEquipoVisitante.getEquipoName());
        dest.writeInt(puntuacionEquipoVisitante.getPuntos());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Partido> CREATOR = new Creator<Partido>() {
        @Override
        public Partido createFromParcel(Parcel in) {
            return new Partido(in);
        }

        @Override
        public Partido[] newArray(int size) {
            return new Partido[size];
        }
    };

    /**
     *
     * @return int puntuacionEquipoLocal
     */
    public int getPuntuacionEquipoLocal(){
        return puntuacionEquipoLocal.getPuntos();
    }

    /**
     * @return int, puntuacion equipo visitante
     */
    public int getPuntuacionEquipoVisitante(){
        return puntuacionEquipoVisitante.getPuntos();
    }

    public String getNombreEquipoLocal(){
        return puntuacionEquipoLocal.getEquipoName();
    }

    public String getNombreEquipoVisitante(){
        return puntuacionEquipoVisitante.getEquipoName();
    }

    /**
     * actualiza la puntuacion del equipo local
     * @param puntos integer, numero de puntos con los que operar
     * @param accion see @AccionesMarcado, accion a realizar
     */
    public void actualizarPuntuacionEquipoLocal(int puntos, AccionesMarcador accion){
        actualizarPuntuacionEquipo(this.puntuacionEquipoLocal, puntos, accion);
    }
    /**
     * actualiza la puntuacion del equipo visitante
     * @param puntos integer, numero de puntos con los que operar
     * @param accion see @AccionesMarcado, accion a realizar
     */
    public void actualizarPuntuacionEquipoVisitante(int puntos, AccionesMarcador accion){
        actualizarPuntuacionEquipo(this.puntuacionEquipoVisitante, puntos, accion);
    }

    /**
     * actualiza la puntuacion de un equipo en un partido
     * @param puntos integer, numero de puntos con los que operar
     * @param accion see @AccionesMarcado, accion a realizar
     */
    public void actualizarPuntuacionEquipo(PuntuacionPartidoEquipo equipo, int puntos, AccionesMarcador accion){

        if(accion.equals(AccionesMarcador.SUMAR)){
            equipo.sumarAPuntos(puntos);
        }
        else  if(accion.equals(AccionesMarcador.RESTAR)){
            equipo.restarAPuntos(puntos);
        }
    }

    /**
     *
     * @return 0 -> empate, 1 -> ganador local, 2 -> ganador visitante
     */
    public String devolverNombreGanador(){
        String nombreGanador = "empate";

        if(puntuacionEquipoLocal.getPuntos() > puntuacionEquipoVisitante.getPuntos()){
            nombreGanador = puntuacionEquipoLocal.getEquipoName();
        }else if(puntuacionEquipoLocal.getPuntos() < puntuacionEquipoVisitante.getPuntos()){
            nombreGanador = puntuacionEquipoVisitante.getEquipoName();
        }

        return nombreGanador;
    }
}
