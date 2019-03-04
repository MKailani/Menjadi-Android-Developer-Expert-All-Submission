package com.appkamus.submission.appkamus.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.appkamus.submission.appkamus.R;
import com.appkamus.submission.appkamus.model.Kamus;
import com.appkamus.submission.appkamus.view.adapter.viewholder.KamusViewHolder;
import com.appkamus.submission.appkamus.view.fragment.KamusView;

import java.util.ArrayList;
import java.util.List;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public class KamusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private final List<Kamus> mDataset = new ArrayList<>();
    private List<Kamus> mDatasetFilter = new ArrayList<>();
    private final KamusView mKamusView;

    public KamusAdapter(KamusView mKamusView) {
        this.mKamusView = mKamusView;
    }

    public void addList(List<Kamus> mDataset){
        this.mDataset.addAll(mDataset);
        this.mDatasetFilter.addAll(mDataset);
        this.notifyDataSetChanged();
    }

    public void clearList(){
        this.mDataset.clear();
        this.mDatasetFilter.clear();
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_item_kamus,viewGroup,false);
        return new KamusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            KamusViewHolder kamusVH = (KamusViewHolder)viewHolder;
            kamusVH.setKamus(mDatasetFilter.get(viewHolder.getAdapterPosition()));
            kamusVH.setView(mKamusView);


            kamusVH.tvTitle.setText(mDatasetFilter.get(viewHolder.getAdapterPosition()).getword());
    }

    @Override
    public int getItemCount() {
        return mDatasetFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if(charString.isEmpty())
                {
                    mDatasetFilter = mDataset;
                }
                else{
                    List<Kamus> filteredList= new ArrayList<>();
                    for(Kamus kamus : mDataset){

                        if(kamus.getword().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(kamus);
                        }
                    }

                    mDatasetFilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDatasetFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDatasetFilter = (ArrayList<Kamus>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
