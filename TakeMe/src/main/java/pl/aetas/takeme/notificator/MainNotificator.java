package pl.aetas.takeme.notificator;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class MainNotificator {

    public void bluetoothDeviceDisconnected(Context context) {

        Log.i("pl.aetas.takeme.receiver.BluetoothBroadcastReceiver", "Bluetooth device disconnected 1");
        Toast.makeText(context, "Bluetooth device disconnected!! TakeMeWithYou man!", Toast.LENGTH_LONG).show();

        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context, notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
