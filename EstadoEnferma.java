public class EstadoEnferma extends EstadoCelda{
    @Override
    public boolean estaViva() {
        return true; //esta viva aunque enferma, sirve conteo vecino
    }

    @Override
    public EstadoCelda proximoEstado(int vecinosVivos) {
        return new EstadoMuerto(); //siempre muere a la proxima gen.
    }
    @Override
    public char getSimbolo() {
        return 'E';
    }
}
