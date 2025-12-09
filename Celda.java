public class Celda {

    private EstadoCelda estadoActual;
    private EstadoCelda estadoSiguiente;

    public Celda(EstadoCelda estadoActual) {
        this.estadoActual = estadoActual; //parametro un estado inicial
    }
    public boolean estaViva() {
        return estadoActual.estaViva(); //consulta estado true o flase
    }
    public EstadoCelda getEstadoActual() {
        return estadoActual; //consulta EstadoClase
    }
    public void calcularSiguienteEstado(int vecinosVivos) {
        this.estadoSiguiente = estadoActual.proximoEstado(vecinosVivos); //analiza estado proximo y guarda.
    }
    public boolean aplicarCambio() {
        if (estadoSiguiente == null) {
            return false; //no cambio el estado
        }

        boolean cambio = (estadoActual != estadoSiguiente); //si nota dos clases distintas

        this.estadoActual = estadoSiguiente; //pasa el siguiente estado al actual
        this.estadoSiguiente = null; // vuelve el siguiente null para q se pueda seguir ejecutando

        return cambio;
    }
}
