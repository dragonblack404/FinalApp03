package com.example.finalapp03.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.finalapp03.model.Articulo;

import java.util.List;

public class ArticulosListAdapter extends BaseAdapter {

    private Context context;
    private List<Articulo> articulosList;

    public ArticulosListAdapter(Context context, List<Articulo> list){
        this.context = context;
        articulosList = list;
    }

    @Override
    public int getCount() {
        return articulosList.size();
    }

    @Override
    public Object getItem(int position) {
        return articulosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
