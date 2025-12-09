public class Tablero {

    private Celda[][] celdas;
    private int filas;
    private int columnas;
    private boolean cambios; //

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.celdas = new Celda[filas][columnas];
        this.cambios = true; //true para q inicie
    }
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
    //armardo de tablero
    public Celda getCelda(int fila, int columna) {
        return celdas[fila][columna];
    }

    public void setCelda(int fila, int columna, Celda celda) {
        celdas[fila][columna] = celda;
    }
    //armardo de tablero
    public boolean cambios() {
        return cambios;
    }
    private boolean estaDentroDeTablero(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas; //se mantenga dentro de fila y columnas
    }
    private int contarVecinosVivos(int fila, int columna) {
        int vivos = 0;

        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i == fila && j == columna) {
                    continue; //no cuento como vecino a la fila/columna a analizarle vecinos
                }

                if (estaDentroDeTablero(i, j) && celdas[i][j].estaViva()) {
                    vivos++; //recorre y si encuentra vivo y dentro de tablero, suma 1;
                }
            }
        }

        return vivos; //retorna cantidad de vivos, para luego saber el estado
    }
    public void avanzarGen() {
        //calcular proximo estado de la celda
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) { //recorro filas y columnas, y voy llamando al conteo de vivos
                int vecinosVivos = contarVecinosVivos(i, j);
                celdas[i][j].calcularSiguienteEstado(vecinosVivos); //llama a metodo
            }
        }

        boolean algunCambio = false;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (celdas[i][j].aplicarCambio()) { //si aplicarCambio() de celda es true, es porq hubo cambio
                    algunCambio = true; //asique guardamos el cambio
                }
            }
        }

        this.cambios = algunCambio; //actualizamos el atributo
    }
    public String toString() {
        StringBuilder sb = new StringBuilder(); //creamos strinbuilder para el mapeo

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) { //recorremos la matriz en fila y columna
                Celda celda = celdas[i][j]; //guardo la celda especifica en los recorridos
                if (celda != null) {
                    sb.append(celda.getEstadoActual().getSimbolo()); //cada celda agrega su simbolo
                } else {
                    sb.append('.');
                }
            }
            sb.append("\n");
        }

        return sb.toString(); //retorno el map
    }
}