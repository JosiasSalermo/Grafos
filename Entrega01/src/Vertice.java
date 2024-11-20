public class Vertice {
    private String nome;

    public Vertice(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    @Override
    public String toString(){
        return nome;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Vertice vertice = (Vertice) obj;
        return nome.equals(vertice.nome);
    }

    @Override
    public int hashCode(){
        return nome.hashCode();
    }
}
