import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        Aresta novaAresta = new Aresta(origem, destino, valorado ? peso : 1);

        if(arestas.contains(novaAresta)){
            System.out.println("Aresta entre '" + nomeOrigem + "' e '" + nomeDestino + "' já existe.");
            return;
        }

        arestas.add(novaAresta);

        if (!orientado) { // Se não for orientado, adiciona a aresta inversa
            Aresta arestaInversa = new Aresta(destino, origem, valorado ? peso : 1);
            arestas.add(arestaInversa);
        }

        System.out.println("Aresta adicionada: " + origem + " -> " + destino + " (Peso: " + peso + ")");
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
            arestas.removeIf(aresta ->
                    aresta.getOrigem().equals(destino) && aresta.getDestino().equals(origem));
        }

        if (removido) {

            System.out.println("Aresta entre '" + nomeOrigem + "' e '" + nomeDestino + "' não existe.");
            return;
        }else{
            System.out.println("Aresta de '" + nomeOrigem + "' para '" + nomeDestino + "' removida.");
        }

       
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

}
