package pl.aetas.takeme.notificator;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

public class SoundNotificator implements Notificator {

    public static final String NOTIFICATION_SOUND_PREF_KEY = "notification_sound";
    public static final String NON_DEFAULT_NOTIFICATION_SOUND_ENABLED_PREF_KEY = "non_default_notification_sound_enabled";
    public static final String NON_DEFAULT_NOTIFICATION_SOUND_VALUE_PREF_KEY = "non_default_notification_sound_value";

    @Override
    public boolean isActive(Context context) {
        return true;
    }

    @Override
    public void bluetoothDeviceDisconnected(Context context) {

        Log.i("pl.aetas.takeme.n.Sound", "Sound notifier received info about bluetooth device disconnected");

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String notificationUriString;
        boolean nonDefaultNotificationSoundEnabled = sharedPref.getBoolean(NON_DEFAULT_NOTIFICATION_SOUND_ENABLED_PREF_KEY, false);
        if (nonDefaultNotificationSoundEnabled) {
            notificationUriString = sharedPref.getString(NON_DEFAULT_NOTIFICATION_SOUND_VALUE_PREF_KEY, "");
        } else {
            notificationUriString = sharedPref.getString(NOTIFICATION_SOUND_PREF_KEY, "");
        }
        Uri notificationUri = Uri.parse(notificationUriString);
        Ringtone ringtone = RingtoneManager.getRingtone(context, notificationUri);
        ringtone.play();
    }
}
