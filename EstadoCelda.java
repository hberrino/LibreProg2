public abstract class EstadoCelda {

    //Si se considera viva
    public abstract boolean estaViva();

    //Analizar vecinos para aplicar regla vive o muere.
    public abstract EstadoCelda proximoEstado(int vecinosVivos);

    public abstract char getSimbolo(); //para que cada estado delege su simbolo al armar el tablero string
}