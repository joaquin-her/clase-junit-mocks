package org.algo3.modelo.proveedor;

import org.algo3.modelo.Chiste;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.io.IOException;

public class ProveedorTxt implements Proveedor {
    private final String rutaArchivo = "./src/chistes/chistes.txt";

    private BufferedReader crearBufferedReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(this.rutaArchivo));
    }

    public Chiste solicitarChiste(String categoria, String idioma) {
        Random random = new Random();
        int linea = random.nextInt(6);
        if (categoria.equals("Christmas")) {
            linea += 5;
        }
        if (idioma.equals("en")) {
            linea += 10;
        }
        try (BufferedReader br = crearBufferedReader()) {
            int i = 0;
            while (i != linea) {
                br.readLine();
                i += 1;
            }
            return new Chiste(categoria, idioma, br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}