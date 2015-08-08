package pl.aetas.takeme.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import pl.aetas.takeme.BuildConfig;
import pl.aetas.takeme.broker.NotificationBroker;
import pl.aetas.takeme.notificator.Notificator;

import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, manifest = Config.NONE)
public class BluetoothBroadcastReceiverTest {

    private BluetoothBroadcastReceiver bluetoothBroadcastReceiver;


    @Mock
    private Context context;

    @Mock
    private Intent intent;

    @Mock
    private Notificator notificator1;
    @Mock
    private Notificator notificator2;
    @Mock
    private Notificator notificatorDisabled;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bluetoothBroadcastReceiver = new BluetoothBroadcastReceiver(new NotificationBroker(notificator1));

        when(notificator1.isActive(Matchers.<Context>anyObject())).thenReturn(true);
        when(notificator2.isActive(Matchers.<Context>anyObject())).thenReturn(true);
        when(notificatorDisabled.isActive(Matchers.<Context>anyObject())).thenReturn(false);
    }

    @Test
    public void shouldNotNotifyWhenBluetoothDeviceHasBeenConnected() throws Exception {
        // given
        when(intent.getAction()).thenReturn(BluetoothDevice.ACTION_ACL_CONNECTED);
        // when
        bluetoothBroadcastReceiver.onReceive(context, intent);
        // then
        verify(notificator1, never()).bluetoothDeviceDisconnected(context);
    }

    @Test
    public void shouldNotifyThroughAllRegisteredNotifiersWhenBluetoothDeviceIsDisconnected() {
        // given
        bluetoothBroadcastReceiver = new BluetoothBroadcastReceiver(new NotificationBroker(notificator1, notificator2));
        when(intent.getAction()).thenReturn(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        // when
        bluetoothBroadcastReceiver.onReceive(context, intent);
        // then
        verify(notificator1).bluetoothDeviceDisconnected(context);
        verify(notificator2).bluetoothDeviceDisconnected(context);
    }

    @Test
    public void shouldNotifyThroughNotifiersWhichAreEnabledOnly() throws Exception {
        // given
        bluetoothBroadcastReceiver = new BluetoothBroadcastReceiver(new NotificationBroker(notificator1, notificatorDisabled));
        when(intent.getAction()).thenReturn(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        // when
        bluetoothBroadcastReceiver.onReceive(context, intent);
        // then
        verify(notificator1).bluetoothDeviceDisconnected(context);
        verify(notificatorDisabled, never()).bluetoothDeviceDisconnected(context);
    }
}