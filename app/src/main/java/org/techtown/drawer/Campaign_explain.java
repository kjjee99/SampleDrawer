package org.techtown.drawer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;

import org.techtown.drawer.Adapter.Campaign_adapter;
import org.techtown.drawer.VO.CampaignData;

import java.util.HashMap;
import java.util.Map;

public class Campaign_explain extends Fragment {
    private CampaignData item;
    private TextView tvTitle;
    private TextView tvContent;
    private Button btnURL;
    private Button kakaoBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item= (CampaignData) getArguments().getSerializable("campaignItem");

        Campaign_adapter adapter = new Campaign_adapter();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.campaign_explain, container, false);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvContent = view.findViewById(R.id.tvContent);
        btnURL = view.findViewById(R.id.btnURL);
        kakaoBtn = (Button) view.findViewById(R.id.kakaoBtn);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText(item.getTitle());
        tvContent.setText(item.getExplain());
        btnURL.setText(item.getTitle()+" 사이트 방문");
        btnURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(item.getUrl()));
                startActivity(intent);
            }
        });
        kakaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share_kakao_template();
            }
        });
    }

    public void share_kakao_template(){
        String templateID = "60439";
        Map<String, String> templateArgs = new HashMap<>();
        templateArgs.put("title", item.getTitle());
        templateArgs.put("description", item.getContent());

        KakaoLinkService.getInstance().sendCustom(getContext(), templateID, templateArgs, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.e("EOTEST", errorResult.toString());
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {
                Log.v("kakao", result.getTemplateArgs().toString());
            }
        });
    }
}