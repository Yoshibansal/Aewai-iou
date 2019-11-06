package com.example.aewai_iou;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProductList extends ArrayAdapter<Product> {

    private Activity context;
    private List<Product> productList;

    public ProductList(Activity context, List<Product> productList){

        super(context,R.layout.list_view,productList);
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_view,null,true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textView2);
        TextView textViewAbout = (TextView) listViewItem.findViewById(R.id.textView3);
        TextView textViewPhone = (TextView) listViewItem.findViewById(R.id.textView4);

        Product artist = productList.get(position);

        textViewName.setText(artist.getName());
        textViewAbout.setText(artist.getAbout());
        textViewPhone.setText(artist.getNumber());

        return listViewItem;
    }
}
