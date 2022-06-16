package com.example.dictionary.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dictionary.adapters.CurrencyAdapter;
import com.example.dictionary.api.ApiInterface;
import com.example.dictionary.databinding.FragmentCurrencyBinding;
import com.example.dictionary.modules.Currency;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.WithFragmentBindings;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@WithFragmentBindings
@AndroidEntryPoint
// A Fragment is a piece of an application's user interface or behavior//
// that can be placed in an Activity .
public class CurrencyFragment extends Fragment {

    private FragmentCurrencyBinding binding;
    private CurrencyAdapter adapter;
    private List<Currency> currencyList;
    @Inject
    ApiInterface apiInterface;

    public CurrencyFragment() {
        // Required empty public constructor
    }
//onViewCreated is called immediately after onCreateView(LayoutInflater, ViewGroup, Bundle)//
// has returned, but before any saved state has been restored in to the view.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCurrencyBinding.inflate(getLayoutInflater());
        currencyList = new ArrayList<>();
        return binding.getRoot();
    }
//onViewCreated is called immediately after onCreateView(LayoutInflater, ViewGroup, Bundle)//
// has returned, but before any saved state has been restored in to the view.
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        ProgressBar progressBar = binding.progressCircular;
        try {
            progressBar.setVisibility(View.VISIBLE);
            apiInterface.getCurrencies().enqueue(new Callback<List<Currency>>() {
                @Override
                public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                    if (response.isSuccessful()) {
                        progressBar.setVisibility(View.GONE);
                        if(response.body() != null) currencyList.addAll(response.body());
                        adapter.notifyDataSetChanged();
                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Currencies couldn't be loaded.", Toast.LENGTH_SHORT).show();
                    }
                }
//This method is called when an object fails
                @Override
                public void onFailure(Call<List<Currency>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Please check your Internet connection and try again!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Please turn on your Internet!", Toast.LENGTH_SHORT).show();
        }
    }
// RecyclerView is the ViewGroup that contains the views corresponding to your data.
    private void setupRecyclerView() {
        adapter = new CurrencyAdapter(getContext(), currencyList);
        binding.rv.setHasFixedSize(true);
        binding.rv.setAdapter(adapter);
    }
}