package entrega6;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Archivos {

        public static void Escribir(File fFichero, String cadena) {
            BufferedWriter bw;

            try {
                if (!fFichero.exists()) {
                    fFichero.createNewFile();
                }
                bw = new BufferedWriter(new FileWriter(fFichero, true));
                bw.write(cadena);
                bw.close();

            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

