package com.jermainebjonesgmail.mydatabase4;

public class Fruit {

    String fruitId;
    String fruitName;
    String fruitPrice;
    String fruitLabelId;

    public Fruit(){

    }

    public Fruit(String fruitId, String fruitName, String fruitPrice, String fruitLabelId) {
        this.fruitId = fruitId;
        this.fruitName = fruitName;
        this.fruitPrice = fruitPrice;
        this.fruitLabelId = fruitLabelId;
    }

    public String getFruitId() {
        return fruitId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public String getFruitPrice() {
        return fruitPrice;
    }

    public String getFruitLabelId() {
        return fruitLabelId;
    }
}
