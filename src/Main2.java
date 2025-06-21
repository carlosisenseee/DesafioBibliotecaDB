import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//Fazer a entrada pelo usuario de funcionario, e no menu de login de funcionario fazer a opcao de sair completamente do sistema

public class Main2 {
    public static void main(String[] args) throws SQLException {
        if (ConexaoDB.getConexao() != null) {
            int opcao;

            do {
                System.out.println("""
                        1 - Cadastrar Livro - ok
                        2 - Cadastrar Usuario - ok
                        3 - Registrar Emprestimo - ok
                        4 - Registrar Devoluções - ok
                        5 - Remover Usuario - ok
                        6 - Alterar Usuario - ok
                        7 - Remover Livro
                        8 - Alterar Livro
                        9 - Consultar Livros
                        10 - Consultar Usuarios - ok
                        11 - Consultar Funcionarios
                        12 - Consultar Emprestimos
                        13 - Sair
                        """);
                System.out.print("Informe sua escolha: ");
                opcao = new Scanner(System.in).nextInt();
                switch (opcao) {
                    case 1: //Perfeito
                        cadastrarLivro();
                        break;
                    case 2: //Perfeito
                        cadastrarUsuario();
                        break;
                    case 3: //Adicionar Opcoes como, buscar pelo cpf, ou nome, ou pelo titulo do livro, e fazer uma pergunta de validação
                        registrarEmprestimo();
                        break;
                    case 4: //Adicionar opcoes, de buscar por nome e cpf, e por titulo
                        System.out.println("\n- Registrar Devolução -");
                        System.out.println("Informe o ISBN do livro: ");
//                        String isbn4 = scan.next();
                        System.out.println("Informe o cpf do usuario: ");
//                        String cpf4 = scan.next();
//                        EmprestimoCrud.excluir(cpf4, isbn4);
                        break;
                    case 5: //Opcao de remover por cpf ou pelo nome, e adicionar um funcao, "É esse o funcionario que deseja remover"
                        System.out.println("\n- Remover Usuario -");
                        System.out.print("Informe o cpf do usuario: ");
                        UsuarioCrud.excluir(UsuarioCrud.getByCpf(new Scanner(System.in).nextLine()));
                        System.out.println("\nUsuario Removido com sucesso!\n");
                        break;
                    case 6: //Buscar o Id automaticamente pelo cpf e pelo nome do usuario, e perguntar se é esse mesmo que ele deseja alterar
                        System.out.println("\n- Alterar Usuario -");
                        Usuario u6 = new Usuario();
                        System.out.println("Informe o Id do usuario: ");
                        u6.setId(new Scanner(System.in).nextInt());
                        System.out.println("Informe o nome do usuario: ");
                        u6.setNome(new Scanner(System.in).nextLine());
                        System.out.println("Informe o cpf do usuario: ");
                        u6.setCpf(new Scanner(System.in).next());
                        UsuarioCrud.alterar(u6);
                        break;
                    case 7: //Buscar o livro pelo titulo ou isbn, e perguntar se é esse mesmo que deve remover
                        System.out.println("\n- Remover Livro -");
                        break;
                    case 8: //buscar o isbn automaticamente pelo ISBN e pelo titulo, e fazer uma pergunta de validacao
                        System.out.println("\n- Alterar Livro -");
                        break;
                    case 9: //Perfeito
                        System.out.println("\n- Consultar livros -");
                        break;
                    case 10: //Perfeito
                        System.out.println("\n- Consultar Usuarios -");
                        try {
                            List<Usuario> usuarios = UsuarioCrud.getAll();
                            if (usuarios.isEmpty()) {
                                System.out.println("Nenhum usuario cadastrado");
                            } else {
                                for (Usuario u10 : usuarios) {
                                    System.out.println(u10.toString());
                                }
                            }
                            } catch(SQLException e10){
                            System.out.println(e10.getMessage());
                        }
                        System.out.println();
                    case 11: //Perfeito
                        System.out.println("\n- Consultar Funcionarios -");
                        break;
                    case 12: //Perfeito
                        System.out.println("\n- Consultar Emprestimos -");
                    default:
                        System.out.println("\nOpção Invalida\n");
                }
            } while (opcao != 13);
        } else {
            System.out.println("ERRO");
        }
    }

    public static void cadastrarLivro() {
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
            return;
        }
        System.out.print("Informe o ISBN: ");
        l.setIsbn(new Scanner(System.in).nextLine());
        LivroCrud.inserir(l);
    }

    public static void cadastrarUsuario() {
        System.out.println("\n- Cadastrar Usuario -");
        Usuario u = new Usuario();
        System.out.print("Informe o nome do usuario: ");
        u.setNome(new Scanner(System.in).nextLine());
        System.out.print("Informe o cpf do usuario: ");
        u.setCpf(new Scanner(System.in).nextLine());
        UsuarioCrud.inserir(u);
    }

    public static void registrarEmprestimo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Registrar Emprestimo -");
        System.out.println("""
                Deseja informar
                1 - Cpf e Isbn
                2 - Nome e Isbn
                3 - Cpf e Titulo
                4 - Nome e Titulo
                """);
        System.out.println("Informe sua opção: ");
        int opcao = new Scanner(System.in).nextInt();
        switch (opcao) {
            case 1:
                System.out.println("Informe o cpf do usuario: ");
                String cpf = scan.next();
                System.out.println("Informe o isbn do livro: ");
                String isbn = scan.next();
                System.out.println("Os dados estão corretos: Cpf " + cpf + " ,ISBN " + isbn + "(S ou N)");
                String sn = scan.next();
                if (sn.equalsIgnoreCase("s")) {
                    EmprestimoCrud.inserir(cpf, isbn, "10928571920");
                } else {
                    System.out.println("Tente novamente!\n");
                    return;
                }
            case 2:
                System.out.println("Informe o nome o usuario: ");
                String nome = scan.nextLine();
                System.out.println("Informe o isbn do livro: ");
                String isbn = scan.next();
                System.out.println("Os dados estão corretor: Nome " + nome + " ,ISBN " + isbn + "(S ou N)");
                String sn = scan.next();
                if (sn.equalsIgnoreCase("s")) {
                    EmprestimoCrud.inserir(UsuarioCrud.getByNome(nome), isbn, "10928571920");
                } else {
                    System.out.println("Tente novamente!\n");
                    return;
                }
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("Opção Invalida!\n");
        }
    }
}
