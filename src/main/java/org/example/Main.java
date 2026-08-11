public class Main {
    public static void main(String[] args) {

        System.out.println("=== INICIANDO ALGORITMO GENÉTICO ===");

        //  (População, Gerações, Taxa de Mutação)
        TesteGenetico teste = new TesteGenetico(10, 3, 10, 0.05);

        teste.iniciarEvolucao();
    }
}