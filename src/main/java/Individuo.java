import java.util.Arrays;

public class Individuo implements Comparable<Individuo> {

    private int[] cromossomo;
    private double fitness;
    private double pesoTotal;

    public Individuo(int tamanhoCromossomo) {
        this.cromossomo = new int[tamanhoCromossomo];
        this.fitness = 0.0;
        this.pesoTotal = 0.0;
    }

    public int[] getCromossomo() {
        return cromossomo;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    @Override
    public int compareTo(Individuo outro) {
        return Double.compare(outro.getFitness(), this.fitness);
    }

    @Override
    public String toString() {
        return "Cromossomo: " + Arrays.toString(cromossomo) +
                " | Fit: " + fitness +
                " | Peso: " + pesoTotal;
    }
}