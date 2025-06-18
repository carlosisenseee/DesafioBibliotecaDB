import java.util.InputMismatchException;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        if (ConexaoDB.getConexao() != null) {
            int opcao;

            do {
                System.out.println("""
                        1 - Cadastrar Livro
                        2 - Cadastrar Usuario
                        3 - Registrar Emprestimo
                        4 - Registrar Devoluções
                        5 - Remover Usuario
                        6 - Alterar Usuario
                        7 - Remover Livro
                        8 - Alterar Livro
                        9 - Sair
                        """);
                System.out.print("Informe sua escolha: ");
                opcao = new Scanner(System.in).nextInt();
                switch (opcao) {
                    case 1:
                        System.out.println("\n- Cadastrar Livro -");
                        Livro l = new Livro();
                        System.out.print("Informe o titulo do livro: ");
                        l.setTitulo(new Scanner(System.in).nextLine());
                        System.out.print("Informe o autor: ");
                        l.setAutor(new Scanner(System.in).nextLine());
                        System.out.print("Informe o ano de publicação: ");
                        try {
                            l.setAnoPublicacao(new Scanner(System.in).nextInt());
                        } catch (InputMismatchException e) {
                            System.out.println("Erro: Digite apenas números!\n");
                            break;
                        }
                        System.out.print("Informe o ISBN: ");
                        l.setIsbn(new Scanner(System.in).nextLine());
                        LivroCrud.inserir(l);
                        break;
                    case 2:
                        System.out.println("\n- Cadastrar Usuario -");
                        Usuario u = new Usuario();
                        System.out.print("Informe o nome do usuario: ");
                        u.setNome(new Scanner(System.in).nextLine());
                        System.out.print("Informe o cpf do usuario: ");
                        u.setCpf(new Scanner(System.in).nextLine());
                        UsuarioCrud.inserir(u);
                        break;
                    case 3:
                        System.out.println("\n- Registrar Emprestimo -");
                        System.out.println("Informe o ISBN do livro: ");
                        String isbn = scan.next();
                        System.out.println("Informe o cpf do usuario: ");
                        String cpf = scan.next();
                        System.out.println("Informe o cpf do funcionario");
                        String cpfU = scan.next();
                        EmprestimoCrud.inserir(cpf, isbn, cpfU);
                    case 4:
                        System.out.println("\n- Registrar Devolução -");
                        System.out.println("Informe o ISBN do livro: ");
                        String isbn4 = scan.next();
                        System.out.println("Informe o cpf do usuario: ");
                        String cpf4 = scan.next();
                        EmprestimoCrud.excluir(cpf4, isbn4);
                    case 5:
                        System.out.println("\n- Remover Usuario -");
                        System.out.print("Informe o cpf do usuario: ");
                        UsuarioCrud.excluir(UsuarioCrud.getByCpf(new Scanner(System.in).nextLine()));
                        System.out.println("\nUsuario Removido com sucesso!\n");
                }
            } while (opcao != 9) ;
        } else {
            System.out.println("ERRO");
        }
    }
}