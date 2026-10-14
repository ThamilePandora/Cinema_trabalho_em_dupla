package com.podora.cinema;

import java.util.Scanner;

public class Cinema {

	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		Filme[] filmes = new Filme[3];
		filmes[0] = new Filme("Missao Impossivel", 150, "Ação", 14);
		filmes[1] = new Filme("Toy Story", 100);
		filmes[2] = new Filme("O Exorcista", 120, "Terror", 16);

		Sessao[] sessoes = new Sessao[5];
		sessoes[0] = new Sessao("A01", filmes[0], 840, 30);
		sessoes[1] = new Sessao("B02", filmes[0], 1110);
		sessoes[2] = new Sessao("C03", filmes[1], 900, 40);
		sessoes[3] = new Sessao("D04", filmes[1], 1020);
		sessoes[4] = new Sessao("E05", filmes[2], 1260, 20);

		int opcao;
		do {
			System.out.println();
			System.out.println("===== CINEMA =====");
			System.out.println("1 - Gerenciar filmes");
			System.out.println("2 - Gerenciar sessoes e vender ingressos");
			System.out.println("3 - Consultar e atualizar informacoes do cinema");
			System.out.println("0 - Sair");
			System.out.print("Opcao: ");
			opcao = teclado.nextInt();
			teclado.nextLine();

			if (opcao == 1) {
				gerenciarFilmes(filmes);
			} else if (opcao == 2) {
				gerenciarSessoes(sessoes);
			} else if (opcao == 3) {
				informacoesCinema(sessoes);
			} else if (opcao == 0) {
				System.out.println("Encerrando...");
			} else {
				System.out.println("Opcao invalida.");
			}
		} while (opcao != 0);
	}

	// Gerenciar filmes
	public static void gerenciarFilmes(Filme[] filmes) {
		System.out.println();
		System.out.println("--- Filmes ---");
		for (int i = 0; i < filmes.length; i++) {
			System.out.println((i + 1) + " - " + filmes[i].getTitulo() + " | " + filmes[i].getCategoria());
		}
		System.out.print("Escolha o filme: ");
		int escolha = teclado.nextInt();
		teclado.nextLine();

		if (escolha < 1 || escolha > 3) {
			System.out.println("Filme invalido.");
			return;
		}

		Filme filme = filmes[escolha - 1];

		int opcao;
		do {
			System.out.println();
			System.out.println("--- Filme: " + filme.getTitulo() + " ---");
			System.out.println("1 - Consultar os dados do filme");
			System.out.println("2 - Alterar a categoria");
			System.out.println("3 - Alterar a sinopse");
			System.out.println("0 - Voltar ao menu principal");
			System.out.print("Opcao: ");
			opcao = teclado.nextInt();
			teclado.nextLine();

			if (opcao == 1) {
				System.out.println("Titulo: " + filme.getTitulo());
				System.out.println("Duracao: " + filme.getDuracao() + " minutos");
				if (filme.getClassificacaoEtaria() == 0) {
					System.out.println("Classificacao: Livre");
				} else {
					System.out.println("Classificacao: " + filme.getClassificacaoEtaria() + " anos");
				}
				System.out.println("Categoria: " + filme.getCategoria());
				System.out.println("Sinopse: " + filme.getSinopse());
			} else if (opcao == 2) {
				System.out.println("Categorias permitidas: Ação, Aventura, Comédia, Drama, Terror");
				System.out.print("Nova categoria: ");
				String novaCategoria = teclado.nextLine();
				if (filme.setCategoria(novaCategoria)) {
					System.out.println("Alteracao aceita.");
				} else {
					System.out.println("Alteracao recusada.");
				}
			} else if (opcao == 3) {
				System.out.print("Nova sinopse: ");
				String novaSinopse = teclado.nextLine();
				filme.setSinopse(novaSinopse);
				System.out.println("Sinopse atualizada.");
			} else if (opcao != 0) {
				System.out.println("Opcao invalida.");
			}
		} while (opcao != 0);
	}

	// Gerenciar sessoes e vender ingressos
	public static void gerenciarSessoes(Sessao[] sessoes) {
		System.out.println();
		System.out.println("--- Sessoes ---");
		for (int i = 0; i < sessoes.length; i++) {
			System.out.print((i + 1) + " - " + sessoes[i].getCodigoIdentificador() + " | "
					+ sessoes[i].getFilme().getTitulo() + " | ");
			mostrarHora(sessoes[i].getHoraInicio());
			System.out.println();
		}
		System.out.print("Escolha a sessao: ");
		int escolha = teclado.nextInt();
		teclado.nextLine();

		if (escolha < 1 || escolha > 5) {
			System.out.println("Sessao invalida.");
			return;
		}

		Sessao sessao = sessoes[escolha - 1];

		int opcao;
		do {
			System.out.println();
			System.out.println("--- Sessao " + sessao.getCodigoIdentificador() + " ---");
			System.out.println("1 - Consultar os detalhes da sessao");
			System.out.println("2 - Vender ingresso");
			System.out.println("3 - Consultar os lugares disponiveis");
			System.out.println("4 - Consultar o horario de termino");
			System.out.println("0 - Voltar ao menu principal");
			System.out.print("Opcao: ");
			opcao = teclado.nextInt();
			teclado.nextLine();

			if (opcao == 1) {
				detalhesSessao(sessao);
			} else if (opcao == 2) {
				venderIngresso(sessao);
			} else if (opcao == 3) {
				System.out.println("Lugares disponiveis: " + sessao.lugaresDisponiveis());
			} else if (opcao == 4) {
				System.out.print("Horario de termino: ");
				mostrarHora(sessao.getHoraTermino());
				System.out.println();
			} else if (opcao != 0) {
				System.out.println("Opcao invalida.");
			}
		} while (opcao != 0);
	}

	public static void detalhesSessao(Sessao sessao) {
		Filme filme = sessao.getFilme();
		System.out.println("Codigo: " + sessao.getCodigoIdentificador());
		System.out.print("Inicio: ");
		mostrarHora(sessao.getHoraInicio());
		System.out.print(" | Termino: ");
		mostrarHora(sessao.getHoraTermino());
		System.out.println();
		System.out.println("Filme: " + filme.getTitulo());
		System.out.println("Duracao: " + filme.getDuracao() + " minutos");
		if (filme.getClassificacaoEtaria() == 0) {
			System.out.println("Classificacao: Livre");
		} else {
			System.out.println("Classificacao: " + filme.getClassificacaoEtaria() + " anos");
		}
		System.out.println("Categoria: " + filme.getCategoria());
		System.out.println("Sinopse: " + filme.getSinopse());
		System.out.println("Capacidade maxima: " + sessao.getCapacidadeMaxima());
		System.out.println("Ingressos vendidos: " + sessao.getQtdIngressos());
		System.out.println("Lugares disponiveis: " + sessao.lugaresDisponiveis());
		System.out.println("Arrecadacao: R$ " + sessao.getValorArrecadado());
	}

	public static void venderIngresso(Sessao sessao) {
		System.out.println("Tipo de ingresso: 1 - Integral | 2 - Meia-entrada");
		System.out.print("Opcao: ");
		int tipo = teclado.nextInt();
		teclado.nextLine();

		float arrecadadoAntes = sessao.getValorArrecadado();
		double resultado;

		if (tipo == 1) {
			System.out.print("Idade do espectador: ");
			int idade = teclado.nextInt();
			teclado.nextLine();
			resultado = sessao.venderIngresso(idade);
		} else if (tipo == 2) {
			System.out.print("Idade do espectador: ");
			int idade = teclado.nextInt();
			teclado.nextLine();
			System.out.print("Numero do documento: ");
			String documento = teclado.nextLine();
			resultado = sessao.venderIngresso(idade, documento);
		} else {
			System.out.println("Tipo invalido.");
			return;
		}

		if (resultado == -1) {
			System.out.println("Venda recusada: dados invalidos.");
		} else if (resultado == -2) {
			System.out.println("Venda recusada: idade incompativel com a classificacao do filme.");
		} else if (resultado == -3) {
			System.out.println("Venda recusada: nao ha lugar disponivel.");
		} else {
			// o valor cobrado e o quanto a arrecadacao aumentou nesta venda
			float cobrado = sessao.getValorArrecadado() - arrecadadoAntes;
			System.out.println("Venda realizada! Valor cobrado: R$ " + cobrado);
		}
	}

	// Informacoes do cinema
	public static void informacoesCinema(Sessao[] sessoes) {
		int opcao;
		do {
			System.out.println();
			System.out.println("--- Informacoes do cinema ---");
			System.out.println("1 - Consultar o preco integral do ingresso");
			System.out.println("2 - Alterar o preco integral do ingresso");
			System.out.println("3 - Consultar os totais de ingressos vendidos e de arrecadacao");
			System.out.println("0 - Voltar ao menu principal");
			System.out.print("Opcao: ");
			opcao = teclado.nextInt();
			teclado.nextLine();

			if (opcao == 1) {
				System.out.println("Preco integral: R$ " + sessoes[0].getPrecoIntegral());
			} else if (opcao == 2) {
				System.out.print("Novo preco: ");
				float novoPreco = teclado.nextFloat();
				teclado.nextLine();
				if (sessoes[0].setPrecoIntegral(novoPreco)) {
					System.out.println("Alteracao aceita.");
				} else {
					System.out.println("Alteracao recusada.");
				}
			} else if (opcao == 3) {
				int totalIngressos = 0;
				float totalArrecadado = 0;
				for (int i = 0; i < sessoes.length; i++) {
					totalIngressos = totalIngressos + sessoes[i].getQtdIngressos();
					totalArrecadado = totalArrecadado + sessoes[i].getValorArrecadado();
				}
				System.out.println("Ingressos vendidos: " + totalIngressos);
				System.out.println("Arrecadacao total: R$ " + totalArrecadado);
			} else if (opcao != 0) {
				System.out.println("Opcao invalida.");
			}
		} while (opcao != 0);
	}

	public static void mostrarHora(int minutos) {
		int horas = minutos / 60;
		int resto = minutos % 60;
		if (horas < 10) {
			System.out.print("0");
		}
		System.out.print(horas + ":");
		if (resto < 10) {
			System.out.print("0");
		}
		System.out.print(resto);
	}
}