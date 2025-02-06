import java.util.*;

public class Malgrange{
    public static List<List<Vertice>> grafoReduzido(Grafo grafo){
        Stack<Vertice> pilha = new Stack<>(); // pilha de vértices
        Set<Vertice> visitados = new HashSet<>(); // conjunto de vértices visitados

        // 1ª Etapa: Percorre todos os vértices do grafo para garantir que a
        // primeira  DFS.
        for(Vertice vertice: grafo.getVertice()){
            if(!visitados.contains(vertice)){
                dfs1(grafo, vertice, visitados, pilha);
            }
        }

        // 2ª Etapa: Criação do Grafo transposto
        Grafo transposto = transporGrafo(grafo);
        visitados.clear(); // limpa o conjunto de vértices visitados

        // 3ª Etapa: Esse trecho de código é responsável por encontrar os
        // Componentes Fortemente Conexos (CFCs) de um grafo transposto
        List<List<Vertice>> componentes = new ArrayList<>(); // Cria uma lista de listas para armazenar os componentes fortemente conexos.
        while(!pilha.isEmpty()){ // Enquanto houver vértices na pilha (carregada na primeira DFS), eles serão processados.
            Vertice vertice = pilha.pop(); // Remove e retorna o vértice do topo da pilha
            if (!visitados.contains(vertice)){ // Verifica se o vértice já foi visitado
                List<Vertice> componente = new ArrayList<>();
                dfs2(transposto, vertice, visitados, componente);
                componentes.add(componente);
            }
        }
        return componentes;
    }