package shop.shipping;

public class FreeShipping extends ShippingService {

    public FreeShipping(String courierName) {
        super(courierName);
    }

    @Override
    protected long ratePerKg() {
        return 0; // Mengimplementasikan method abstrak yang wajib di-override
    }
    // Mencoba meng-override method final dari ShippingService (akan memicu error compiler)
    @Override
    public long calculateCost(int weightGrams) {
        return 0; 
    }
}