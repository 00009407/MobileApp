package com.example.dictionary.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.dictionary.R;
import com.example.dictionary.adapters.ObjectAdapter;
import com.example.dictionary.adapters.ObjectListAdapter;
import com.example.dictionary.databinding.FragmentHomeBinding;
import com.example.dictionary.db.DatabaseHelper;
import com.example.dictionary.modules.Object;
import com.example.dictionary.modules.Type;
import com.example.dictionary.utils.Prefs;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeFragment extends Fragment implements ObjectAdapter.OnItemClickListener {

    private FragmentHomeBinding binding;
    // private ObjectAdapter adapter;
    private List<Object> objectList;
    private List<Object> searchList;
    private DatabaseHelper databaseHelper;
    private ObjectListAdapter adapter;

    public HomeFragment() {
    }
//onCreate(Bundle) called to do initial creation of the fragment.//
// onCreateView(LayoutInflater, ViewGroup, Bundle) creates and returns the view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
////Database Helper class whose only function is to provide for the creation,//
//// modification, and deletion of tables in the database.
        if (getContext() != null) {
            Prefs sharedPreferences = new Prefs(getContext());
            if (sharedPreferences.getFirstUser()) {
                sharedPreferences.setFirstUser(false);
                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                databaseHelper.insert(new Object("Go", "went", "forward", "3", Type.Russianword, "2021.11.23", "Go was added here"));
                databaseHelper.insert(new Object("Donkey", "eshak", "horse", "3", Type.Frenchword, "2021.11.23", "Donkey was added here"));
                databaseHelper.insert(new Object("Gun", "boom", "gunshot", "3", Type.Russianword, "2021.11.23", "Gun was added here"));

                databaseHelper.close();
            }
        }

        return binding.getRoot();
    }
//onViewCreated is called immediately after onCreateView(LayoutInflater, ViewGroup, Bundle)//
// has returned, but before any saved state has been restored in to the view.
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addCard.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_galleryFragment, null);
        });
        databaseHelper = new DatabaseHelper(getContext());

        setupRecyclerView();
        setHasOptionsMenu(true);
    }
//RecyclerView. Adapter base class for presenting List data in a RecyclerView

    private void setupRecyclerView() {
        objectList = databaseHelper.getObjects();
        searchList = databaseHelper.getObjects();
        adapter = new ObjectListAdapter(getContext(), searchList);
        //adapter = new ObjectAdapter(getContext(), searchList);
        binding.recyclerView.setAdapter(adapter);
        // binding.recyclerView.setHasFixedSize(true);
        adapter.setListener(this);
    }

//What is the use of onCreateOptionsMenu?
//You use onCreateOptionsMenu() to specify the options menu for an activity. In this method, you can inflate//
// your menu resource (defined in XML) into the Menu provided in the callback
    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//public abstract boolean onQueryTextSubmit (String query)//
// Called when the user submits the query.
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) {
                    // binding.recyclerView.scrollToPosition(0);
                    searchView.clearFocus();
                }
                return true;
            }
//Called when the query text is changed by the user.
            @Override
            public boolean onQueryTextChange(String newText) {
                performFilter(newText);
                return true;
            }
        });

    }

//the constraint used to filter the data
    private void performFilter(String query) {
        if (query != null && !query.isEmpty()) {
            searchList.clear();
            for (int i = 0; i < objectList.size(); i++) {
                if (objectList.get(i).getName().contains(query)) {
                    if (searchList.size() > 1) {
                        for (int j = 0; j < searchList.size(); j++) {
                            if (searchList.get(j) != (objectList.get(i))) {
                                searchList.add(objectList.get(i));
                            }
                        }
                    } else {
                        searchList.add(objectList.get(i));
                    }
                }
            }
        } else {
            searchList.clear();
            searchList.addAll(objectList);
        }
        adapter.notifyDataSetChanged();
    }

//onDestroyView() allows the fragment to clean up resources associated with its View.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClicked(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", searchList.get(position));
        if (getView() != null) {
            Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_galleryFragment, bundle);
        }
    }
}