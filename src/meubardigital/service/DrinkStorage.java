package meubardigital.service;

import meubardigital.model.Drink;
import meubardigital.model.TipoDrink;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrinkStorage {
    private static final String ARQUIVO = "drinks.txt";

    // Salva todos os drinks da lista em um arquivo
    public static void salvarDrinks(List<Drink> drinks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Drink drink : drinks) {
                String linha = String.join(";", Arrays.asList(
                   drink.getNome(),
                   String.join(",", drink.getIngredientes()),
                   drink.getModoPreparo(),
                   drink.getTipo().name()
                ));
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar drinks: " + e.getMessage());
        }
    }

    
}
