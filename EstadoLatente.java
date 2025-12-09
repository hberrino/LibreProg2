public class EstadoLatente extends EstadoCelda{
    @Override
    public boolean estaViva() {
        return false; //esta muerta de base, no cuenta de vecino
    }

    @Override
    public EstadoCelda proximoEstado(int vecinosVivos) {

        if (vecinosVivos == 1) {
            return new EstadoVivo(); //con 1 vecino vivo pasa a estar viva
        }

        return this; // sino sigue como latente viva=false
    }
    @Override
    public char getSimbolo() {
        return 'X';
    }
}
