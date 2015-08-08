package pl.aetas.takeme.notificator;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.net.Uri;
import android.preference.PreferenceManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import pl.aetas.takeme.BuildConfig;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static pl.aetas.takeme.notificator.SoundNotificator.NON_DEFAULT_NOTIFICATION_SOUND_ENABLED_PREF_KEY;
import static pl.aetas.takeme.notificator.SoundNotificator.NON_DEFAULT_NOTIFICATION_SOUND_VALUE_PREF_KEY;
import static pl.aetas.takeme.notificator.SoundNotificator.NOTIFICATION_SOUND_PREF_KEY;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest = Config.NONE, shadows = {ShadowRingtoneManager.class})
public class SoundNotificatorTest {

    private SoundNotificator soundNotificator;

    @Mock
    private Context context;

    @Mock
    private Ringtone ringtone;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        soundNotificator = new SoundNotificator();
    }

    @Test
    public void shouldUseNotificationSoundFromSettingsWhenNonDefaultIsSetToFalse() throws Exception {
        // given
        // given
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit()
                .putBoolean(NON_DEFAULT_NOTIFICATION_SOUND_ENABLED_PREF_KEY, false)
                .putString(NOTIFICATION_SOUND_PREF_KEY, "content:/testNotificationSound")
                .commit();
        ShadowRingtoneManager.addRingtone(Uri.parse("content:/testNotificationSound"), ringtone);
        // when
        soundNotificator.bluetoothDeviceDisconnected(context);
        // then
        verify(ringtone).play();
    }

    @Test
    public void shouldUseNonDefaultNotificationSoundFromSettingsWhenNonDefaultIsSetToTrue() throws Exception {
        // given
        // given
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit()
                .putBoolean(NON_DEFAULT_NOTIFICATION_SOUND_ENABLED_PREF_KEY, true)
                .putString(NON_DEFAULT_NOTIFICATION_SOUND_VALUE_PREF_KEY, "content:/nonDefault/testNotificationSound")
                .putString(NOTIFICATION_SOUND_PREF_KEY, "content:/testNotificationSound")
                .commit();
        Ringtone correctRingtone = mock(Ringtone.class);
        ShadowRingtoneManager.addRingtone(Uri.parse("content:/testNotificationSound"), ringtone);
        ShadowRingtoneManager.addRingtone(Uri.parse("content:/nonDefault/testNotificationSound"), correctRingtone);
        // when
        soundNotificator.bluetoothDeviceDisconnected(context);
        // then
        verifyNoMoreInteractions(ringtone);
        verify(correctRingtone).play();
    }
}

