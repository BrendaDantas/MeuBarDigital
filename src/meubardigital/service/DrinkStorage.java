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

    // Carrega todos os drinks do arquivo e retorna a lista de drinks a ser apresentada quando o programa começa
    public static List<Drink> carregarDrinks() {
        List<Drink> drinks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    String nome = partes[0];
                    List<String> ingredientes = Arrays.asList(partes[1].split(","));
                    String modoPreparo = partes[2];
                    TipoDrink tipo = TipoDrink.valueOf(partes[3].toUpperCase());

                    drinks.add(new Drink(nome, ingredientes, modoPreparo, tipo));
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.out.println("Erro ao carregar drinks: "  + e.getMessage());
        }

        return drinks;
    }
}
