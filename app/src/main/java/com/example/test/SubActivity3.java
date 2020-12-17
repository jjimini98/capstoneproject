package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SubActivity3 extends AppCompatActivity {

    // 드디어 투표를 하는 페이지입니다!!!

    private TextView tv_studentID;
    private TextView tv_token;
    RadioGroup rg;

    private Button btn_submit;
    private static final float FONT_SIZE = 15;

    //private Button btn_show;   //누르면 투표창이 보입니다
    // 여기서 체크된 후보자를 임의의 변수 'pick' 에 저장할 수 있어야합니다.
    //private String pick;

    // 그럼 투표까지 한다면 몇개의 변수가 생길까요?
    // 1. token     : str 변수에 저장되어있던 토큰을 저장. 현재 진행되고 있는 투표의 고유 토큰입니다
    // 2. studentID : str2 변수에 저장되어있던 학번을 저장. 현재 투표를 할 학생의 학번입니다.
    // 3. pick      : !!!!!학생이 투표한 후보자 이름이 저장되어 있는 변수입니다!!!!!

    // 이 세가지 변수를 이용해 dict2와 dict3을 업데이트 해줘야합니다.

    // Ex)
    //- dict2
    //   -  "302327vyztx"
    //           "20171478" : 0
    //           "20171489" : 0
    //           "20181466" : 0

    // dict2[token][studentID] == 1
    // 해당 학생이 투표를 완료했으므로 값을 1로 업데이트 해줍니다. 삭제해줘도 됩니다.

    // EX)
    // - dict3
    //       - "302327vyztx"
    //                - name
    //                       - "유지민" : 0
    //                       - "이지희" : 0
    //                       - "기권" : 0
    //                - pledge
    //                       - "유지민" : "피자사줄게요"
    //                       - "이지희" : "콜라사줄게요"

    // dict3[token][name][pick] 값을 +1해준 상태로 업데이트 해줍니다.
    // 여기까지 하면 투표가 완료됩니다.
    // 이건 next 버튼을 눌렀을 때 일어나야 하는 일입니다.

    // 확인용 임시 데이터입니다
    String[] names = new String[]{"유지민", "이지희", "기권"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);

        tv_studentID = findViewById(R.id.tv_studentID);
        tv_token = findViewById(R.id.tv_token);
        Intent intent = getIntent();  // 어디선가 날라오는 데이터값이 있으면 이쪽에서 그 값을 받겠다 = getIntent()
        String studentID = intent.getStringExtra("studentID"); // 이건 학번 저장해둔거
        String token = intent.getStringExtra("token"); // 이건 토큰 저장해둔거
        tv_studentID.setText(studentID);
        tv_token.setText(token);

        //-------------------------------------------------------------------------

        DisplayRadioButton();

        rg = (RadioGroup) findViewById(R.id.RadioGroup01);

        // submit 버튼 누르면 생기는 일
        // sub4로 이동하게 해야합니다.
        // 이동할 때 submit 버튼을 누르는데, 이때 후보자의 득표수가 +1 되야합니다
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rd = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                String pick = rd.getText().toString();
                //Toast.makeText(getApplicationContext(), str_Qtype+" 선택됨", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), SubActivity4.class);
                intent.putExtra("studentID", studentID);  // putExtra로 studentID(학번)데이터를 담는다
                intent.putExtra("token", token);    // token을 담는다
                intent.putExtra("pick", pick);      // 누굴 뽑았나요? == pick
                startActivity(intent);

            }
        });


    }

    public void DisplayRadioButton() {
        for(int i = 0; i < names.length; i++) {
            RadioGroup radiogroup = (RadioGroup)findViewById(R.id.RadioGroup01);
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i);
            rdbtn.setText(names[i]);
            rdbtn.setTextSize(FONT_SIZE);
            radiogroup.addView(rdbtn);
        }

    }

}







