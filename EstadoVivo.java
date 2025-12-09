import java.util.Random;

public class EstadoVivo extends EstadoCelda {



    private static double probEnfermarse = 0.25;
    private static final Random random = new Random();

    //se crea el estado vivo
    public boolean estaViva(){
        return true;
    }
    public static void setProbEnfermarse(double probabilidad) {
        probEnfermarse = probabilidad; //puede varias la probabilidad y cambia para todos x static
    }
    //se crean las reglas, segun vecinos se vuelve enferma, muerta o se mantiene
    @Override
    public EstadoCelda proximoEstado(int vecinosVivos) {

        if (random.nextDouble() < probEnfermarse) {
            return new EstadoEnferma(); //si es menor a el % se enferma
        }

        if (vecinosVivos < 2) {
            return new EstadoMuerto(); //regla menor dos vecinos muere
        }

        if (vecinosVivos == 2 || vecinosVivos == 3) {
            return this; //regla sobrevive
        }

        return new EstadoMuerto(); //sino muerta
    }
    @Override
    public char getSimbolo() {
        return 'O';
    }
}
