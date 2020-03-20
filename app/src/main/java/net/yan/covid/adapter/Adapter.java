package net.yan.covid.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import net.yan.covid.R;
import net.yan.covid.model.Covid;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> implements Filterable {

    List<Covid> list = new ArrayList<Covid>();
    private List<Covid> exampleListFull;
    public Adapter(List<Covid> list) {
        this.list = list;
        exampleListFull = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card
                , parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Covid covid = list.get(position);
        holder.casos.setText("Casos: "+covid.getCases());
        holder.pais.setText(covid.getCoutry());
    }

    @Override
    public int getItemCount() {
        Log.d("TAM", String.valueOf(list.size()));
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        private TextView pais;
        private TextView casos;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            pais = itemView.findViewById(R.id.pais);
            casos = itemView.findViewById(R.id.casos);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Covid> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Covid item : exampleListFull) {
                    if (item.getCoutry().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}
