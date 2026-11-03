package shop.model;

import shop.shipping.Shippable;

public class PhysicalProduct extends Product implements Shippable {
    private final int weight;

    public PhysicalProduct(String sku, String name, long price, int weight) {
        super(sku, name, price);
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must not be non-positive");
        }
        this.weight = weight;
    }

    public int getWeight() { return weight; }

    @Override
    public int getWeightGrams() {
        return weight;
    }

    @Override
    public String category() { return "Fisik"; }
}