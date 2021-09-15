package org.techtown.drawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Challenge_all_dialog extends androidx.fragment.app.DialogFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //variable
    SharedPreferences sharedPreferences;
    public static final String MyPreferences = "MyPrefs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_all_dialog,container);
        TextView tvExplain = view.findViewById(R.id.joinchallengeContent);
        ImageView image = view.findViewById(R.id.challengeImage);
        image.setImageResource(getArguments().getInt("image"));
        tvExplain.setText(getArguments().getString("content"));
        Button joinChallengeBtn = (Button) view.findViewById(R.id.btn_joinChallenge);
        joinChallengeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                Challenge_my_fragment challengeMy = new Challenge_my_fragment();
//                challengeMy.newInstance(getArguments().getString("title"), getArguments().getString("content"));
                Intent intent = new Intent(getContext(), MainActivity.class);
                //휴대폰 내부 저장
                sharedPreferences = getActivity().getSharedPreferences(MyPreferences, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(!sharedPreferences.getString("title", null).equals(getArguments().getString("title"))){
                    editor.remove("day");
                    editor.remove("date");
                    editor.commit();
                }

                editor.putString("title", getArguments().getString("title"));
                editor.putString("content", getArguments().getString("content"));
                editor.putInt("image", getArguments().getInt("image"));
                editor.apply();
                //updayNumber display
                startActivity(intent);
                Toast toasting = Toast.makeText(getActivity().getApplicationContext(), "나의 챌린지에 추가 성공!", Toast.LENGTH_SHORT);
                toasting.show();
                dismiss();
            }
        });
        return view;
    }
}