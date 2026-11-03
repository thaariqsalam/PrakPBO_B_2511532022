package shop.payment;

public interface PaymentMethod {
    String getName();
    
    /** @return true if payment is successful, false otherwise */
    boolean pay(long amount);
}
