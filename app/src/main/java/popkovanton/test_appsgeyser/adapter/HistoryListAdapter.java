package popkovanton.test_appsgeyser.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import popkovanton.test_appsgeyser.R;
import popkovanton.test_appsgeyser.data.ModelHistoryElement;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder> {

    private ArrayList<ModelHistoryElement> textLanguages;


    public HistoryListAdapter(ArrayList<ModelHistoryElement> textLanguages) {
        this.textLanguages = textLanguages;
    }


    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_list_element, parent, false);
        //TextView text = v.findViewById(R.id.rvTextElement);
        //TextView language = v.findViewById(R.id.rvLanguageElement);

        return new HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) { //просечиваем значения на элемент
        holder.item = textLanguages.get(position); //получаем элемент списка
        holder.text.setText(textLanguages.get(position).getText());
        holder.language.setText(textLanguages.get(position).getLanguage());
    }

    @Override
    public int getItemCount() {
        return textLanguages.size();
    }


    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView text;
        TextView language;
        ModelHistoryElement item;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.text = itemView.findViewById(R.id.rvTextElement);
            this.language = itemView.findViewById(R.id.rvLanguageElement);
        }
    }
}
