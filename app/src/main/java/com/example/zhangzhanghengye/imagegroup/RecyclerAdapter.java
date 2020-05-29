package com.example.zhangzhanghengye.imagegroup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;


import com.example.zhangzhanghengye.lx.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHoder> {

    private Context mContext;
    private List<Bean> mList;
    public OnCliked onCliked;

    public void setOnCliked(OnCliked onCliked) {
        this.onCliked = onCliked;
    }

    public RecyclerAdapter(Context mContext, List<Bean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.recylayout, parent, false);
        MyViewHoder myViewHoder = new MyViewHoder(inflate);
        return myViewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHoder holder, final int position) {
            if(mList.size()==0){
                holder.radioButton.setVisibility(View.GONE);
            }else{
                Bitmap bitmap = BitmapFactory.decodeFile(mList.get(position).getPath());
                holder.imageView.setImageBitmap(bitmap);
            }
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.radioButton.isChecked()){
                        //
                        holder.radioButton.setChecked(false);
                        mList.get(position).setA(false);
                    }else{
                        //
                        holder.radioButton.setChecked(true);
                        mList.get(position).setA(true);
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return mList.size()==0?1:mList.size();
    }
    public class MyViewHoder extends RecyclerView.ViewHolder{
        ImageView imageView;
        CheckBox radioButton;
        public MyViewHoder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.recy_img_id);
            radioButton=itemView.findViewById(R.id.rb);
        }
    }
    public interface OnCliked{
        void onClid(int po,CheckBox radioButton);
    }
}
