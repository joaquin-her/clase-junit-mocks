package org.algo3.modelo;

import org.algo3.modelo.invitado.Invitado;
import org.algo3.modelo.proveedor.Proveedor;
import org.algo3.modelo.tiempo.Tiempo;

import java.util.ArrayList;
import java.util.Comparator;

public class Yayo {

    private ArrayList<Chiste> chistes;
    private Proveedor proovedor;
    private Invitado invitado;

    public Yayo(Proveedor proovedor, Invitado invitado) {
        this.chistes = new ArrayList<>();
        this.proovedor = proovedor;
        this.invitado = invitado;
    }

    public ArrayList<Chiste> todosLosChistes(){
        return this.chistes;
    }

    private Chiste puntuar(Chiste chiste){
        int puntaje = this.invitado.puntuar(chiste);
        chiste.aplicarPuntaje(puntaje);
        return chiste;
    }

    private String determinarCategoria(Tiempo tiempo) {
        int dia = tiempo.obtenerDiaDeHoy();
        if (dia % 2 == 0){
            return "Programming";
        }
        else {
            return "Christmas";
        }
    }

    private String determinarIdioma(Tiempo tiempo) {
        int dia = tiempo.obtenerDiaDeHoy();
        if (dia % 2 == 0){
            return  "es";
        }
        else {
            return  "en";
        }
    }

    public Chiste contarChiste(Tiempo tiempo){
        String categoria = this.determinarCategoria(tiempo);
        String idioma = this.determinarIdioma(tiempo);

        Chiste chiste = proovedor.solicitarChiste(categoria,idioma);
        this.puntuar(chiste);

        this.chistes.add(chiste);
        return chiste;
    }

    public ArrayList<Chiste> mejoresChistes(int cantidad) {
    if (cantidad > this.chistes.size()){
        throw new ChistesInsuficientes();
    }

    this.chistes.sort(Comparator.comparingInt(Chiste::getPuntaje).reversed());
    ArrayList<Chiste> mejoresChistes = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
        mejoresChistes.add(chistes.get(i));
    }
    return mejoresChistes;
    }
}
