package entrega6;
import java.util.Scanner;
public class pruebaas {


        //Constantes
        private static final String NUMERO_FILA = "Número de fila (1-4)";
        private static final String NUMERO_COLUMNA = "Número de columna (1-40)";
        private static final String CONSTANTE_INPUT ="Favor ingresar el ";
        private static final String CONSTANTE_NO_VALIDO="Valor no válido, ";
        private static int asiento[][] = new int[4][40]; //Declaramos el array para poder usar en diferentes metodos

        public static void main(String args[]) {

                // Inicio

                Scanner sc = new Scanner(System.in);
                int c = 1, fila, columna;

                for (int i = 0; i < 160; i++) { // Inicio for
                    fila = obtenerInformacion(NUMERO_FILA, sc, 'F'); //Obtenemos la fila
                    columna = obtenerInformacion(NUMERO_COLUMNA, sc, 'C');//Obtenemos la columna

                    if (i > 0 && !verificarDisponibilidad(fila, columna)) { //La validación de si el asiento esta reservado, lo haremos despues del primmer registro
                        System.out.println("El asiento de la fila: " + fila + " y la columna:" + columna + " esta ocupado");
                    } else { //Si no existe asiento ocupado se procede a ingresar.
                        asiento[fila - 1][columna - 1] = 1; //- 1 ya que el array inicializa en 0
                        System.out.println("Reserva exitosa");
                    }
                } // Fin while

                for (int i = 0; i < 4; i++) { // For
                    for (int j = 0; j < 40; j++) { // For
                        System.out.println(asiento[i][j] + "\t");
                    } // For
                } // For
                System.exit(0);
            }
        // Fin

        //Metodo para obtener la fila o columna desde el teclado
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
            for (int x = 0; x < asiento.length; x++) {
                for (int y = 0; y < asiento[x].length; y++) {
                    if (asiento[x][y]>0 && (x == aFila && y == aColumna)) {
                        return false; //Se retorna false en caso de que el asiento este ocupado.
                    }
                }
            }
            return true;
        }

    }

