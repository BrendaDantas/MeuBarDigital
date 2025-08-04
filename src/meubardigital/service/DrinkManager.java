package meubardigital.service;

/*
* Essa classe vai conter:
* - Uma lista de drinks armazenados
* - Métodos para:
*   - adicionar um novo drink
*   - listar todos os drinks
*   - buscar um drink pelo nome
*   - remover um drink pelo nome
* */

import meubardigital.model.Drink;
import meubardigital.model.TipoDrink;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DrinkManager {

    private List<Drink> listaDeDrinks;

    public DrinkManager() {
        this.listaDeDrinks = DrinkStorage.carregarDrinks();
    }

    public void adicionarDrink(Drink drink) {
        listaDeDrinks.add(drink);
        DrinkStorage.salvarDrinks(listaDeDrinks);
        System.out.println("Drink adicionado com sucesso!");
    }

    public void listarDrinks() {
        if (listaDeDrinks.isEmpty()) {
            System.out.println("Nenhum drink cadastrado.");
        } else {
            listaDeDrinks.stream()
                    .sorted(Comparator.comparing(Drink::getNome))
                    .forEach(System.out::println);
        }
    }

    public Drink buscaDrinkPorNome(String nome) {
        for (Drink d : listaDeDrinks) {
            if (d.getNome().equalsIgnoreCase(nome)) {
                return d;
            }
        }
        return null;
    }

    public List<Drink> buscarPorNomeIncompleto(String parteDoNome) {
        return listaDeDrinks.stream()
                .filter(d -> d.getNome().toLowerCase().contains(parteDoNome.toLowerCase()))
                .sorted(Comparator.comparing(Drink::getNome))
                .toList();
    }

    public boolean removerDrink(String nome) {
        Drink drinkEncontrado = buscaDrinkPorNome(nome);
        if (drinkEncontrado != null) {
            listaDeDrinks.remove(drinkEncontrado);
            DrinkStorage.salvarDrinks(listaDeDrinks);
            System.out.println("Drink removido com sucesso!");
            return true;
        } else {
            System.out.println("Drink não encontrado.");
            return false;
        }
    }
}
