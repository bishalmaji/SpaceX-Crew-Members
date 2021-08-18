package com.bishal.datafrominternet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bishal.datafrominternet.R;
import com.bishal.datafrominternet.model.CrewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.MyViewHolder> {
    private Context context;
    private List<CrewModel> movieList;
    private ItemClickListener clickListener;

    public CrewAdapter(Context context, List<CrewModel> movieList, ItemClickListener clickListener) {
        this.context = context;
        this.movieList = movieList;
        this.clickListener = clickListener;
    }

    public void setMovieList(List<CrewModel> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.crew_name.setText(movieList.get(position).getName());
        holder.crew_agency.setText(movieList.get(position).getAgency());
        holder.crew_wikipedia.setText(movieList.get(position).getWikipedia());
        holder.crew_status.setText(movieList.get(position).getStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onMovieClick(movieList.get(position));
            }
        });
        Glide.with(context)
                .load(this.movieList.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(this.movieList != null) {
            return this.movieList.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView crew_name,crew_agency,crew_wikipedia,crew_status;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            crew_name = (TextView)itemView.findViewById(R.id.tvname);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            crew_agency = (TextView)itemView.findViewById(R.id.tvagency);
            crew_wikipedia = (TextView)itemView.findViewById(R.id.tvwikipedia);
            crew_status = (TextView)itemView.findViewById(R.id.tvstatus);

        }
    }


    public interface ItemClickListener{
        void onMovieClick(CrewModel movie);
    }
}
