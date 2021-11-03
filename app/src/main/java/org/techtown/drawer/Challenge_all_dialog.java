package org.techtown.drawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class Challenge_all_dialog extends androidx.fragment.app.DialogFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //variable
    SharedPreferences prefs, sharedPreferences;
    public static final String MyPreferences = "MyPrefs";
    Set<String> titles = new ArraySet<>();
    Set<String> contents = new ArraySet<>();
    Set<String> images = new ArraySet<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_all_dialog,container);
        //initialize
        TextView tvExplain = view.findViewById(R.id.joinchallengeContent);
        ImageView image = view.findViewById(R.id.challengeImage);
        image.setImageResource(getArguments().getInt("image"));
        tvExplain.setText(getArguments().getString("content"));
        Button joinChallengeBtn = (Button) view.findViewById(R.id.btn_joinChallenge);
        //Click Event
        joinChallengeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getContext(), MainActivity.class);
                //휴대폰 내부 저장
                prefs = getActivity().getSharedPreferences(MyPreferences, 0);
                SharedPreferences.Editor editor = prefs.edit();
                //title 비교
                boolean result = compareTitle();
                //true일 때, title이 같지 않음
                if(result == true){
                    defineElements();
                    titles.add(getArguments().getString("title"));
                    contents.add(getArguments().getString("content"));
                    images.add(String.valueOf(getArguments().getInt("image")));
                    editor.putStringSet("title", titles);
                    editor.putStringSet("content", contents);
                    editor.putStringSet("image", images);
                    editor.apply();

                    startActivity(intent);
                    Toast toasting = Toast.makeText(getActivity().getApplicationContext(), "나의 챌린지에 추가 성공!", Toast.LENGTH_SHORT);
                    toasting.show();
                    dismiss();
                }
                //false일 때 title이 같음
                else{
                    Toast toasting = Toast.makeText(getActivity().getApplicationContext(), "이미 존재하는 챌린지입니다.", Toast.LENGTH_SHORT);
                    toasting.show();
                }

            }
        });
        return view;
    }

    //저장된 값 가져오기
    public void defineElements() {
        prefs = getActivity().getSharedPreferences(MyPreferences, 0);
        if (prefs.getStringSet("title", null) != null) {
            Log.v("all", prefs.getAll().toString());
            if (!prefs.getAll().isEmpty()) {
                titles.addAll(prefs.getStringSet("title", null));
                contents.addAll(prefs.getStringSet("content", null));
                images.addAll(prefs.getStringSet("image", null));
            }
        }
    }

    public boolean compareTitle(){
        prefs = getActivity().getSharedPreferences(MyPreferences, 0);
        if (prefs.getStringSet("title", null) != null) {
            Set<String> set = prefs.getStringSet("title", null);
            ArrayList<String> list = new ArrayList<>(set);
            for(String str : list){
                if(str.equals(getArguments().getString("title"))){ return false; }      //같은 title이 존재
            }
        }
        return true;
    }
}