package org.techtown.drawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static org.techtown.drawer.Challenge_my_fragment.MyPREFERENCES;

public class Challenge_my_dialog extends androidx.fragment.app.DialogFragment {

    //variable
    private List<Button> days = new ArrayList<>();
    private Button btn;
    private int dayNumber = 0;
    private final int GET_GALLERY_IMAGE = 200;
    String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
    private SharedPreferences sharedPreferences;
    TextView tvTitle;
    Button exitBtn;
    Button retryBtn;
    TextView tvSuccess;

    public Challenge_my_dialog() {
    }

    public static Challenge_my_dialog newInstance(String param1, String param2) {
        Challenge_my_dialog fragment = new Challenge_my_dialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences(MyPREFERENCES, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_my_dialog, container, false);
        setButtons(view);

        //다시 도전하기 보여주기
        dayNumber = sharedPreferences.getInt("day", 0);
        if(dayNumber == 7){
            Toast.makeText(getContext().getApplicationContext(), "7일동안 성공했어요! 대단해요👏", Toast.LENGTH_LONG);
            retryBtn.setVisibility(View.VISIBLE);
            retryBtn.setEnabled(true);
            retryBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("day");
                    editor.remove("date");
                    editor.commit();
                    Toast.makeText(getActivity().getApplicationContext(), "다시 도전하는 당신! 😘😘", Toast.LENGTH_SHORT).show();
                }
            });
            tvSuccess.setVisibility(View.VISIBLE);
        }

        //완료된 날짜 토스팅
        for(int i = 0; i < dayNumber; i++){
            days.get(i).setText("완료");
            days.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity().getApplicationContext(), "완료된 날짜입니다.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        //하루에 한번만 가능하게 -> 실행되는지는 아직 모름
        String date = sharedPreferences.getString("date", null);
        if(date == null || !date.equals(this.today)){
            btn = days.get(dayNumber);
            dayClick();
        }

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.detach(Challenge_my_dialog.this).attach(Challenge_my_dialog.this);
                ft.commit();
            }
        });

        return view;
    }

    public void setButtons(View view){
        tvTitle = view.findViewById(R.id.dialogTitle);
        tvTitle.setText(getArguments().getString("title"));
        days.add((Button) view.findViewById(R.id.challenge_first));
        days.add((Button) view.findViewById(R.id.challenge_second));
        days.add((Button) view.findViewById(R.id.challenge_third));
        days.add((Button) view.findViewById(R.id.challenge_fourth));
        days.add((Button) view.findViewById(R.id.challenge_fifth));
        days.add((Button) view.findViewById(R.id.challenge_sixth));
        days.add((Button) view.findViewById(R.id.challenge_last));
        exitBtn = (Button) view.findViewById(R.id.btn_exit);
        retryBtn = (Button) view.findViewById(R.id.btn_retry);
        tvSuccess = view.findViewById(R.id.tvSuccess);
    }

    public void dayClick(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
            btn.setEnabled(false);
            btn.setText("완료");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("day", ++dayNumber);
            String setDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
            editor.putString("date", setDate);
            Log.v("dialog", setDate);
            editor.apply();
            Toast.makeText(getActivity().getApplicationContext(), "갤러리 선택 완료", Toast.LENGTH_SHORT).show();
        }
    }
}