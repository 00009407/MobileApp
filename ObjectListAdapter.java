package com.example.dictionary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dictionary.R;
import com.example.dictionary.modules.Object;
import java.util.List;
// Base adapter Common base class of common implementation for an Adapter
public class ObjectListAdapter extends BaseAdapter {

    private List<Object> objectList;
    private Context context;
    private ObjectAdapter.OnItemClickListener listener;
//public interface ListAdapter
//implements Adapter
    public ObjectListAdapter(Context context, List<Object> objects) {
        this.context = context;
        this.objectList = objects;
    }
//A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    public class MyViewHolder {
        TextView name, definition, Synonym, origin,  date, comment;

        public MyViewHolder(View view) {
            name = view.findViewById(R.id.name_object);
            definition = view.findViewById(R.id.definition_object);
            Synonym = view.findViewById(R.id.Synonym_object);
            origin = view.findViewById(R.id.origin);
            date = view.findViewById(R.id.date_txt);
            comment = view.findViewById(R.id.comment_txt);
//the OnClickListener() interface has an onClick(View v) method //
// that is called when the view (component) is clicked.
            view.setOnClickListener(v -> {
                if (listener != null) {
                    int position = view.getId();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClicked(position);
                    }
                }
            });
        }
    }

        @Override
        public int getCount() {
            return objectList.size();
        }

        @Override
        public java.lang.Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
//Get a View that displays the data at the specified position in the data set.
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Object object = objectList.get(position);
            View view = convertView;
            MyViewHolder myViewHolder;
            if (view == null) {
//LayoutInflater is a class used to instantiate layout XML file into//
// its corresponding view objects which can be used in Java programs.
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.item_object, parent, false);
                myViewHolder = new MyViewHolder(view);
                view.setTag(myViewHolder);
                view.setId(position);
            } else {
                myViewHolder = (MyViewHolder) view.getTag();
            }
            myViewHolder.name.setText(object.getName());
            myViewHolder.definition.setText("Definition: " + object.getDefinition());
            myViewHolder.Synonym.setText("Synonym: " + object.getSynonym());

            myViewHolder.date.setText(object.getDate());
            myViewHolder.comment.setText(object.getNote());
            switch (object.getType()){
                case Arabicword:  {
                    myViewHolder.origin.setText("Arabicword");
                    break;
                }
                case Russianword: {
                    myViewHolder.origin.setText("Russianword");
                    break;
                }
                case Latinword: {
                    myViewHolder.origin.setText("Latinword");
                    break;
                }
                case Spanishword: {
                    myViewHolder.origin.setText("Spanishword");
                    break;
                }
                case Frenchword:  {
                    myViewHolder.origin.setText("Frenchword");
                    break;
                }
            }

            return view;
        }

        public interface OnItemClickListener {
            void onItemClicked(int position);
        }

        public void setListener(ObjectAdapter.OnItemClickListener listener) {
            this.listener = listener;
        }

    }
