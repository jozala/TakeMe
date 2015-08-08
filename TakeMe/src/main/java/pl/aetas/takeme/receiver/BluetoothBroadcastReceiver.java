package pl.aetas.takeme.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import pl.aetas.takeme.notificator.Notificator;
import pl.aetas.takeme.notificator.ScreenNotificator;
import pl.aetas.takeme.notificator.SoundNotificator;

import java.util.Arrays;
import java.util.List;

public class BluetoothBroadcastReceiver extends BroadcastReceiver {

    private final List<Notificator> notificators;

    public BluetoothBroadcastReceiver() {
        notificators = Arrays.asList(
                new SoundNotificator(),
                new ScreenNotificator()
        );
    }

    public BluetoothBroadcastReceiver(Notificator... notificators) {
        this.notificators = Arrays.asList(notificators);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
            Log.i("pl.aetas.takeme.r.BBR", "Bluetooth device disconnected");

            for (Notificator notificator : notificators) {
                if (notificator.isActive(context)) {
                    notificator.bluetoothDeviceDisconnected(context);
                }

            }
        }

    }
}
