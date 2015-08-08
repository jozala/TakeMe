package pl.aetas.takeme.broker;

import pl.aetas.takeme.notificator.ScreenNotificator;
import pl.aetas.takeme.notificator.SoundNotificator;

public class NotificationBrokerFactory {

    private static final NotificationBroker INSTANCE = new NotificationBroker(
            new SoundNotificator(),
            new ScreenNotificator()
    );

    public static NotificationBroker getNotificationBroker() {
        return INSTANCE;
    }
}
