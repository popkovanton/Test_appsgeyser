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

import popkovanton.test_appsgeyser.MainActivity;
import popkovanton.test_appsgeyser.R;
import popkovanton.test_appsgeyser.adapter.HistoryListAdapter;

public class HistoryFragment extends Fragment {

    private RecyclerView rvHistoryList;
    private RecyclerView.LayoutManager layoutManager;

    public static HistoryListAdapter historyListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        rvHistoryList = rootView.findViewById(R.id.rvHistoryList);
        historyListAdapter = new HistoryListAdapter(MainActivity.arrayHistoryElements);
        layoutManager = new LinearLayoutManager(getActivity());

        rvHistoryList.setAdapter(historyListAdapter);
        rvHistoryList.setLayoutManager(layoutManager);


        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("History");
    }


}
