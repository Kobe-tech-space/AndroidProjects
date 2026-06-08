package com.example.course8.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.course8.R;
import com.example.course8.entity.Planet;
import com.example.course8.util.ToastUtil;

import java.util.List;

public class PlanetListWithButtonAdapter extends BaseAdapter {
    private Context mContext;
    private List<Planet> mPlaneList;
    public PlanetListWithButtonAdapter(Context mContext, List<Planet> mPlaneList) {
        this.mContext = mContext;
        this.mPlaneList = mPlaneList;
    }

    @Override
    public int getCount() {
        return mPlaneList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlaneList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_with_button, null);
            holder = new ViewHolder();
            holder.ll_item = convertView.findViewById(R.id.ll_item);
            holder.iv_icon = convertView.findViewById(R.id.iv_icon);
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_desc = convertView.findViewById(R.id.tv_desc);
            holder.btn_oper = convertView.findViewById(R.id.btn_oper);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Planet planet = mPlaneList.get(position);
        holder.ll_item.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        holder.iv_icon.setImageResource(planet.image);
        holder.tv_name.setText(planet.name);
        holder.tv_desc.setText(planet.desc);
        holder.btn_oper.setOnClickListener(v -> {
            ToastUtil.show(mContext, "按钮点击了" + planet.name);
        });

        return convertView;
    }

    public final class ViewHolder {
        public LinearLayout ll_item;
        public ImageView iv_icon;
        public TextView tv_name;
        public TextView tv_desc;
        public Button btn_oper;
    }
}
