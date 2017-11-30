package popkovanton.test_appsgeyser.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.Serializable;

import popkovanton.test_appsgeyser.R;
import popkovanton.test_appsgeyser.data.ModelHistoryElement;

public class NewTextFragment extends Fragment {
    private FloatingActionButton fab;
    private EditText editText;
    ModelHistoryElement modelHistoryElement = new ModelHistoryElement();
    HistoryFragment historyFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_text, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewElement(view);
        initFab(view);
        getActivity().setTitle("Enter text");
    }

    private void initViewElement(View view) { // инициализация элементов
        editText = view.findViewById(R.id.fragment_main_editText);
    }

    private void initFab(View view) {  // инициализация кнопки
        fab = view.findViewById(R.id.fab);
        historyFragment = new HistoryFragment();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().length() == 0) {
                    editText.setError("Заполните пустое поле");
                } else
                    modelHistoryElement.setText(editText.getText().toString());
                    modelHistoryElement.setLanguage("Заглушка");
                    onHistoryListAdded(modelHistoryElement);
            }
        });
    }

    private void onHistoryListAdded(ModelHistoryElement modelHistoryElement) { //добавления текста по клику на fab
        Bundle args = new Bundle();
        args.putSerializable("tag", modelHistoryElement);
        historyFragment.setArguments(args);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, historyFragment, "TAG_TO_FRAGMENT")
                .addToBackStack("TAG_TO_FRAGMENT").commit();
    }
}
