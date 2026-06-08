package com.example.exercise4_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class AvatarAdapter extends BaseAdapter {

    private Context context;
    private int[] imageIds;
    private LayoutInflater inflater;

    public AvatarAdapter(Context context, int[] imageIds) {
        this.context = context;
        this.imageIds = imageIds;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public Object getItem(int position) {
        return imageIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_avatar, parent, false);
            holder = new ViewHolder();
            holder.ivAvatar = convertView.findViewById(R.id.iv_avatar_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivAvatar.setImageResource(imageIds[position]);
        return convertView;
    }

    static class ViewHolder {
        ImageView ivAvatar;
    }
}