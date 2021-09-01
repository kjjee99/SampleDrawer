package org.techtown.drawer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.drawer.Adapter.ChallengeAdapter;
import org.techtown.drawer.Adapter.ChallengeItemClickListener;
import org.techtown.drawer.VO.ChallengeItem;

import java.util.ArrayList;

public class Challenge_my_fragment extends Fragment {

    //variable
    private String title;
    private String content;
    private ArrayList<ChallengeItem> items = new ArrayList<>();
    private ListView myListView;
    public static final String MyPREFERENCES = "MyPrefs";

//    public static ExplainCampaign newInstance(String param1, String param2) {
//        ExplainCampaign fragment = new ExplainCampaign();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items.add(new ChallengeItem("Fragment2", "222222"));
        SharedPreferences prefs = getActivity().getSharedPreferences(MyPREFERENCES, 0);
        if(prefs.contains("title") != false){
            title = prefs.getString("title", null);
            content = prefs.getString("content", null);
//            Log.v("my", prefs.getString("title", null));
//            Log.v("my", prefs.getAll().toString());
            items.add(new ChallengeItem(title, content));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.challenge_my_fragment,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_main_list);
        ChallengeAdapter adapter = new ChallengeAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ChallengeItemClickListener() {
            @Override
            public void onItemClick(ChallengeAdapter.ViewHolderPage holder, View view, int position) {
                Log.v("frag2", items.get(position).getContent());
                Toast.makeText(getContext(), items.get(position).getContent(), Toast.LENGTH_SHORT);

            }
        });

        return view;
    }
}