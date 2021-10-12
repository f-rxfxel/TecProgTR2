package trabalhos.periodo2;

import java.io.IOException;
import java.util.Scanner;

public class Trabalho02 {
	
	static final int SIZE = 100;// <- altere a quantidade de espaço disponível no dicionário aqui =)
	static Scanner input = new Scanner(System.in);
	static int index = 0;
	static String word;

	// Estou usando o return das funções de maneira inadequada?
	// Provavelmente... Mas não encontrei outra maneira de parar a execução do método quando necessário.
	
	// Salvar classe como "UTF-8" para os caracteres especiais aparecerem.

	public static void main(String[] args) throws IOException {

		String[] wordsEnglish = new String[100], wordsPortuguese = new String[100];
		int option;

		do {
			System.out.println("┌─┄ Dicionário de Inglês para Português");
			System.out.println("┝ 1 - Adicionar palavra");
			System.out.println("┝ 2 - Consultar palavra");
			System.out.println("┝ 3 - Editar palavra");
			System.out.println("┝ 4 - Excluir palavra");
			System.out.println("┕ 9 - Fechar dicionário");
			System.out.println("\n┍ Digite uma opcão:");
			System.out.print("┕┅ ");
			option = input.nextInt();

			switch (option) {
				case 1:
					newWord(wordsEnglish, wordsPortuguese);
					break;
				case 2:
					searchWord(wordsEnglish, wordsPortuguese);
					break;
				case 3:
					editWord(wordsEnglish, wordsPortuguese);
					break;
				case 4:
					deleteWord(wordsEnglish, wordsPortuguese);
					break;
				case 9:
					System.out.print("\n┅ Dicionário fechado!");
					System.exit(0);
				default:
					System.out.println("\n┅ Opção inválida, tente novamente!\n");
			}
				
		} while (true);
	}

	public static String newWord(String[] wordsEnglish, String[] wordsPortuguese) {
		
		if (index == SIZE) {
			System.out.println("\n┅ Não há espaco disponível no dicionário!\n");
			return null;
		}

		System.out.print("\n┍ Digite a palavra em inglês:\n┕┅ ");
		word = input.next();

		for (int i = 0; i < SIZE; i++) {
			if (word.equalsIgnoreCase(wordsEnglish[i])) {
				System.out.println("\n┅ " + word + " já consta no dicionário!\n");
				return null;
			}
		}

		wordsEnglish[index] = word;
		System.out.print("\n┍ Digite a tradução de " + wordsEnglish[index] + ":\n┕┅ ");
		wordsPortuguese[index] = input.next();
		System.out.println("\n┍┅ Nova palavra catalogada!");
		System.out.println("┝ " + wordsEnglish[index] + " = " + wordsPortuguese[index]);
		index++;
		System.out.println("┕ (Espaço restante de palavras no dicionário: " + (SIZE - index) + ")\n");
		return null;
	}

	public static String searchWord(String[] wordsEnglish, String[] wordsPortuguese) {

		if (isDictionaryEmpty()) return null;

		System.out.print("\n┍ Digite a palavra a ser consultada: \n┕┅ ");
		word = input.next();

		for (int i = 0; i < SIZE; i++) {
			if (word.equalsIgnoreCase(wordsPortuguese[i]) || word.equalsIgnoreCase(wordsEnglish[i])) {
				System.out.print("\n┍┅ Palavra encontrada!");
				System.out.println("\n┕ " + wordsPortuguese[i] + " = " + wordsEnglish[i] + "\n");
				return null;
			}
		}
		
		System.out.println("\n┅ " + word + " não consta no dicionário!\n");
		return null;
	}

	public static String editWord(String[] wordsEnglish, String[] wordsPortuguese) {

		if (isDictionaryEmpty()) return null;
		
		System.out.print("\n┍┅ Digite a palavra a ser editada: \n┕┅ ");
		word = input.next();
		
		for (int i = 0; i < SIZE; i++) {
			if (word.equalsIgnoreCase(wordsPortuguese[i])) {
				System.out.print("\n┍┅ Digite a palavra editada para alterar \"" + word + "\": \n┕┅ ");
				word = input.next();
				
				if (word.equalsIgnoreCase(wordsPortuguese[i])) {
					System.out.println("\n┅ Não houveram edições na palavra!\n");
					return null;
				}
				
				wordsPortuguese[i] = word;
				System.out.print("\n┍┅ Digite a tradução de \"" + wordsPortuguese[i] + "\": \n┕┅ ");
				wordsEnglish[i] = input.next();
				System.out.println("\n┍┅ Palavra editada!");
				System.out.println("┕ " + wordsEnglish[i] + " = " + wordsPortuguese[i] + "\n");
				break;
			} else if (word.equalsIgnoreCase(wordsEnglish[i])) {
				System.out.print("\n┍┅ Digite a palavra editada para alterar \"" + word + "\": \n┕┅ ");
				word = input.next();
				
				if (word.equalsIgnoreCase(wordsEnglish[i])) {
					System.out.println("\n┅ Não houveram edições na palavra!\n");
					return null;
				}
				
				wordsEnglish[i] = word;
				System.out.print("\n┍┅ Digite a tradução de \"" + wordsEnglish[i] + "\": \n┕┅ ");
				wordsPortuguese[i]= input.next();
				System.out.println("\n┍┅ Palavra editada!");
				System.out.println("┕ " + wordsEnglish[i] + " = " + wordsPortuguese[i] + "\n");
				break;
			} else {
				System.out.println("\n┅ " + word + " não consta no dicionário!\n");
				break;
			}
		}
		return null;
	}
	
	public static String deleteWord(String[] wordsEnglish, String[] wordsPortuguese) {

		if (isDictionaryEmpty()) return null;

		System.out.print("\n┍ Digite a palavra a ser excluída: \n┕┅ ");
		word = input.next();

		for (int i = 0; i < SIZE; i++) {
			if (word.equalsIgnoreCase(wordsPortuguese[i]) || word.equalsIgnoreCase(wordsEnglish[i])) {
				System.out.println("\n┍┅ Palavra excluída!");
				System.out.println("┝ " + word + " foi apagada!");
				wordsPortuguese[i] = null;
				wordsEnglish[i] = null;
				index--;
				System.out.println("┕ (Espaço restante de palavras no dicionário: " + (SIZE - index) + ")\n");
				return null;
			}
		}
		System.out.println("\n┅ " + word + " não consta no dicionário!\n");
		return null;
	}

	public static boolean isDictionaryEmpty() {
		if (index == 0) {
			System.out.println("\n┅ Adicione ao menos uma palavra ao dicionário!\n");
			return true;
		}
		return false;
	}
}