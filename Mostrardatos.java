package entrega6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mostrardatos {
    public static void mostrardatos(){
        File myFile = new File(
                "src/entrega6/aviones.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] avioness = new String[1000];
        int p = 0;
        sc.nextLine(); //Para evitar la primera linea
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            avioness[p++] = data;
            System.out.println(data);
        }
    }
}
