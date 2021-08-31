package org.techtown.drawer;

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

import org.techtown.drawer.backend.CampaignData;

import java.util.HashMap;
import java.util.Map;

public class Campaign_activity extends AppCompatActivity {
    private CampaignData item;
    private TextView tvTitle;
    private TextView tvContent;
    private Button kakaoBtn;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_explain);

        Bundle bundle = getIntent().getExtras();
        item = (CampaignData) bundle.getSerializable("campaignItem");

        Campaign_adapter adapter = new Campaign_adapter();

        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);

    }
    protected void onResume() {
        super.onResume();
        tvTitle.setText(item.getTitle());
        tvContent.setText(item.getContent());
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
        templateArgs.put("title", "바다거북이를 함께 살려요!");
        templateArgs.put("description", "text");
        templateArgs.put("url", "https://www.naver.com");
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
