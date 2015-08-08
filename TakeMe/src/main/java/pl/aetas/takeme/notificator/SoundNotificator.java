package pl.aetas.takeme.notificator;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

public class SoundNotificator implements Notificator {

    @Override
    public boolean isActive(Context context) {
        return true;
    }

    @Override
    public void bluetoothDeviceDisconnected(Context context) {

        Log.i("pl.aetas.takeme.n.Sound", "Sound notifier received info about bluetooth device disconnected");

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(context, notification);
        r.play();

    }
}
