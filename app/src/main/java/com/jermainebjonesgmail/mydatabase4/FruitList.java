package com.jermainebjonesgmail.mydatabase4;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FruitList extends ArrayAdapter<Fruit> {

    private Activity context;
    private List<Fruit> fruitList;

    public FruitList(Activity context, List<Fruit> fruitList){
        super(context, R.layout.list_layout, fruitList);
        this.context = context;
        this.fruitList = fruitList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);

        Fruit fruit = fruitList.get(position);

        textViewName.setText(fruit.getFruitName());
        textViewPrice.setText(fruit.getFruitPrice());
        textViewId.setText(fruit.getFruitId());

        return listViewItem;
    }
}

