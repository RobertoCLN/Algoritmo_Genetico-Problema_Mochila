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
            Individuo ind = new Individuo(tamanhoCromossomo);
            for (int j = 0; j < tamanhoCromossomo; j++) {
                ind.setGene(j, random.nextInt(2));
            }
            populacao.add(ind);
        }
        return populacao;
    }

    public void calcularFitness(Individuo individuo, double capacidadeMax) {
        double pesoTotal = 0;
        double valorTotal = 0;

        for (int i = 0; i < tamanhoCromossomo; i++) {
            if (individuo.getGene(i) == 1) {
                pesoTotal += dados.lista_objetos.get(i).getPeso();
                valorTotal += dados.lista_objetos.get(i).getValor();
            }
        }

        individuo.setPesoTotal(pesoTotal);

        /*if (pesoTotal > capacidadeMax) {
            double fatorPenalidade = capacidadeMax / pesoTotal;
            individuo.setFitness(valorTotal * fatorPenalidade * fatorPenalidade);
        } else {
            individuo.setFitness(valorTotal);
        }*/

        if (pesoTotal > capacidadeMax) {
            individuo.setFitness(0);
        } else {
            individuo.setFitness(valorTotal);
        }
    }

    public double calcularSomaFitness(ArrayList<Individuo> populacao) {
        double soma = 0;
        for (Individuo ind : populacao) {
            soma += ind.getFitness();
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

    public Individuo[] cruzamento(Individuo pai1, Individuo pai2, double taxaReproducao) {
        Individuo filho1 = new Individuo(tamanhoCromossomo);
        Individuo filho2 = new Individuo(tamanhoCromossomo);

        if (random.nextDouble() <= taxaReproducao) {

            int pontoCorte = random.nextInt(tamanhoCromossomo - 1) + 1;

            for (int i = 0; i < tamanhoCromossomo; i++) {
                if (i < pontoCorte) {
                    filho1.setGene(i, pai1.getGene(i));
                    filho2.setGene(i, pai2.getGene(i));
                } else {
                    filho1.setGene(i, pai2.getGene(i));
                    filho2.setGene(i, pai1.getGene(i));
                }
            }
        } else {
            for (int i = 0; i < tamanhoCromossomo; i++) {
                filho1.setGene(i, pai1.getGene(i));
                filho2.setGene(i, pai2.getGene(i));
            }
        }
        return new Individuo[]{filho1, filho2};
    }

    public boolean mutar(Individuo individuo, double taxaMutacao) {
        if (random.nextDouble() <= taxaMutacao) {
            int posicao = random.nextInt(tamanhoCromossomo);
            individuo.inverterGene(posicao);
            return true;
        }
        return false;
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