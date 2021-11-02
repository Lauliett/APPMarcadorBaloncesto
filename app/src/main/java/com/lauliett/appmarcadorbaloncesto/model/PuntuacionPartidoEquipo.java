package com.lauliett.appmarcadorbaloncesto.model;

public class PuntuacionPartidoEquipo {

    private Equipo equipo;
    private int puntos;

    /**
     * consturctor
     * @param equipo equipo
     * @param puntos puntos
     */
    public PuntuacionPartidoEquipo(Equipo equipo, int puntos) {
        this.equipo = equipo;
        this.puntos = puntos;
    }

    public String getEquipoName() {
        return equipo.getNombre();
    }

    public int getPuntos() {
        return puntos;
    }

    public void sumarAPuntos(int valor){
        this.puntos+=valor;
    }

    public boolean restarAPuntos(int valor){
        boolean puntosSonModificados = false;

        if(this.puntos - valor >= 0){
           this.puntos -= valor;
            puntosSonModificados = true;
        }
        return puntosSonModificados;
    }
}
