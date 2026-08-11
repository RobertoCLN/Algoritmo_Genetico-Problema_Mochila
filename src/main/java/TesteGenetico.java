import java.util.ArrayList;
import java.util.Arrays;

public class TesteGenetico {

    private int tamanhoPopulacao;
    private int geracoes;
    private double taxaMutacao;

    private Dados dadosProblema;
    private double capacidade;
    private Genetico algoritmo;

    public TesteGenetico(int tamanhoPopulacao, int geracoes, double taxaMutacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.geracoes = geracoes;
        this.taxaMutacao = taxaMutacao;

        this.dadosProblema = new Dados();
        this.capacidade = dadosProblema.getCapacidadeMochila();
        this.algoritmo = new Genetico(dadosProblema);
    }

    public void iniciarEvolucao() {
        System.out.println("Capacidade: " + capacidade + "Kg | População: " + tamanhoPopulacao + " | Gerações: " + geracoes);

        ArrayList<Individuo> populacao = algoritmo.inicializarPopulacao(tamanhoPopulacao);

        for (Individuo ind : populacao) {
            algoritmo.calcularFitness(ind, capacidade);
        }

        for (int g = 1; g <= geracoes; g++) {
            System.out.println("\n--- GERAÇÃO " + g + " ---");

            double somaFitness = algoritmo.calcularSomaFitness(populacao);
            ArrayList<Individuo> descendentes = new ArrayList<>();

            while (descendentes.size() < tamanhoPopulacao) {
                Individuo pai1 = algoritmo.selecionarPaiRoleta(populacao, somaFitness);
                Individuo pai2 = algoritmo.selecionarPaiRoleta(populacao, somaFitness);

                Individuo[] filhos = algoritmo.crossover(pai1, pai2);

                algoritmo.mutar(filhos[0], taxaMutacao);
                algoritmo.mutar(filhos[1], taxaMutacao);

                algoritmo.calcularFitness(filhos[0], capacidade);
                algoritmo.calcularFitness(filhos[1], capacidade);

                descendentes.add(filhos[0]);
                descendentes.add(filhos[1]);
            }

            ArrayList<Individuo> combinados = new ArrayList<>(populacao);
            combinados.addAll(descendentes);

            populacao = algoritmo.ajustePopulacional(combinados, tamanhoPopulacao);

            System.out.println("Melhor da Geração " + g + ": " + populacao.get(0).toString());
        }

        System.out.println("\n>>>> SOLUÇÃO ENCONTRADA <<<<");
        Individuo melhorSolucao = populacao.get(0);

        System.out.println("INDIVÍDUO SOLUÇÃO: " + Arrays.toString(melhorSolucao.getCromossomo()) +
                " / Fit:" + melhorSolucao.getFitness());
        System.out.println("Lucro Total = R$" + melhorSolucao.getFitness());
        System.out.println("Peso Total = " + melhorSolucao.getPesoTotal() + "Kg");
    }
}