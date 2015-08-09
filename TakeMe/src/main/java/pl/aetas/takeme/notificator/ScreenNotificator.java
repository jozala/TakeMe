package pl.aetas.takeme.notificator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class ScreenNotificator implements Notificator {

    public static final String SCREEN_NOTIFICATION_ENABLED_PREF_KEY = "screen_notification_enabled";

    @Override
    public boolean isActive(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getBoolean(SCREEN_NOTIFICATION_ENABLED_PREF_KEY, false);
    }

    @Override
    public void bluetoothDeviceDisconnected(Context context) {
        Log.i("pl.aetas.takeme.n.Scree", "Screen notifier received info about bluetooth device disconnected");

        Toast.makeText(context, "Bluetooth device disconnected. Man, please TakeMe with you!",
                Toast.LENGTH_LONG).show();

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wl.acquire();

    }
}
