package pl.aetas.takeme.notificator;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

import java.util.HashMap;
import java.util.Map;

@Implements(RingtoneManager.class)
public class ShadowRingtoneManager {

    private static Map<Uri, Ringtone> ringtones = new HashMap<>();

    public static void addRingtone(Uri ringtoneUri, Ringtone ringtone) {
        ringtones.put(ringtoneUri, ringtone);
    }

    @Implementation
    public static Ringtone getRingtone(Context context, Uri ringtoneUri) {
        if (!ringtones.containsKey(ringtoneUri)) {
            throw new AssertionError("unknown ringtone URI: " + ringtoneUri.toString());
        }
        return ringtones.get(ringtoneUri);
    }
}
