import java.util.Scanner;


public class Taller01 {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        Scanner respuesta = new Scanner(System.in);
        int opcion;
        Scanner frase = new Scanner(System.in);

        java.lang.String palabra = obtenerFrase(frase);

        do{
            mostrarMenu();
            opcion = leerOpcion(respuesta);
            ejecutarOpcion(opcion, palabra);
        } while (opcion != 5);


    }

    public static String obtenerFrase(Scanner inputScanner) {
        System.out.println("Ingresa una frase para experimentar con ella: ");
        String frase = inputScanner.nextLine();
        return frase;
    }

    public static void mostrarMenu() {

        System.out.println("¿Que opcion deseas realizar?");
        System.out.println("1. Verificar si una frase es Reves-Derecho");
        System.out.println("2. Contar vocales en una frase");
        System.out.println("3. Encriptar una frase");
        System.out.println("4. Desencriptar una frase");
        System.out.println("5. Salir");
    }

    public static  int leerOpcion(Scanner in) {
        int opcion = in.nextInt();
        return opcion;
    }

    public static void ejecutarOpcion(int opcion, String palabra) {
        switch (opcion) {
            case 1:
                verificarRevesDerecho(palabra);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.out.println("Opcion invalida");
                break;

        }
    }

    public static void verificarRevesDerecho(String palabra) {
        System.out.println(palabra);



    }




}
