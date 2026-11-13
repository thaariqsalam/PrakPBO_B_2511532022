package shop.notification;

public interface NotificationChannel {
    String channelName();
    boolean send(CustomerContact customer, String message);

}
