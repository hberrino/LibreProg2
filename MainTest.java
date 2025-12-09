public class MainTest {
    public static void main(String[] args){

        Tablero tablero = new Tablero(5, 5); //creo tablero 5x5

        //creo la primera gen (0) para darle arranque, usando celdas muertas
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tablero.setCelda(i, j, new Celda(new EstadoMuerto()));
            }
        }
        //agrego 3 celdas vivas para darle intro a la generacion y empiece el bucle;
        tablero.setCelda(1, 2, new Celda(new EstadoVivo()));
        tablero.setCelda(2, 2, new Celda(new EstadoVivo()));
        tablero.setCelda(3, 2, new Celda(new EstadoVivo()));

        //creo el bucle que se rompe cuando ya no hay cambios y el juego termino, o llego a la gen 20.
        //esto para evitar posible bucle sin terminar
        int generacion = 0;//config gens max


        //constructor de tablero tiene cambios=true para que inicie y gen es menor a 20(num ejemplo);
        while (tablero.cambios() && generacion < 20) {

            System.out.println("Gen: " + generacion);
            System.out.println(tablero);

            tablero.avanzarGen();     //avanzo a la proxima actualizacion de gen
            generacion++;
        }
    }
}
