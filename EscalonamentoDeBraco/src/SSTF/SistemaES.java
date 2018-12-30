package SSTF;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaES {

	public static void main(String[] args) {
		
		int limiteInferiorDisco = 0;
		int limiteSuperiorDisco = 0;
		Integer atualBloco = 0;
		List<Integer> listaBlocos= new ArrayList<>();
		
		Scanner entrada = new Scanner(System.in);
		
		do {
			System.out.print("Digite a quantidade de blocos de disco: ");
			limiteSuperiorDisco = entrada.nextInt();
			
			if (limiteSuperiorDisco < 0) {
				System.out.println("Quantidade de blocos invalida!");
				limiteSuperiorDisco = 0;
			} else {
				limiteSuperiorDisco--;
			}
			
		} while (limiteSuperiorDisco == 0);
		
		System.out.print("Digite a lista de leitura de blocos inicial entre " + limiteInferiorDisco + " e " + limiteSuperiorDisco +
				" (separados por virgula): ");
		entrada.nextLine();
		String lista = entrada.nextLine();
		String numero = "";
		
		for (char caracter : lista.toCharArray()) {
			if (caracter >= 48 && caracter <= 57) {
				numero += caracter;
			} else if (caracter == 44) {
				int valor = Integer.parseInt(numero);
				if (valor >= limiteInferiorDisco && valor <= limiteSuperiorDisco) {
					listaBlocos.add(valor);
				}
				numero = "";
			}
		}
		
		if (!numero.equals("")) {	//adiciona o ultimo numero
			int valor = Integer.parseInt(numero);
			if (valor >= limiteInferiorDisco && valor <= limiteSuperiorDisco) {
				listaBlocos.add(valor);
			}
		}

		if (lista.length() > 0) {
			boolean blocoExistente;
			do {
				blocoExistente = false;
				System.out.print("Digite o bloco inicial: ");
				atualBloco = entrada.nextInt();
				
				for (int blocoRequisitado : listaBlocos) {
					if (blocoRequisitado == atualBloco) {
						blocoExistente = true;
						atualBloco = blocoRequisitado;
					}
				}
				
				if (!blocoExistente) {
					System.out.println("Bloco Inexistente na lista de blocos, tente novamente!");
				}
				
			} while (!blocoExistente);
			
			entrada.nextLine();
			
			String comando = "";
			do {
				System.out.println("Digite 'iniciar' para iniciar as leituras: ");
				comando = entrada.nextLine();
				
				if (!comando.equals("iniciar")) {
					System.out.println("Comando invalido, tente novamente!");
				}
				
			} while (!comando.equals("iniciar"));
			
			while (listaBlocos.size() > 0) {
				listaBlocos.remove(atualBloco);
				System.out.println("### O sistema leu o bloco " + atualBloco + " ###");
				
				do {
					System.out.print("Lista atual de blocos requisitados: ");
					for (int i = 0; i < listaBlocos.size(); i++) {
						if (i < listaBlocos.size() - 1) {
							System.out.print(listaBlocos.get(i) + ",");
						} else {
							System.out.print(listaBlocos.get(i) + "\n");
						}
					}
					
					System.out.print("Pressione ENTER para prosseguir com o proximo bloco ou digite um bloco adicional (ou uma lista e blocos separados por virgula): ");
					lista = entrada.nextLine();
					numero = "";
					
					for (char caracter : lista.toCharArray()) {
						if (caracter >= 48 && caracter <= 57) {
							numero += caracter;
						} else if (caracter == 44) {
							int valor = Integer.parseInt(numero);
							if (valor >= limiteInferiorDisco && valor <= limiteSuperiorDisco) {
								listaBlocos.add(valor);
							}
							numero = "";
						}
					}
					
					if (!numero.equals("")) {	//adiciona o ultimo numero
						int valor = Integer.parseInt(numero);
						if (valor >= limiteInferiorDisco && valor <= limiteSuperiorDisco) {
							listaBlocos.add(valor);
						}
					}
				} while (!lista.equals(""));
				
				Integer proximoBloco = null;
				int distanciaMinima = Integer.MAX_VALUE;
				
				for (int blocoRequisitado : listaBlocos) {
					int distanciaRequisitada = Math.abs(atualBloco - blocoRequisitado);
					if (distanciaRequisitada < distanciaMinima) {
						distanciaMinima = distanciaRequisitada;
						proximoBloco = blocoRequisitado;
					}
				}
				
				atualBloco = proximoBloco;
			}
			
			System.out.println("A lista de bloco esta vazia!");
			System.out.println("Finalizando sistema de entrada e saida!");
			
			entrada.close();
		} else {
			System.out.println("Nenhum bloco foi requisitado!");
			System.out.println("Finalizando sistema de entrada e saida!");
		}
		
	}
}
