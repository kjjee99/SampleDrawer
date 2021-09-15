package org.techtown.drawer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.drawer.Adapter.ChallengeAdapter;
import org.techtown.drawer.Adapter.ChallengeItemClickListener;
import org.techtown.drawer.VO.ChallengeData;

import java.util.ArrayList;

public class Challenge_my_fragment extends Fragment {

    //variable
    private String title;
    private String content;
    private int image;
    private ArrayList<ChallengeData> items = new ArrayList<>();
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

        //itemList
        SharedPreferences prefs = getActivity().getSharedPreferences(MyPREFERENCES, 0);
        if(prefs.contains("title") != false){
            title = prefs.getString("title", null);
            content = prefs.getString("content", null);
            image = prefs.getInt("image", 0);
            items.add(new ChallengeData(title, content, image));
        }


        adapter.setOnItemClickListener(new ChallengeItemClickListener() {
            @Override
            public void onItemClick(ChallengeAdapter.ViewHolderPage holder, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("title", items.get(position).getTitle());
                bundle.putString("content", items.get(position).getContent());
                Challenge_my_dialog dialog = new Challenge_my_dialog();
                dialog.setArguments(bundle);
                dialog.show(getActivity().getSupportFragmentManager(), "dialog_my");
            }
        });

        return view;
    }
}