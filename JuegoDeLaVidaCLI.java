import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class JuegoDeLaVidaCLI {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            // pido ruta del archivo
            System.out.print("ruta archivo estado inicial: ");
            String ruta = sc.nextLine();

            // pido cantidad de generaciones
            System.out.print("cantidad de generaciones: ");
            int maxGen = sc.nextInt();

            // pido intervalo entre pasos
            System.out.print("intervalo en ms: ");
            int intervaloMs = sc.nextInt();

            // cargo el tablero desde el archivo
            Tablero tablero = cargarDesdeArchivo(ruta);

            int generacion = 0;

            // bucle principal
            // corto solo cuando ya no haya cambios o alcance el maximo de gens.
            while (tablero.cambios() || generacion == maxGen ) {
                System.out.println("gen: " + generacion);
                System.out.println(tablero);

                tablero.avanzarGen();
                generacion++;

                Thread.sleep(intervaloMs);
            }
        } catch (Exception e) {
            System.out.println("error al ejecutar el juego: " + e.getMessage());
        }
    }
    private static Tablero cargarDesdeArchivo(String ruta) throws Exception {

        // leo todas las lineas del archivo de texto
        List<String> lineas = Files.readAllLines(Path.of(ruta));

        // si el archivo no tiene nada, no tiene sentido seguir
        if (lineas.isEmpty()) {
            throw new IllegalArgumentException("el archivo esta vacio");
        }

        // primera linea: cantidad de filas y columnas del tablero
        String[] datos = lineas.get(0).trim().split("\\s+");

        // verifico que vengan exactamente dos valores
        if (datos.length != 2) {
            throw new IllegalArgumentException("formato invalido, se esperan filas y columnas");
        }

        int filas = Integer.parseInt(datos[0]);
        int columnas = Integer.parseInt(datos[1]);

        // creo el tablero con el tamaño leido
        Tablero tablero = new Tablero(filas, columnas);

        // a partir de la segunda linea arranca la grilla
        for (int i = 0; i < filas; i++) {

            // leo la linea que representa una fila del tablero
            String fila = lineas.get(i + 1);

            // verifico que la fila tenga la cantidad correcta de columnas
            if (fila.length() < columnas) {
                throw new IllegalArgumentException("la fila " + i + " tiene menos columnas de las esperadas");
            }

            for (int j = 0; j < columnas; j++) {

                // tomo el simbolo del estado
                char c = fila.charAt(j);
                EstadoCelda estado;

                // segun el simbolo creo el estado
                switch (c) {
                    case 'O':
                        estado = new EstadoVivo();
                        break;
                    case '.':
                        estado = new EstadoMuerto();
                        break;
                    case 'X':
                        estado = new EstadoLatente();
                        break;
                    case 'E':
                        estado = new EstadoEnferma();
                        break;
                    default:
                        // si aparece un caracter que no conozco tiro error
                        throw new IllegalArgumentException(
                                "estado invalido '" + c + "' en (" + i + "," + j + ")"
                        );
                }

                // creo la celda con ese estado y la pongo en el tablero
                tablero.setCelda(i, j, new Celda(estado));
            }
        }

        // devuelvo el tablero ya armado con la generacion inicial
        return tablero;
    }
}
