package org.techtown.drawer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Set;

public class Challenge_my_fragment extends Fragment {

    //variable
//    private String title;
//    private String content;
//    private int image;
    private Set<String> titles;
    private Set<String> contents;
    private Set<String> images;
    private ArrayList<ChallengeData> items = new ArrayList<>();
    private ListView myListView;
    public static final String MyPREFERENCES = "MyPrefs";


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
        if(!prefs.getAll().isEmpty()){
            titles = prefs.getStringSet("title", null);
            contents = prefs.getStringSet("content", null);
            images = prefs.getStringSet("image", null);
            Log.v("my", prefs.getAll().toString());
            if(titles != null){
                ArrayList<String> titleList = new ArrayList<>(titles);
                ArrayList<String> contentList = new ArrayList<>(contents);
                ArrayList<String> imageList = new ArrayList<>(images);
                for(int i = 0; i < titleList.size(); i++){
                    items.add(new ChallengeData(titleList.get(i), contentList.get(i), Integer.parseInt(imageList.get(i))));
                }
            }
        }

        adapter.setOnItemClickListener(new ChallengeItemClickListener() {
            @Override
            public void onItemClick(ChallengeAdapter.ViewHolderPage holder, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("title", items.get(position).getTitle());
                Challenge_my_dialog dialog = new Challenge_my_dialog();
                dialog.setArguments(bundle);
                dialog.show(getActivity().getSupportFragmentManager(), "dialog_my");
            }
        });

        return view;
    }
}