public class EstadoMuerto  extends EstadoCelda{

    //retorna el estado muerto
    public boolean estaViva (){
        return false;
    }
    //Reglas, segun vecinos, vive o se mantiene muerta.
    public EstadoCelda proximoEstado (int vecinosVivos){

        if (vecinosVivos == 3) {
            return new EstadoVivo(); //celda muerta revive si tiene 3 vecinos vivos
        }

        return this; // sigue muerta

    }
    @Override
    public char getSimbolo() {
        return '.';
    }
}
