package pl.aetas.takeme.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import pl.aetas.takeme.notificator.MainNotificator;

public class BluetoothBroadcastReceiver extends BroadcastReceiver {

    private final MainNotificator notificator;

    public BluetoothBroadcastReceiver() {
        notificator = new MainNotificator();
    }

    public BluetoothBroadcastReceiver(MainNotificator notificator) {
        this.notificator = notificator;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
            notificator.bluetoothDeviceDisconnected(context);
        }

    }
}
