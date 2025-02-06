import java.util.*;


public class Malgrange {
    /**
     * Método para encontrar os componentes Fortemente Conexos (CFCs) em um grafo
     * utilizando o algoritmo de Kosaraju.
     * */

    public static List<List<Vertice>> grafoReduzido(Grafo grafo){
        Stack<Vertice> pilha = new Stack<>(); // Pilha para armazenar a ordem de finalização da DFS
        Set<Vertice> visitados = new HashSet<>(); // Conjunto para rastrear os vértices visitados

        // 1ª etapa: DFS para calcular a ordem de finalização
        for (Vertice vertice : grafo.getVertices()){
            if (!visitados.contains(vertice)){
                dfs1(grafo, vertice, visitados, pilha);
            }
        }

        // 2ª etapa: Transpor o grafo
        Grafo transposto = transporGrafo(grafo);
        visitados.clear(); // Limpar o conjunto de visitados para a próxima etapa


        // 3ª etapa: DFS no Grafo transposto para encontrar os CFCs
        List<List<Vertice>> componentes = new ArrayList<>();
        while (!pilha.isEmpty()){
            Vertice vertice = pilha.pop(); // Pega o vértice com maior ordem de finalização
            if (!visitados.contains(vertice)){
                List<Vertice> componente = new ArrayList<>();
                dfs2(transposto, vertice, visitados, componente);
                componentes.add(componente); // Adiciona o componente encontrado
            }
        }
        return componentes;
    }

    // DFS para empilhar os vértices conforme a ordem de finalização.
    private static void dfs1(Grafo grafo, Vertice vertice, Set<Vertice> visitados, Stack<Vertice> pilha) {
        visitados.add(vertice); // Marca o vértice como visitado
        for (Vertice vizinho : grafo.getVizinhos(vertice)){ // Percorre os visinhos do vértice
            if (!visitados.contains(vizinho)){
                dfs1(grafo, vizinho, visitados, pilha);
            }
        }
        pilha.push(vertice); // Adiciona o vértice na pilha após a recursão


    }


    // DFS no Grafo transposto para encontrar os CFCs.
    private static void dfs2(Grafo grafo, Vertice vertice, Set<Vertice> visitados, List<Vertice> componente){
        visitados.add(vertice);
        componente.add(vertice);
        for(Vertice vizinho : grafo.getVizinhos(vertice)){
            if (!visitados.contains(vizinho)){
                dfs2(grafo, vizinho, visitados, componente);
            }
        }
    }


    // Método para transpor o grafo (inverter as direções das arestas)
    private static Grafo transporGrafo(Grafo grafo){
        Grafo transposto = new Grafo(grafo.isOrientado(), grafo.isValorado()); // Cria um novo grafo com as mesmas propriedades


        // Adiciona os vértices ao grafo transposto
        for(Vertice vertice : grafo.getVertices()){
            transposto.adicionarVerticeSemMensagem(vertice.getNome());
        }

        // Adiciona as arestas invertidas
        for(Aresta aresta : grafo.getArestas()){
            transposto.adicionarArestaSemMensagem(
                    aresta.getDestino().getNome(), // Origem vira destino
                    aresta.getOrigem().getNome(), // Destino vira origem
                    aresta.getPeso()
            );

        }
        return transposto;
    }
}