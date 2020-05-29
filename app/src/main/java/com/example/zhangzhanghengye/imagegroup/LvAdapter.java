package com.example.zhangzhanghengye.imagegroup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.zhangzhanghengye.lx.R;

import java.util.List;
public class LvAdapter extends BaseAdapter {

    private List<String> ls;
    private Context mContext;

    LvAdapter(List<String> ls, Context mContext) {
        this.ls = ls;
        this.mContext = mContext;
    }
    //dont want to work overtime
    @Override
    public int getCount() {
        return ls.size() == 0 ? 1 : ls.size();
    }

    @Override
    public Object getItem(int position) {
        return ls.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.img, parent, false);
        ImageView viewById = inflate.findViewById(R.id.image);
        if(ls.size() == 0 || ls.size()<position){
            viewById.setImageResource(R.drawable.shoucang);
        }else{
            Bitmap bitmap = BitmapFactory.decodeFile(ls.get(position));
            viewById.setImageBitmap(bitmap);
        }
        return inflate;
    }
}
