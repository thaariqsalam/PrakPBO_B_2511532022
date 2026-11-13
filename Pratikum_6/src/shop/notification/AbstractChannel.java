package shop.notification;

public abstract class AbstractChannel implements NotificationChannel {
    private int sentCount = 0;
    private String lastDelivered = "";

    protected abstract String resolveRecipient(CustomerContact contact);
    protected abstract boolean doSend(String recipient, String preparedMessage);

    protected String prepare(String message) {
        return message;
    }

    @Override
    public final boolean send(CustomerContact contact, String message) {
        if (message == null || message.isBlank() || contact == null) {
            return false;
        }
        String recipient = resolveRecipient(contact);
        if (recipient == null || recipient.isBlank()) {
            return false;
        }
        String prepared = prepare(message);
        if (doSend(recipient, prepared)) {
            sentCount++;
            lastDelivered = prepared;
            return true;
        }
        return false;
    }

    public int getSentCount() { return sentCount; }
    public String getLastDelivered() { return lastDelivered; }
}