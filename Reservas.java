package entrega6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Reservas {

    public static  void reservas(){
        Scanner teclado = new Scanner(System.in);
        File reservas = new File(
                "src/entrega6/aviones-reservas.txt");
        Scanner scaner = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/entrega6/aviones-reservas.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            scaner = new Scanner(reservas);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] reservar = new String[1000];
        int x = 0;
        scaner.nextLine();
        System.out.println("Ingresar el nombre del usuario");
        teclado.nextLine();
        String identificadorusuario = teclado.nextLine();

        while (scaner.hasNextLine()) {
            String datosreserva = scaner.nextLine();
            reservar[x++] = datosreserva;


            String numeroavionreservado[] = datosreserva.split(" ");
            String vueloreservado = numeroavionreservado[0];
            System.out.println(vueloreservado);


            if (identificadorusuario == vueloreservado){
                System.out.println(datosreserva);
            }
        }

    }
}
