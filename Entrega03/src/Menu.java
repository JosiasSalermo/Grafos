import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;

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

    private void novoGrafo() {
        System.out.println("O grafo é orientado? (s/n):");
        String orientado = scanner.nextLine();

        System.out.println("O grafo é valorado? (s/n):");
        String valorado = scanner.nextLine();

        boolean isOrientado = orientado.equalsIgnoreCase("s");
        boolean isValorado = valorado.equalsIgnoreCase("s");

        grafoAtual = new Grafo(isOrientado, isValorado);

        int opcao;
        do {
            System.out.println("Opções de Grafo:");
            System.out.println("1.1 - Criar o Grafo");
            System.out.println("1.2 - Informações do Grafo");
            System.out.println("1.3 - Salvar Grafo");
            System.out.println("1.4 - Operações com Grafos");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criarGrafo();
                case 2 -> informacoesGrafo();
                case 3 -> salvarGrafo();
                case 4 -> operacoesGrafo();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");

            }
        } while (opcao != 0);
    }

    private void criarGrafo() {
        int opcao;
        do {
            System.out.println("1.1 - Criar o Grafo:");
            System.out.println("1 - Adicionar Vértice");
            System.out.println("2 - Adicionar Aresta");
            System.out.println("3 - Remover Vértice");
            System.out.println("4 - Remover Aresta");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Nome do vértice:");
                    String nomeVertice = scanner.nextLine();
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
                    grafoAtual.adicionarAresta(nomeOrigem, nomeDestino, peso);
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
                    grafoAtual.removerAresta(nomeOrigem, nomeDestino);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }


    private void informacoesGrafo() {
        if (grafoAtual == null) {
            System.out.println("Nenhum grafo foi criado!");
            return;
        }

        int opcao;
        do {
            System.out.println("1.2 - Informações do Grafo:");
            System.out.println("1 - Ordem do Grafo");
            System.out.println("2 - Grau de um Vértice");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> System.out.println("Ordem do Grafo (número de vértices): " + grafoAtual.getOrdem());
                case 2 -> {
                    System.out.println("Digite o nome do vértice para verificar o grau:");
                    String nomeVertice = scanner.nextLine();
                    int grau = grafoAtual.getGrau(nomeVertice);
                    if (grau == -1) {
                        System.out.println("O vértice não existe.");
                    } else {
                        System.out.println("Grau do vértice '" + nomeVertice + "': " + grau);
                    }
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }


    private void operacoesGrafo() {
        if (grafoAtual == null) {
            System.out.println("Nenhum grafo foi criado!");
            return;
        }

        int opcao;
        do {
            System.out.println("1.4 - Operações:");
            System.out.println("1.4.1 - Adicionar Vértice");
            System.out.println("1.4.2 - Adicionar Aresta");
            System.out.println("1.4.3 - Remover Vértice");
            System.out.println("1.4.4 - Remover Aresta");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> adicionarVertice();
                case 2 -> adicionarAresta();
                case 3 -> removerVertice();
                case 4 -> removerAresta();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void adicionarVertice() {
        System.out.println("Nome do vértice:");
        String nomeVertice = scanner.nextLine();
        grafoAtual.adicionarVertice(nomeVertice);
    }

    private void adicionarAresta() {
        System.out.println("Nome do vértice de origem:");
        String nomeOrigem = scanner.nextLine();
        System.out.println("Nome do vértice de destino:");
        String nomeDestino = scanner.nextLine();
        System.out.println("Peso da aresta:");
        int peso = scanner.nextInt();
        scanner.nextLine();
        grafoAtual.adicionarAresta(nomeOrigem, nomeDestino, peso);
    }

    private void removerVertice() {
        System.out.println("Nome do vértice:");
        String nomeVertice = scanner.nextLine();
        grafoAtual.removerVertice(nomeVertice);
    }

    private void removerAresta() {
        System.out.println("Nome do vértice de origem:");
        String nomeOrigem = scanner.nextLine();
        System.out.println("Nome do vértice de destino:");
        String nomeDestino = scanner.nextLine();
        grafoAtual.removerAresta(nomeOrigem, nomeDestino);
    }

    private void salvarGrafo() {
        if (grafoAtual == null) {
            System.out.println("Nenhum grafo foi criado!");
            return;
        }

        System.out.println("Digite o nome do arquivo para salvar o grafo:");
        String nomeArquivo = scanner.nextLine();

        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write(grafoAtual.toDot());
            System.out.println("Grafo salvo com sucesso em: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o grafo: " + e.getMessage());
        }

        //grafoAtual.salvar(nomeArquivo); // Delegação para a classe Grafo
    }

    private void abrirGrafo() {
        System.out.println("Digite o nome do arquivo para abrir o grafo:");
        String nomeArquivo = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            grafoAtual = new Grafo(false, false); // Grafo padrão

            String linha;
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();

                // Ignorar linhas que não representam arestas ou vértices
                if (linha.isEmpty() || linha.startsWith("{") || linha.startsWith("}")) continue;

                // Adicionar vértices ou arestas
                if (linha.contains("->") || linha.contains("--")) {
                    boolean isOrientado = linha.contains("->");
                    String separator = isOrientado ? "->" : "--";
                    String[] partes = linha.split(separator);
                    String origem = partes[0].replaceAll("[\";]", "").trim();
                    String destino = partes[1].replaceAll("[\";\\[label=\\].*]", "").trim();

                    grafoAtual.adicionarVertice(origem);
                    grafoAtual.adicionarVertice(destino);
                    grafoAtual.adicionarAresta(origem, destino, 1); // Peso padrão
                }
            }

            System.out.println("Grafo carregado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao abrir o grafo: " + e.getMessage());
        }
    }


}
