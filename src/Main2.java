import Cruds.EmprestimoCrud;
import Cruds.FuncionarioCrud;
import Cruds.LivroCrud;
import Cruds.UsuarioCrud;
import Models.Emprestimo;
import Models.Funcionario;
import Models.Livro;
import Models.Usuario;
import db.ConexaoDB;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    static Funcionario funcionarioPrincipal = new Funcionario();
    public static void main(String[] args) throws Exception {
        if (ConexaoDB.getConexao() != null) {
            int opcao;

            login();

            do {
                System.out.println("""
                        1 - Cadastrar Models.Livro
                        2 - Cadastrar Models.Usuario
                        3 - Cadastrar Models.Funcionario
                        4 - Registrar Models.Emprestimo
                        5 - Registrar Devoluções
                        6 - Remover Models.Livro
                        7 - Remover Models.Usuario
                        8 - Remover Models.Funcionario
                        9 - Alterar Models.Livro
                        10 - Alterar Models.Usuario
                        11 - Alterar Models.Funcionario
                        12 - Consultar Livros
                        13 - Consultar Usuarios
                        14 - Consultar Funcionarios
                        15 - Consultar Emprestimos
                        16 - Sair
                        17 - Trocar login de funcionario
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
                        cadastrarFuncionario();
                        break;
                    case 4: //Perfeito
                        registrarEmprestimo();
                        break;
                    case 5: //Perfeito
                        devolverEmprestimo();
                        break;
                    case 6: //Perfeito
                        removerLivro();
                        break;
                    case 7: //Perfeito
                        removerUsuario();
                        break;
                    case 8: //Perfeito
                        removerFuncionario();
                        break;
                    case 9: //Perfeito
                        alterarLivro();
                        break;
                    case 10: //Perfeito
                        alterarUsuario();
                        break;
                    case 11: //Perfeito
                        alterarFuncionario();
                        break;
                    case 12: //Perfeito
                        consultarLivros();
                        break;
                    case 13: // Perfeito
                        consultarUsuarios();
                        break;
                    case 14: //Perfeito
                        consultarFuncionarios();
                        break;
                    case 15: //Perfeito
                        consultarEmprestimos();
                        break;
                    case 16: //Perfeito
                        System.exit(0);
                        break;
                    case 17:
                        login();
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
        System.out.println("\n- Cadastrar Models.Livro -");
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
        System.out.println("\n- Cadastrar Models.Usuario -");
        Usuario u = new Usuario();
        System.out.print("Informe o nome do usuario: ");
        u.setNome(new Scanner(System.in).nextLine());
        System.out.print("Informe o cpf do usuario: ");
        u.setCpf(new Scanner(System.in).nextLine());
        UsuarioCrud.inserir(u);
    }

    public static void cadastrarFuncionario() {
        System.out.println("\n- Cadastrar Models.Funcionario -");
        Funcionario f = new Funcionario();
        System.out.print("Informe o nome do funcionario: ");
        f.setNome(new Scanner(System.in).nextLine());
        System.out.print("Informe o cpf do funcionario: ");
        f.setCpf(new Scanner(System.in).nextLine());
        System.out.println("Informe o cargo do funcionario: ");
        f.setCargo(new Scanner(System.in).nextLine());
        System.out.println("Informe o usuario do funcionario (nome.sobrenome)");
        f.setUsuario(new Scanner(System.in).nextLine());
        System.out.println("Informe a senha do usuario: ");
        f.setUsuario(new Scanner(System.in).nextLine());
        FuncionarioCrud.inserir(f);
    }

    public static void registrarEmprestimo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Registrar Models.Emprestimo -");
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
                System.out.println("Os dados estão corretos: Cpf " + cpf + ", ISBN " + isbn + "(S ou N)");
                String sn1 = scan.next();
                if (sn1.equalsIgnoreCase("s")) {
                    if (UsuarioCrud.getByCpf(cpf).getEmprestimosAtivos() >= 3) {
                        System.out.println("Limite de emprestimos atingido!\n");
                        return;
                    } else {
                        EmprestimoCrud.inserir(cpf, isbn, funcionarioPrincipal.getCpf());
                    }

                } else {
                    System.out.println("Models.Emprestimo cancelado\n");
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
                    EmprestimoCrud.inserir(UsuarioCrud.getByNome(nome).getCpf(), isbn2, funcionarioPrincipal.getCpf());
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
                    EmprestimoCrud.inserir(cpf3, LivroCrud.getByTitulo(titulo).getIsbn(), funcionarioPrincipal.getCpf());
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
                    EmprestimoCrud.inserir(UsuarioCrud.getByNome(nome4).getNome(), LivroCrud.getByTitulo(titulo4).getIsbn(), funcionarioPrincipal.getCpf());
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

    public static void devolverEmprestimo() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Registrar Devolução -");
        System.out.println("Informe o cpf do Models.Usuario: ");
        String cpf4 = scan.next();
        List<Emprestimo> lista = EmprestimoCrud.getByCpf(cpf4);
        if (lista.isEmpty()) {
            System.out.println("Models.Usuario sem emprestimos\n");
        } else {
            int control = 1;
            for (Emprestimo e : lista) {
                Livro l = LivroCrud.getById(e.getLivro_id());
                System.out.println(control + " - " + l.getTitulo() + " " + l.getAutor() + " " + l.getAnoPublicacao() + " " + l.getIsbn());
                control++;
            }
            System.out.println("Informe o numero livro que deseja devolver: ");
            int opc = scan.nextInt() - 1;
            EmprestimoCrud.excluir(cpf4, LivroCrud.getById(lista.get(opc).getLivro_id()).getIsbn());
        }
    }

    public static void removerLivro() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Remover Models.Livro -");
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
                Livro livro = LivroCrud.getByIsbn(isbn);
                if (livro.getId() == 0) {
                    System.out.println("Models.Livro não encontrado");
                    break;
                }
                System.out.println("Esse é o livro que deseja remover? (S ou N) \n" + livro.toString());
                String sn = scan.nextLine();

                if (!sn.equalsIgnoreCase("s")) {
                    System.out.println("Cancelando");
                    break;
                }
                LivroCrud.excluir(livro.getId());
                break;
            case 2:
                System.out.print("Informe o Titulo do livro: ");
                String titulo = scan.nextLine();
                Livro livro2 = LivroCrud.getByTitulo(titulo);
                if (livro2.getId() == 0) {
                    System.out.println("Models.Livro não encontrado");
                    break;
                }
                System.out.println("Esse é o livro que deseja remover? (S ou N) \n" + livro2.toString());
                String sn2 = scan.nextLine();

                if (!sn2.equalsIgnoreCase("s")) {
                    System.out.println("Cancelando");
                    break;
                }
                LivroCrud.excluir(livro2.getId());
                break;
            case 3:
                System.out.println("Saindo...\n");
                break;
            default:
                System.out.println("Opção Invalida\n");
                break;
        }
    }

    public static void removerUsuario() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Remover Models.Usuario -");
        System.out.println("""
                                        Deseja Informar
                                        1 - Cpf do Models.Usuario
                                        2 - Nome do Models.Usuario
                                        3 - Sair""");
        System.out.print("Informe sua opção: ");
        int opc5 = scan.nextInt();
        scan.nextLine();
        switch (opc5) {
            case 1:
                System.out.print("Informe o Cpf do usuario: ");
                String cpf = scan.next();
                System.out.println("Confirme os dados");
                System.out.println(UsuarioCrud.getByCpf(cpf));
                System.out.println("Digite S se quiser remover ou N para cancelar");
                String sn = scan.next();
                if (sn.equalsIgnoreCase("s")) {
                    UsuarioCrud.excluir(UsuarioCrud.getByCpf(cpf).getId());
                } else {
                    System.out.println("Cancelado");
                }
                break;
            case 2:
                System.out.println("Informe o nome do usuario: ");
                String nome = scan.nextLine();
                System.out.println("Confirme os dados");
                System.out.println(UsuarioCrud.getByNome(nome));
                System.out.println("Digite S se quiser remover ou N para cancelar");
                String sn2 = scan.nextLine().trim();
                if (sn2.equalsIgnoreCase("s")) {
                    UsuarioCrud.excluir(UsuarioCrud.getByNome(nome).getId());
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

    public static void removerFuncionario() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Remover Models.Funcionario -");
        System.out.println("""
                                        Deseja Informar
                                        1 - Cpf do funcionario
                                        2 - Nome do funcionario
                                        3 - Sair""");
        System.out.print("Informe sua opção: ");
        int opc5 = scan.nextInt();
        scan.nextLine();
        switch (opc5) {
            case 1:
                System.out.print("Informe o Cpf do funcionario: ");
                String cpf = scan.next();
                System.out.println("Confirme os dados");
                System.out.println(FuncionarioCrud.getByCpf(cpf));
                System.out.println("Digite S se quiser remover ou N para cancelar");
                String sn = scan.next();
                if (sn.equalsIgnoreCase("s")) {
                    FuncionarioCrud.excluir(FuncionarioCrud.getByCpf(cpf).getId());
                } else {
                    System.out.println("Cancelado");
                }
                break;
            case 2:
                System.out.println("Informe o nome do funcionario: ");
                String nome = scan.nextLine();
                System.out.println("Confirme os dados");
                System.out.println(UsuarioCrud.getByNome(nome));
                System.out.println("Digite S se quiser remover ou N para cancelar");
                String sn2 = scan.nextLine().trim();
                if (sn2.equalsIgnoreCase("s")) {
                    UsuarioCrud.excluir(UsuarioCrud.getByNome(nome).getId());
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

    public static void alterarLivro() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Alterar Models.Livro -");
        System.out.println("""
                                        Deseja Informar
                                        1 - Isbn do Models.Livro
                                        2 - Titulo do Models.Livro
                                        3 - Sair""");
        System.out.print("Informe sua opção: ");
        int opc = scan.nextInt();
        scan.nextLine();
        switch (opc) {
            case 1:
                System.out.print("Informe o ISBN do livro: ");
                String isbn = scan.nextLine();
                Livro livro = LivroCrud.getByIsbn(isbn);
                if (livro.getId() == 0) {
                    System.out.println("Models.Livro não encontrado");
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
                LivroCrud.alterar(livro);
                break;
            case 2:
                System.out.print("Informe o titulo do livro: ");
                String titulo2 = scan.nextLine();
                Livro livro2 = LivroCrud.getByTitulo(titulo2);
                if (livro2.getId() == 0) {
                    System.out.println("Models.Livro não encontrado");
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
                LivroCrud.alterar(livro2);
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
        System.out.println("\n- Alterar Models.Usuario -");
        System.out.println("""
                                        Deseja Informar
                                        1 - Cpf do Models.Usuario
                                        2 - Nome do Models.Usuario
                                        3 - Sair""");
        System.out.print("Informe sua opção: ");
        int opc = scan.nextInt();
        scan.nextLine();
        switch (opc) {
            case 1:
                System.out.print("Informe o Cpf do usuario: ");
                String cpf = scan.nextLine();
                Usuario usuario = UsuarioCrud.getByCpf(cpf);
                if (usuario.getId() == 0) {
                    System.out.println("Models.Usuario não encontrado");
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
                UsuarioCrud.alterar(usuario);
                break;
            case 2:
                System.out.println("Informe o nome do usuario: ");
                String nome2 = scan.nextLine();
                Usuario usuario2 = UsuarioCrud.getByNome(nome2);
                if (usuario2.getId() == 0) {
                    System.out.println("Models.Usuario não encontrado");
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
                UsuarioCrud.alterar(usuario2);
                break;
            case 3:
                System.out.println("Saindo...\n");
                break;
            default:
                System.out.println("Opção Invalida\n");
                break;
        }
    }

    public static void alterarFuncionario() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Alterar funcionario -");
        System.out.println("""
                                        Deseja Informar
                                        1 - Cpf do funcionario
                                        2 - Nome do funcionario
                                        3 - Sair""");
        System.out.print("Informe sua opção: ");
        int opc = scan.nextInt();
        scan.nextLine();
        switch (opc) {
            case 1:
                System.out.print("Informe o Cpf do funcionario: ");
                String cpf = scan.nextLine();
                Funcionario funcionario = FuncionarioCrud.getByCpf(cpf);
                if (funcionario.getId() == 0) {
                    System.out.println("Models.Funcionario não encontrado");
                    break;
                }
                System.out.println("Esse é o funcionario que deseja alterar? (S ou N) \n" + funcionario.toString());
                String sn = scan.nextLine();

                if (!sn.equalsIgnoreCase("s")) {
                    System.out.println("Cancelando");
                    break;
                }

                System.out.println("Digite o novo nome: ");
                funcionario.setNome(scan.nextLine());

                System.out.println("Digite o novo Cpf: ");
                funcionario.setCpf(scan.nextLine());

                System.out.println("Digite o novo cargo: ");
                funcionario.setCargo(scan.nextLine());

                System.out.println("Digite o novo usuario de login (nome.sobrenome): ");
                funcionario.setSenha(scan.nextLine());

                System.out.println("Digite a nova senha: ");
                funcionario.setSenha(scan.nextLine());
                FuncionarioCrud.alterar(funcionario);
                break;
            case 2:
                System.out.println("Informe o nome do usuario: ");
                Funcionario funcionario1 = FuncionarioCrud.getByNome(scan.nextLine());
                if (funcionario1.getId() == 0) {
                    System.out.println("Models.Funcionario não encontrado");
                    break;
                }
                System.out.println("Esse é o usuario que deseja alterar? (S ou N) \n" + funcionario1.toString());
                String sn2 = scan.nextLine();

                if (!sn2.equalsIgnoreCase("s")) {
                    System.out.println("Cancelando");
                    break;
                }

                System.out.println("Digite o novo nome: ");
                funcionario1.setNome(scan.nextLine());

                System.out.println("Digite o novo Cpf: ");
                funcionario1.setCpf(scan.nextLine());

                System.out.println("Digite o novo cargo: ");
                funcionario1.setCargo(scan.nextLine());

                System.out.println("Digite o novo usuario de login (nome.sobrenome): ");
                funcionario1.setSenha(scan.nextLine());

                System.out.println("Digite a nova senha: ");
                funcionario1.setSenha(scan.nextLine());

                FuncionarioCrud.alterar(funcionario1);
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
        List<Livro> acervo = LivroCrud.getAll();
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
        List<Usuario> usuarios = UsuarioCrud.getAll();
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
        List<Funcionario> funcionarios = FuncionarioCrud.getAll();
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
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- Consultar Emprestimos -");
        System.out.println("Informe o cpf do usuario que deseja consultar os emprestimos: ");
        String cpf = scan.nextLine();
        List<Emprestimo> emprestimos = EmprestimoCrud.getByCpf(cpf);
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum emprestimo cadastrado\n");
        } else {
            System.out.printf("%-4s %-30s %-15s %-6s%n",
                    "ID", "Models.Usuario", "Models.Livro", "Models.Funcionario");
            for (Emprestimo e : emprestimos) {
                System.out.printf("%-4s %-30s %-15s %-6s%n",
                        e.getId(), UsuarioCrud.getById(e.getUsuario_id()).getNome(), LivroCrud.getById(e.getLivro_id()).getTitulo(), FuncionarioCrud.getById(e.getFuncionario_id()).getNome());
            }
            System.out.println();
        }
    }

    public static void login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("- Login Models.Funcionario -");
        System.out.println("Informe seu usuario: ");
        funcionarioPrincipal.setUsuario(scan.nextLine());
        System.out.println("Informe sua senha: ");
        funcionarioPrincipal.setSenha(scan.nextLine());
        if (FuncionarioCrud.login(funcionarioPrincipal).getNome() != null) {
            funcionarioPrincipal.setNome(FuncionarioCrud.login(funcionarioPrincipal).getNome());
            funcionarioPrincipal.setCpf(FuncionarioCrud.login(funcionarioPrincipal).getCpf());
            System.out.println("Login Realizado com sucesso!\n");
        } else {
            login();
        }
    }
}
