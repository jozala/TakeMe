package pl.aetas.takeme.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import pl.aetas.takeme.BuildConfig;
import pl.aetas.takeme.notificator.MainNotificator;

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
    private MainNotificator notificator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bluetoothBroadcastReceiver = new BluetoothBroadcastReceiver(notificator);
    }

    @Test
    public void shouldNotifyThroughNotifierWhenBluetoothDeviceIsDisconnected() throws Exception {
        // given
        when(intent.getAction()).thenReturn(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        // when
        bluetoothBroadcastReceiver.onReceive(context, intent);
        // then
        verify(notificator).bluetoothDeviceDisconnected(context);
    }

    @Test
    public void shouldNotNotifyWhenBluetoothDeviceHasBeenConnected() throws Exception {
        // given
        when(intent.getAction()).thenReturn(BluetoothDevice.ACTION_ACL_CONNECTED);
        // when
        bluetoothBroadcastReceiver.onReceive(context, intent);
        // then
        verify(notificator, never()).bluetoothDeviceDisconnected(context);
    }
}