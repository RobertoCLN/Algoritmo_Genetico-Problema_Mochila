import java.util.ArrayList;
import java.util.Arrays;

public class TesteGenetico {

    private int tamanhoPopulacao;
    private int geracoes;
    private double taxaReproducao;
    private double taxaMutacao;
    private Dados dadosProblema;
    private double capacidade;
    private Genetico genetico;

    public TesteGenetico(int tamanhoPopulacao, int geracoes, double taxaReproducao, double taxaMutacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.geracoes = geracoes;
        this.taxaReproducao = taxaReproducao;
        this.taxaMutacao = taxaMutacao;
        this.dadosProblema = new Dados();
        this.capacidade = dadosProblema.getCapacidadeMochila();
        this.genetico = new Genetico(dadosProblema);
    }

    public void iniciarEvolucao() {
        System.out.println("Capacidade: " + capacidade + "Kg | População: " + tamanhoPopulacao + " | Gerações: " + geracoes);

        ArrayList<Individuo> populacao = genetico.inicializarPopulacao(tamanhoPopulacao);

        for (Individuo ind : populacao) {
            genetico.calcularFitness(ind, capacidade);
        }

        populacao.sort(null);

        System.out.println("\n--- GERAÇÃO 0 (População Inicial) ---");
        for (int i = 0; i < populacao.size(); i++) {
            System.out.println((i + 1) + " -> " + populacao.get(i).toString());
        }

        for (int g = 1; g <= geracoes; g++) {
            System.out.println("\n--- GERAÇÃO " + g + " ---");

            double somaFitness = genetico.calcularSomaFitness(populacao);
            ArrayList<Individuo> descendentes = new ArrayList<>();

            while (descendentes.size() < tamanhoPopulacao) {
                Individuo pai1 = genetico.selecionarPaiRoleta(populacao, somaFitness);
                Individuo pai2 = genetico.selecionarPaiRoleta(populacao, somaFitness);

                Individuo[] filhos = genetico.cruzamento(pai1, pai2, taxaReproducao);

                String dnaAntes1 = Arrays.toString(filhos[0].getCromossomo());
                String dnaAntes2 = Arrays.toString(filhos[1].getCromossomo());

                boolean mutouFilho1 = genetico.mutar(filhos[0], taxaMutacao);
                boolean mutouFilho2 = genetico.mutar(filhos[1], taxaMutacao);

                if (mutouFilho1) {
                    System.out.println("Mutação: " + dnaAntes1 + " -> " + Arrays.toString(filhos[0].getCromossomo()));
                }
                if (mutouFilho2) {
                    System.out.println("Mutação: " + dnaAntes2 + " -> " + Arrays.toString(filhos[1].getCromossomo()));
                }

                genetico.calcularFitness(filhos[0], capacidade);
                genetico.calcularFitness(filhos[1], capacidade);

                descendentes.add(filhos[0]);
                descendentes.add(filhos[1]);
            }

            ArrayList<Individuo> combinados = new ArrayList<>(populacao);
            combinados.addAll(descendentes);

            populacao = genetico.ajustePopulacional(combinados, tamanhoPopulacao, capacidade);

            for (int i = 0; i < populacao.size(); i++) {
                System.out.println((i + 1) + " -> " + populacao.get(i).toString());
            }
        }

        Individuo melhorSolucao = null;

        for (Individuo ind : populacao) {
            if (ind.getPesoTotal() <= capacidade) {
                melhorSolucao = ind;
                break;
            }
        }

        if (melhorSolucao == null || melhorSolucao.getFitness() <= 0) {
            System.out.println("O algoritmo falhou em encontrar uma mochila válida nesta execução.");
            return;
        }

        double somaFitnessFinal = genetico.calcularSomaFitness(populacao);
        double probabilidade = melhorSolucao.getFitness() / somaFitnessFinal;



            System.out.println("\n>>>> SOLUÇÃO ENCONTRADA <<<<");
            System.out.println("INDIVÍDUO SOLUÇÃO: " + Arrays.toString(melhorSolucao.getCromossomo()) +
                    " / Fit:" + melhorSolucao.getFitness() +
                    " / Prob:" + probabilidade);
            System.out.println("Lucro Total = R$" + melhorSolucao.getFitness());
            System.out.println("Peso Total = " + melhorSolucao.getPesoTotal() + "Kg");
    }
}