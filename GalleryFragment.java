package com.example.dictionary.ui.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.dictionary.databinding.FragmentGalleryBinding;
import com.example.dictionary.db.DatabaseHelper;
import com.example.dictionary.modules.Object;
import com.example.dictionary.modules.Type;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

// A fragment defines and manages its own layout,//
// has its own lifecycle, and can handle its own input events.
public class GalleryFragment extends Fragment {


    private FragmentGalleryBinding binding;
    private DatabaseHelper databaseHelper;
    private GalleryFragmentArgs args;
    private Object updateObject;
    private DatePickerDialog datePickerDialog;
    private String dateTxt;
    private String Synonym;

    public GalleryFragment() {
        // Required empty public constructor
    }

//SynonymHolder only creates a new view holder when
// there are no existing view holders which the RecyclerView can reuse

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGalleryBinding.inflate(getLayoutInflater());
        databaseHelper = new DatabaseHelper(getContext());
        if(GalleryFragmentArgs.fromBundle(getArguments()).getObject() != null) {
            args = GalleryFragmentArgs.fromBundle(getArguments());
        }else {
            args = null;
        }
        return binding.getRoot();
    }
//onViewCreated is called immediately after onCreateView(LayoutInflater, ViewGroup, Bundle)//
// has returned, but before any saved state has been restored in to the view.
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupDatePicker();

        if (args != null) {
            binding.delete.setVisibility(View.VISIBLE);
            binding.delete.setOnClickListener(v -> {
                if(updateObject != null) databaseHelper.delete(updateObject);
                else if(args.getObject() != null) databaseHelper.delete(args.getObject());
                Navigation.findNavController(getView()).popBackStack();
            });
            setUpdateObject();
        }

        binding.create.setOnClickListener(v -> {
            if (args != null) {
                //Update
                updateObject();
            } else {
                //Add new object
                addNewObject();
            }
        });

        binding.cancel.setOnClickListener(v -> {
            if (getView() != null) Navigation.findNavController(getView()).popBackStack();
        });

        binding.datePicker.setOnClickListener(v -> {
            datePickerDialog.show();
        });
    }
//The DatePicker control allows//
// the user to enter a date as text or to select a date from a calendar popup.
    private void setupDatePicker(){
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year, month, dayOfMonth) -> {
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.set(year, month, dayOfMonth);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                    dateTxt = simpleDateFormat.format(calendar1.getTime());
                    binding.date.setText(dateTxt);
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void addNewObject() {
        String name = binding.objectName.getText().toString();
        String definition = binding.objectDefinition.getText().toString();
        String Synonym = binding.objectSynonym.getText().toString();
        String origin = "";
        String comment = binding.objectComment.getText().toString();
        Type type = Type.None;

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(definition) && !TextUtils.isEmpty(Synonym) && !TextUtils.isEmpty(dateTxt)  && !TextUtils.isEmpty(comment)) {
            //origin = String.valueOf((Integer.parseInt(Synonym) * Integer.parseInt(definition)));
            switch ((String) binding.wordsSpinner.getSelectedItem()) {
                case "Arabicword" : {
                    Log.d("word", "arabicword");
                    type = Type.Arabicword;
                    break;
                }
                case "Russianword": {
                    Log.d("word", "russianword");
                    type = Type.Russianword;
                    break;
                }
                case "Latinword": {
                    Log.d("word", "latinword");
                    type = Type.Latinword;
                    break;
                }
                case "Spanishword": {
                    Log.d("word", "spanishword");
                    type = Type.Spanishword;
                    break;
                }
                case "Frenchword" : {
                    Log.d("word", "frenchword");
                    type = Type.Frenchword;
                    break;
                }
            }
            if (type != Type.None) {
                Object object = new Object(name, definition, Synonym, origin, type, dateTxt, comment);
                databaseHelper.insert(object);
                Toast.makeText(getContext(), "Object added", Toast.LENGTH_SHORT).show();
                if (getView() != null) {
                    Navigation.findNavController(getView()).popBackStack();
                }
            } else {
                Toast.makeText(getContext(), "You need to choose a word!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUpdateObject() {
        //Fragment was calling from Home Fragment to edit a object
        //Update the object
        updateObject = args.getObject();
        binding.create.setText("Edit");
        binding.titleDoes.setText("Edit object");
        binding.objectName.setText(updateObject.getName());
        binding.objectDefinition.setText(updateObject.getDefinition());
        binding.objectSynonym.setText(updateObject.getSynonym());
        binding.objectComment.setText(updateObject.getNote());
        binding.date.setText(updateObject.getDate());


        switch (updateObject.getType()) {
            case Arabicword:  {
                Log.d("word", "arabicword");
                binding.wordsSpinner.setSelection(0);
                break;
            }
            case Russianword: {
                Log.d("word", "russianword");
                binding.wordsSpinner.setSelection(1);
                break;
            }
            case Latinword: {
                Log.d("word", "latinword");
                binding.wordsSpinner.setSelection(2);
                break;
            }
            case Spanishword: {
                Log.d("word", "spanishword");
                binding.wordsSpinner.setSelection(4);
                break;
            }
            case Frenchword:  {
                Log.d("word", "frenchword");
                binding.wordsSpinner.setSelection(3);
                break;
            }
        }
    }

    private void updateObject() {
        if (updateObject != null) {
            String name = binding.objectName.getText().toString();
            String definition = binding.objectDefinition.getText().toString();
            String Synonym = binding.objectSynonym.getText().toString();
            String comment = binding.objectComment.getText().toString();
            dateTxt = binding.date.getText().toString();
            String origin = "";
            Type type = Type.None;

            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(definition) && !TextUtils.isEmpty(Synonym) && !TextUtils.isEmpty(dateTxt)  && !TextUtils.isEmpty(comment)) {
               // origin = String.valueOf((Integer.parseInt(Synonym) * Integer.parseInt(definition)));
                switch ((String) binding.wordsSpinner.getSelectedItem()) {
                    case "Arabicword" : {
                        type = Type.Arabicword;
                        break;
                    }
                    case "Russianword": {
                        type = Type.Russianword;
                        break;
                    }
                    case "Latinword": {
                        type = Type.Latinword;
                        break;
                    }
                    case "Spanishword": {
                        type = Type.Spanishword;
                        break;
                    }
                    case "Frenchword" : {
                        type = Type.Frenchword;
                        break;
                    }
                }
                if (type != Type.None) {
                    int id = updateObject.getId();
                    updateObject = new Object(id, name, definition, Synonym, origin, type, dateTxt, comment);
                    databaseHelper.update(updateObject, updateObject.getId());
                    Toast.makeText(getContext(), "Object updated!", Toast.LENGTH_SHORT).show();
                    if (getView() != null) {
                        Navigation.findNavController(getView()).popBackStack();
                    }
                }
            }
        }
    }
//onDestroyView() allows the fragment to clean up resources associated with its View.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}