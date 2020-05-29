package com.example.zhangzhanghengye.lx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhangzhanghengye.lx.R;
import com.example.zhangzhanghengye.lx.bean.Bean;
import com.example.zhangzhanghengye.lx.utils.TimeUtils;

import java.util.List;

public class TwoFragmentRecyclerAdapter extends RecyclerView.Adapter<TwoFragmentRecyclerAdapter.MyViewHoder> {

    private Context mContext;
    private List<Bean.DataBean> beans;
    private MyOnCilk myOnCilk;
    public TwoFragmentRecyclerAdapter(Context mContext, List<Bean.DataBean> beans) {
        this.mContext = mContext;
        this.beans = beans;
    }

    public void setMyOnCilk(MyOnCilk myOnCilk) {
        this.myOnCilk = myOnCilk;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHoder(LayoutInflater.from(mContext).inflate(R.layout.two_recy_adapter, null));
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, final int position) {
        Bean.DataBean dataBean = beans.get(position);
        holder.userName.setText(dataBean.getUser_name());
        Glide.with(mContext).load(dataBean.getUser_header())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(holder.userIcon);
        if(dataBean.getUser_profession()!=null){
            if (TextUtils.isEmpty(dataBean.getUser_profession().toString())) {
                holder.userDq.setVisibility(View.GONE);
            } else {
                holder.userDq.setText(dataBean.getUser_profession().toString());
            }
        }
        List<String> img = dataBean.getImg();
        if (img.size() == 1) {
            Glide.with(mContext).load(img.get(0)).into(holder.img1);
            holder.img2.setVisibility(View.INVISIBLE);
            holder.img3.setVisibility(View.INVISIBLE);
            holder.jiu.setVisibility(View.INVISIBLE);
        } else if (img.size() == 2) {
            Glide.with(mContext).load(img.get(0)).into(holder.img1);
            Glide.with(mContext).load(img.get(1)).into(holder.img2);
            holder.img3.setVisibility(View.INVISIBLE);
            holder.jiu.setVisibility(View.INVISIBLE);
        } else if (img.size() >= 3) {
            Glide.with(mContext).load(img.get(0)).into(holder.img1);
            Glide.with(mContext).load(img.get(1)).into(holder.img2);
            Glide.with(mContext).load(img.get(2)).into(holder.img3);
            if (img.size() == 3) {
                holder.jiu.setVisibility(View.INVISIBLE);
            } else {
                holder.jiu.setText(img.size() + "张");
            }
        }
        if (TextUtils.isEmpty(dataBean.getContent())) {
            holder.pl.setText("暂无评论");
        } else {
            holder.pl.setText(dataBean.getContent());
        }
        Glide.with(mContext).load(dataBean.getGoods_album()).into(holder.spIcon);
        if (!TextUtils.isEmpty(dataBean.getGoods_name()))
            holder.spName.setText(dataBean.getGoods_name());
        List<String> order_goods_type = dataBean.getOrder_goods_type();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < order_goods_type.size(); i++) {
            if (i == order_goods_type.size() - 1) {
                stringBuffer.append(order_goods_type.get(i));
            } else {
                stringBuffer.append(order_goods_type.get(i) + "/");
            }
        }
        holder.spZl.setText(stringBuffer.toString());
        holder.plTime.setText(TimeUtils.timeStamp2Date(dataBean.getAdd_time(), null));
        holder.plStatistics.setText(dataBean.getComment_count() + "");
        holder.scStatistics.setText(dataBean.getZan_num() + "");
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOnCilk.onClik(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (beans == null) return 0;
        return beans.size() == 0 ? 0 : beans.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        //用户头像
        ImageView userIcon;
        //用户名字
        TextView userName;
        //用户地区
        TextView userDq;
        //用户评论图片1 2 3
        ImageView img1;
        ImageView img2;
        ImageView img3;
        //用户评论的图片大于3张
        TextView jiu;
        //用户评论信息
        TextView pl;
        //商品图片
        ImageView spIcon;
        //商品名称
        TextView spName;
        //商品重量
        TextView spZl;
        //是否好评
        TextView spPj;
        //评论时间
        TextView plTime;
        //对这个评论的评论数量
        TextView plStatistics;
        //对这个评论的收藏数量
        TextView scStatistics;
        LinearLayout linearLayout;
        public MyViewHoder(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.ll);
            userIcon = itemView.findViewById(R.id.icon);
            userName = itemView.findViewById(R.id.name);
            userDq = itemView.findViewById(R.id.dz);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            jiu = itemView.findViewById(R.id.duozhang);
            pl = itemView.findViewById(R.id.plMessage);
            spIcon = itemView.findViewById(R.id.spIcon);
            spName = itemView.findViewById(R.id.spName);
            spZl = itemView.findViewById(R.id.spzl);
            spPj = itemView.findViewById(R.id.spjf);
            plTime = itemView.findViewById(R.id.plTime);
            plStatistics = itemView.findViewById(R.id.plStatistics);
            scStatistics = itemView.findViewById(R.id.scStatistics);
        }
    }
    public interface MyOnCilk{
        void onClik(int i);
    }
}
