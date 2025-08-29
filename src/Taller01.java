import java.util.Scanner;


public class Taller01 {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        Scanner respuesta = new Scanner(System.in);
        int opcion;

        do{
            mostrarMenu();
            opcion = leerOpcion(respuesta);
            ejecutarOpcion(opcion, respuesta);
        } while (opcion != 5);


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

    public static void ejecutarOpcion(int opcion, Scanner in) {
        switch (opcion) {
            case 1:
                //algo
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


}
