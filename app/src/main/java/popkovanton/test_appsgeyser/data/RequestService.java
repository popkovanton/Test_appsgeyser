package popkovanton.test_appsgeyser.data;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.ResultReceiver;

import popkovanton.test_appsgeyser.MainActivity;
import popkovanton.test_appsgeyser.fragment.HistoryFragment;
import popkovanton.test_appsgeyser.fragment.NewTextFragment;


public class RequestService extends IntentService {

    public static final String LANGUAGE = "language";
    private String text;
    private String language;
    private ResultReceiver resultReceiver;

    public RequestService() {
        super("RequestService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        language = "ЗАГЛУШКА";
        resultReceiver = intent.getParcelableExtra(DataResultReceiver.RECEIVER);
        text = intent.getStringExtra(NewTextFragment.TEXT);
        writeResult();
        sendResult(language);
    }

    private void writeResult() {
        MainActivity.dbHelper.saveHistoryItem(text, language); //добавление айтема в бд
        ModelHistoryElement modelHistoryElement = new ModelHistoryElement();
        modelHistoryElement.setText(text);
        modelHistoryElement.setLanguage(language);
        MainActivity.arrayHistoryElements.add(modelHistoryElement);
        if (HistoryFragment.historyListAdapter != null) {
            HistoryFragment.historyListAdapter.notifyDataSetChanged();
        }
    }


    private void sendResult(String language) {
        Bundle bundle = new Bundle();
        bundle.putString(LANGUAGE, language);
        resultReceiver.send(DataResultReceiver.RESULT, bundle);
    }
}
