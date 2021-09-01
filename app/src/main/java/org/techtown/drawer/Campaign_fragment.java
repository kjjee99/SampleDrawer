package org.techtown.drawer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.drawer.Adapter.CampaignItemClickListener;
import org.techtown.drawer.Adapter.Campaign_adapter;
import org.techtown.drawer.VO.CampaignData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Campaign_fragment extends Fragment {
    ArrayList<CampaignData> items = new ArrayList<CampaignData>();
    private RecyclerView recyclerView;
    private Campaign_adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.campaign_fragment,container,false);

        final Context context=rootView.getContext();
        recyclerView = rootView.findViewById(R.id.campaign_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Campaign_adapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CampaignItemClickListener() { //게시글 누르면
            public void onItemClick(Campaign_adapter.ViewHolder holder, View view, int position) {
                CampaignData item = adapter.getItem(position);

                Intent intent = new Intent(getContext(), Campaign_activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("campaignItem", item);
                intent.putExtras(bundle);//putExtras로 Bundle 데이터를 넘겨주고 여기에서getExtras로 데이터를 참조한다.
                startActivity(intent);
            }
        });
        getData();
        return rootView;
    }
    private void getData() {
        // 임의의 데이터
        List<String> listTitle = Arrays.asList(
                "Think Eat Save!",
                "Surfurs Againt Sewage (SAS)",
                "[참새클럽] 플라스틱방앗간"
        );
        List<Integer> listImage = Arrays.asList(
                R.drawable.ex1,
                R.drawable.ex2,
                R.drawable.ex3
        );
        List<String> listContent = Arrays.asList(
                "캠페인1의 내용입니다.",
                "캠페인2의 내용입니다.",
                "캠페인3의 내용입니다."
        );


        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            CampaignData data = new CampaignData();
            data.setTitle(listTitle.get(i));
            data.setResId(listImage.get(i));
            data.setContent(listContent.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}
