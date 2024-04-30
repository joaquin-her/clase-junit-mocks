package org.algo3.modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Yayo {

    private ArrayList<Chiste> chistes;
    private Proovedor proovedor;
    private Invitado invitado;

    public Yayo() {
        this.chistes = new ArrayList<>();
        this.proovedor = new Proovedor();
        this.invitado = new Invitado();
    }

    public ArrayList<Chiste> todosLosChistes(){
        return this.chistes;
    }

    public Chiste contarChiste(){
        LocalDate hoy = LocalDate.now();
        int dia = hoy.getDayOfMonth();
        String categoria;
        String idioma;

        if (dia % 2 == 0){
            categoria = "Programming";
            idioma = "es";
        }
        else {
            categoria = "Christmas";
            idioma = "en";
        }

        Chiste chiste = proovedor.solicitarChiste(categoria,idioma);
        int puntaje = this.invitado.puntuar(chiste);
        chiste.aplicarPuntaje(puntaje);
        this.chistes.add(chiste);

        return chiste;
    }


}
