package popkovanton.test_appsgeyser.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import popkovanton.test_appsgeyser.R;
import popkovanton.test_appsgeyser.data.ModelHistoryElement;
import popkovanton.test_appsgeyser.fragment.HistoryFragment;

public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ModelHistoryElement> items = new ArrayList<>();

    HistoryFragment historyFragment;

    public HistoryListAdapter(){
    }

    public HistoryListAdapter addHistoryListAdapter(ModelHistoryElement modelHistoryElement) { //обновить список айтемов
        this.items.add(modelHistoryElement);
        return new HistoryListAdapter(items);
    }

    public HistoryListAdapter(List<ModelHistoryElement> items) { //передача массива item'ов
        this.items = items;
    }


    public void addItemHistory(ModelHistoryElement modelHistoryElement) { //добавляем элементы списка
        items.add(modelHistoryElement);
        notifyItemInserted(getItemCount() - 1);//сообщаем о добавлении нового элемента в список
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_list_element, parent, false);
        TextView text = v.findViewById(R.id.rvTextElement);
        TextView language = v.findViewById(R.id.rvLanguageElement);

        return new HistoryViewHolder(v, text, language);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) { //просечиваем значения на элемент
        ModelHistoryElement item = items.get(position); //получаем элемент списка
        HistoryViewHolder historyViewHolder = (HistoryViewHolder) holder;
        historyViewHolder.text.setText(item.getText());
        historyViewHolder.language.setText(item.getLanguage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        TextView language;

        public HistoryViewHolder(View itemView, TextView text, TextView language) {
            super(itemView);
            this.text = text;
            this.language = language;
        }
    }
}
