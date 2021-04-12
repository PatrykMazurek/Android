package com.masiad.test_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends BaseAdapter {

    private Context context;
    private List<Country> countries;

    public CountryAdapter(Context context, List<Country> country){
        this.context = context;
        this.countries = country;
    }


    @Override
    public int getCount() {
        return this.countries.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Country country = this.countries.get(position);

        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }

        ImageView countryFlag = (ImageView)convertView.findViewById(R.id.country_flag);
        countryFlag.setImageResource(country.flag);

        TextView countryName = (TextView)convertView.findViewById(R.id.country_name);
        countryName.setText(country.name);

        TextView countryArea = (TextView)convertView.findViewById(R.id.country_area);
        countryArea.setText(String.valueOf(country.area));

        return convertView;
    }
}
