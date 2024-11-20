import java.util.Scanner;

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

    
}