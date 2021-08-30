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
import androidx.fragment.app.FragmentTransaction;

import org.techtown.drawer.backend.CampaignItem;

import java.util.ArrayList;
import java.util.List;

public class Campaign_fragment extends Fragment {

    //variable
    ArrayList<CampaignItem> items = new ArrayList<CampaignItem>();
    private ListView listView;
    private static CampaignAdapter campaignAdapter;
    private SharedPreferences sharedPreferences;
    public static final String CampaignPref = "campaign";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.campaign_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.campaign_list);
        CampaignAdapter campaignAdapter = new CampaignAdapter(getContext(), items);
        items.add(CampaignItem.builder().title("hello").content("future").build());
        //campaign 내용 저장
//        sharedPreferences = getActivity().getSharedPreferences(CampaignPref, 0);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("hello", "이 화면은 캠페인 화면입니다.");
//        editor.apply();
        items.add(CampaignItem.builder().title("music box").content("life is still going on").build());

        campaignAdapter = new CampaignAdapter(getContext(), items);
        listView.setAdapter(campaignAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ExplainCampaign explainCampaign = new ExplainCampaign();
                Bundle bundle = new Bundle();
                bundle.putString("title", items.get(position).getTitle());
                explainCampaign.setArguments(bundle);
                transaction.replace(R.id.container, explainCampaign);
                transaction.commitNow();
            }
        });
        return view;
    }

    class CampaignAdapter extends ArrayAdapter {
        private List list;
        private Context context;

        public CampaignAdapter(Context context, ArrayList list){
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
        @Override
        public Object getItem(int position){
            return items.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            final Campaign_fragment.CampaignAdapter.ViewHolder viewHolder;
            if(convertView == null){
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                convertView = layoutInflater.inflate(R.layout.campaign_item, parent, false);
            }
            viewHolder = new Campaign_fragment.CampaignAdapter.ViewHolder();
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.campaign_title);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.campaign_content);

            final CampaignItem campaignItem = (CampaignItem) list.get(position);
            viewHolder.tv_title.setText(campaignItem.getTitle());
            viewHolder.tv_content.setText(campaignItem.getContent());
            viewHolder.tv_title.setTag(campaignItem.getTitle());

            return convertView;
        }


    }
}
