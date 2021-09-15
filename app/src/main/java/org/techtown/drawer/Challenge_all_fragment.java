package org.techtown.drawer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.drawer.Adapter.ChallengeAdapter;
import org.techtown.drawer.Adapter.ChallengeItemClickListener;
import org.techtown.drawer.VO.ChallengeData;

import java.util.ArrayList;

public class Challenge_all_fragment extends Fragment {
    private ArrayList<ChallengeData> items = new ArrayList<>();

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("all","create");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.challenge_all_fragment, container, false);
        Log.v("all","createView");
        RecyclerView recyclerView = v.findViewById(R.id.recyclerview_main_list);
        ChallengeAdapter vpAdapter = new ChallengeAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(vpAdapter);


        vpAdapter.setOnItemClickListener(new ChallengeItemClickListener() {
            @Override
            public void onItemClick(ChallengeAdapter.ViewHolderPage holder, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("title", items.get(position).getTitle());
                bundle.putString("content", items.get(position).getContent());
                bundle.putInt("image", items.get(position).getResId());
                Challenge_all_dialog dialoging = new Challenge_all_dialog();
                dialoging.setArguments(bundle);
                dialoging.show(getActivity().getSupportFragmentManager(), "dialog_all");
            }
        });

        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.v("all","resume");
        //itemList
        items.add(new ChallengeData("텀블러 사용 챌린지", "텀블러 사용하고 플라스틱 컵 사용 줄이자!", R.drawable.tumbler));
        items.add(new ChallengeData("자전거 이용 챌린지", "가까운 거리는 자전거로 이동하고 운동도 하고!", R.drawable.bicycle));
        items.add(new ChallengeData("식물 키우기 챌린지", "반려식물 키우며 \n 이산화탄소 줄이고 \n 내 방 인테리어도 화사하게!", R.drawable.planting));
        items.add(new ChallengeData("채식 챌린지", "채식 도전하고 온실가스 줄이자!", R.drawable.vegetable));
        items.add(new ChallengeData("용기내 챌린지", "용기내서 다회용 용기에 음식 포장하자!", R.drawable.lunch_box));
    }
}