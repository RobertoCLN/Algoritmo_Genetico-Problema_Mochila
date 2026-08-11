# Algoritmo Genético - Problema da Mochila

Este projeto implementa um Algoritmo Genético em Java puro para solucionar o clássico **Problema da Mochila (Knapsack Problem)**. A abordagem utilizada foca em um forte encapsulamento e princípios de Orientação a Objetos.

## ⚙️ Características da Implementação

Este algoritmo foi construído com arquitetura Orientada a Objetos, garantindo maior escalabilidade e fácil manutenção. 

As principais características biológicas implementadas incluem:
*   **Seleção por Roleta:** Indivíduos com maior *fitness* possuem fatias proporcionais maiores para sorteio, preservando a chance de itens menores.
*   **Recombinação (Crossover) de 1 Ponto:** Corte transversal e combinação de genes de dois indivíduos pais.
*   **Mutação Genética:** Taxa configurável (atualmente 5%) de inversão de *bit* (flip) para evitar convergência prematura em ótimos locais.
*   **Elitismo (Ajuste Populacional):** Os melhores indivíduos da união entre a geração anterior e a nova prole são mantidos através da implementação da interface `Comparable`.
*   **Penalidade Proporcional:** Indivíduos que ultrapassam a capacidade máxima da mochila não são sumariamente descartados. Seu *fitness* recebe uma penalidade matemática baseada no excesso de peso, permitindo que genes valiosos permaneçam no *pool* genético da população.

## 📂 Estrutura do Projeto

*   `Main.java`: Ponto de entrada limpo da aplicação, responsável por configurar e iniciar a simulação.
*   `TesteGenetico.java`: Classe orquestradora que gerencia o fluxo de tempo (gerações), a reprodução e exibe os relatórios.
*   `Objeto.java`: Entidade que representa os itens disponíveis, encapsulando ID, valor e peso.
*   `Individuo.java`: Modela uma possível solução (a mochila). Contém o cromossomo (vetor binário), métodos de manipulação de DNA e implementa a ordenação descendente por *fitness*.
*   `Dados.java`: Classe responsável por inicializar a base de conhecimento, definindo a capacidade máxima e catalogando os objetos.
*   `Genetico.java`: O motor do sistema. Contém a lógica matemática de evolução, avaliação, seleção, cruzamento e mutação.

## 🚀 Como Executar

Certifique-se de ter o [Java JDK](https://www.oracle.com/java/technologies/downloads/) instalado em sua máquina.

1. Clone este repositório:
   ```bash
   git clone https://github.com/RobertoCLN/Algoritmo_Genetico-Problema_Mochila.git
   ```
2. Compile os arquivos:
   ```bash
   javac *.java
   ```
3. Execute a classe principal:
   ```bash
   java Main
   ```
