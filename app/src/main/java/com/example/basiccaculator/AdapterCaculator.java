package com.example.basiccaculator;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class AdapterCaculator extends RecyclerView.Adapter<AdapterCaculator.ViewHolder> {
    private OnItemClickListenner onItemClickListenner;
    private ArrayList<String> listNumbers;


    AdapterCaculator(ArrayList listNumbers) {
        this.listNumbers = listNumbers;
        listNumbers = new ArrayList();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_caculator, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        for (int j = 0; j < listNumbers.size(); j++) {
            viewHolder.btnCalculator.setText(listNumbers.get(position));
        }
        viewHolder.btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListenner.onItemClicked(position);
            }
        });


    }

    interface OnItemClickListenner {
        void onItemClicked(int position);
    }

    public void setOnItemClickListenner(OnItemClickListenner onItemClickListenner) {
        this.onItemClickListenner = onItemClickListenner;
    }

    @Override
    public int getItemCount() {
        return listNumbers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        Button btnCalculator = itemView.findViewById(R.id.btnCalculator);

    }
}
