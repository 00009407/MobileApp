package com.example.dictionary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;
import com.example.dictionary.modules.Object;

import org.jetbrains.annotations.NotNull;

import java.util.List;
//RecyclerView. Adapter base class for presenting List data in a RecyclerView
// Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView
public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.ObjectViewHolder> {

    private List<Object> objectList;
    private Context context;
    private OnItemClickListener listener;
//// The Adapter acts as a wrapper between two objects.
    public ObjectAdapter(Context context, List<Object> objects) {
        objectList = objects;
        this.context = context;
    }
//A RecyclerView. ViewHolder class which caches views associated with the default Preference layouts.//
// A ViewHolder describes an item view and metadata about its place within the RecyclerView.

    class ObjectViewHolder extends RecyclerView.ViewHolder {
        public ObjectViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        listener.onItemClicked(position);
                    }
                }
            });
        }

    }
//This method calls onCreateViewHolder(ViewGroup, int) to create//
// a new RecyclerView.ViewHolder and initializes
    @Override
    public ObjectViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ObjectViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_object, parent, false));
    }

//onBindViewHolder(VH holder, int position) Called by RecyclerView to display the data at the specified position.
    @Override
    public void onBindViewHolder(@NonNull @NotNull ObjectViewHolder holder, int position) {
        Object object = objectList.get(position);
        TextView name = holder.itemView.findViewById(R.id.name_object);
        TextView definition = holder.itemView.findViewById(R.id.definition_object);
        TextView Synonym = holder.itemView.findViewById(R.id.Synonym_object);
        TextView origin = holder.itemView.findViewById(R.id.origin);
        TextView date = holder.itemView.findViewById(R.id.date_txt);

        name.setText(object.getName());
        definition.setText(object.getDefinition());
        Synonym.setText(object.getSynonym());
        Synonym.setText(object.getSynonym());
        date.setText(object.getDate());
        origin.setText(object.getOrigin());

        switch (object.getType()) {
            case Arabicword: {
                name.setText("Arabicword");
                break;
            }
            case Russianword: {
                name.setText("Russianword");
                break;
            }
            case Latinword: {
                name.setText("Latinword");
                break;
            }
            case Spanishword: {
                name.setText("Spanishword");
                break;
            }
            case Frenchword: {
                name.setText("Frenchword");
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
