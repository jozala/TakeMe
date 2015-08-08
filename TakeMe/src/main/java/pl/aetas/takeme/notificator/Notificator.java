package pl.aetas.takeme.notificator;

import android.content.Context;

public interface Notificator {
    boolean isActive(Context context);
    void bluetoothDeviceDisconnected(Context context);
}
