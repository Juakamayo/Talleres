import java.util.*;


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
                contarVocales(palabra);
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

        java.lang.String palabraInvertida = invertirPalabra(palabra);

        palabra = palabra.replaceAll("\\s+","").toLowerCase();
        if (Objects.equals(palabra, palabraInvertida)){
            System.out.println("Efectivamente esta palabra es un palindromo");
        }else {
            System.out.println("Esta palabra no es un palindromo");
        }
    }

    public static String invertirPalabra(String palabra){

        String palabraInv = palabra.replaceAll("\\s+","");
        palabraInv = palabraInv.toLowerCase();
        String palabraInvertida = new StringBuilder(palabraInv).reverse().toString();
        System.out.println(palabraInv);
        return palabraInvertida;
    }

    public static void contarVocales(String palabra) {

        Character[] vocalesArray = {'a','e','i','o','u'};
        List<Character> listaVocales = new ArrayList<>(Arrays.asList(vocalesArray));

        int contador = 0;
        String palabraNormalizada = palabra.toLowerCase();

        for (int i = 0; i < palabraNormalizada.length(); i++) {
            char caracter = palabraNormalizada.charAt(i);

            if (listaVocales.contains(caracter)) {
                contador++;

            }
        }
        System.out.println("La palabra tiene " +contador+ " vocales");
    }

}
