package org.algo3.modelo.proveedor;

import org.algo3.modelo.Chiste;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.nio.file.Paths;

public class ProveedorTxt implements Proveedor {
    private final String rutaArchivo = Paths.get(System.getProperty("user.dir"), "src/chistes/archivo.txt").toString();

    public Chiste solicitarChiste(String categoria, String idioma) {
        Random random = new Random();
        int numero = random.nextInt(6); 
        if (categoria.equals("Christmas")){
            numero += 5;
        }
        if (idioma.equals("en")) {
            numero += 10;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            int i = 0;
            while (i != numero) {
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