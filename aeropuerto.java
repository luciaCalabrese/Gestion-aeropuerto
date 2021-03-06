//Lucia Calabrese Maffei
//https://github.com/lucia816/Gestion-aeropuerto
package entrega6;


import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static entrega6.Mostrardatos.mostrardatos;
import static entrega6.Reservas.reservas;

public class aeropuerto {
    static ArrayList<String> vuelos = new ArrayList<>();
    static ArrayList<String> asientoturista = new ArrayList<>();
    private static final String NUMERO_FILA = "Número de fila (1-4)";
    private static final String NUMERO_COLUMNA = "Número de columna (1-40)";
    private static final String CONSTANTE_INPUT ="Favor ingresar el ";
    private static final String CONSTANTE_NO_VALIDO="Valor no válido, ";
    private static int asientosaltas[][] = new int[4][20];
    private static int asientostuista[][] = new int[4][15];
    private  static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {

        Archivos arc = new Archivos();
        File myFile = new File(
                "src/entrega6/aviones.txt");
        Scanner sc = null;

        try {
            sc = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean salir = false;
        int opciones = 10;

        while (salir == false) {
            System.out.println("Bienvenido, ¿que desea hacer?");
            System.out.println("1. Ingresar datos de un vuelo");
            System.out.println("2. Realizar una reserva para un cliente");
            System.out.println("3. Verificar datos del vuelo");
            System.out.println("4.Ver los vuelos proximos");
            System.out.println("5. Salir");
            System.out.println("Elije opcion");
            int opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Crear nuevo viaje");

                    teclado.nextLine();
                    System.out.println("Ingrese el identificador (vuelo)");
                    String identificador = teclado.nextLine();
                    System.out.println("Ingrese cuantos asientos de turista hay disponibles");
                    String turista = teclado.nextLine();
                    System.out.println("Ingrese cuantos asientos de clase alta hay disponibles");
                    String alta = teclado.nextLine();
                    System.out.println("Ingrese el destino");
                    String destino = teclado.nextLine();
                    System.out.println("Ingrese el dia de vuelo");
                    String dia = teclado.nextLine();
                    System.out.println("Ingrese la hora del vuelo");
                    String hora = teclado.nextLine();


                    arc.Escribir(myFile, "\n" + identificador + "=" + turista + "," + alta + "," + destino + "," + dia + "-" + hora);

                case 2:
                    separardatos();
                case 3:
                    reservas();
                case 4:
                    mostrardatos();
                case 5:
                    salir = true;

            }

        }
    }

    public static void separardatos(){
            Scanner teclado = new Scanner(System.in);


            Archivos arch = new Archivos();
            File myFile = new File(
                    "src/entrega6/aviones.txt");
            Scanner sc = null;

            try {
                sc = new Scanner(myFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Clientes cliente = new Clientes();
            System.out.println("Ingrese los nombres del cliente");
            String nombre = teclado.nextLine();
            cliente.setNombre(nombre);
            System.out.println("Ingrese los apellidos del cliente");
            String apellidos = teclado.nextLine();
            cliente.setApellidos(apellidos);
            System.out.println("Ingrese el pasaporte del cliente");
            String pasaporte = teclado.nextLine();
            String dniRegexp = "@\"^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$\"";
            System.out.println(Pattern.matches(dniRegexp,pasaporte ));

            cliente.setDni(pasaporte);
            System.out.println("Que destino desea");
            String destinos = teclado.nextLine();


            System.out.println("Para que fecha?");
            String fechas = teclado.nextLine();
            String regexp = "\\d{1,2}/\\d{1,2}/\\d{4}";
            System.out.println(Pattern.matches(regexp,fechas));

            System.out.println(vuelos);
            System.out.println("¿Cuantos asientos?");
            int asientos = teclado.nextInt();



        String[] avioness = new String[1000];
            int p = 0;
            sc.nextLine(); //Para evitar la primera linea
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                avioness[p++] = data;


                String numeroavion[] = data.split("=");
                String vuelo = numeroavion[0];
                String diayhora[] = numeroavion[1].split(",");
                String dia = diayhora[3].split("-")[0];
                String horario = diayhora[3].split("-")[1];
                String asientosalta = numeroavion[1].split(",")[0];
                String asientosturista = numeroavion[1].split(",")[1];

                String[] destinoss = numeroavion[1].split(",");
                System.out.println(destinoss[2]);
                asientoturista.add(asientosturista);
                System.out.println(data);
                System.out.println(avioness[0]);
                if (destinos.equals(destinoss[2])) {
                    teclado.nextLine();
                    System.out.println("¿que clase quiere?");
                    String clase = teclado.nextLine();
                    if (clase.equals("alta")) {




                        for (int i = 0; i < asientos; i++) {
                            int c = 1, fila, columna;


                            fila = obtenerInformacion(NUMERO_FILA, teclado, 'F');
                            columna = obtenerInformacion(NUMERO_COLUMNA, teclado, 'C');

                            if (!verificarDisponibilidad(fila, columna)) {
                                System.out.println("El asiento de la fila: " + fila + " y la columna:" + columna + " esta ocupado");
                            } else {
                                asientosaltas[fila - 1][columna - 1] = 1;
                                System.out.println("Reserva exitosa");
                            }


                        }
                    }
                    if (clase.equals("turista")){
                        for (int i = 0; i < asientos - 1; i++) {
                            int c = 1, fila, columna;


                            fila = obtenerInformacion(NUMERO_FILA, teclado, 'F');
                            columna = obtenerInformacion(NUMERO_COLUMNA, teclado, 'C');

                            if (!verificarDisponibilidad(fila, columna)) {
                                System.out.println("El asiento de la fila: " + fila + " y la columna:" + columna + " esta ocupado");
                            } else {
                                asientostuista[fila - 1][columna - 1] = 1;
                                System.out.println("Reserva exitosa");
                            }
                        }
                    }
                }
            }



    }

    private static int obtenerInformacion(String constant, Scanner sc, char bandera) {
        int entero = 0;

        while (entero == 0) {
            System.out.println(CONSTANTE_INPUT + constant);
            entero = sc.nextInt();

            if ((bandera == 'F' && entero > 4) || (bandera == 'C' && entero > 40)) {
                System.out.println(CONSTANTE_NO_VALIDO + constant);
                entero = 0;
            }
        }
        return entero;
    }


    private static boolean verificarDisponibilidad(int aFila, int aColumna) {
        aFila= aFila-1; //-1 ya que el array inicializa en 0
        aColumna=aColumna -1;
        for (int x = 0; x < asientosaltas.length; x++) {
            for (int y = 0; y < asientosaltas[x].length; y++) {
                if (asientosaltas[x][y]>0 && (x == aFila && y == aColumna)) {
                    return false;
                }
            }
        }
        return true;
    }

}