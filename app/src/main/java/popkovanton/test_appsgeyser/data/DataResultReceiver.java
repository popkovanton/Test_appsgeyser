package popkovanton.test_appsgeyser.data;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

@SuppressLint("ParcelCreator")
public class DataResultReceiver extends ResultReceiver {
    private Receiver receiver;

    @SuppressLint("RestrictedApi")
    public DataResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle data);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (receiver != null) {
            try {
                receiver.onReceiveResult(resultCode, resultData);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
