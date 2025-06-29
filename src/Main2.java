import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//Fazer a entrada pelo usuario de funcionario, e no menu de login de funcionario fazer a opcao de sair completamente do sistema
// (Criar na tabela tb_funcionarios e coluna login(nome.sobrenome) e a coluna senha)
//Fazer o limitador de livros de ate 3 por usuario

public class Main2 {
    public static void main(String[] args) throws SQLException {
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
                        9 - Consultar Livros
                        10 - Consultar Usuarios
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
                    case 3: //Perfeito
                        registrarEmprestimo();
                        break;
                    case 4: //Perfeito
                        devolverEmprestimo();
                        break;
                    case 5: //Perfeito
                        removerUsuario();
                        break;
                    case 6: //Perfeito
                        alterarUsuario();
                        break;
                    case 7: //Perfeito
                        removerLivro();
                        break;
                    case 8: //Perfeito
                        alterarLivro();
                        break;
                    case 9: //Fazer
                        consultarLivros();
                        break;
                    case 10: //Perfeito
                        consultarUsuarios();
                        break;
                    case 11:
                        consultarFuncionarios();
                        break;
                    case 12:
                        consultarEmprestimos();
                        break;
                    default:
                        System.out.println("\nOpção Invalida\n");
                        break;
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
        LivroDao.inserir(l);
    }

    public static void cadastrarUsuario() {
        System.out.println("\n- Cadastrar Usuario -");
        Usuario u = new Usuario();
        System.out.print("Informe o nome do usuario: ");
        u.setNome(new Scanner(System.in).nextLine());
        System.out.print("Informe o cpf do usuario: ");
        u.setCpf(new Scanner(System.in).nextLine());
        UsuarioDao.inserir(u);
    }

    public static void registrarEmprestimo(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Registrar Emprestimo -");
        System.out.println("""
                Deseja informar
                1 - Cpf e Isbn
                2 - Nome e Isbn
                3 - Cpf e Titulo
                4 - Nome e Titulo
                5 - Sair
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
                String sn1 = scan.next();
                if (sn1.equalsIgnoreCase("s")) {
                    EmprestimoDao.inserir(cpf, isbn, "10928571920");
                } else {
                    System.out.println("Tente novamente!\n");
                    return;
                }
                break;
            case 2:
                System.out.println("Informe o nome o usuario: ");
                String nome = scan.nextLine();
                System.out.println("Informe o isbn do livro: ");
                String isbn2 = scan.next();
                System.out.println("Os dados estão corretor: Nome " + nome + " ,ISBN " + isbn2 + "(S ou N)");
                String sn2 = scan.next();
                if (sn2.equalsIgnoreCase("s")) {
                    EmprestimoDao.inserir(UsuarioDao.getByNome(nome).getCpf(), isbn2, "10928571920");
                } else {
                    System.out.println("Tente novamente!\n");
                    return;
                }
                break;
            case 3:
                System.out.println("Informe o cpf o usuario: ");
                String cpf3 = scan.next();
                System.out.println("Informe o titulo do livro: ");
                String titulo = scan.nextLine();
                System.out.println("Os dados estão corretor: Cpf " + cpf3 + " ,Titulo " + titulo + "(S ou N)");
                String sn3 = scan.next();
                if (sn3.equalsIgnoreCase("s")) {
                    EmprestimoDao.inserir(cpf3, LivroDao.getByTitulo(titulo).getIsbn(), "10928571920");
                } else {
                    System.out.println("Tente novamente!\n");
                    return;
                }
                break;
            case 4:
                System.out.println("Informe o nome o usuario: ");
                String nome4 = scan.nextLine();
                System.out.println("Informe o titulo do livro: ");
                String titulo4 = scan.nextLine();
                System.out.println("Os dados estão corretor: Nome " + nome4 + " ,Titulo " + titulo4 + "(S ou N)");
                String sn4 = scan.next();
                if (sn4.equalsIgnoreCase("s")) {
                    EmprestimoDao.inserir(UsuarioDao.getByNome(nome4).getNome(), LivroDao.getByTitulo(titulo4).getIsbn(), "10928571920");
                } else {
                    System.out.println("Tente novamente!\n");
                    return;
                }
                break;
            case 5:
                System.out.println("Saindo...\n");
                break;
            default:
                System.out.println("Opção Invalida!\n");
        }
    }

    public static void devolverEmprestimo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Registrar Devolução -");
        System.out.println("Informe o cpf do Usuario: ");
        String cpf4 = scan.next();
        List<Emprestimo> lista = EmprestimoDao.getByCpf(cpf4);
        int control = 1;
        for (Emprestimo e : lista) {
            Livro l = LivroDao.getById(e.getLivro_id());
            System.out.println(control + " - " + l.getTitulo() + " " + l.getAutor() + " " + l.getAnoPublicacao() + " " + l.getIsbn());
            control++;
        }
        System.out.println("Informe o numero livro que deseja devolver: ");
        int opc = scan.nextInt() - 1;
        EmprestimoDao.excluir(cpf4, LivroDao.getById(lista.get(opc).getLivro_id()).getIsbn());
    }

    public static void removerUsuario() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Remover Usuario -");
        System.out.println("""
                                        Deseja Informar
                                        1 - Cpf do Usuario
                                        2 - Nome do Usuario
                                        3 - Sair""");
        System.out.print("Informe sua opção: ");
        int opc5 = scan.nextInt();
        scan.nextLine();
        switch (opc5) {
            case 1:
                System.out.print("Informe o Cpf do usuario: ");
                String cpf = scan.next();
                System.out.println("Confirme os dados");
                System.out.println(UsuarioDao.getByCpf(cpf));
                System.out.println("Digite S se quiser remover ou N para cancelar");
                String sn = scan.next();
                if (sn.equalsIgnoreCase("s")) {
                    UsuarioDao.excluir(UsuarioDao.getByCpf(cpf).getId());
                } else {
                    System.out.println("Cancelado");
                }
                break;
            case 2:
                System.out.println("Informe o nome do usuario: ");
                String nome = scan.nextLine();
                System.out.println("Confirme os dados");
                System.out.println(UsuarioDao.getByNome(nome));
                System.out.println("Digite S se quiser remover ou N para cancelar");
                String sn2 = scan.nextLine().trim();
                if (sn2.equalsIgnoreCase("s")) {
                    UsuarioDao.excluir(UsuarioDao.getByNome(nome).getId());
                } else {
                    System.out.println("Cancelado");
                }
                break;
            case 3:
                System.out.println("Saindo...\n");
                break;
            default:
                System.out.println("Opção Invalida\n");
                break;
        }
    }

    public static void alterarUsuario() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Alterar Usuario -");
        System.out.println("""
                                        Deseja Informar
                                        1 - Cpf do Usuario
                                        2 - Nome do Usuario
                                        3 - Sair""");
        System.out.print("Informe sua opção: ");
        int opc = scan.nextInt();
        scan.nextLine();
        switch (opc) {
            case 1:
                System.out.print("Informe o Cpf do usuario: ");
                String cpf = scan.nextLine();
                Usuario usuario = UsuarioDao.getByCpf(cpf);
                if (usuario.getId() == 0) {
                    System.out.println("Usuario não encontrado");
                    break;
                }
                System.out.println("Esse é o usuario que deseja alterar? (S ou N) \n" + usuario.toString());
                String sn = scan.nextLine();

                if (!sn.equalsIgnoreCase("s")) {
                    System.out.println("Cancelando");
                    break;
                }

                System.out.println("Digite o novo nome: ");
                String nome = scan.nextLine();
                usuario.setNome(nome);

                System.out.println("Digite o novo Cpf: ");
                String cpf2 = scan.nextLine();
                usuario.setCpf(cpf2);
                UsuarioDao.alterar(usuario);
                break;
            case 2:
                System.out.println("Informe o nome do usuario: ");
                String nome2 = scan.nextLine();
                Usuario usuario2 = UsuarioDao.getByNome(nome2);
                if (usuario2.getId() == 0) {
                    System.out.println("Usuario não encontrado");
                    break;
                }
                System.out.println("Esse é o usuario que deseja alterar? (S ou N) \n" + usuario2.toString());
                String sn2 = scan.nextLine();

                if (!sn2.equalsIgnoreCase("s")) {
                    System.out.println("Cancelando");
                    break;
                }

                System.out.println("Digite o novo nome: ");
                String nome3 = scan.nextLine();
                usuario2.setNome(nome3);

                System.out.println("Digite o novo Cpf: ");
                String cpf4 = scan.nextLine();
                usuario2.setCpf(cpf4);
                UsuarioDao.alterar(usuario2);
                break;
            case 3:
                System.out.println("Saindo...\n");
                break;
            default:
                System.out.println("Opção Invalida\n");
                break;
        }
    }

    public static void removerLivro() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Remover Livro -");
        System.out.println("""
                                        Deseja Informar
                                        1 - Isbn do livro
                                        2 - Titulo do livro
                                        3 - Sair""");
        System.out.print("Informe sua opção: ");
        int opc = scan.nextInt();
        scan.nextLine();
        switch (opc) {
            case 1:
                System.out.print("Informe o ISBN do livro: ");
                String isbn = scan.nextLine();
                Livro livro = LivroDao.getByIsbn(isbn);
                if (livro.getId() == 0) {
                    System.out.println("Livro não encontrado");
                    break;
                }
                System.out.println("Esse é o livro que deseja remover? (S ou N) \n" + livro.toString());
                String sn = scan.nextLine();

                if (!sn.equalsIgnoreCase("s")) {
                    System.out.println("Cancelando");
                    break;
                }
                LivroDao.excluir(livro.getId());
                break;
            case 2:
                System.out.print("Informe o Titulo do livro: ");
                String titulo = scan.nextLine();
                Livro livro2 = LivroDao.getByTitulo(titulo);
                if (livro2.getId() == 0) {
                    System.out.println("Livro não encontrado");
                    break;
                }
                System.out.println("Esse é o livro que deseja remover? (S ou N) \n" + livro2.toString());
                String sn2 = scan.nextLine();

                if (!sn2.equalsIgnoreCase("s")) {
                    System.out.println("Cancelando");
                    break;
                }
                LivroDao.excluir(livro2.getId());
                break;
            case 3:
                System.out.println("Saindo...\n");
                break;
            default:
                System.out.println("Opção Invalida\n");
                break;
        }
    }

    public static void alterarLivro() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Alterar Livro -");
        System.out.println("""
                                        Deseja Informar
                                        1 - Isbn do Livro
                                        2 - Titulo do Livro
                                        3 - Sair""");
        System.out.print("Informe sua opção: ");
        int opc = scan.nextInt();
        scan.nextLine();
        switch (opc) {
            case 1:
                System.out.print("Informe o ISBN do livro: ");
                String isbn = scan.nextLine();
                Livro livro = LivroDao.getByIsbn(isbn);
                if (livro.getId() == 0) {
                    System.out.println("Livro não encontrado");
                    break;
                }
                System.out.println("Esse é o livro que deseja alterar? (S ou N) \n" + livro.toString());
                String sn = scan.nextLine();

                if (!sn.equalsIgnoreCase("s")) {
                    System.out.println("Cancelando");
                    break;
                }

                System.out.println("Digite o novo titulo: ");
                String titulo = scan.nextLine();
                livro.setTitulo(titulo);

                System.out.println("Digite o novo autor: ");
                String autor = scan.nextLine();
                livro.setAutor(autor);

                System.out.println("Digite o novo ano de publicação: ");
                int anoPublicacao = scan.nextInt();
                livro.setAnoPublicacao(anoPublicacao);

                System.out.println("Digite o novo ISBN: ");
                String isbnNovo = scan.next();
                livro.setIsbn(isbnNovo);
                LivroDao.alterar(livro);
                break;
            case 2:
                System.out.print("Informe o titulo do livro: ");
                String titulo2 = scan.nextLine();
                Livro livro2 = LivroDao.getByTitulo(titulo2);
                if (livro2.getId() == 0) {
                    System.out.println("Livro não encontrado");
                    break;
                }
                System.out.println("Esse é o livro que deseja alterar? (S ou N) \n" + livro2.toString());
                String sn2 = scan.nextLine();

                if (!sn2.equalsIgnoreCase("s")) {
                    System.out.println("Cancelando");
                    break;
                }

                System.out.println("Digite o novo titulo: ");
                String tituloNovo2 = scan.nextLine();
                livro2.setTitulo(tituloNovo2);

                System.out.println("Digite o novo autor: ");
                String autor2 = scan.nextLine();
                livro2.setAutor(autor2);

                System.out.println("Digite o novo ano de publicação: ");
                int anoPublicacao2 = scan.nextInt();
                livro2.setAnoPublicacao(anoPublicacao2);

                System.out.println("Digite o novo ISBN: ");
                String isbnNovo2 = scan.next();
                livro2.setIsbn(isbnNovo2);
                LivroDao.alterar(livro2);
                break;
            case 3:
                System.out.println("Saindo...\n");
                break;
            default:
                System.out.println("Opção Invalida\n");
                break;
        }
    }

    public static void consultarLivros() throws SQLException {
        System.out.println("\n- Consultar livros -");
        List<Livro> acervo = LivroDao.getAll();
        if (acervo.isEmpty()) {
            System.out.println("Nenhum livro cadastrado");
        } else {
            System.out.printf("%-4s %-25s %-15s %-6s %-10s %-6s%n",
                    "ID", "Título", "Autor", "Ano", "ISBN", "Disponivel");
            for (Livro l : acervo) {
                System.out.printf("%-4s %-25s %-15s %-6s %-10s %-6s%n",
                        l.getId(), l.getTitulo(), l.getAutor(), l.getAnoPublicacao(), l.getIsbn(), l.isDisponivel());
            }
            System.out.println();
            }
    }

    public static void consultarUsuarios() throws SQLException {
        System.out.println("\n- Consultar Usuarios -");
        List<Usuario> usuarios = UsuarioDao.getAll();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado");
        } else {
            System.out.printf("%-4s %-30s %-15s%n",
                    "ID", "Nome", "Cpf");
            for (Usuario u : usuarios) {
                System.out.printf("%-4s %-30s %-15s%n",
                        u.getId(), u.getNome(), u.getCpf());
            }
            System.out.println();
        }
    }

    public static void consultarFuncionarios() throws SQLException {
        System.out.println("\n- Consultar Funcionarios -");
        List<Funcionario> funcionarios = FuncionarioDao.getAll();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado");
        } else {
            System.out.printf("%-4s %-30s %-15s %-6s%n",
                    "ID", "Nome", "Cpf", "Cargo");
            for (Funcionario f : funcionarios) {
                System.out.printf("%-4s %-30s %-15s %-6s%n",
                        f.getId(), f.getNome(), f.getCpf(), f.getCargo());
            }
            System.out.println();
        }
    }

    public static void consultarEmprestimos() throws SQLException {
        System.out.println("\n- Consultar Emprestimos -");
        List<Emprestimo> emprestimos = EmprestimoDao.getAll();
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado");
        } else {
            System.out.printf("%-4s %-30s %-15s %-6s%n",
                    "ID", "Usuario", "Livro", "Funcionario");
            for (Emprestimo e : emprestimos) {
                System.out.printf("%-4s %-30s %-15s %-6s%n",
                        e.getId(), UsuarioDao.getById(e.getUsuario_id()).getNome(), LivroDao.getById(e.getLivro_id()).getTitulo(), FuncionarioDao.getById(e.getFuncionario_id()).getNome());
            }
            System.out.println();
        }
    }
}
