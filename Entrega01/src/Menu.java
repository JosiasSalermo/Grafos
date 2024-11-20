import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Grafo grafoAtual;

    public void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("MENU PRINCIPAL:");
            System.out.println("1 - Novo Grafo");
            System.out.println("2 - Abrir Grafo");
            System.out.println("0 - Sair");
            System.out.println("Escolha uma opção:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> novoGrafo();
                case 2 -> abrirGrafo();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void novoGrafo(){
        System.out.println("O grafo é orientado? (s/n):");
        String orientado = scanner.nextLine();

        System.out.println("O grafo é valorado? (s/n):");
        String valorado = scanner.nextLine();

        boolean isOrientado = orientado.equalsIgnoreCase("s");
        boolean isValorado = valorado.equalsIgnoreCase("s");

        grafoAtual = new Grafo(isOrientado,isValorado);

        int opcao;
        do{
            System.out.println("Opções de Grafo:");
            System.out.println("1 - Adicionar vértice");
            System.out.println("2 - Adicionar aresta");
            System.out.println("3 - Remover vértice");
            System.out.println("4 - Remover aresta");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){

                case 1 -> {
                    System.out.println("Nome do vértice:");
                    String nomeVertice = scanner.nextLine();
                    //scanner.nextLine();
                    grafoAtual.adicionarVertice(nomeVertice);
                }
                case 2 -> {
                    System.out.println("Nome do vértice de origem:");
                    String nomeOrigem = scanner.nextLine();

                    System.out.println("Nome do vértice de destino:");
                    String nomeDestino = scanner.nextLine();

                    System.out.println("Peso da aresta:");
                    int peso = scanner.nextInt();
                    scanner.nextLine();
                    grafoAtual.adicionarAresta(nomeOrigem,nomeDestino,peso);
                }
                case 3 -> {
                    System.out.println("Nome do vértice:");
                    String nomeVertice = scanner.nextLine();
                    grafoAtual.removerVertice(nomeVertice);
                }
                case 4 -> {
                    System.out.println("Nome do vértice de origem:");
                    String nomeOrigem = scanner.nextLine();
                    
                    System.out.println("Nome do vértice de destino:");
                    String nomeDestino = scanner.nextLine();
                    grafoAtual.removerAresta(nomeOrigem,nomeDestino);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        }while(opcao !=0);
    }
}