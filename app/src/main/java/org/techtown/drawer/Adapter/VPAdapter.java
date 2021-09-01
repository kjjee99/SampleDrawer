package org.techtown.drawer.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.techtown.drawer.Challenge_all_fragment;
import org.techtown.drawer.Challenge_my_fragment;

import java.util.ArrayList;

public class VPAdapter extends FragmentPagerAdapter {
    public ArrayList<Fragment> items = new ArrayList<>();
    public VPAdapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Challenge_all_fragment challengeAll = new Challenge_all_fragment();
                return challengeAll;
            case 1:
                Challenge_my_fragment challengeMy = new Challenge_my_fragment();
                return challengeMy;
            default:
                return null;
        }
    }

    public void addItem(Fragment item) {items.add(item);}

    @Override
    public int getCount() {
        return items.size();
    }

    public CharSequence getPageTitle(int position){
        if(position == 0)
            return "진행중인 챌린지";
        else
            return "나의 챌린지";
    }
}
