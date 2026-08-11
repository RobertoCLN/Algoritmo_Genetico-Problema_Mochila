import java.util.ArrayList;
import java.util.Random;

public class Genetico {

    private Dados dados;
    private int tamanhoCromossomo;
    private Random random;

    public Genetico(Dados dados) {
        this.dados = dados;
        this.tamanhoCromossomo = dados.lista_objetos.size();
        this.random = new Random();
    }

    public ArrayList<Individuo> inicializarPopulacao(int tamanhoPopulacao) {
        ArrayList<Individuo> populacao = new ArrayList<>();

        for (int i = 0; i < tamanhoPopulacao; i++) {
            Individuo individuo = new Individuo(tamanhoCromossomo);
            int[] cromossomo = individuo.getCromossomo();

            for (int j = 0; j < tamanhoCromossomo; j++) {
                cromossomo[j] = random.nextInt(2);
            }
            populacao.add(individuo);
        }
        return populacao;
    }

    public void calcularFitness(Individuo individuo, double capacidadeMax) {
        double pesoTotal = 0;
        double valorTotal = 0;
        int[] cromossomo = individuo.getCromossomo();

        for (int i = 0; i < tamanhoCromossomo; i++) {
            if (cromossomo[i] == 1) {
                pesoTotal += dados.lista_objetos.get(i).getPeso();
                valorTotal += dados.lista_objetos.get(i).getValor();
            }
        }

        individuo.setPesoTotal(pesoTotal);


        if (pesoTotal > capacidadeMax) {
            double fatorPenalidade = capacidadeMax / pesoTotal;
            individuo.setFitness(valorTotal * fatorPenalidade * fatorPenalidade);
        } else {
            individuo.setFitness(valorTotal);
        }
    }

    public double calcularSomaFitness(ArrayList<Individuo> populacao) {
        double soma = 0;
        for (Individuo individuo : populacao) {
            soma += individuo.getFitness();
        }
        return soma;
    }

    public Individuo selecionarPaiRoleta(ArrayList<Individuo> populacao, double somaFitness) {

        if (somaFitness <= 0) {
            return populacao.get(random.nextInt(populacao.size()));
        }

        double valorSorteado = random.nextDouble() * somaFitness;
        double acumulado = 0;

        for (Individuo ind : populacao) {
            acumulado += ind.getFitness();
            if (acumulado >= valorSorteado) {
                return ind;
            }
        }

        return populacao.get(populacao.size() - 1);
    }

    public Individuo[] crossover(Individuo pai1, Individuo pai2) {
        Individuo filho1 = new Individuo(tamanhoCromossomo);
        Individuo filho2 = new Individuo(tamanhoCromossomo);

        int[] cromossomoPai1 = pai1.getCromossomo();
        int[] cromossomoPai2 = pai2.getCromossomo();
        int[] cromossomoFilho1 = filho1.getCromossomo();
        int[] cromossomoFilho2 = filho2.getCromossomo();

        int pontoCorte = random.nextInt(tamanhoCromossomo - 1) + 1;

        for (int i = 0; i < tamanhoCromossomo; i++) {
            if (i < pontoCorte) {
                cromossomoFilho1[i] = cromossomoPai1[i];
                cromossomoFilho2[i] = cromossomoPai2[i];
            } else {
                cromossomoFilho1[i] = cromossomoPai2[i];
                cromossomoFilho2[i] = cromossomoPai1[i];
            }
        }

        return new Individuo[]{filho1, filho2};
    }


    public void mutar(Individuo individuo, double taxaMutacao) {
        if (random.nextDouble() <= taxaMutacao) {
            int[] cromossomo = individuo.getCromossomo();

            int posicao = random.nextInt(tamanhoCromossomo);

            cromossomo[posicao] = 1 - cromossomo[posicao];

        }
    }

    public ArrayList<Individuo> ajustePopulacional(ArrayList<Individuo> combinados, int limitePopulacao) {

        combinados.sort(null);

        ArrayList<Individuo> novaPopulacao = new ArrayList<>();

        for (int i = 0; i < limitePopulacao && i < combinados.size(); i++) {
            novaPopulacao.add(combinados.get(i));
        }

        return novaPopulacao;
    }
}