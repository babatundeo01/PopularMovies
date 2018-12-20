package com.example.misterright.popularmovies.view.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.misterright.popularmovies.R;
import com.example.misterright.popularmovies.model.movies.Result;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private final HomeContract.View homeView;
    private List<Result> results;

    public HomeAdapter(HomeContract.View homeView) {
        this.homeView = homeView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Result result = results.get(i);
        viewHolder.movieTitle.setText(result.getTitle());
    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }

    public void updateDataset(List<Result> results){
        if(this.results!=null) {
            this.results.addAll(results);
        } else {
            this.results = results;
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView movieTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            homeView.navigateToMovie(results.get(getAdapterPosition()).getId());
        }
    }
}
