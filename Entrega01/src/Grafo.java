import java.util.ArrayList;
import java.util.List;

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

    // Adicionar um vértice ao grafo
    public void adicionarVertice(String nome) {
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
            System.out.println("Um ou ambos os vértices não existem.");
            return;
        }

        Aresta novaAresta = new Aresta(origem, destino, valorado ? peso : 1);
        arestas.add(novaAresta);

        if (!orientado) { // Se não for orientado, adiciona a aresta inversa
            Aresta arestaInversa = new Aresta(destino, origem, valorado ? peso : 1);
            arestas.add(arestaInversa);
        }

        System.out.println("Aresta adicionada: " + origem + " -> " + destino + " (Peso: " + peso + ")");
    }

    // Remover uma aresta específica
    public void removerAresta(String nomeOrigem, String nomeDestino) {
        Vertice origem = buscarVertice(nomeOrigem);
        Vertice destino = buscarVertice(nomeDestino);

        if (origem == null || destino == null) {
            System.out.println("Um ou ambos os vértices não existem.");
            return;
        }

        arestas.removeIf(aresta ->
                aresta.getOrigem().equals(origem) && aresta.getDestino().equals(destino));

        if (!orientado) {
            arestas.removeIf(aresta ->
                    aresta.getOrigem().equals(destino) && aresta.getDestino().equals(origem));
        }

        System.out.println("Aresta de '" + nomeOrigem + "' para '" + nomeDestino + "' removida.");
    }

    // Buscar um vértice pelo nome
    private Vertice buscarVertice(String nome) {
        return vertices.stream().filter(v -> v.getNome().equals(nome)).findFirst().orElse(null);
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
}
