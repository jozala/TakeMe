package pl.aetas.takeme.notificator;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ScreenNotificator implements Notificator {

    @Override
    public void bluetoothDeviceDisconnected(Context context) {
        Log.i("pl.aetas.takeme.n.Scree", "Screen notifier received info about bluetooth device disconnected");

        Toast.makeText(context, "Bluetooth device disconnected. Man, please TakeMe with you!",
                Toast.LENGTH_LONG).show();
    }
}
