package org.techtown.drawer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.drawer.Adapter.ChallengeAdapter;
import org.techtown.drawer.Adapter.ChallengeItemClickListener;
import org.techtown.drawer.VO.ChallengeItem;

import java.util.ArrayList;

public class Challenge_all_fragment extends Fragment {
    private ArrayList<ChallengeItem> items = new ArrayList<>();

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items.add(new ChallengeItem("텀블러 사용 챌린지", "텀블러 사용하고 플라스틱 컵 사용 줄이자!"));
        items.add(new ChallengeItem("자전거 이용 챌린지", "가까운 거리는 자전거로 이동하고 운동도 하고!"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.challenge_all_fragment, container, false);
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
                Challenge_all_dialog dialoging = new Challenge_all_dialog();
                dialoging.setArguments(bundle);
                dialoging.show(getActivity().getSupportFragmentManager(), "dialog_all");
            }
        });

        return v;
    }
}