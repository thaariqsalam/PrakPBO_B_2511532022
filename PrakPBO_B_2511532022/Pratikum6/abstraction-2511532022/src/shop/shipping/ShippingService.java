package shop.shipping;

public abstract class ShippingService {
    private final String name;

    protected ShippingService(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        this.name = name;
    }

    public String getName() { return name; }
    
    /** Rate in rupiah per started kilogram. */
    protected abstract long ratePerKg();

    /** Weight is rounded up: 700 g -> 1 kg, 1000 g -> 1 kg, 1001 g -> 2 kg */
    public final long calculateCost(int weightInGrams) {
        if (weightInGrams <= 0) {
            return 0;
        }
        long kilograms = (weightInGrams + 999L) / 1000;
        return kilograms * ratePerKg();
    }

}
