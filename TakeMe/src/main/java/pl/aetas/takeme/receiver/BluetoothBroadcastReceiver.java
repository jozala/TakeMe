package pl.aetas.takeme.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import pl.aetas.takeme.broker.NotificationBroker;
import pl.aetas.takeme.broker.NotificationBrokerFactory;

public class BluetoothBroadcastReceiver extends BroadcastReceiver {

    private final NotificationBroker notificationBroker;

    public BluetoothBroadcastReceiver() {
        notificationBroker = NotificationBrokerFactory.getNotificationBroker();
    }

    public BluetoothBroadcastReceiver(NotificationBroker notificationBroker) {
        this.notificationBroker = notificationBroker;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
            Log.i("pl.aetas.takeme.r.BBR", "Bluetooth device disconnected");
            notificationBroker.bluetoothDeviceDisconnected(context);
        }

    }
}
