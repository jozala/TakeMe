package pl.aetas.takeme.broker;

import android.content.Context;
import pl.aetas.takeme.notificator.Notificator;

import java.util.Arrays;
import java.util.List;

public class NotificationBroker {
    private final List<Notificator> listeners;

    public NotificationBroker(Notificator... listeners) {
        this.listeners = Arrays.asList(listeners);
    }

    public void bluetoothDeviceDisconnected(Context context) {
        for (Notificator notificator : listeners) {
            if (notificator.isActive(context)) {
                notificator.bluetoothDeviceDisconnected(context);
            }
        }
    }
}
