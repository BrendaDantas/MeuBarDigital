package meubardigital.model;

import java.util.List;

public class Drink {
    private String nome;
    private List<String> ingredientes;
    private String modoPreparo;
    private TipoDrink tipo;

    public Drink(String nome, List<String> ingredientes, String modoPreparo, TipoDrink tipo) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.modoPreparo = modoPreparo;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public TipoDrink getTipo() {
        return tipo;
    }

    public void setTipo(TipoDrink tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "\n--- " + nome.toUpperCase() + " ---" +
                "\nTipo: " + tipo +
                "\nIngredientes: " + String.join(", ", ingredientes) +
                "\nModo de preparo: " + modoPreparo + "\n";
    }
}
