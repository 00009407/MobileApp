package com.example.dictionary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;
import com.example.dictionary.modules.Currency;

import org.jetbrains.annotations.NotNull;

import java.util.List;
//RecyclerView. Adapter base class for presenting List data in a RecyclerView ,//
// including computing diffs between Lists on a background thread. //
// Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView .
public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    private List<Currency> currencies;
    private Context context;
//Adapter is a structural design pattern, which allows incompatible objects to collaborate.
// The Adapter acts as a wrapper between two objects.
    public CurrencyAdapter(Context context, List<Currency> currencies) {
        this.currencies = currencies;
        this.context = context;
    }
//A RecyclerView. ViewHolder class which caches views associated with the default Preference layouts.//
// A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    class CurrencyViewHolder extends RecyclerView.ViewHolder {

        public CurrencyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }

    }
//onCreateViewHolder only creates a new view holder when
// there are no existing view holders which the RecyclerView can reuse
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new CurrencyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_layout, parent, false));
    }
//This method internally calls onBindViewHolder(ViewHolder, int)//
// to update the RecyclerView.ViewHolder contents//
// with the item at the given position and also sets up some private fields to be used by RecyclerView.
    @Override
    public void onBindViewHolder(@NonNull @NotNull CurrencyViewHolder holder, int position) {
        Currency currency = currencies.get(position);
        TextView data = holder.itemView.findViewById(R.id.data);
        TextView dollar = holder.itemView.findViewById(R.id.dollar);
        TextView som = holder.itemView.findViewById(R.id.som);

        data.setText(currency.getDate());
        dollar.setText(currency.getCcyNm_UZ());
        som.setText(currency.getRate());
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }


}
