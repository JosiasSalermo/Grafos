import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Malgrange {
    static class Grafo{
        private final int vertices;
        private final List<List<Integer>> adj;

        public Grafo(int vertices){
            this.vertices = vertices;
            this.adj = new ArrayList<>();
            for(int i = 0; i < vertices; i++){
                adj.add(new ArrayList<>());
            }
        }

        public void adicionarAresta(int origem, int destino){
            adj.get(origem).add(destino);
        }

        public List<Integer> getAdjacentes(int vertice){
            return adj.get(vertice);
        }

        public Grafo transpor(){
            Grafo transporto = new Grafo(vertices);
            for(int v = 0; v < vertices; v++){
                for(int u : getAdjacentes(v)){
                    transporto.adicionarAresta(u, v);
                }
            }
            return transporto;
        }

        public int getVertices(){
            return vertices;
        }
    }

    private static void dfs1(Grafo grafo, int v, boolean[] visitado, Stack<Integer> pilha){
        visitado[v] = true;
        for(int vizinho : grafo.getAdjacentes(v)){
            if(!visitado[vizinho]){
                dfs1(grafo, vizinho, visitado, pilha);
            }
        }
        pilha.push(v);
    }

    private static void dfs2(Grafo grafo, int v, boolean[] visitado, List<Integer> componente){
        visitado[v] = true;
        componente.add(v);
        for(int vizinho : grafo.getAdjacentes(v)){
            if(!visitado[vizinho]){
                dfs2(grafo, vizinho, visitado, componente);
            }
        }
    }

    public static List<List<Integer>> grafoReduzido(Grafo grafo){
        Stack<Integer> pilha = new Stack<>();
        boolean[] visitado = new boolean[grafo.getVertices()];

        for(int i = 0; i < grafo.getVertices(); i++){
            if(!visitado[i]){
                dfs1(grafo, i, visitado, pilha);
            }
        }

        Grafo transposto = grafo.transpor();
        Arrays.fill(visitado, false);

        List<List<Integer>> componentes = new ArrayList<>();
        while(!pilha.isEmpty()){
            int v = pilha.pop();
            if(!visitado[v]){
                List<Integer> componente = new ArrayList<>();
                dfs2(transposto, v, visitado, componente);
                componentes.add(componente);
            }
        }

        return componentes;
    }


}