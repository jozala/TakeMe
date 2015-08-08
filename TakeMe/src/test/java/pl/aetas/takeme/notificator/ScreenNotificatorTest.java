package pl.aetas.takeme.notificator;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import pl.aetas.takeme.BuildConfig;

import static org.fest.assertions.api.Assertions.assertThat;
import static pl.aetas.takeme.notificator.ScreenNotificator.SCREEN_NOTIFICATION_ENABLED_KEY;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest = Config.NONE)
public class ScreenNotificatorTest {

    @Mock
    private Context context;
    private ScreenNotificator screenNotificator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        screenNotificator = new ScreenNotificator();
    }

    @Test
    public void shouldBeInactiveWhenPropertyInSettingsIsSetToFalse() throws Exception {
        // given
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit().putBoolean(SCREEN_NOTIFICATION_ENABLED_KEY, false).commit();
        // when
        boolean active = screenNotificator.isActive(context);
        // then
        assertThat(active).isFalse();
    }

    @Test
    public void shouldBeActiveWhenPropertyInSettingsIsSetToTrue() throws Exception {
        // given
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit().putBoolean(SCREEN_NOTIFICATION_ENABLED_KEY, true).commit();
        // when
        boolean active = screenNotificator.isActive(context);
        // then
        assertThat(active).isTrue();
    }
}