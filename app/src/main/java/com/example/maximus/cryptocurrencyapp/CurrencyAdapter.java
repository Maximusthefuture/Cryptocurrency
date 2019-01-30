package com.example.maximus.cryptocurrencyapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maximus.cryptocurrencyapp.model.Model;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    private List<Model> models;
    Context context;
    View view;


    public CurrencyAdapter(List<Model> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.currency_layout, parent, false);

        return new CurrencyViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {


        Model currencyModel = models.get(position);
        holder.currencyName.setText(currencyModel.getName());
        holder.currencyInDollars.setText(String.valueOf(String.format("%.2f", currencyModel.getQuote().getUSD().getPrice())) + "$");
        holder.changeIn7Days.setText(String.valueOf(String.format("%.2f",currencyModel.getQuote().getUSD().getPercentChange7d())) + " %");
        holder.changeIn24hour.setText(String.valueOf(String.format("%.2f", currencyModel.getQuote().getUSD().getPercentChange24h()))+ " %");
        holder.bindImage(currencyModel);
        if (currencyModel.getQuote().getUSD().getPercentChange7d() < 0) {
            holder.changeIn7Days.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        } else {
            holder.changeIn7Days.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }
        if (currencyModel.getQuote().getUSD().getPercentChange24h() < 0) {
            holder.changeIn24hour.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        } else {
            holder.changeIn24hour.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }

    }



    @Override
    public int getItemCount() {
        return models.size();
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder {

        final CurrencyAdapter mAdapter;
        public TextView currencyName;
        public TextView currencyInDollars;
        public TextView changeIn24hour;
        public TextView changeIn7Days;
        public ImageView symbol;


        public CurrencyViewHolder(View itemView, CurrencyAdapter adapter) {
            super(itemView);
            currencyName = itemView.findViewById(R.id.currency_name);
            currencyInDollars = itemView.findViewById(R.id.currency_in_dollars);
            changeIn24hour = itemView.findViewById(R.id.change_in_24h_int);
            changeIn7Days = itemView.findViewById(R.id.change_in_7d_int);
            symbol = itemView.findViewById(R.id.icon_currency);
            this.mAdapter = adapter;

        }

        public void bindImage(Model model) {
            Glide.with(context)
                    .load(model.getLogo())
                    .into(symbol);
        }
    }
}
