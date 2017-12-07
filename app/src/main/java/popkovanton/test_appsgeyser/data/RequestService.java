package popkovanton.test_appsgeyser.data;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.ResultReceiver;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Language;
import com.ibm.watson.developer_cloud.service.exception.BadRequestException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import popkovanton.test_appsgeyser.Constants;
import popkovanton.test_appsgeyser.MainActivity;
import popkovanton.test_appsgeyser.fragment.HistoryFragment;


//Данный класс выполняет ресурсоемкие действия в фоне.

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
        language = null;
        resultReceiver = intent.getParcelableExtra(Constants.RECEIVER);
        text = intent.getStringExtra(Constants.TEXT);
        languageDetect(text);
    }

    private void languageDetect(final String inputText) {
        if (checkConnection()) {
            final AlchemyLanguage service = new AlchemyLanguage();
            service.setApiKey(Constants.API_KEY);

            final Map<String, Object> params = new HashMap<>();
            params.put(AlchemyLanguage.TEXT, inputText);

            final Language language;
            try {
                //запрос на сервер
                language = service.getLanguage(params).execute();
                //распознанный язык
                final String detectLanguage = language.getLanguage();
                this.language = detectLanguage;
                writeResult();

            } catch (final BadRequestException e) {
                e.printStackTrace();
            }
        }
        sendResult(this.language);
    }

    private boolean checkConnection() {
        try {
            final URL httpsLink = new URL(Constants.GOOGLE_URL);
            final HttpURLConnection httpURLConnection
                    = (HttpURLConnection) httpsLink.openConnection();
            httpURLConnection.connect();
            try {
                return HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode();
            } finally {
                httpURLConnection.disconnect();
            }
        } catch (final IOException e) {
            return false;
        }
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


    @SuppressLint("RestrictedApi")
    private void sendResult(String language) {
        Bundle bundle = new Bundle();
        bundle.putString(LANGUAGE, language);
        resultReceiver.send(Constants.RESULT, bundle);
    }
}
