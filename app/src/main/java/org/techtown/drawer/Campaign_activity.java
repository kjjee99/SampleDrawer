package org.techtown.drawer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;

import org.techtown.drawer.Adapter.Campaign_adapter;
import org.techtown.drawer.VO.CampaignData;

import java.util.HashMap;
import java.util.Map;

public class Campaign_activity extends AppCompatActivity {
    private CampaignData item;
    private TextView tvTitle;
    private TextView tvContent;
    private Button btnURL;
    private Button kakaoBtn;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_explain);

        Bundle bundle = getIntent().getExtras();
        item = (CampaignData) bundle.getSerializable("campaignItem");

        Campaign_adapter adapter = new Campaign_adapter();

        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        btnURL = findViewById(R.id.btnURL);
    }
    protected void onResume() {
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
        kakaoBtn = (Button) findViewById(R.id.kakaoBtn);
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

        KakaoLinkService.getInstance().sendCustom(this, templateID, templateArgs, new ResponseCallback<KakaoLinkResponse>() {
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
