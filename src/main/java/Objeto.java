public class Objeto {

    private int id;
    private double peso;
    private double valor;

    public Objeto() {
        this.id = 0;
        this.valor = 0;
        this.peso = 0;
    }

    public Objeto(int id, double peso, double valor) {
        this.id = id;
        this.peso = peso;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public double getPeso() {
        return peso;
    }
}
