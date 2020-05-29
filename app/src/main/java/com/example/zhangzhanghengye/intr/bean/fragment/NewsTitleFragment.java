package com.example.zhangzhanghengye.intr.bean.fragment;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangzhanghengye.intr.bean.News;
import com.example.zhangzhanghengye.intr.bean.activity.NewsCotentActivity;
import com.example.zhangzhanghengye.lx.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    private View inflate;
    private boolean isTwoPane;
    private RecyclerView re;
    ArrayList<News> news = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflate = inflater.inflate(R.layout.news_title_frag, container, false);
        re = inflate.findViewById(R.id.recycler);
        initNews();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        re.setLayoutManager(linearLayoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(news);
        re.setAdapter(newsAdapter);
        return inflate;
    }

    private void initNews() {
        for (int i = 0; i < 50; i++) {
            News news1 = new News();
            news1.setTitle("This is news Title"+i);
            news1.setContent(getRandowLengthContent("This is news Content"+i+"."));
            news.add(news1);
        }
    }

    private String getRandowLengthContent(String content) {

        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            stringBuffer.append(content);
        }
        return stringBuffer.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //当能找到平面适配的布局时
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane=true;
        }else{
            isTwoPane=false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHoder>{
        private List<News> mNewsList;
        public NewsAdapter(List<News> mNewsList) {
            this.mNewsList = mNewsList;
        }
        class ViewHoder extends RecyclerView.ViewHolder{
            TextView textView;
            public ViewHoder(View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.news_item);
            }
        }


        @NonNull
        @Override
        public NewsAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_title, parent, false);
            final ViewHoder viewHoder = new ViewHoder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    News news = mNewsList.get(viewHoder.getAdapterPosition());
                    if(isTwoPane){
                        NewsContentFragment fragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        fragment.refresh(news.getTitle(),news.getContent());
                    }else{
                        //单页模式
                        NewsCotentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                    }
                }
            });
            return viewHoder;
        }

        @Override
        public void onBindViewHolder(@NonNull NewsAdapter.ViewHoder holder, int position) {
            News news = mNewsList.get(position);
            holder.textView.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

}
