package meubardigital.app;

/*
* Essa classe vai:
* - Exibir um menu com opções numeradas de ações
* - Ler a entrada do usuário
* - Chamar os métodos do drinkmanager
* - Manter o menu funcionando até o usuário sair
* */

import meubardigital.model.Drink;
import meubardigital.model.TipoDrink;
import meubardigital.service.DrinkManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DrinkManager drinkManager = new DrinkManager();

        int opcao = -1;

        do {
            System.out.println("\nMENU - Meu Bar Digital");
            System.out.println("1. Cadastrar drink");
            System.out.println("2. Listar drinks");
            System.out.println("3. Buscar drink pelo nome");
            System.out.println("4. Remover drink");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String entrada = scanner.nextLine();
            try {
                opcao = Integer.parseInt(entrada); //tenta converter o texto lido para número
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Nome do drink: ");
                    String nome = scanner.nextLine();

                    System.out.println("Digite os ingredientes separados por vírgula: ");
                    String ingredientesRecebidos = scanner.nextLine();
                    List<String> ingredientes = new ArrayList<>();
                    for (String ing : ingredientesRecebidos.split(",")) {
                        /*trim: remove os espaços no começo e fim de cada palavra*/
                        ingredientes.add(ing.trim());
                    }

                    System.out.println("Modo de preparo: ");
                    String modoPreparo = scanner.nextLine();

                    System.out.println("Tipo do drink: ");
                    for (TipoDrink tipo : TipoDrink.values()) {
                        System.out.println("- " + tipo);
                    }

                    TipoDrink tipo = null;
                    boolean tipoValido = false;

                    do {
                        System.out.println("Escolha o tipo: ");
                        String tipoStr = scanner.nextLine().toUpperCase();

                        try {
                            tipo = TipoDrink.valueOf(tipoStr);
                            tipoValido = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Tipo inválido");
                        }

                    } while (!tipoValido);

                    Drink novoDrink = new Drink(nome, ingredientes, modoPreparo, tipo);
                    drinkManager.adicionarDrink(novoDrink);
                    break;

                case 2:
                    drinkManager.listarDrinks();
                    break;

                case 3:
                    System.out.print("Digite o nome do drink: ");
                    String nomeBusca = scanner.nextLine();
                    Drink encontrado = drinkManager.buscaDrinkPorNome(nomeBusca);

                    if (encontrado != null) {
                        System.out.println(encontrado);
                    } else {
                        System.out.println("Drink não encontrado");
                    }
                    break;

                case 4:
                    System.out.print("Digite o nome do drink que deseja remover: ");
                    String nomeRemover = scanner.nextLine();
                    drinkManager.removerDrink(nomeRemover);
                    break;

                case 0:
                    System.out.println("Encerrando. Até mais!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
        scanner.close();
    }
}
