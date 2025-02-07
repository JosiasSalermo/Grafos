import br.com.davesmartins.api.Graph;

import java.awt.*;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Grafo grafoAtual;

    public void exibirMenuPrincipal() {
        int opcao = 0;
        do {
            System.out.println("MENU PRINCIPAL:");
            System.out.println("1 - Novo Grafo");
            System.out.println("2 - Abrir Grafo");
            System.out.println("0 - Sair");
            System.out.println("Escolha uma opção:");

            if(!scanner.hasNextInt()){ // Entrega 05
                System.out.println("Por Favor, insira um número válido.");
                scanner.next();
                continue;
            }

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
            System.out.println("OPÇÕES DO GRAFO:");
            System.out.println("1 - [1.1]Criar o Grafo");
            System.out.println("2 - [1.2]Informações do Grafo");
            System.out.println("3 - [1.3]Salvar Grafo");
            System.out.println("4 - [1.4]Operações com Grafos");
            System.out.println("5 - [1.5]Problemas");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criarGrafo();
                case 2 -> informacoesGrafo();
                case 3 -> salvarGrafo();
                case 4 -> operacoesGrafo();
                case 5 -> menuProblemas(); // Entrega 05
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");

            }
        } while (opcao != 0);
    }

    private void criarGrafo() {
        int opcao;
        do {
            System.out.println("[1.1]CRIAR O GRAFO:");
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
            System.out.println("[1.2]INFORMAÇÕES DO GRAFO:");
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
            System.out.println("[1.4]OPERAÇÕES:");
            System.out.println("1 - [1.4.1]Adicionar Vértice");
            System.out.println("2 - [1.4.2]Adicionar Aresta");
            System.out.println("3 - [1.4.3]Remover Vértice");
            System.out.println("4 - [1.4.4]Remover Aresta");
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

        System.out.println("Digite o nome do arquivo para salvar o grafo (ex: grafo):");
        String nomeArquivo = scanner.nextLine();
        if (!nomeArquivo.endsWith(".dot")) {
            nomeArquivo += ".dot"; // Garante a extensão correta
        }


        try {
            // Representação DOT do grafo
            String dotRepresentation = grafoAtual.toDot();
            String caminhoDot = System.getProperty("user.dir") + "/" + nomeArquivo;

            // Salvar o arquivo DOT
            try (FileWriter writer = new FileWriter(caminhoDot)) {
                writer.write(dotRepresentation);
                System.out.println("Arquivo DOT salvo em: " + caminhoDot);
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo DOT: " + e.getMessage());
        }
    }

    private void exibirGrafo() {
        if (grafoAtual == null) {
            System.out.println("Nenhum grafo foi carregado ou criado!");
            return;
        }

        System.out.println("Vértices do Grafo:");
        for (Vertice vertice : grafoAtual.getVertices()) {
            System.out.println(" - " + vertice.getNome());
        }

        System.out.println("Arestas do Grafo:");
        if (grafoAtual.getArestas().isEmpty()) {
            System.out.println("Nenhuma aresta encontrada.");
            return;
        }

        if (grafoAtual.isOrientado()) {
            for (Aresta aresta : grafoAtual.getArestas()) {
                String origem = aresta.getOrigem().getNome();
                String destino = aresta.getDestino().getNome();
                int peso = aresta.getPeso();
                System.out.println(" - " + origem + " -> " + destino + " (Peso: " + peso + ")");
            }
        } else {
            Set<String> exibidas = new HashSet<>();
            for (Aresta aresta : grafoAtual.getArestas()) {
                String origem = aresta.getOrigem().getNome();
                String destino = aresta.getDestino().getNome();
                int peso = aresta.getPeso();

                String representacao = origem.compareTo(destino) < 0
                        ? origem + "--" + destino
                        : destino + "--" + origem;

                if (!exibidas.contains(representacao)) {
                    System.out.println(" - " + origem + " -- " + destino + " (Peso: " + peso + ")");
                    exibidas.add(representacao);
                }
            }
        }
    }

    private void abrirGrafo() {
        System.out.println("Digite o nome do arquivo para abrir o grafo (ex: grafo.dot):");
        String nomeArquivo = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            grafoAtual = null; // Resetar o grafo atual
            String linha;

            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();

                if (linha.isEmpty() || linha.startsWith("{") || linha.startsWith("}")) continue;

                if (linha.startsWith("digraph")) {
                    grafoAtual = new Grafo(true, true); // Grafo orientado e valorado
                    continue;
                }
                if (linha.startsWith("graph")) {
                    grafoAtual = new Grafo(false, true); // Grafo não orientado e valorado
                    continue;
                }

                if (linha.contains("->") || linha.contains("--")) {
                    boolean isOrientado = linha.contains("->");
                    String separator = isOrientado ? "->" : "--";
                    String[] partes = linha.split(separator);

                    String origem = partes[0].replaceAll("[\";]", "").trim();
                    String destinoComPeso = partes[1].replaceAll("[\";]", "").trim();

                    String destino = destinoComPeso.split("\\[")[0].trim();
                    int peso = 1; // Peso padrão
                    if (destinoComPeso.contains("label=")) {
                        String pesoStr = destinoComPeso.split("label=")[1].replaceAll("[\\[\\]\"]", "").trim();
                        peso = Integer.parseInt(pesoStr);
                    }

                    grafoAtual.adicionarVerticeSemMensagem(origem);
                    grafoAtual.adicionarVerticeSemMensagem(destino);
                    grafoAtual.adicionarArestaSemMensagem(origem, destino, peso);

                    // Debug para verificar as arestas carregadas
                    System.out.println("Aresta carregada: " + origem + (isOrientado ? " -> " : " -- ") + destino + " (Peso: " + peso + ")");
                }
            }

            if (grafoAtual != null) {
                System.out.println("Grafo carregado com sucesso!");
                exibirGrafo();
            } else {
                System.out.println("Erro: O arquivo não contém um grafo válido.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao abrir o grafo: " + e.getMessage());
        }
    }

    private void problemasGrafo(){
        if(grafoAtual == null){
            System.out.println("Nenhum grafo foi criado!");
            return;
        }

        int opcao;
        do {
            System.out.println("[1.5]PROBLEMAS:");
            System.out.println("1 - [1.5.1]Caminho Mínimo");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> caminhoMinimo();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        }while(opcao != 0);
    }

    private void caminhoMinimo(){
        System.out.println("Digite o vértice de origem:");
        String origem = scanner.nextLine();
        grafoAtual.dijkstra(origem);
    }

    // Entrega 05
    private void menuProblemas() {
        if (grafoAtual == null) {
            System.out.println("Nenhum grafo foi criado!");
            return;
        }

        int opcao;
        do {
            System.out.println("[1.5]PROBLEMAS:");
            System.out.println("1 - [1.5.1]Caminho Mínimo");
            System.out.println("2 - [1.5.2]Árvore Geradora");
            System.out.println("3 - [1.5.3]Visualizar Grafo"); // Entrega 05
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> menuCaminhoMinimo();
                case 2 -> menuArvoreGeradora();
                case 3 -> visualizarGrafo(); // Entrega 05
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void menuArvoreGeradora() {
        int opcao;
        do {
            System.out.println("1.5.2 - Árvore Geradora:");
            System.out.println("1.5.2.1 - Algoritmo de Prim");
            System.out.println("1.5.2.2 - Algoritmo de Kruskal");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção:");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> executarPrim();
                case 2 -> executarKruskal();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void executarPrim() {
        System.out.println("Digite o vértice inicial para o algoritmo de Prim:");
        String verticeInicial = scanner.nextLine();

        try {
            List<Aresta> agm = grafoAtual.algoritmoPrim(verticeInicial);
            System.out.println("Árvore Geradora Mínima (Prim):");
            for (Aresta aresta : agm) {
                System.out.println(aresta);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void executarKruskal() {
        try {
            List<Aresta> agm = grafoAtual.algoritmoKruskal();
            System.out.println("Árvore Geradora Mínima (Kruskal):");
            for (Aresta aresta : agm) {
                System.out.println(aresta);
            }
        } catch (Exception e) {
            System.out.println("Erro ao executar o algoritmo de Kruskal: " + e.getMessage());
        }
    }

    private void menuCaminhoMinimo() {
        System.out.println("Função para Caminho Mínimo ainda não implementada.");
    }

    private void visualizarGrafo(){
        if(grafoAtual == null){
            System.out.println("Nenhum grafo foi criado!");
            return;
        }

        System.out.println("Digite o nome do arquivo para salvar a imagem (ex: grafo.png):");
        String nomeImagem = scanner.nextLine();
        if(!nomeImagem.endsWith(".png")){
            nomeImagem += ".png";
        }

        try {
            String dotRepresentation = grafoAtual.toDot(); // Gere a representação DOT do grafo
            String caminhoCompleto = System.getProperty("user.dir") + "/" + nomeImagem; // Caminho absoluto
            Graph.createStringDotToPng(dotRepresentation, caminhoCompleto); // Use a API para gerar a imagem
            System.out.println("Imagem do grafo salva em: " + caminhoCompleto);


        } catch (IOException e) {
            System.out.println("Erro ao gerar  a imagem: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Erro inesperado: " + e.getMessage());
        }



    }



























}
