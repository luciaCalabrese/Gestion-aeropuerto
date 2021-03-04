package entrega6;


import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class aviones {
    static ArrayList<String> vuelos = new ArrayList<>();
    static ArrayList<String> asientoturista = new ArrayList<>();
    private static final String NUMERO_FILA = "Número de fila (1-4)";
    private static final String NUMERO_COLUMNA = "Número de columna (1-40)";
    private static final String CONSTANTE_INPUT ="Favor ingresar el ";
    private static final String CONSTANTE_NO_VALIDO="Valor no válido, ";
    private static int asientosaltas[][] = new int[4][40]; //Declaramos el array para poder usar en diferentes metodos
    private static int asientostuista[][] = new int[4][40];

    public static void main(String[] args) {



        try {
            datos();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void datos() throws IOException {
        Scanner teclado = new Scanner(System.in);


        avionesdatos a1 = new avionesdatos();
        Archivos arch = new Archivos();
        File myFile = new File(
                "src/entrega6/aviones.txt");
        Scanner sc = null;

        try {
            sc = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File reservas = new File(
                "src/entrega6/aviones-reservas.txt");
        Scanner scaner = null;
        FileWriter fileWriter = new FileWriter("src/entrega6/aviones-reservas.txt", true);
        try {
            scaner = new Scanner(reservas);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] reservar = new String[1000];
        int x = 0;
        scaner.nextLine();
        while (scaner.hasNextLine()) {
            String datosreserva = scaner.nextLine();
            reservar[x++] = datosreserva;
            System.out.println(datosreserva);
        }
        clientes cliente = new clientes();
        System.out.println("Ingrese los nombres del cliente");
        String nombre = teclado.nextLine();
        cliente.setNombre(nombre);
        System.out.println("Ingrese los apellidos del cliente");
        String apellidos = teclado.nextLine();
        cliente.setApellidos(apellidos);
        System.out.println("Ingrese el pasaporte del cliente");
        String pasaporte = teclado.nextLine();
        cliente.setDni(pasaporte);
        System.out.println("Que destino desea");
        String destinos = teclado.nextLine();

        System.out.println("Para que fecha?");
        String fechas = teclado.nextLine();

        System.out.println(vuelos);
        System.out.println("¿Cuantos asientos?");
        int asientos = teclado.nextInt();
        fileWriter.write("\n");
        System.out.println(cliente.getNombre() + cliente.getApellidos() + cliente.getDni());
        fileWriter.write(cliente.getNombre() + " " + cliente.getApellidos() + " " + cliente.getDni() + "_" + destinos + "_" + fechas + "_" + asientos);
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

            String[] destino = numeroavion[1].split(",");
            System.out.println(destino[2]);
            asientoturista.add(asientosturista);
            System.out.println(data);

            if (destinos.equals(destino[2])) {
                System.out.println("¿que clase quiere?");
                String clase = teclado.nextLine();
                if (clase.equals("alta")) {


                    for (int i = 0; i < asientos - 1; i++) {
                        int c = 1, fila, columna;


                        fila = obtenerInformacion(NUMERO_FILA, teclado, 'F'); //Obtenemos la fila
                        columna = obtenerInformacion(NUMERO_COLUMNA, teclado, 'C');//Obtenemos la columna

                        if (!verificarDisponibilidad(fila, columna)) { //La validación de si el asiento esta reservado, lo haremos despues del primmer registro
                            System.out.println("El asiento de la fila: " + fila + " y la columna:" + columna + " esta ocupado");
                        } else { //Si no existe asiento ocupado se procede a ingresar.
                            asientosaltas[fila - 1][columna - 1] = 1; //- 1 ya que el array inicializa en 0
                            System.out.println("Reserva exitosa");
                        }
                    }
                }
                if (clase.equals("turista")){
                    for (int i = 0; i < asientos - 1; i++) {
                        int c = 1, fila, columna;


                        fila = obtenerInformacion(NUMERO_FILA, teclado, 'F'); //Obtenemos la fila
                        columna = obtenerInformacion(NUMERO_COLUMNA, teclado, 'C');//Obtenemos la columna

                        if (!verificarDisponibilidad(fila, columna)) { //La validación de si el asiento esta reservado, lo haremos despues del primmer registro
                            System.out.println("El asiento de la fila: " + fila + " y la columna:" + columna + " esta ocupado");
                        } else { //Si no existe asiento ocupado se procede a ingresar.
                            asientostuista[fila - 1][columna - 1] = 1; //- 1 ya que el array inicializa en 0
                            System.out.println("Reserva exitosa");
                        }
                    }
                }
            }
        }






        /*System.out.println("Crear nuevo viaje");
           /* arch.Escribir(myFile,"\n");
            arch.Escribir(myFile, " ");*/


            for (int j = 0; j < x; j++) {
                String destinoreserva[] = reservar[j].split("_");
                String destinor = destinoreserva[1];
                String diar = destinoreserva[2];
                String asientosr = destinoreserva[3];
            }
            if (destinos.equals((a1.destino))) {
                System.out.println("El vuelo disponible es:" + vuelos);
            }


            fileWriter.close();
        }
        public static class avionesdatos {
            private String dia;
            private String horario;
            private String asientosalta;
            private String asientosturista;

            public String getDestino() {
                return destino;
            }

            public void setDestino(String destino) {
                this.destino = destino;
            }

            private String destino;


            public String getDia() {
                return dia;
            }

            public void setDia(String dia) {
                this.dia = dia;
            }

            public String getHorario() {
                return horario;
            }

            public void setHorario(String horario) {
                this.horario = horario;
            }

            public String getAsientosalta() {
                return asientosalta;
            }

            public void setAsientosalta(String asientosalta) {
                this.asientosalta = asientosalta;
            }

            public String getAsientosturista() {
                return asientosturista;
            }

            public void setAsientosturista(String asientosturista) {
                this.asientosturista = asientosturista;
            }
        }
        public static class clientes {
            private String nombre;
            private String apellidos;
            private String dni;

            public String getNombre() {
                return nombre;
            }

            public void setNombre(String nombre) {
                this.nombre = nombre;
            }

            public String getApellidos() {
                return apellidos;
            }

            public void setApellidos(String apellidos) {
                this.apellidos = apellidos;
            }

            public String getDni() {
                return dni;
            }

            public void setDni(String dni) {
                this.dni = dni;
            }
        }


        private static class Archivos {
            void Escribir(File fFichero, String cadena) {
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
    private static int obtenerInformacion(String constant, Scanner sc, char bandera) {
        int entero = 0;

        while (entero == 0) {
            System.out.println(CONSTANTE_INPUT + constant);
            entero = sc.nextInt();
            //Esto es para que no se ingrese valor erroneo.
            if ((bandera == 'F' && entero > 4) || (bandera == 'C' && entero > 40)) {
                System.out.println(CONSTANTE_NO_VALIDO + constant);
                entero = 0;
            }
        }
        return entero;
    }

    //Metodo para validar asiento disponible
    private static boolean verificarDisponibilidad(int aFila, int aColumna) {
        aFila= aFila-1; //-1 ya que el array inicializa en 0
        aColumna=aColumna -1;
        for (int x = 0; x < asientosaltas.length; x++) {
            for (int y = 0; y < asientosaltas[x].length; y++) {
                if (asientosaltas[x][y]>0 && (x == aFila && y == aColumna)) {
                    return false; //Se retorna false en caso de que el asiento este ocupado.
                }
            }
        }
        return true;
    }

}
