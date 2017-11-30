package popkovanton.test_appsgeyser.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import popkovanton.test_appsgeyser.R;
import popkovanton.test_appsgeyser.adapter.HistoryListAdapter;
import popkovanton.test_appsgeyser.data.ModelHistoryElement;

public class HistoryFragment extends Fragment {

    private RecyclerView rvHistoryList;
    private RecyclerView.LayoutManager layoutManager;

    private HistoryListAdapter historyListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        rvHistoryList = rootView.findViewById(R.id.rvHistoryList);
        layoutManager = new LinearLayoutManager(getActivity());
        rvHistoryList.setLayoutManager(layoutManager);

        historyListAdapter = new HistoryListAdapter(createMockHistoryItemList()); //заглушка
        rvHistoryList.setAdapter(historyListAdapter);

        Bundle args = getArguments();
        if(args != null) {
            ModelHistoryElement modelHistoryElement = (ModelHistoryElement) args.getSerializable("tag");
            rvHistoryList.setAdapter(historyListAdapter.addHistoryListAdapter(modelHistoryElement)); // обновление списка айтемов
        }

        return rootView;
    }


    private List<ModelHistoryElement> createMockHistoryItemList() {  //заглушка
        List<ModelHistoryElement> list = new ArrayList<>();

        list.add(new ModelHistoryElement("Test1_text", "Test1_lang"));
        list.add(new ModelHistoryElement("Test2_text", "Test2_lang"));
        list.add(new ModelHistoryElement("Test3_text", "Test3_lang"));
        list.add(new ModelHistoryElement("Test4_text", "Test4_lang"));

        return list;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("History");
    }
}
