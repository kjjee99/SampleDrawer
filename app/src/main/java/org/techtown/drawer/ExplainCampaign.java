package org.techtown.drawer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class ExplainCampaign extends Fragment {

    //variable
    private TextView tvTitle;
    private TextView tvContent;
    private Button kakaoBtn;
    SharedPreferences sharedPreferences;
    public static final String CampaignPref = "campaign";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explain_campaign_fragment, container, false);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvContent = view.findViewById(R.id.tvContent);
        sharedPreferences = getActivity().getSharedPreferences(CampaignPref, 0);
        Bundle bundle = getArguments();
        String title ="";
        if(bundle != null) {
            title = bundle.getString("title");
            tvTitle.setText(title);
        }
        if(sharedPreferences.contains(title) != false){
            tvContent.setText(sharedPreferences.getString(title, null));
        }

        return view;
    }
}