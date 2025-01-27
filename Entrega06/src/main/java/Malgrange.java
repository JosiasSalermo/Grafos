import java.util.*;

public class Malgrange {
    public static List<List<Vertice>> grafoReduzido(Grafo grafo) {
        Stack<Vertice> pilha = new Stack<>();
        Set<Vertice> visitados = new HashSet<>();

        // 1ª etapa: DFS para calcular a ordem de finalização
        for (Vertice vertice : grafo.getVertices()) {
            if (!visitados.contains(vertice)) {
                dfs1(grafo, vertice, visitados, pilha);
            }
        }

        // 2ª etapa: Transpor o grafo
        Grafo transposto = transporGrafo(grafo);
        visitados.clear(); // Limpar o conjunto de visitados para a próxima etapa

        // 3ª etapa: DFS no grafo transposto para encontrar os CFCs
        List<List<Vertice>> componentes = new ArrayList<>();
        while (!pilha.isEmpty()) {
            Vertice vertice = pilha.pop();
            if (!visitados.contains(vertice)) {
                List<Vertice> componente = new ArrayList<>();
                dfs2(transposto, vertice, visitados, componente);
                componentes.add(componente);
            }
        }

        return componentes;
    }

    private static void dfs1(Grafo grafo, Vertice vertice, Set<Vertice> visitados, Stack<Vertice> pilha) {
        visitados.add(vertice);
        for (Vertice vizinho : grafo.getVizinhos(vertice)) { // Adicione getVizinhos na classe Grafo
            if (!visitados.contains(vizinho)) {
                dfs1(grafo, vizinho, visitados, pilha);
            }
        }
        pilha.push(vertice);
    }

    private static void dfs2(Grafo grafo, Vertice vertice, Set<Vertice> visitados, List<Vertice> componente) {
        visitados.add(vertice);
        componente.add(vertice);
        for (Vertice vizinho : grafo.getVizinhos(vertice)) {
            if (!visitados.contains(vizinho)) {
                dfs2(grafo, vizinho, visitados, componente);
            }
        }
    }

    private static Grafo transporGrafo(Grafo grafo) {
        Grafo transposto = new Grafo(grafo.isOrientado(), grafo.isValorado());
        for (Vertice vertice : grafo.getVertices()) {
            transposto.adicionarVerticeSemMensagem(vertice.getNome());
        }
        for (Aresta aresta : grafo.getArestas()) {
            transposto.adicionarArestaSemMensagem(
                    aresta.getDestino().getNome(),
                    aresta.getOrigem().getNome(),
                    aresta.getPeso()
            );
        }
        return transposto;
    }
}
