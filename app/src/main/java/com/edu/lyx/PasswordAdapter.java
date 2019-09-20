package com.edu.lyx;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PasswordAdapter extends BaseAdapter {
    private Context context;
    private List<Password> datas;

    public PasswordAdapter(Context context, List<Password> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Password password = datas.get(i);
        View v;
        ViewHolder viewHolder;
        if (view == null) {
            v = LayoutInflater.from(context).inflate(R.layout.password_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.title = v.findViewById(R.id.title);
            viewHolder.password = v.findViewById(R.id.password);
            v.setTag(viewHolder);
        } else {
            v = view;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.title.setText(password.getTitle());
        viewHolder.password.setText(password.getPassword());
        return v;
    }

    class ViewHolder{
        TextView title;
        TextView password;
    }
}
