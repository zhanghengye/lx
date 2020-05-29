package com.example.zhangzhanghengye.intr.bean.fragment;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangzhanghengye.lx.R;

public class NewsContentFragment extends Fragment {
    private View view;
    private TextView newsTitle;
    private TextView newsContent;
    private LinearLayout linlayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.news_content_frag, container, false);
        newsTitle = view.findViewById(R.id.news_title);
        newsContent = view.findViewById(R.id.news_content);
        linlayout = view.findViewById(R.id.visibility_layout);
        return view;
    }

    public void refresh(String title, String content) {
        linlayout.setVisibility(View.VISIBLE);
        newsTitle.setText(title);
        newsContent.setText(content);
    }

}
