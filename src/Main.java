import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Funcionario funcionarioPrincipal = new Funcionario();
	public static void main(String[] args) throws Exception {
		if (ConexaoDB.getConexao() != null) {
			int opcao;

			login();

			do {
				System.out.println("""
                        1 - Cadastrar Livro
                        2 - Cadastrar Usuario
                        3 - Cadastrar Funcionario
                        4 - Registrar Emprestimo
                        5 - Registrar Devoluções
                        6 - Remover Livro
                        7 - Remover Usuario
                        8 - Remover Funcionario
                        9 - Alterar Livro
                        10 - Alterar Usuario
                        11 - Alterar Funcionario
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

	public static void cadastrarFuncionario() {
		System.out.println("\n- Cadastrar Funcionario -");
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
		FuncionarioDao.inserir(f);
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
					if (UsuarioDao.getByCpf(cpf).getEmprestimosAtivos() >= 3) {
						System.out.println("Limite de emprestimos atingido!\n");
						return;
					} else {
						EmprestimoDao.inserir(cpf, isbn, funcionarioPrincipal.getCpf());
					}

				} else {
					System.out.println("Emprestimo cancelado\n");
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
					EmprestimoDao.inserir(UsuarioDao.getByNome(nome).getCpf(), isbn2, funcionarioPrincipal.getCpf());
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
					EmprestimoDao.inserir(cpf3, LivroDao.getByTitulo(titulo).getIsbn(), funcionarioPrincipal.getCpf());
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
					EmprestimoDao.inserir(UsuarioDao.getByNome(nome4).getNome(), LivroDao.getByTitulo(titulo4).getIsbn(), funcionarioPrincipal.getCpf());
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
		if (lista.isEmpty()) {
			System.out.println("Usuario sem emprestimos\n");
		} else {
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

	public static void removerFuncionario() {
		Scanner scan = new Scanner(System.in);
		System.out.println("\n- Remover Funcionario -");
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
				System.out.println(FuncionarioDao.getByCpf(cpf));
				System.out.println("Digite S se quiser remover ou N para cancelar");
				String sn = scan.next();
				if (sn.equalsIgnoreCase("s")) {
					FuncionarioDao.excluir(FuncionarioDao.getByCpf(cpf).getId());
				} else {
					System.out.println("Cancelado");
				}
				break;
			case 2:
				System.out.println("Informe o nome do funcionario: ");
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
				Funcionario funcionario = FuncionarioDao.getByCpf(cpf);
				if (funcionario.getId() == 0) {
					System.out.println("Funcionario não encontrado");
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
				FuncionarioDao.alterar(funcionario);
				break;
			case 2:
				System.out.println("Informe o nome do usuario: ");
				Funcionario funcionario1 = FuncionarioDao.getByNome(scan.nextLine());
				if (funcionario1.getId() == 0) {
					System.out.println("Funcionario não encontrado");
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

				FuncionarioDao.alterar(funcionario1);
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
		Scanner scan = new Scanner(System.in);
		System.out.println("\n- Consultar Emprestimos -");
		System.out.println("Informe o cpf do usuario que deseja consultar os emprestimos: ");
		String cpf = scan.nextLine();
		List<Emprestimo> emprestimos = EmprestimoDao.getByCpf(cpf);
		if (emprestimos.isEmpty()) {
			System.out.println("Nenhum emprestimo cadastrado\n");
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

	public static void login() {
		Scanner scan = new Scanner(System.in);
		System.out.println("- Login Funcionario -");
		System.out.println("Informe seu usuario: ");
		funcionarioPrincipal.setUsuario(scan.nextLine());
		System.out.println("Informe sua senha: ");
		funcionarioPrincipal.setSenha(scan.nextLine());
		if (FuncionarioDao.login(funcionarioPrincipal).getNome() != null) {
			funcionarioPrincipal.setNome(FuncionarioDao.login(funcionarioPrincipal).getNome());
			funcionarioPrincipal.setCpf(FuncionarioDao.login(funcionarioPrincipal).getCpf());
			System.out.println("Login Realizado com sucesso!\n");
		} else {
			login();
		}
	}
}
