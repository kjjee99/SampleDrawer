package org.techtown.drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.techtown.drawer.Adapter.VPAdapter;

public class Challenge_fragment extends Fragment {
    private static final String TAG = "Challenge_fragment";

    //variable
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private VPAdapter adapter;
    Fragment challenge_all = new Challenge_all_fragment();
    Fragment challenge_my = new Challenge_my_fragment();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater. inflate(R.layout.challenge_fragment, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.pager);
        adapter = new VPAdapter(getActivity().getSupportFragmentManager());

        adapter.AddFragement(new Challenge_all_fragment(), "진행중인 챌린지");
        adapter.AddFragement(new Challenge_my_fragment(), "나의 챌린지");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}