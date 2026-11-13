package shop.model;

public class PhysicalProduct extends Product {
    private final int weightGrams;

    public PhysicalProduct(String sku, String name, long price, int weightGrams) {
        super(sku, name, price);
        if (weightGrams <= 0) {
            throw new IllegalArgumentException("Weight grams must be positive");
        }
        this.weightGrams = weightGrams;
    }

    public int getWeightGrams() {
        return weightGrams;
    }

    @Override
    public String category() {
        return "Fisik";
    }
}