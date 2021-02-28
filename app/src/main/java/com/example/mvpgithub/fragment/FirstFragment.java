package com.example.mvpgithub.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvpgithub.R;
import com.example.mvpgithub.adapter.Find_tab_Adapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment {
    private TabLayout tab_FirstFragment;                            //定义TabLayout
    private ViewPager vp_FirstFragment;                             //定义viewPager
    private FragmentPagerAdapter fAdapter;                               //定义adapter
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    private First_1 school1;
    private First_2 school2;
    private First_3 school3;
    private First_4 school4;
    private First_5 school5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        initControls(view);
        return view;
    }

    /**
     * 初始化各控件
     *
     * @param view
     */
    private void initControls(View view) {

        tab_FirstFragment = (TabLayout) view.findViewById(R.id.tab_FirstFragment);
        vp_FirstFragment = (ViewPager) view.findViewById(R.id.vp_FirstFragment);

        //初始化各fragment
        school1 = new First_1();
        school2 = new First_2();
        school3 = new First_3();
        school4 = new First_4();
        school5=new First_5();

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(school1);
        list_fragment.add(school2);
        list_fragment.add(school3);
        list_fragment.add(school4);
        list_fragment.add (school5);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("首页");
        list_title.add("待续");
        list_title.add("待续");
        list_title.add("待续");
        list_title.add("待续");
        //设置TabLayout的模式
        tab_FirstFragment.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tab_FirstFragment.addTab(tab_FirstFragment.newTab().setText(list_title.get(0)));
        tab_FirstFragment.addTab(tab_FirstFragment.newTab().setText(list_title.get(1)));
        tab_FirstFragment.addTab(tab_FirstFragment.newTab().setText(list_title.get(2)));
        tab_FirstFragment.addTab(tab_FirstFragment.newTab().setText(list_title.get(3)));
        tab_FirstFragment.addTab(tab_FirstFragment.newTab().setText(list_title.get(4)));

        fAdapter = new Find_tab_Adapter(getActivity().getSupportFragmentManager(), list_fragment, list_title);

        //viewpager加载adapter
        vp_FirstFragment.setAdapter(fAdapter);

        tab_FirstFragment.setupWithViewPager(vp_FirstFragment);

    }
}