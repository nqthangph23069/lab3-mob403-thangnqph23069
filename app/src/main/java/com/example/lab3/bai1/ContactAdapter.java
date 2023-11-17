package com.example.lab3.bai1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab3.R;
import com.example.lab3.bai1.Contact;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    Context context;
    ArrayList<Contact> list;

    public ContactAdapter(Context context, ArrayList<Contact> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public static class ViewHolder {
        TextView tvName, tvEmail, tvMobile;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item, null);
            viewHolder.tvName = view.findViewById(R.id.tvName);
            viewHolder.tvEmail = view.findViewById(R.id.tvEmail);
            viewHolder.tvMobile = view.findViewById(R.id.tvSdt);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Contact contact = list.get(i);
        viewHolder.tvName.setText(contact.getName());
        viewHolder.tvEmail.setText(contact.getEmail());
        viewHolder.tvMobile.setText(contact.getMobile());
        return view;
    }
}
