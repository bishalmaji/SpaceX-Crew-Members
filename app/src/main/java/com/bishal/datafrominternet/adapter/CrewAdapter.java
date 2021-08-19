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
    private List<CrewModel> crewList;
    private ItemClickListener clickListener;

    public CrewAdapter(Context context, List<CrewModel> crewList, ItemClickListener clickListener) {
        this.context = context;
        this.crewList = crewList;
        this.clickListener = clickListener;
    }
    public void clear(){
     crewList.clear();
     notifyDataSetChanged();
    }

    public void setCrewList(List<CrewModel> crewList) {
        this.crewList = crewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.crew_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.crew_name.setText(crewList.get(position).getName());
        holder.crew_agency.setText(crewList.get(position).getAgency());
        holder.crew_wikipedia.setText(crewList.get(position).getWikipedia());
        holder.crew_status.setText(crewList.get(position).getStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onMovieClick(crewList.get(position));
            }
        });
        Glide.with(context)
                .load(this.crewList.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(this.crewList != null) {
            return this.crewList.size();
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
