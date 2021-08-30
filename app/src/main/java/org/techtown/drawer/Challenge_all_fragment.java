package org.techtown.drawer;

import android.content.Context;
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

public class Challenge_all_fragment extends Fragment {
    //variable
    private ArrayList<ChallengeItem> challengeItems = new ArrayList<>();
    private ListView listView;
    private static ChallengeAdapter challengeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_all_fragment,container,false);
        listView = (ListView) view.findViewById(R.id.challenge_all_list);
        ChallengeAdapter ca= new ChallengeAdapter(getContext(), challengeItems);

        //add Items
        challengeItems.add(ChallengeItem.builder().title("plastic").content("reduce the plastics.").build());

        challengeAdapter = new ChallengeAdapter(getContext(), challengeItems);
        listView.setAdapter(ca);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("title", challengeItems.get(position).getTitle());
                bundle.putString("content", challengeItems.get(position).getContent());
                Challenge_dialog dialog = new Challenge_dialog();
                dialog.setArguments(bundle);
                dialog.show(getActivity().getSupportFragmentManager(), "tag");
            }
        });
        return view;
    }

    class ChallengeAdapter extends ArrayAdapter{
        private List list;
        private Context context;

        public ChallengeAdapter(Context context, ArrayList list){
            super(context, 0, list);
            this.context = context;
            this.list = list;
        }

        class ViewHolder{
            public TextView tvTitle;
            public TextView tvContent;
        }

        public int getCount(){ return challengeItems.size(); }

        @Override
        public Object getItem(int position) { return challengeItems.get(position); }

        public View getView(int position, View convertView, ViewGroup parent){
            final ViewHolder viewHolder;
            if(convertView == null){
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                convertView = layoutInflater.inflate(R.layout.challenge_all_item, parent, false);
            }
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.challenge_all_title);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.challenge_all_content);

            final ChallengeItem citem = (ChallengeItem) list.get(position);
            viewHolder.tvTitle.setText(citem.getTitle());
            viewHolder.tvContent.setText(citem.getContent());
            viewHolder.tvTitle.setTag(citem.getTitle());

            return convertView;
        }
    }
}
