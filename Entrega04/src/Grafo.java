import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;


public class Grafo {
    private boolean orientado;  // Indica se o grafo é orientado
    private boolean valorado;   // Indica se o grafo é valorado
    private List<Vertice> vertices; // Lista de vértices do grafo
    private List<Aresta> arestas;   // Lista de arestas do grafo

    public Grafo(boolean orientado, boolean valorado) {
        this.orientado = orientado;
        this.valorado = valorado;
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }


    public void adicionarVertice(String nome) {
        if(nome == null || nome.isBlank()){ // Ascresentei a verificação de nome vazio
            System.out.println("Nome do vértice inválido. Não pode ser vazio.");
            return;
        }

        Vertice novoVertice = new Vertice(nome);
        if (!vertices.contains(novoVertice)) {
            vertices.add(novoVertice);
            System.out.println("Vértice '" + nome + "' adicionado.");
        } else {
            System.out.println("O vértice '" + nome + "' já existe.");
        }
    }

    // Remover um vértice e suas arestas associadas
    public void removerVertice(String nome) {

        if(nome == null || nome.isBlank()){ // Ascresentei a verificação de nome vazio
            System.out.println("Nome do vértice inválido. Não pode ser vazio.");
            return;
        }


        Vertice vertice = buscarVertice(nome);
        if (vertice != null) {
            vertices.remove(vertice);
            arestas.removeIf(aresta ->
                    aresta.getOrigem().equals(vertice) || aresta.getDestino().equals(vertice));
            System.out.println("Vértice '" + nome + "' removido.");
        } else {
            System.out.println("O vértice '" + nome + "' não existe.");
        }
    }

    // Adicionar uma aresta entre dois vértices
    public void adicionarAresta(String nomeOrigem, String nomeDestino, int peso) {
        Vertice origem = buscarVertice(nomeOrigem);
        Vertice destino = buscarVertice(nomeDestino);

        if (origem == null || destino == null) {
            System.out.println("Erro: Um ou ambos os vértices não existem.");
            return;
        }

        Aresta novaAresta = new Aresta(origem, destino, peso);

        // Adiciona a aresta original
        if (!arestas.contains(novaAresta)) {
            arestas.add(novaAresta);

            // Adiciona a aresta inversa se o grafo não for orientado
            if (!orientado) {
                Aresta arestaInversa = new Aresta(destino, origem, peso);
                arestas.add(arestaInversa);
            }

            System.out.println("Aresta adicionada: " + nomeOrigem + " -> " + nomeDestino + " (Peso: " + peso + ")");
        } else {
            System.out.println("Aresta já existente: " + nomeOrigem + " -> " + nomeDestino);
        }

        if(arestas.contains(novaAresta)){
            System.out.println("Aresta entre '" + nomeOrigem + "' e '" + nomeDestino + "' já existe.");
            return;

        }

        arestas.add(novaAresta);

        if(!orientado){
            Aresta arestaInversa = new Aresta(destino, origem, valorado? peso : 1);
            arestas.add(arestaInversa);
        }
    }

    // Remover uma aresta específica
    public void removerAresta(String nomeOrigem, String nomeDestino) {

        if(nomeOrigem == null || nomeOrigem.isBlank() || nomeDestino == null || nomeDestino.isBlank()){ // Ascresentei a verificação de nome vazio
            System.out.println("Os nomes de origem e destino não podem ser vazios.");
            return;
        }

        Vertice origem = buscarVertice(nomeOrigem);
        Vertice destino = buscarVertice(nomeDestino);

        if (origem == null || destino == null) {
            System.out.println("Um ou ambos os vértices não existem.");
            return;
        }

        boolean removido = arestas.removeIf(aresta ->
                aresta.getOrigem().equals(origem) && aresta.getDestino().equals(destino));

        if (!orientado) {
            removido |= arestas.removeIf(aresta ->
                    aresta.getOrigem().equals(destino) && aresta.getDestino().equals(origem));
        }

        if (!removido) {
            System.out.println("Aresta entre '" + nomeOrigem + "' e '" + nomeDestino + "' não existe.");
        } else {
            System.out.println("Aresta de '" + nomeOrigem + "' para '" + nomeDestino + "' removida.");
        }


    }

    // Buscar um vértice pelo nome
    public Vertice buscarVertice(String nome) {
        for (Vertice vertice : vertices) {
            if (vertice.getNome().equals(nome)) {
                return vertice;
            }
        }
        return null;
    }

    // Retorna o número de vértices (ordem do grafo)
    public int getOrdem() {
        return vertices.size();
    }

    // Calcula o grau de um vértice
    public int getGrau(String nome) {
        Vertice vertice = buscarVertice(nome);
        if (vertice == null) {
            System.out.println("O vértice '" + nome + "' não existe.");
            return -1;
        }

        return (int) arestas.stream().filter(aresta ->
                aresta.getOrigem().equals(vertice) || (!orientado && aresta.getDestino().equals(vertice))).count();
    }

    // Exibe as informações do grafo
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vértices: ").append(vertices).append("\n");
        sb.append("Arestas: ").append(arestas);
        return sb.toString();
    }
    /*
        public void salvar(String nomeArquivo) {
            try (FileWriter writer = new FileWriter(nomeArquivo)) {
                writer.write(toString());
                System.out.println("Grafo salvo com sucesso em: " + nomeArquivo);
            } catch (IOException e) {
                System.out.println("Erro ao salvar o grafo: " + e.getMessage());
            }
        }

        public void abrir(String nomeArquivo) {
            try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
                // Lógica para reconstruir o grafo a partir do arquivo
                String linha;
                while ((linha = reader.readLine()) != null) {
                    System.out.println("Linha lida: " + linha); // Exemplo de leitura
                    // Aqui você pode interpretar as linhas e reconstruir vértices e arestas
                }
            } catch (IOException e) {
                System.out.println("Erro ao abrir o grafo: " + e.getMessage());
            }
        }

         public boolean existeAresta(String origem, String destino) {
            Vertice verticeOrigem = buscarVertice(origem);
            Vertice verticeDestino = buscarVertice(destino);

            if (verticeOrigem == null || verticeDestino == null) {
                return false;
            }

            return arestas.stream().anyMatch(aresta ->
                    aresta.getOrigem().equals(verticeOrigem) && aresta.getDestino().equals(verticeDestino));
        }
    */
    public String toDot() {
        StringBuilder sb = new StringBuilder();
        sb.append(orientado ? "digraph {" : "graph {").append("\n");

        // Adicionar vértices
        for (Vertice vertice : vertices) {
            sb.append("    \"").append(vertice.getNome()).append("\";\n");
        }

        // Adicionar arestas
        Set<String> exibidas = new HashSet<>(); // Evitar duplicação em grafos não orientados
        for (Aresta aresta : arestas) {
            String origem = aresta.getOrigem().getNome();
            String destino = aresta.getDestino().getNome();
            int peso = aresta.getPeso();

            String representacao = orientado
                    ? origem + " -> " + destino
                    : origem.compareTo(destino) < 0
                    ? origem + " -- " + destino
                    : destino + " -- " + origem;

            if (!orientado && exibidas.contains(representacao)) continue;

            sb.append("    \"").append(origem).append("\" ")
                    .append(orientado ? "->" : "--")
                    .append(" \"").append(destino).append("\"");

            if (valorado) {
                sb.append(" [label=\"").append(peso).append("\"]");
            }
            sb.append(";\n");

            exibidas.add(representacao);
        }

        sb.append("}");
        return sb.toString();
    }

    public void adicionarVerticeSemMensagem(String nome) {
        Vertice novoVertice = new Vertice(nome);
        if (!vertices.contains(novoVertice)) {
            vertices.add(novoVertice);
        }
    }

    public void adicionarArestaSemMensagem(String origem, String destino, int peso) {
        Vertice verticeOrigem = buscarVertice(origem);
        Vertice verticeDestino = buscarVertice(destino);

        if (verticeOrigem == null || verticeDestino == null) return;

        Aresta novaAresta = new Aresta(verticeOrigem, verticeDestino, peso);

        if (!arestas.contains(novaAresta)) {
            arestas.add(novaAresta);
        }

        if (!orientado) {
            Aresta arestaInversa = new Aresta(verticeDestino, verticeOrigem, peso);
            if (!arestas.contains(arestaInversa)) {
                arestas.add(arestaInversa);
            }
        }
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public boolean isOrientado() {
        return orientado;
    }

    public void dijkstra(String nomeOrigem){
        Vertice origem = buscarVertice(nomeOrigem);
          if(origem = null){
              System.out.println("O vértice de origem não existe no Grafo.");
              return;
          }

          Map<Vertice, Integer> distancias = new HashMap<>();
          Map<Vertice, Vertice> predecessores = new hashPriorityQueue<>();
          PriorityQueue<Vertice> filaPrioridade new Prioridade<>(Comparator.comparingInt(distancia::get);

          // Inicialização
          for(Vertice vestice : vertices){
              distancias.put(vertice, Integer.MAX_VALUE);
              predecessores.put(vertice, null);
          }
          distancias.put(origem, 0);
          filaPrioridade.add(origem);

          // Processamento
        while (!filaPrioridade.isEmpty()){
            Vertice atual = filaPrioridade.poll();

            // Relaxar as arestas adjacentes
            for(Aresta aresta : arestas){
                if (aresta.getOrigem().equals(atual)){
                    Vertice vizinho = aresta.getDestino();
                    int novaDistancia = distancias.get(atual) + aresta.getPeso();

                    if(novaDistancia < distancias.get(vizinho)){
                        distancias.put(vizinho, novaDistancia);
                        predecessores.put(vizinho, atual);

                        // Atualizar a fila e prioridade
                        filaprioridade.remove(vizinho);
                        filaPrioridade.add(vizinho);
                    }
                }
            }
        }

        // Exibir resultados
        System.out.println("Menores distâncias a partir do vértice '" + nomeOrigem + "':");
        for (Vertice vertice : vertices){
            int distancia = distancias.get(vertice);
            if(distancia == Integer.MAX_VALUE){
                System.out.println(" - " + vertice.getNome() + ": Inalcançável");
            }else{
                System.out.println(" - " + vertice.getNome() + ": " + distancia);
                System.out.println(" (Caminho: ");
                imprimirCaminho(predecessores, vertice);
                System.out.println(")");
            }
        }

    }





}
