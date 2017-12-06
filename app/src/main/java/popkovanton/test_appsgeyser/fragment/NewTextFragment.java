package popkovanton.test_appsgeyser.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import popkovanton.test_appsgeyser.Constants;
import popkovanton.test_appsgeyser.MainActivity;
import popkovanton.test_appsgeyser.R;
import popkovanton.test_appsgeyser.data.DataResultReceiver;
import popkovanton.test_appsgeyser.data.RequestService;

public class NewTextFragment extends Fragment implements DataResultReceiver.Receiver {
    private FloatingActionButton fab;
    private EditText editText;

    public DataResultReceiver dataResultReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_new_text, container, false);

        if (savedInstanceState != null) {
            dataResultReceiver = savedInstanceState.getParcelable("receiver");
        }
        else {
            dataResultReceiver = new DataResultReceiver(new Handler());
        }
        dataResultReceiver.setReceiver(this);

        initViewElement(rootView);
        initFab(rootView);

        fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Constants.RECEIVER, dataResultReceiver);
        super.onSaveInstanceState(outState);
    }
    private void initViewElement(View view) { // инициализация элементов
        editText = view.findViewById(R.id.fragment_main_editText);
    }

    private void initFab(View view) {  // инициализация кнопки
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });
    }

    private void request() {
        dataResultReceiver = new DataResultReceiver(new Handler());
        dataResultReceiver.setReceiver(this);
        String text = String.valueOf(editText.getText());
        Intent intent = new Intent(getContext(), RequestService.class);
        intent.putExtra(Constants.RECEIVER, dataResultReceiver);
        intent.putExtra(Constants.TEXT, text);
        getActivity().startService(intent);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle data) {
        String language = data.getString(RequestService.LANGUAGE);
        if (language != null) {
            Toast toast = Toast.makeText(getContext(),
                    "Язык вашего текста: " + language, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getContext(),
                    "Проверьте соединение с интенетом", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
