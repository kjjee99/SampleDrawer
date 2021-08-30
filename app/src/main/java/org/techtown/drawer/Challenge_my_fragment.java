package org.techtown.drawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.techtown.drawer.backend.ChallengeItem;

import java.util.ArrayList;
import java.util.List;

public class Challenge_my_fragment extends Fragment {

    //variable
    private String title;
    private String content;
    private ArrayList<ChallengeItem> items = new ArrayList<>();
    private ListView myListView;
    private static ChallengeAdapter myChallengeAdapter;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.challenge_my_fragment,container,false);
        myListView = (ListView) view.findViewById(R.id.Challenge_my_list);
        ChallengeAdapter myCA = new ChallengeAdapter(getContext(), items);
        SharedPreferences prefs = getActivity().getSharedPreferences(MyPREFERENCES, 0);
        if(prefs.contains("title") != false){
            title = prefs.getString("title", null);
            content = prefs.getString("content", null);
//            Log.v("my", prefs.getString("title", null));
//            Log.v("my", prefs.getAll().toString());
            items.add(new ChallengeItem(title, content));
            myChallengeAdapter = new ChallengeAdapter(getContext(), items);
        }
        myListView.setAdapter(myCA);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               popupChallenging(position);
            }
        });
        return view;
    }
    class ChallengeAdapter extends ArrayAdapter {
        private List list;
        private Context context;

        public ChallengeAdapter(Context context, ArrayList list){
            super(context,0,list);
            this.context = context;
            this.list = list;
        }

        class ViewHolder{
            public TextView tv_title;
            public TextView tv_content;
        }

        public int getCount(){
            return items.size();
        }

        public Object getItem(int position){
            return items.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            final ViewHolder myviewHolder;
            if(convertView == null){
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                convertView = layoutInflater.inflate(R.layout.challenge_my_item, parent, false);
            }
            myviewHolder = new ChallengeAdapter.ViewHolder();
            myviewHolder.tv_title = (TextView) convertView.findViewById(R.id.challenge_my_title);
            myviewHolder.tv_content = (TextView) convertView.findViewById(R.id.challenge_my_content);

            final ChallengeItem myItem = (ChallengeItem) list.get(position);
            myviewHolder.tv_title.setText(myItem.getTitle());
            myviewHolder.tv_content.setText(myItem.getContent());
            myviewHolder.tv_title.setTag(myItem.getTitle());

            return convertView;
        }
    }
}
