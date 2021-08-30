package org.techtown.drawer;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.techtown.drawer.backend.CampaignData;

import java.util.ArrayList;

public class Campaign_activity extends AppCompatActivity {
    private CampaignData item;
    TextView tvTitle;
    TextView tvContent;

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

    }
}
