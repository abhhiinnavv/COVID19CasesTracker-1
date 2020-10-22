package com.example.covid19tracker;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterStat extends RecyclerView.Adapter<AdapterStat.HolderStat> implements Filterable {

    private Context context;
    public ArrayList<ModelStat> statArrayList, filterList;
    private FilterStat filter;

    public AdapterStat(Context context, ArrayList<ModelStat> statArrayList) {
        this.context = context;
        this.statArrayList = statArrayList;
        this.filterList = statArrayList;
    }

    @NonNull
    @Override
    public HolderStat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_stat, parent, false);

        return new HolderStat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderStat holder, int position) {
        final ModelStat modelStat = statArrayList.get(position);

        final String country = modelStat.getCountry();
        final String totalConfirmed = modelStat.getTotalConfirmed();
        final String totalDeaths = modelStat.getTotalDeaths();
        final String totalRecovered = modelStat.getTotalRecovered();
        final String newConfirmed = modelStat.getNewConfirmed();
        final String newDeaths = modelStat.getNewDeaths();
        final String newRecovered = modelStat.getNewRecovered();


        holder.countryTv.setText(country);
        holder.todayRecoveredTv.setText(newRecovered);
        holder.todayDeathTv.setText(newDeaths);
        holder.todayCasesTv.setText(newConfirmed);
        holder.recoveredTv.setText(totalRecovered);
        holder.deathsTv.setText(totalDeaths);
        holder.casesTv.setText(totalConfirmed);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(context, country, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(context, CountryDetailedActivity.class);
                intent.putExtra("countryName", country);
                intent.putExtra("totalCases", totalConfirmed);
                intent.putExtra("totalDeaths", totalDeaths);
                intent.putExtra("totalRecovered", totalRecovered);
                intent.putExtra("todayCases", newConfirmed);
                intent.putExtra("todayDeaths", newDeaths);
                intent.putExtra("todayRecovered", newRecovered);


                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return statArrayList.size();
    }

    @Override
    public Filter getFilter() {

        if (filter == null) {
            filter = new FilterStat(this, filterList);
        }
        return filter;
    }

    class HolderStat extends RecyclerView.ViewHolder{

        TextView countryTv, casesTv, todayCasesTv, deathsTv, todayDeathTv, recoveredTv, todayRecoveredTv;

        public HolderStat(@NonNull View itemView) {
            super(itemView);

            countryTv = itemView.findViewById(R.id.countryTv);
            casesTv = itemView.findViewById(R.id.casesTv);
            todayCasesTv = itemView.findViewById(R.id.todayCasesTv);
            deathsTv = itemView.findViewById(R.id.deathsTv);
            todayDeathTv = itemView.findViewById(R.id.todayDeathTv);
            recoveredTv = itemView.findViewById(R.id.recoveredTv);
            todayRecoveredTv = itemView.findViewById(R.id.todayRecoveredTv);


        }
    }
}
